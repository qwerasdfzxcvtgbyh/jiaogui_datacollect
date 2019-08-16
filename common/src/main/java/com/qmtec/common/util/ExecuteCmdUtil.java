package com.qmtec.common.util;


import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

@Slf4j
public class ExecuteCmdUtil {

    public static boolean exec(String cmd) {
        Boolean isSuccess = false;
        Process process = null;
        String os = Systems.getSystemType();
        if (os.equals("windows")) {
            try {
                process = Runtime.getRuntime().exec("cmd /c " + cmd);
                isSuccess = true;
            } catch (IOException var6) {
                var6.printStackTrace();
            }
        } else if (os.equals("linux")) {
            try {
                process = Runtime.getRuntime().exec(cmd);
                isSuccess = true;
            } catch (IOException var5) {
                var5.printStackTrace();
            }
        }

        return isSuccess;
    }

    public static boolean execDisplay(String cmd, boolean isConsoleDisplay) {
        Boolean isSuccess = false;
        Process process = null;
        String os = Systems.getSystemType();
        if (os.equals("windows")) {
            try {
                process = Runtime.getRuntime().exec("cmd /c " + cmd);
                isSuccess = true;
            } catch (IOException var9) {
                var9.printStackTrace();
            }
        } else if (os.equals("linux")) {
            try {
                process = Runtime.getRuntime().exec(cmd);
                isSuccess = true;
            } catch (IOException var8) {
                var8.printStackTrace();
            }
        }

        if (isConsoleDisplay) {
            BufferedReader strCon = new BufferedReader(new InputStreamReader(process.getInputStream()));

            try {
                System.out.println("##########执行命令输出结果#########");

                String line;
                while((line = strCon.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException var10) {
                var10.printStackTrace();
            }
        }

        return isSuccess;
    }

    public static Object[] execWithResults(String cmd, boolean isConsoleDisplay) {
        Boolean isSuccess = false;
        String strResult = "";
        Process process = null;
        String os = Systems.getSystemType();

        try {
            process = Runtime.getRuntime().exec(cmd);
        } catch (IOException var12) {
            var12.printStackTrace();
        }

        try {
            System.out.println(process.getClass().getDeclaredField("pid"));
        } catch (NoSuchFieldException var11) {
            var11.printStackTrace();
        }

        isSuccess = true;
        BufferedReader strCon;
        String line;
        if (isSuccess && os.equals("windows")) {
            strCon = null;

            try {
                strCon = new BufferedReader(new InputStreamReader(process.getInputStream(), "gbk"));
            } catch (UnsupportedEncodingException var10) {
                var10.printStackTrace();
            }

            try {
                while((line = strCon.readLine()) != null) {
                    System.out.println(line);
                    strResult = strResult + line;
                }
            } catch (IOException var14) {
                var14.printStackTrace();
            }
        } else if (isSuccess && os.equals("linux")) {
            strCon = null;

            try {
                strCon = new BufferedReader(new InputStreamReader(process.getInputStream(), "utf-8"));
            } catch (UnsupportedEncodingException var9) {
                var9.printStackTrace();
            }

            try {
                while((line = strCon.readLine()) != null) {
                    strResult = strResult + line;
                }
            } catch (IOException var13) {
                var13.printStackTrace();
            }
        }

        return new Object[]{isSuccess, strResult};
    }

    /**
     * 调用shell命令
     *
     * @param cmd 字符串类型
     * @throws Exception
     */
    public static void execCmd(String cmd) throws Exception {
        log.info("-----------------java调用shell命令: " + cmd);
        Process pr = Runtime.getRuntime().exec(cmd);
    }


    /**
     * 调用shell命令
     *
     * @param cmd 字符串数组
     * @return
     * @throws Exception
     */
    public static String execCmd(String[] cmd) throws Exception {
        String exeCmd = "";
        for (int i = 0; i < cmd.length; i++) {
            exeCmd += cmd[i] + " ";
        }
        log.info("-----------------java调用shell命令: " + exeCmd);
        Process pr = Runtime.getRuntime().exec(cmd);
        BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        StringBuffer stringBuffer = new StringBuffer();

        String line = null;
        while ((line = in.readLine()) != null) {
            stringBuffer.append(line);
        }
        pr.waitFor();
        log.info("-------------执行命令返回日志: " + stringBuffer.toString());
        if (stringBuffer.toString().length() > 0)
            return stringBuffer.toString().trim();
        return null;
    }

    /**
     * 调用shell脚本,获取执行程序的进程id
     *
     * @param cmd 字符串数组
     * @return
     * @throws Exception
     */
    public static String getProccessId(String[] cmd) throws Exception {
        String exeCmd = "";
        for (int i = 0; i < cmd.length; i++) {
            exeCmd += cmd[i] + " ";
        }
        log.info("-----------------java调用shell命令: " + exeCmd);
        Process pr = Runtime.getRuntime().exec(cmd);
        BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));

        boolean getProcessIdFlag = true;
        String processId = null;
        String line = null;
        while ((line = in.readLine()) != null) {
            if (getProcessIdFlag) {
                processId = line;
            }
            getProcessIdFlag = false;
        }
        pr.waitFor();
        log.info("-------------执行命令返回进程id: " + processId);
        if (processId != null)
            return processId.trim();
        return null;
    }

    /**
     * 调用shell脚本,获取执行程序的进程id
     *
     * @param cmd 字符串类型
     * @throws Exception
     */
    public static String getProccessId(String cmd) throws Exception {
        log.info("-----------------shell脚本路径: " + cmd);
        Process pr = Runtime.getRuntime().exec(cmd);
        BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        StringBuffer sb = new StringBuffer();
        boolean getProcessIdFlag = true;
        String processId = null;
        String line = null;
        while ((line = in.readLine()) != null) {
            if (getProcessIdFlag) {
                processId = line;
            }
            getProcessIdFlag = false;
        }
        pr.waitFor();
        log.info("-------------执行命令返回进程id: " + processId);
        if (processId != null)
            return processId.trim();
        return null;
    }

    /**
     * 判断进程pid是否还存在
     *
     * @param pid
     * @param monitorport
     * @param agentName
     * @return
     * @throws Exception
     */
    public static boolean isExistProccessId(Integer pid, Integer monitorport, String agentName)throws Exception {
        boolean flag = false;
        String cmd = "ps -ef |grep " + monitorport.toString() + "|grep " + agentName + "|grep " + pid + "|grep -v grep|cut -c 9-15";
        String ProcessId = getProccessId(new String[]{"/bin/bash", "-c", cmd});  //获取进程id
        if (!StringUtils.isEmpty(ProcessId)) {
            if(pid.toString().equals(ProcessId)){
                flag = true;
            }
        }
        return flag;
    }

}

