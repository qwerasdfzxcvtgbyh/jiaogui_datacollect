package com.qmtec.hdfsUtil.resource.hdfs;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.net.URI;

//Hdfs - 工具类
@Slf4j

public  class HdfsUtil {
    //hdfs的url
    private static String nameNodeUrl;
    //操作角色
    private static String user;

    //缓冲大小
    private static final int bufferSize = 1024 * 1024 * 64;

    public HdfsUtil() {}

    public static void setNameNodeUrl(String nameNodeUrl) {
        nameNodeUrl = nameNodeUrl;
    }
    public static void setUser(String user) { user = user;}

    private static Configuration getConfiguration() {
        Configuration configuration = new Configuration();
        return configuration;
    }

    /**
     * 获取HDFS文件系统对象
     *
     * @return
     * @throws Exception
     */
    public static FileSystem getFileSystem() throws Exception {
        // 客户端去操作hdfs时是有一个用户身份的，默认情况下hdfs客户端api会从jvm中获取一个参数作为自己的用户身份
        // DHADOOP_USER_NAME=hadoop
        // 也可以在构造客户端fs对象时，通过参数传递进去
        FileSystem fileSystem = FileSystem.get(new URI(nameNodeUrl), getConfiguration(), user);
        return fileSystem;
    }

    /**
     * 判断HDFS文件是否存在
     *
     * @param path
     * @return
     * @throws Exception
     */
    public static boolean existFile(String path) throws Exception {
        if (StringUtils.isBlank(path)) {
            return false;
        }
        FileSystem fs = getFileSystem();
        Path srcPath = new Path(path);
        boolean isExists = fs.exists(srcPath);
        fs.close();
        return isExists;
    }

    /**
     * 创建目录
     *
     * @param path
     * @return
     * @throws Exception
     */
    public static boolean createDir(String path) throws Exception {
        boolean is_mkdirs = false;
        if (StringUtils.isBlank(path)) {
            throw new Exception("参数异常");
        }
        if (existFile(path)) {
            return true;
        }
        FileSystem fileSystem = getFileSystem();
        if (!fileSystem.exists(new Path(path))) {
            is_mkdirs = fileSystem.mkdirs(new Path(path));
            if (is_mkdirs) {
                System.out.println("创建目录成功！");
            } else {
                System.out.println("创建目录失败");
            }
        }
        fileSystem.close();
        return is_mkdirs;
    }

    /**
     * 列出path下的文件和目录
     *
     * @param path
     * @return
     * @throws Exception
     */
    public static FileStatus[] listFileStatus(String path) throws Exception {
        if (StringUtils.isBlank(path)) {
            throw new Exception("参数异常");
        }
        if (!existFile(path)) {
            return null;
        }
        FileSystem fileSystem = getFileSystem();
        FileStatus[] fileStatuses = fileSystem.listStatus(new Path(path));
        fileSystem.close();
        return fileStatuses;
    }

    /**
     * 列出path下的文件
     *
     * @param path
     * @param recursive : 是否迭代
     * @throws Exception
     */
    public static RemoteIterator<LocatedFileStatus> listFiles(String path, boolean recursive) throws Exception {
        if (StringUtils.isBlank(path)) {
            throw new Exception("参数异常");
        }
        if (!existFile(path)) {
            return null;
        }
        FileSystem fileSystem = getFileSystem();
        RemoteIterator<LocatedFileStatus> iterator = fileSystem.listFiles(new Path(path), recursive);
        while (iterator.hasNext()) {
            LocatedFileStatus fileStatus = iterator.next();
            System.out.println("blockSize" + fileStatus.getBlockSize());
            System.out.println("owner:" + fileStatus.getOwner());
            System.out.println("replication:" + fileStatus.getReplication());
            //文件路径
            System.out.println("path:" + fileStatus.getPath());
            //文件名
            System.out.println("name:" + fileStatus.getPath().getName());
            //关于block块的一些信息
            BlockLocation[] blockLocations = fileStatus.getBlockLocations();
            for (BlockLocation block : blockLocations) {
                System.out.println("块大小:" + block.getLength());
                System.out.println("偏移量:" + block.getOffset());
                String[] hosts = block.getHosts();
                for (String host : hosts) {
                    System.out.println("所在datanode:" + host);
                }
            }
            System.out.println("------------");
        }
        fileSystem.close();
        return iterator;
    }

