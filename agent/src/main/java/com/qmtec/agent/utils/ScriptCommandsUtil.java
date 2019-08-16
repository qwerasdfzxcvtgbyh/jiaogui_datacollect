package com.qmtec.agent.utils;

/**
 * 脚本命令生成的工具类
 * @author hejingl
 *
 */
public class ScriptCommandsUtil {

    /**
     * 根据参数生成flume启动文件的内容
     *
     * @param flumeHome  flume安装目录
     * @param propName   flume启动文件名
     * @param flumePort  flume监听端口
     * @param agentName  agent名称
     * @return
     */
    public static String createFlumeStartCmd(String flumeHome,
                  String propName, String flumePort, String agentName){
        StringBuffer sb = new StringBuffer("#!/bin/bash");
        sb.append("\n");
        sb.append(flumeHome).append("/bin/flume-ng agent -c ");
        sb.append(flumeHome).append("/conf -f ");
        sb.append(flumeHome).append("/conf/");
        if (propName.endsWith(".properties")){
            sb.append(propName);
        }else{
            sb.append(propName).append(".properties");
        }
        sb.append(" -Dflume.monitoring.type=http");
        sb.append(" -Dflume.monitoring.port=").append(flumePort);
        sb.append(" -Dflume.root.logger=INFO,console ");
        sb.append(" -n ").append(agentName);
        sb.append(" > ").append(flumeHome).append("/logs/");
        sb.append(propName).append(".log").append(" 2>&1 &");
        //sb.append("\n");
        //sb.append("echo `ps -ef | grep flume | grep ").append(flumePort);
        //sb.append("| grep ").append(agentName).append("|grep -v grep|cut -c 9-15`");

        System.out.println(sb.toString());
        return sb.toString();
    }
}
