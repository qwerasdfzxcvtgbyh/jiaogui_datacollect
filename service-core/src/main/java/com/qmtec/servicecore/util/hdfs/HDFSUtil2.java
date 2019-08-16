package com.qmtec.servicecore.util.hdfs;


import java.io.IOException;
import java.util.List;

import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class HDFSUtil2 {

    public HDFSUtil2() {
    }

    public static List<Path> list(FileSystem hdfs, String path, List<Path> paths) {
        try {
            Path listPath = new Path(path);
            FileStatus[] files = hdfs.listStatus(listPath);
            if (hdfs.exists(listPath)) {
                FileStatus[] var5 = files;
                int var6 = files.length;

                for (int var7 = 0; var7 < var6; ++var7) {
                    FileStatus file = var5[var7];
                    if (file.isDirectory()) {
                        list(hdfs, file.getPath().toUri().toString(), paths);
                    } else {
                        paths.add(file.getPath());
                    }
                }
            } else {
                System.out.println("该目录或文件不存在！");
            }
            return paths;
        } catch (IOException var9) {
            var9.printStackTrace();
            return paths;
        }
    }

    public static void delete(FileSystem hdfs, String path) {
        try {
            Path delPath = new Path(path);
            boolean ok = hdfs.exists(delPath);
            if (ok) {
                hdfs.delete(delPath, true);
                System.out.println("目录：" + path + "\t删除成功");
            } else {
                System.out.println("该目录或文件不存在！");
            }
        } catch (IOException var4) {
            var4.printStackTrace();
        }
    }

    public static void rename(FileSystem hdfs, String srcPathStr, String desPathStr) {
        try {
            Path srcPath = new Path(srcPathStr);
            Path desPath = new Path(desPathStr);
            hdfs.rename(srcPath, desPath);
            hdfs.close();
        } catch (IOException var5) {
            var5.printStackTrace();
        }
    }

    public static void upload(FileSystem hdfs, String srcPathStr, String desPathStr) {
        try {
            Path srcPath = new Path(srcPathStr);
            Path desPath = new Path(desPathStr);
            hdfs.copyFromLocalFile(srcPath, desPath);
            hdfs.close();
        } catch (IOException var5) {
            var5.printStackTrace();
        }
    }

    public static void main(String[] args) {
    }
}
