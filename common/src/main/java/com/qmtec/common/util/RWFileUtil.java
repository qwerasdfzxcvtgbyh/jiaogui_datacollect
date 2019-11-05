package com.qmtec.common.util;


import com.qmtec.common.exception.CustomException;

import java.io.*;

/**
 * 读,写文件工具
 */
public class RWFileUtil {

    /**
     * 读取指定路径下的文件
     *
     * @param path 文件路径
     * @return 所读取到的内容
     */
    public static String readFile(String path) {
        StringBuffer strBuff = new StringBuffer();
        try {

            FileReader fileReader = new FileReader(new File(path));
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String readStr = null;

            while ((readStr = bufferedReader.readLine()) != null) {
                strBuff.append(readStr + "\n");
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException(e.getMessage());
        }
        return strBuff.toString();
    }

    /**
     * @param content  写出的内容
     * @param filePath 写出文件的路径
     */
    public static void writeFile(String content, String filePath) throws Exception {
        File file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
            ExecuteCmdUtil.execCmd("chmod 775 " + filePath); //更改权限
        }

        FileWriter fw = new FileWriter(filePath);
        PrintWriter out = new PrintWriter(fw);
        out.write(content);
        out.println();
        out.close();
    }

    public static boolean isExistFile(String filePath) {
        File file = new File(filePath);
        return file.exists();
    }

    /**
     * 创建目录,如果目录不存在
     *
     * @param dirPath 目录路径
     */
    public static void createDir(String dirPath) {
        File dir = new File(dirPath);
        if (!dir.exists())
            dir.mkdirs();
    }

    /**
     * 创建多个目录,如果目录不存在
     *
     * @param dirPaths 目录路径数组
     */
    public static void createDirs(String[] dirPaths) {
        for (String dirPath : dirPaths) {
            createDir(dirPath);
        }
    }
}