    /**
     * 创建新文件
     *
     * @param dir
     * @param filename
     * @param content
     * @return
     * @throws Exception
     */
    public static boolean createNewHDFSFile(String dir, String filename, String content) throws Exception {
        if (StringUtils.isBlank(dir) || StringUtils.isBlank(filename)) {
            throw new Exception("参数异常");
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(dir).append(File.separator).append(filename);
        return createNewHDFSFile(stringBuffer.toString(), content);
    }

    /**
     * 创建新文件
     *
     * @param newFilePath
     * @param content
     * @return
     * @throws Exception
     */
    public static boolean createNewHDFSFile(String newFilePath, String content) throws Exception {
        if (StringUtils.isBlank(newFilePath)) {
            throw new Exception("参数异常");
        }
        FileSystem fileSystem = getFileSystem();
        Path path = new Path(newFilePath);
        if (!fileSystem.exists(path)) {
            FSDataOutputStream os = fileSystem.create(path);
            if (null != content) {
                os.write(content.getBytes("UTF-8"));
                os.close();
            }
        } else {
            throw new Exception("已存在文件，新建失败");
        }
        fileSystem.close();
        return true;
    }

    /**
     * 读取HDFS文件内容,返回字符串
     *
     * @param path
     * @return
     * @throws Exception
     */
    public static String readFile(String path) throws Exception {
        if (StringUtils.isEmpty(path)) {
            throw new Exception("参数异常");
        }
        if (!existFile(path)) {
            return null;
        }
        FileSystem fs = getFileSystem();
        // 目标路径
        Path srcPath = new Path(path);
        FSDataInputStream inputStream = null;
        try {
            inputStream = fs.open(srcPath);
            // 防止中文乱码
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String lineTxt = "";
            StringBuffer sb = new StringBuffer();
            while ((lineTxt = reader.readLine()) != null) {
                sb.append(lineTxt);
            }
            return sb.toString();
        } finally {
            inputStream.close();
            fs.close();
        }
    }

    /**
     * 打开HDFS上的文件，并返回byte数组
     *
     * @param path
     * @return
     * @throws Exception
     */
    public static byte[] openFileToBytes(String path) throws Exception {
        if (StringUtils.isEmpty(path)) {
            return null;
        }
        if (!existFile(path)) {
            return null;
        }
        FileSystem fs = getFileSystem();
        // 目标路径
        Path srcPath = new Path(path);
        try {
            FSDataInputStream inputStream = fs.open(srcPath);
            return IOUtils.readFullyToByteArray(inputStream);
        } finally {
            fs.close();
        }
    }

    /**
     * 打开HDFS上的文件，并返回指定长度byte数组
     *
     * @param path
     * @param k    ： kb
     * @return
     * @throws Exception
     */
    public static byte[] openFileToBytes(String path, int k) throws Exception {
        if (StringUtils.isEmpty(path)) {
            return null;
        }
        if (!existFile(path)) {
            return null;
        }
        FileSystem fs = getFileSystem();
        // 目标路径
        Path srcPath = new Path(path);
        byte[] fileNameBytes = null;
        try {
            int length = (k * 1024);
            fileNameBytes = new byte[length];
            FSDataInputStream inputStream = fs.open(srcPath);
            int fileNameReadLength = 0;
            int hasReadLength = 0;//已经读取的字节数
            while ((fileNameReadLength = inputStream.read(fileNameBytes, hasReadLength, (int) length - hasReadLength)) > 0) {
                hasReadLength = hasReadLength + fileNameReadLength;
            }
        } finally {
            fs.close();
        }
        return fileNameBytes;
    }

    /**
     * 打开HDFS上的文件并返回java对象
     *
     * @param path
     * @return
     * @throws Exception
     */
    public static <T extends Object> T openFileToObject(String path, Class<T> clazz) throws Exception {
        if (StringUtils.isEmpty(path)) {
            return null;
        }
        if (!existFile(path)) {
            return null;
        }
        String jsonStr = readFile(path);
        return JSON.parseObject(jsonStr, clazz);
    }

    /**
     * 移除文件
     *
     * @param dir
     * @param filename
     * @return
     * @throws Exception
     */
    public static boolean removeFile(String dir, String filename) throws Exception {
        if (StringUtils.isBlank(dir) || StringUtils.isBlank(filename)) {
            throw new Exception("参数异常");
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(dir).append(File.separator).append(filename);
        return removeFile(stringBuffer.toString());
    }

    /**
     * 移除文件
     *
     * @param path
     * @return
     * @throws Exception
     */
    public static boolean removeFile(String path) throws Exception {
        if (StringUtils.isBlank(path)) {
            throw new Exception("参数异常");
        }
        return delete(path, false);
    }

    /**
     * 迭代删除目录
     *
     * @param path
     * @return
     * @throws Exception
     */
    public static boolean removeDir(String path) throws Exception {
        if (StringUtils.isBlank(path)) {
            throw new Exception("参数异常");
        }
        return delete(path, true);
    }

    /**
     * 删除文件
     *
     * @param path
     * @param f
     * @return
     * @throws Exception
     */
    public static boolean delete(String path, boolean f) throws Exception {
        if (StringUtils.isBlank(path)) {
            throw new Exception("参数异常");
        }
        FileSystem fileSystem = getFileSystem();
        if (!existFile(path)) {
            return false;
        }
        boolean is_delete = fileSystem.delete(new Path(path), f);//true/false是否是用递归进行删除
        fileSystem.close();
        if (is_delete) {
            System.out.println("删除文件成功！");
        } else {
            System.out.println("删除文件失败！");
        }
        return is_delete;
    }

    /**
     * 重命名
     *
     * @param path
     * @param oldName
     * @param newName
     * @return
     * @throws Exception
     */
    public static boolean fileRenaming(String path, String oldName, String newName) throws Exception {
        if (StringUtils.isBlank(path) || StringUtils.isBlank(oldName) || StringUtils.isBlank(newName)) {
            throw new Exception("参数异常");
        }
        Path oldPath = new Path(path + File.separator + oldName);
        Path newPath = new Path(path + File.separator + newName);
        return fileRenaming(oldPath, newPath);
    }

    /**
     * 重命名
     *
     * @param oldPath
     * @param newPath
     * @return
     * @throws Exception
     */
    public static boolean fileRenaming(Path oldPath, Path newPath) throws Exception {
        if (null == oldPath || null == newPath) {
            throw new Exception("参数异常");
        }
        FileSystem fileSystem = getFileSystem();
        boolean is_rename = fileSystem.rename(oldPath, newPath);
        if (is_rename) {
            System.out.println("重命名文件成功！");
        } else {
            System.out.println("重命名文件失败！");
        }
        fileSystem.close();
        return is_rename;
    }

    /**
     * 将path路径的文件的流输出到OutputStream
     *
     * @param path
     * @param out
     * @throws Exception
     */
    public static void down(String path, OutputStream out) throws Exception {
        if (StringUtils.isBlank(path)) {
            throw new Exception("参数异常");
        }
        FileSystem fileSystem = getFileSystem();
        Path downPath = new Path(path);
        if (fileSystem.exists(downPath)) {
            FSDataInputStream fsDataInputStream = fileSystem.open(downPath);
            IOUtils.copyBytes(fsDataInputStream, out, 1024);
            fsDataInputStream.close();
        } else {
            throw new Exception("路径不存在");
        }
        fileSystem.close();
    }

    /**
     * 将服务器的本地文件上传到HDFS
     *
     * @param src
     * @param dst
     * @param delSrc
     * @throws Exception
     */
    public static void uploadFile(String src, String dst, boolean delSrc) throws Exception {
        if (StringUtils.isEmpty(src) || StringUtils.isEmpty(dst)) {
            throw new Exception("参数异常");
        }
        FileSystem fs = getFileSystem();
        Path srcPath = new Path(src);
        Path dstPath = new Path(dst);
        fs.copyFromLocalFile(delSrc, srcPath, dstPath);
        fs.close();
    }

    /**
     * 将HDFS的文件下载到服务器本地
     *
     * @param sourcePath
     * @param localPath
     * @param delSrc
     * @throws Exception
     */
    public static void downloadFile(String sourcePath, String localPath, boolean delSrc) throws Exception {
        if (StringUtils.isEmpty(sourcePath) || StringUtils.isEmpty(localPath)) {
            throw new Exception("参数异常");
        }
        FileSystem fs = getFileSystem();
        Path clientPath = new Path(sourcePath);
        Path serverPath = new Path(localPath);
        fs.copyToLocalFile(delSrc, clientPath, serverPath);
        fs.close();
    }

    /**
     * HDFS文件复制
     *
     * @param sourcePath
     * @param targetPath
     * @throws Exception
     */
    public static void copyFile(String sourcePath, String targetPath) throws Exception {
        if (StringUtils.isEmpty(sourcePath) || StringUtils.isEmpty(targetPath)) {
            return;
        }
        FileSystem fs = getFileSystem();
        // 原始文件路径
        Path oldPath = new Path(sourcePath);
        // 目标路径
        Path newPath = new Path(targetPath);
        FSDataInputStream inputStream = null;
        FSDataOutputStream outputStream = null;
        try {
            inputStream = fs.open(oldPath);
            outputStream = fs.create(newPath);
            IOUtils.copyBytes(inputStream, outputStream, bufferSize, false);
        } finally {
            inputStream.close();
            outputStream.close();
            fs.close();
        }
    }

    /**
     * MultipartFile文件上传HDFS
     *
     * @param path
     * @param file
     * @throws Exception
     */
    public static void uploadFileToHdfs(String path, MultipartFile file) throws Exception {
        if (StringUtils.isEmpty(path) || null == file.getBytes()) {
            return;
        }
        String fileName = file.getOriginalFilename();
        upload(file.getInputStream(), path, fileName);
    }

    /**
     * 通过文件流上传到HDFS
     *
     * @param in
     * @param dir
     * @param filename
     * @throws Exception
     */
    public static void upload(InputStream in, String dir, String filename) throws Exception {
        if (StringUtils.isBlank(dir) || StringUtils.isBlank(filename) || null == in) {
            throw new Exception("参数异常");
        }
        FileSystem fileSystem = getFileSystem();
        Path path = new Path(dir + File.separator + filename);
        if (fileSystem.exists(path)) {
            throw new Exception("已存在名为： " + filename + "的文件");
        } else {
            FSDataOutputStream fsDataOutputStream = fileSystem.create(path);
            IOUtils.copyBytes(in, fsDataOutputStream, bufferSize);
            fsDataOutputStream.close();
        }
        fileSystem.close();
        in.close();
    }

    /**
     * 获取某个文件在HDFS的集群位置
     *
     * @param path
     * @return
     * @throws Exception
     */
    public static BlockLocation[] getFileBlockLocations(String path) throws Exception {
        if (StringUtils.isEmpty(path)) {
            return null;
        }
        if (!existFile(path)) {
            return null;
        }
        FileSystem fs = getFileSystem();
        // 目标路径
        Path srcPath = new Path(path);
        FileStatus fileStatus = fs.getFileStatus(srcPath);
        return fs.getFileBlockLocations(fileStatus, 0, fileStatus.getLen());
    }
}
