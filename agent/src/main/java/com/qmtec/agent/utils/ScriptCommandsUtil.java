package com.qmtec.agent.utils;

import com.qmtec.agent.comm.Constant;

/**
 * 脚本命令生成的工具类
 *
 * @author hejingl
 */
public class ScriptCommandsUtil {

    /**
     * 根据参数生成flume启动文件的内容
     *
     * @param flumeHome flume安装目录
     * @param propName  flume启动文件名
     * @param flumePort flume监听端口
     * @param agentName agent名称
     * @return
     */
    public static String createFlumeStartCmd(String flumeHome,
                                             String propName, String flumePort, String agentName) {
        StringBuffer sb = new StringBuffer("#!/bin/bash");
        sb.append("\n");
        sb.append(flumeHome).append("/bin/flume-ng agent -c ");
        sb.append(flumeHome).append("/conf -f ");
        sb.append(flumeHome).append("/conf/");
        if (propName.endsWith(".properties")) {
            sb.append(propName);
        } else {
            sb.append(propName).append(".properties");
        }
        sb.append(" -Dflume.monitoring.type=http");
        sb.append(" -Dflume.monitoring.port=").append(flumePort);
        sb.append(" -Dflume.root.logger=INFO,console ");
        sb.append(" -n ").append(agentName);
        sb.append(" > ").append(flumeHome).append("/logs/");
        sb.append(propName).append(".log").append(" 2>&1 &");
        System.out.println(sb.toString());
        return sb.toString();
    }

    public static String createDataXStartCmd(String jsonFilePath, String jsonFileName,
                                             String pythonscriptPath, String pythonscriptFileName
                                            ,String dataxHome,String scripFilename) {
        StringBuffer sb = new StringBuffer("#!/bin/bash");
        sb.append("\n");
        sb.append("python ");
        sb.append(pythonscriptPath);
        sb.append(pythonscriptFileName)
                .append(" ");
        sb.append(jsonFilePath);
        sb.append(jsonFileName).append(" ");
        sb.append(" > ").append(dataxHome);
        if(!dataxHome.endsWith("/")){
            sb.append("/");
        }
        sb.append(Constant.DATAX_ETLLOG+"/");
        sb.append(scripFilename).append(".log").append(" 2>&1 &");
        System.out.println(sb.toString());
        return sb.toString();
    }
}
