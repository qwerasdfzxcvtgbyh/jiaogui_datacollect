package com.qmtec.agent.service.DataX;

import com.alibaba.fastjson.JSON;
import com.qmtec.agent.comm.Constant;
import com.qmtec.agent.entity.DataXConfig;
import com.qmtec.agent.entity.DataXConfig;
import com.qmtec.agent.manager.DataXConfigManage;
import com.qmtec.agent.utils.ScriptCommandsUtil;
import com.qmtec.common.exception.CustomException;
import com.qmtec.common.util.ExecuteCmdUtil;
import com.qmtec.common.util.NetworkUtil;
import com.qmtec.common.util.RWFileUtil;
import com.qmtec.common.web.HttpCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class DataxService {

    @Autowired
    private DataXConfigManage dataXConfigManage;

    //创建JSON文件
    private void createJsonFile(Boolean jsonSwitch, String jsonFilePath, String jsonFileName, String jsonFileContent)
            throws Exception {
        if (jsonSwitch) {
            if (!StringUtils.isEmpty(jsonFileContent)) {
                RWFileUtil.writeFile(jsonFileContent, jsonFilePath + jsonFileName);
            } else {
                throw new CustomException("Datax Json File :==> " + jsonFilePath + jsonFileName + " Content Is Null");
            }
        } else {
            if (!RWFileUtil.isExistFile(jsonFilePath + jsonFileName)) {
                throw new CustomException("Datax Json File Path :==> " + jsonFilePath + jsonFileName + " Not Exist");
            }
        }
    }

    //创建py脚本
    private void createPythonScript(Boolean pythonSwitch, String pythonscriptPath, String pythonscriptFileName, String pythonscriptContent)
            throws Exception {
        if (pythonSwitch) {
            if (!StringUtils.isEmpty(pythonscriptContent)) {
                RWFileUtil.writeFile(pythonscriptContent, pythonscriptPath + pythonscriptFileName);
            } else {
                throw new CustomException("Datax PythonScript File :==> " + pythonscriptFileName + " Content Is Null");
            }
        } else {
            if (!RWFileUtil.isExistFile(pythonscriptPath + pythonscriptFileName)) {
                throw new CustomException("Datax PythonScript File Path :==> " + pythonscriptPath + pythonscriptFileName + " Not Exist");
            }
        }
    }

    //创建DataX启动脚本，并执行
    private void createStartScript(String id, String jsonPath, String jsonFileName, String pyPath, String pythonscriptFileName,
                                   String dataxHome, String scripFilename)
            throws Exception {

        String startScripFilename = null;//带后缀的启动脚本名字
        if (dataxHome.endsWith("/")) {
            RWFileUtil.createDir(dataxHome + Constant.DATAX_ETLLOG);
            startScripFilename = scripFilename + ".sh";
        } else {
            RWFileUtil.createDir(dataxHome + "/" + Constant.DATAX_ETLLOG);
            startScripFilename = "/" + scripFilename + ".sh";
        }
        log.info("startScripFilename =: " + startScripFilename);

        RWFileUtil.writeFile(
                ScriptCommandsUtil.createDataXStartCmd(jsonPath, jsonFileName, pyPath, pythonscriptFileName, dataxHome, scripFilename),
                dataxHome + startScripFilename); //生成启动DataX的命令

        //更改运行状态为：“创建脚本”
        dataXConfigManage.updateDataXConfigByIdAndRunstate(id, DataXConfig.Runstate.CREATESCRIPTS.getValue());
        log.debug("id :" + id + "==》更改运行状态为：“创建脚本”");

        ExecuteCmdUtil.execCmd("chmod 775 " + dataxHome + startScripFilename);   //更改权限
        ExecuteCmdUtil.execCmd("/bin/sh " + dataxHome + startScripFilename);   //启动DataX的启动脚本

        //更改运行状态为：“执行脚本”
        dataXConfigManage.updateDataXConfigByIdAndRunstate(id, DataXConfig.Runstate.EXECSCRIPTS.getValue());
        log.debug("id :" + id + "==》更改运行状态为：“执行脚本”");
    }

    private Boolean startComm(Map<String, Object> dataXConfig, String id) throws Exception {
        Boolean flag = false;

        String name = (String) dataXConfig.get("name");
        String dataxHome = (String) dataXConfig.get("dataxHome");
        String jsonFileName = (String) dataXConfig.get("jsonFileName") + Constant.DATAX_JSON_SUFFIX;
        String jsonFileContent = (String) dataXConfig.get("jsonFileContent");
        String pythonscriptFileName = (String) dataXConfig.get("pythonscriptFileName") + Constant.DATAX_PYTHON_SUFFIX;
        String pythonscriptContent = (String) dataXConfig.get("pythonscriptContent");

        Boolean pythonSwitch = (Boolean) dataXConfig.get("pythonSwitch");
        Boolean jsonSwitch = (Boolean) dataXConfig.get("jsonSwitch");

        String jsonPath = "";
        String pyPath = "";
        if (dataxHome.endsWith("/")) {
            jsonPath = dataxHome + "conf/";
            pyPath = dataxHome + "bin/";
        } else {
            jsonPath = dataxHome + "/conf/";
            pyPath = dataxHome + "/bin/";
        }
        log.info("jsonPath =: " + jsonPath);
        log.info("pyPath =: " + pyPath);

        //创建JSON
        this.createJsonFile(jsonSwitch, jsonPath, jsonFileName, jsonFileContent);

        //创建py脚本
        this.createPythonScript(pythonSwitch, pyPath, pythonscriptFileName, pythonscriptContent);

        //更改运行状态为：“创建配置文件”
        dataXConfigManage.updateDataXConfigByIdAndRunstate(id, DataXConfig.Runstate.CREATEPROFILE.getValue());
        log.debug("id :" + id + "==》更改运行状态为：“创建配置文件”");

        //创建DataX启动脚本，并执行
        this.createStartScript(id, jsonPath, jsonFileName, pyPath, pythonscriptFileName, dataxHome, name);

        String cmd = "ps -ef |grep " + jsonFileName + "|grep " + pythonscriptFileName + "|grep -v grep|cut -c 9-15";
        String procId = ExecuteCmdUtil.getProccessId(new String[]{"/bin/bash", "-c", cmd});  //获取进程id
        log.debug("id :" + id + "==》获取进程pid: " + procId);

        if (StringUtils.hasLength(procId)) {
            if (dataXConfigManage.updateDataXConfigByProcesspidAndRunstateAndId(
                    id, DataXConfig.Runstate.RUNING.getValue(),
                    Integer.valueOf(procId), new Date())) {
                flag = true;
                log.debug("id :" + id + "==》启动成功");
            } else {
                log.debug("id :" + id + "==》已获取进程，更新状态失败");
                throw new CustomException("已获取进程，更新状态失败");
            }
        } else {
            //未获取到进程pid ,修改为"启动异常"。以便重启
            dataXConfigManage.updateDataXConfigByIdAndRunstate(id, DataXConfig.Runstate.EXCEPTION.getValue());
            log.debug("id :" + id + "==》更改运行状态为：“启动异常”");
        }
        return flag;
    }

    public Boolean startDataX(String id, Map<String, String> initMap) {
        Boolean flag = false;

        if (!StringUtils.isEmpty(id)) {
            if (dataXConfigManage.receiveCheckDataXConfigById(id, DataXConfig.Runstate.PUSHING.getValue())) {
                try {
                    dataXConfigManage.updateDataXConfigByIdAndRunstate(id, DataXConfig.Runstate.PUSHED.getValue());
                    log.debug("Id :" + id + "==》DATAX运行状态更改为：“已接收请求”");
                    Map<String, Object> dataXConfig = dataXConfigManage.selectOneDataxConfigById(id);
                    if (dataXConfig.size() > 0) {
                        dataXConfigManage.updateDataXConfigByIdAndRunstate(id, DataXConfig.Runstate.STARTING.getValue());
                        log.debug("id :" + id + "==》更改运行状态为：“正在启动”");
                        flag = startComm(dataXConfig, id);
                    } else {
                        throw new CustomException("id :" + id + "数据不存在");
                    }
                } catch (Exception e) {
                    dataXConfigManage.updateDataXConfigByIdAndRunstate(id, DataXConfig.Runstate.FAIL.getValue());
                    log.debug("id :" + id + "==》更改运行状态为：“启动失败”,异常信息 ：" + e.getMessage());
                    e.printStackTrace();
                    throw new CustomException("启动失败,异常信息：" + e.getMessage(), HttpCode.CODE_500);
                }
            } else {
                throw new CustomException("id :" + id + "==> 状态不合符，已拒绝", HttpCode.CODE_400);
            }
        } else {
            throw new CustomException("参数异常", HttpCode.CODE_400);
        }
        return flag;
    }

    public Boolean restartDataX(String id, Map<String, String> initMap) {
        Boolean flag = false;

        if (null != id) {
            if (dataXConfigManage.updateDataXConfigByIdAndRunstate(id, DataXConfig.Runstate.RESTART.getValue())) {
                try {
                    //更改运行状态为：“已接收请求”
                    dataXConfigManage.updateDataXConfigByIdAndRunstate(id, DataXConfig.Runstate.PUSHED.getValue());
                    log.debug("id :" + id + "==》更改运行状态为：“已接收请求”");

                    Map<String, Object> dataXConfig = dataXConfigManage.selectOneDataxConfigById(id);
                    if (dataXConfig.size() > 0) {
                        String serverIp = (String) dataXConfig.get("serverIp");
                        Integer processPid = (Integer) dataXConfig.get("processPid");
                        //更改运行状态为：“正在重启”
                        dataXConfigManage.updateDataXConfigByIdAndRunstate(id, DataXConfig.Runstate.RESTARTING.getValue());
                        log.debug("id :" + id + "==》更改运行状态为：“正在重启”");

                        if (processPid != null) {
                            ExecuteCmdUtil.execCmd("kill -9 " + processPid);
                            log.debug("serverIp :" + serverIp + "==》杀死进程Pid ：" + processPid);
                        }
                        flag = startComm(dataXConfig, id);
                    } else {
                        throw new CustomException("id :" + id + "数据不存在");
                    }
                } catch (Exception e) {
                    //更改运行状态为：“启动失败”
                    dataXConfigManage.updateDataXConfigByIdAndRunstate(id, DataXConfig.Runstate.FAIL.getValue());
                    log.debug("id :" + id + "==》更改运行状态为：“启动失败”,异常信息 ：" + e.getMessage());
                    e.printStackTrace();
                    throw new CustomException("启动失败,异常信息：" + e.getMessage(), HttpCode.CODE_500);
                }
            } else {
                throw new CustomException("id :" + id + "==> 状态不合符，已拒绝");
            }
        } else {
            throw new CustomException("参数异常", HttpCode.CODE_400);
        }
        return flag;
    }

    public Boolean stopDataX(String id, Map<String, String> initMap) {
        Boolean flag = false;
        if (!StringUtils.isEmpty(id)) {
            Map<String, Object> dataXConfig = dataXConfigManage.queryPartialFieldsDataXConfigById(id);
            if (dataXConfig.size() > 0) {
                try {
                    String serverIp = (String) dataXConfig.get("serverIp");
                    Integer runstate = (Integer) dataXConfig.get("runstate");
                    Integer processPid = (Integer) dataXConfig.get("processPid");

                    String localIP = NetworkUtil.getLocalHostLANAddress().getHostAddress();
                    if (!StringUtils.isEmpty(serverIp)) {
                        if (serverIp.equals(localIP)) {

                            if (DataXConfig.Runstate.RUNING.getValue().equals(runstate)) {
                                if (processPid != null) {
                                    ExecuteCmdUtil.execCmd("kill -9 " + processPid);
                                    log.debug("serverIp :" + serverIp + "==》杀死进程Pid ：" + processPid);
                                }
                                dataXConfigManage.updateDataXConfigByIdAndRunstate(id, DataXConfig.Runstate.UNOPENED.getValue());
                                log.debug("id :" + id + "==》更改运行状态为：“未开启”");
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
                throw new CustomException("id :" + id + "数据不存在");
            }
        } else {
            throw new CustomException("参数异常");
        }
        return flag;
    }

    public void processCheck() {
        String localIP = NetworkUtil.getLocalHostLANAddress().getHostAddress();

        List<Map<String, Object>> runDataXConfigs = dataXConfigManage.queryDataXConfigByServerIpAndRunstate(localIP,
                DataXConfig.Runstate.RUNING.getValue());

        if (runDataXConfigs.size() > 0) {
            runDataXConfigs.forEach(stringObjectMap -> {

                DataXConfig dataXConfig = JSON.parseObject(JSON.toJSONString(stringObjectMap), DataXConfig.class);
                String id = dataXConfig.getId();
                Integer processPid = dataXConfig.getProcessPid();
                String jsonFileName = dataXConfig.getJsonFileName();
                String pythonscriptFileName = dataXConfig.getPythonscriptFileName();

                try {
                    boolean flag = ExecuteCmdUtil.isExistDataXProccessId(processPid, pythonscriptFileName, jsonFileName);
                    if (!flag) {
                        dataXConfigManage.updateDataXConfigByIdAndRunstate(id, DataXConfig.Runstate.UNOPENED.getValue());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } else {
            log.info("无运行的DataX任务");
        }
    }


}
