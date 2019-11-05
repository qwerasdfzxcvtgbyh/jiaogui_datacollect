package com.qmtec.dataSource.dbModel.mysql;

import com.qmtec.dataSource.dbModel.BaseSource;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class MysqlBaseSource extends BaseSource implements Serializable {

    private String ip;//IP地址
    private int port;//端口
    private String userName;//用户账号
    private String pwd;//密码
    private String dbName;//数据库名称


    public String getUrl() {
        return "jdbc:mysql://" + ip + ":" + port+ "/" + dbName;
    }

}
