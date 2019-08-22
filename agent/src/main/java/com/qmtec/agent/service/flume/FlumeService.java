package com.qmtec.agent.service.flume;

import com.alibaba.fastjson.JSON;
import com.qmtec.agent.comm.Constant;
import com.qmtec.agent.entity.FlumeConfig;
import com.qmtec.agent.manager.FlumeConfigManage;
import com.qmtec.agent.utils.ScriptCommandsUtil;
import com.qmtec.common.exception.CustomException;
import com.qmtec.common.util.ExecuteCmdUtil;
import com.qmtec.common.util.NetworkUtil;
import com.qmtec.common.util.RWFileUtil;
import com.qmtec.common.web.HttpCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class FlumeService {

    @Autowired
    private Constant constant;

    @Autowired
    private FlumeConfigManage flumeConfigManage;

    @Value("${flume.portBegin}")
    public int portBegin; //端口的开始(包括)

    @Value("${flume.portEnd}")
    public int portEnd; //端口的结束(不包括)

    @Value("${port.acquisition.times}")
    public int port_acquisition_times;//flume监控端口获取次数

    /**
     * 启动、重启公共部分
     */
    private Boolean startComm(Map<String, Object> flumConfig, Long contextId) throws Exception {
        Boolean flag = false;

        String flumeHome = (String) flumConfig.get("flumeHome");
        String profileName = (String) flumConfig.get("profileName");
        String agentName = (String) flumConfig.get("agentName");
        String fileConfigContent = (String) flumConfig.get("fileConfigContent");
        String serverIp = (String) flumConfig.get("serverIp");

        //创建日志目录、配置文件
        this.createFlumeConfigFile(flumeHome, fileConfigContent, contextId, profileName);

        //获取端口
        Integer monitorport = this.createPort(serverIp, port_acquisition_times, contextId);

        //创建并执行脚本
        this.createAndExecuteScripts(contextId, flumeHome, monitorport, profileName, agentName);

        String cmd = "ps -ef |grep " + monitorport.toString() + "|grep " + agentName + "|grep -v grep|cut -c 9-15";
        String procId = ExecuteCmdUtil.getProccessId(new String[]{"/bin/bash", "-c", cmd});  //获取进程id
        log.debug("contextId :" + contextId + "==》获取进程pid: " + procId);

        if (StringUtils.hasLength(procId)) {

            if (flumeConfigManage.updateFlumeConfigByProcesspidAndMonitorportAndRunstateAndContextId(
                    contextId, monitorport, FlumeConfig.Runstate.RUNING.getValue(),
                    Integer.valueOf(procId), new Date())) {
                flag = true;
                log.debug("contextId :" + contextId + "==》启动成功");
            } else {
                log.debug("contextId :" + contextId + "==》已获取进程，更新状态失败");
                throw new CustomException("已获取进程，更新状态失败");
            }

        } else {
            //未获取到进程pid ,修改为"启动异常"。以便重启
            flumeConfigManage.updateFlumeConfigByContextIdAndRunstate(contextId, FlumeConfig.Runstate.EXCEPTION.getValue());
            log.debug("contextId :" + contextId + "==》更改运行状态为：“启动异常”");
        }
        return flag;
    }

    /**
     * 生成配置文件，并启动flume
     *
     * @param initMap 系统初始化值
     * @return
     */
    public Boolean start(Long contextId, Map<String, String> initMap) {
        Boolean flag = false;

        if (null != contextId) {
            if (flumeConfigManage.receiveCheckFlumeConfigByContextId(contextId, FlumeConfig.Runstate.PUSHING.getValue())) {
                try {

                    //更改运行状态为：“已接收请求”
                    flumeConfigManage.updateFlumeConfigByContextIdAndRunstate(contextId, FlumeConfig.Runstate.PUSHED.getValue());
                    log.debug("contextId :" + contextId + "==》更改运行状态为：“已接收请求”");

                    Map<String, Object> flumConfig = flumeConfigManage.selectOneFlumeConfigByContextId(contextId);
                    if (flumConfig.size() > 0) {

                        //更改运行状态为：“正在启动”
                        flumeConfigManage.updateFlumeConfigByContextIdAndRunstate(contextId, FlumeConfig.Runstate.STARTING.getValue());
                        log.debug("contextId :" + contextId + "==》更改运行状态为：“正在启动”");

                        flag = startComm(flumConfig, contextId);

                    } else {
                        throw new CustomException("contextId :" + contextId + "数据不存在");
                    }

                } catch (Exception e) {
                    //更改运行状态为：“启动失败”
                    flumeConfigManage.updateFlumeConfigByContextIdAndRunstate(contextId, FlumeConfig.Runstate.FAIL.getValue());
                    log.debug("contextId :" + contextId + "==》更改运行状态为：“启动失败”,异常信息 ：" + e.getMessage());
                    e.printStackTrace();
                    throw new CustomException("启动失败,异常信息：" + e.getMessage(), HttpCode.CODE_500);
                }
            } else {
                throw new CustomException("contextId :" + contextId + "==> 状态不合符，已拒绝", HttpCode.CODE_400);
            }
        } else {
            throw new CustomException("参数异常", HttpCode.CODE_400);
        }
        return flag;
    }


    /**
     * 重启flume
     *
     * @param initMap 系统初始化值
     * @return
     */
    public Boolean restart(Long contextId, Map<String, String> initMap) {
        Boolean flag = false;

        if (null != contextId) {
            if (flumeConfigManage.receiveCheckFlumeConfigByContextId(contextId, FlumeConfig.Runstate.RESTART.getValue())) {
                try {
                    //更改运行状态为：“已接收请求”
                    flumeConfigManage.updateFlumeConfigByContextIdAndRunstate(contextId, FlumeConfig.Runstate.PUSHED.getValue());
                    log.debug("contextId :" + contextId + "==》更改运行状态为：“已接收请求”");

                    Map<String, Object> flumConfig = flumeConfigManage.selectOneFlumeConfigByContextId(contextId);
                    if (flumConfig.size() > 0) {

                        String serverIp = (String) flumConfig.get("serverIp");
                        Integer processPid = (Integer) flumConfig.get("processPid");

                        //更改运行状态为：“正在重启”
                        flumeConfigManage.updateFlumeConfigByContextIdAndRunstate(contextId, FlumeConfig.Runstate.RESTARTING.getValue());
                        log.debug("contextId :" + contextId + "==》更改运行状态为：“正在重启”");

                        if (processPid != null) {
                            ExecuteCmdUtil.execCmd("kill -9 " + processPid);
                            log.debug("serverIp :" + serverIp + "==》杀死进程Pid ：" + processPid);
                        }

                        flag = startComm(flumConfig, contextId);

                    } else {
                        throw new CustomException("contextId :" + contextId + "数据不存在");
                    }

                } catch (Exception e) {
                    //更改运行状态为：“启动失败”
                    flumeConfigManage.updateFlumeConfigByContextIdAndRunstate(contextId, FlumeConfig.Runstate.FAIL.getValue());
                    log.debug("contextId :" + contextId + "==》更改运行状态为：“启动失败”,异常信息 ：" + e.getMessage());
                    e.printStackTrace();
                    throw new CustomException("启动失败,异常信息：" + e.getMessage(), HttpCode.CODE_500);
                }
            } else {
                throw new CustomException("contextId :" + contextId + "==> 状态不合符，已拒绝");
            }
        } else {
            throw new CustomException("参数异常", HttpCode.CODE_400);
        }
        return flag;
    }


    /**
     * 创建日志目录、配置文件
     *
     * @param flumeHome
     * @param fileConfigContent
     * @param contextId
     * @param profileName
     * @throws Exception
     */
    private void createFlumeConfigFile(String flumeHome, String fileConfigContent,
                                       Long contextId, String profileName) throws Exception {
        RWFileUtil.createDir(flumeHome + "/logs");    //创建日志目录
        //生成flume的启动配置文件
        RWFileUtil.writeFile(fileConfigContent, flumeHome + "/conf/" + profileName + ".properties");
        //更改运行状态为：“创建配置文件”
        flumeConfigManage.updateFlumeConfigByContextIdAndRunstate(contextId, FlumeConfig.Runstate.CREATEPROFILE.getValue());
        log.debug("contextId :" + contextId + "==》更改运行状态为：“创建配置文件”");
    }

    /**
     * 根据serverIp创建flume可用端口
     *
     * @param serverIp
     * @param repeatNum
     * @return
     */
    private Integer createPort(String serverIp, int repeatNum, Long contextId) {
        Integer portBegin = this.portBegin;
        Integer portEnd = this.portEnd;

        Integer flumePort = Integer.valueOf(NetworkUtil.createPort(portBegin, portEnd));

        Boolean flag = flumeConfigManage.verifyMonitorPortIsAvailableByServerIp(serverIp, flumePort);
        while (!flag) {
            flumePort = Integer.valueOf(NetworkUtil.createPort(portBegin, portEnd));
            flag = flumeConfigManage.verifyMonitorPortIsAvailableByServerIp(serverIp, flumePort);

            repeatNum--;
            if (repeatNum == 0) {
                throw new CustomException("ServerIp:" + serverIp + "的服务器多次未获取到可用端口");
            }
        }
        log.debug("contextId :" + contextId + "==》获取监控端口port：" + flumePort);
        return flumePort;
    }

    /**
     * 创建并执行脚本
     *
     * @param contextId
     * @param flumeHome
     * @param monitorport
     * @param profileName
     * @param agentName
     * @throws Exception
     */
    private void createAndExecuteScripts(Long contextId, String flumeHome, Integer monitorport,
                                         String profileName, String agentName) throws Exception {

        String scriptFileName = null;
        if (profileName.lastIndexOf(".") != -1) {
            scriptFileName = "/" + profileName.substring(0, profileName.lastIndexOf(".")) + ".sh";
        } else {
            scriptFileName = "/" + profileName + ".sh";
        }

        RWFileUtil.writeFile(
                ScriptCommandsUtil.createFlumeStartCmd(flumeHome, profileName,
                        String.valueOf(monitorport), agentName),
                flumeHome + scriptFileName); //生成启动flume的命令

        //更改运行状态为：“创建脚本”
        flumeConfigManage.updateFlumeConfigByContextIdAndRunstate(contextId, FlumeConfig.Runstate.CREATESCRIPTS.getValue());
        log.debug("contextId :" + contextId + "==》更改运行状态为：“创建脚本”");

        ExecuteCmdUtil.execCmd("chmod 775 " + flumeHome + scriptFileName);   //更改权限
        ExecuteCmdUtil.execCmd("/bin/sh " + flumeHome + scriptFileName);   //启动flume
        //更改运行状态为：“执行脚本”
        flumeConfigManage.updateFlumeConfigByContextIdAndRunstate(contextId, FlumeConfig.Runstate.EXECSCRIPTS.getValue());
        log.debug("contextId :" + contextId + "==》更改运行状态为：“执行脚本”");
    }

    /**
     * 停止flume
     *
     * @param initMap 系统初始化值
     * @return
     */
    public Boolean stop(Long contextId, Map<String, String> initMap) {
        Boolean flag = false;

        if (null != contextId) {
            Map<String, Object> flumConfig = flumeConfigManage.queryPartialFieldsFlumeConfigByContextId(contextId);
            if (flumConfig.size() > 0) {
                try {
                    String serverIp = (String) flumConfig.get("serverIp");
                    Integer runstate = (Integer) flumConfig.get("runstate");
                    Integer processPid = (Integer) flumConfig.get("processPid");

                    String localIP = NetworkUtil.getLocalHostLANAddress().getHostAddress();
                    if (!StringUtils.isEmpty(serverIp)) {
                        if (serverIp.equals(localIP)) {
                            if (FlumeConfig.Runstate.RUNING.getValue().equals(runstate)
                                    || FlumeConfig.Runstate.PORTMONITORINGEXCEPTION.getValue().equals(runstate)
                            ) {

                                ExecuteCmdUtil.execCmd("kill -9 " + processPid);
                                log.debug("serverIp :" + serverIp + "==》杀死进程Pid ：" + processPid);

                                flumeConfigManage.updateFlumeConfigByContextIdAndRunstate(contextId,
                                        FlumeConfig.Runstate.UNOPENED.getValue());
                                log.debug("contextId :" + contextId + "==》更改运行状态为：“未开启”");

                                flag = true;

                            } else {
                                throw new CustomException("杀进程失败,异常信息：非开启状态");
                            }
                        } else {
                            throw new CustomException("杀进程失败,异常信息：与实际IP不符");
                        }
                    } else {
                        throw new CustomException("杀进程失败,异常信息：服务器IP为空");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new CustomException(e.getMessage(), HttpCode.CODE_500);
                }
            } else {
                throw new CustomException("contextId :" + contextId + "数据不存在");
            }
        } else {
            throw new CustomException("参数异常");
        }
        return flag;
    }

    public void processCheck() {
        String localIP = NetworkUtil.getLocalHostLANAddress().getHostAddress();

        List<Map<String, Object>> runFlumConfigs = flumeConfigManage.queryFlumeConfigByServerIpAndRunstate(localIP,
                FlumeConfig.Runstate.RUNING.getValue());

        if (runFlumConfigs.size() > 0) {
            runFlumConfigs.forEach(stringObjectMap -> {

                FlumeConfig flumeConfig = JSON.parseObject(JSON.toJSONString(stringObjectMap), FlumeConfig.class);

                String agentName = flumeConfig.getAgentName();
                Integer processPid = flumeConfig.getProcessPid();
                Integer monitorPort = flumeConfig.getMonitorPort();
                Long contextId = flumeConfig.getContextId();

                try {
                    boolean flag = ExecuteCmdUtil.isExistProccessId(processPid, monitorPort, agentName);
                    if (!flag) {
                        flumeConfigManage.updateFlumeConfigByContextIdAndRunstate(contextId,
                                FlumeConfig.Runstate.UNOPENED.getValue());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } else {
            log.info("无运行的flume");
        }
    }


}
