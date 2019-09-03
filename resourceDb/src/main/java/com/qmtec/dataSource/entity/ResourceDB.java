package com.qmtec.dataSource.entity;

import com.alibaba.fastjson.JSON;
import com.qmtec.dataSource.Enum.DbType;
import com.qmtec.dataSource.dbModel.Datasource;
import com.qmtec.dataSource.dbModel.hdfs.HdfsDatasource;
import com.qmtec.dataSource.dbModel.mysql.MysqlDatasource;
import lombok.*;
import org.apache.commons.lang3.StringUtils;

import java.beans.Transient;
import java.util.HashMap;
import java.util.Map;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResourceDB implements java.io.Serializable{

    public static final Map<String, Class<? extends Datasource>> DEFINE_MAP = new HashMap<String, Class<? extends Datasource>>();
    static {
        DEFINE_MAP.put(DbType.TYPE_HDFS.getType(), HdfsDatasource.class);
        DEFINE_MAP.put(DbType.TYPE_MYSQL.getType(), MysqlDatasource.class);
    }

    private String type;// 类型
    private String define;// 定义子类

    @Transient
    public Datasource getDatasourceDefine() {
        String define = getDefine();
        if (StringUtils.isBlank(define)) {
            return null;
        } else {
            Class<? extends Datasource> clazz = DEFINE_MAP.get(getType());
            return JSON.parseObject(define, clazz);
        }
    }


}
