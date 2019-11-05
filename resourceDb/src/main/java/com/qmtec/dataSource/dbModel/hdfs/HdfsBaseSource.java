package com.qmtec.dataSource.dbModel.hdfs;

import com.qmtec.dataSource.dbModel.BaseSource;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class HdfsBaseSource extends BaseSource implements Serializable {

    private boolean IsHighAvailability;//是否高可用
    private String ower;//操作角色
    private String sourcePath;

    private String defaultFs;

    private String nameservice;  //nameservice
    private String nameNodeAddr1;//nameNodeService1
    private String nameNodeAddr2;//nameNodeService2

}
