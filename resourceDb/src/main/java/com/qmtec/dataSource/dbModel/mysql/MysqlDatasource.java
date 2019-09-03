package com.qmtec.dataSource.dbModel.mysql;

import com.qmtec.dataSource.dbModel.Datasource;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class MysqlDatasource extends Datasource implements Serializable {

    private String ip;//IP地址
    private int port;//端口
    private String userName;//用户账号
    private String pwd;//密码
    private String dbName;//数据库名称

}
