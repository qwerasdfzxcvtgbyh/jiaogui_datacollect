package com.qmtec.common.util;

import com.qmtec.common.exception.CustomException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.Random;

public class NetworkUtil {
    /**
     * 获取本地机器名
     * @return
     */
    public static String getLocalHostName() {
        String hostName;
        try {
            InetAddress addr = InetAddress.getLocalHost();
            hostName = addr.getHostName();
        } catch (Exception ex) {
            hostName = "";
        }
        return hostName;
    }

    /**
     * 是否ping 通ip地址
     * @param ipAddress
     * @return Boolean
     */
    public static boolean isPingPass(String ipAddress){
        int  timeOut =  5000 ;   //超时5秒以上
        boolean status = false;  // 当返回值是true时，说明host是可用的，false则不可。
        try {
            status = InetAddress.getByName(ipAddress).isReachable(timeOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return status;
    }

    public static InetAddress getLocalHostLANAddress() {
        try {
            InetAddress candidateAddress = null;
            // 遍历所有的网络接口
            for (Enumeration ifaces = NetworkInterface.getNetworkInterfaces(); ifaces.hasMoreElements(); ) {
                NetworkInterface iface = (NetworkInterface) ifaces.nextElement();
                // 在所有的接口下再遍历IP
                for (Enumeration inetAddrs = iface.getInetAddresses(); inetAddrs.hasMoreElements(); ) {
                    InetAddress inetAddr = (InetAddress) inetAddrs.nextElement();
                    if (!inetAddr.isLoopbackAddress()) {// 排除loopback类型地址
                        if (inetAddr.isSiteLocalAddress()) {
                            // 如果是site-local地址，就是它了
                            return inetAddr;
                        } else if (candidateAddress == null) {
                            // site-local类型的地址未被发现，先记录候选地址
                            candidateAddress = inetAddr;
                        }
                    }
                }
            }
            if (candidateAddress != null) {
                return candidateAddress;
            }
            // 如果没有发现 non-loopback地址.只能用最次选的方案
            InetAddress jdkSuppliedAddress = InetAddress.getLocalHost();
            return jdkSuppliedAddress;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 随机生成一个指定范围的ip端口
     * @param begin
     * @param end
     * @return
     */
    public static String createPort(Integer begin, Integer end){
        Integer minBegin = 1025;
        Integer maxEnd = 65534;
        if (begin > end){
            throw new CustomException("指定的端口范围开始值不能大于结束值");
        } else if(begin < minBegin || end > maxEnd){
            throw new CustomException("指定的端口的有效范围:" + minBegin + "~" + maxEnd);
        }
        Random rd = new Random();
        return rd.nextInt((end - begin)) + begin + "";
    }
    /**
     *
     * @param ip
     * @return
     */
    public static String ip2Str(InetAddress ip){
        String ipStr = ip + "";
        int subFlag = ipStr.indexOf("/");

        if (subFlag != -1){
            ipStr = ipStr.substring(subFlag + 1);
            checkIp(ipStr);
        } else {
            checkIp(ipStr);
        }
        return ipStr;
    }

    /**
     * 判断一个字符串是否是一个有效的ip地址
     * @param ip
     */
    public static boolean isIp(String ip){
        String ipRegex = "^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." +
                "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." +
                "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." +
                "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";

        if (ip.matches(ipRegex)){
            return true;
        }
        return false;
    }
    /**
     * 检查一个字符串是否是一个有效的ip地址
     * @param ip
     */
    public static void checkIp(String ip){
        if (!isIp(ip)){
            throw new CustomException("无效Ip地址");
        }
    }

}
