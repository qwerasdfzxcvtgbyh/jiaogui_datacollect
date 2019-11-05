package com.qmtec.dataSource.entity;

import com.alibaba.fastjson.JSON;
import com.qmtec.dataSource.Enum.DbType;
import com.qmtec.dataSource.dbModel.BaseSource;
import com.qmtec.dataSource.dbModel.hdfs.HdfsBaseSource;
import com.qmtec.dataSource.dbModel.hive.HiveBaseSource;
import com.qmtec.dataSource.dbModel.mysql.MysqlBaseSource;
import lombok.*;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DatabaseSource implements java.io.Serializable {

    public static final Map<Integer, Class<? extends BaseSource>> DEFINE_MAP = new HashMap<Integer, Class<? extends BaseSource>>();

    static {
        DEFINE_MAP.put(DbType.TYPE_HDFS.getValue(), HdfsBaseSource.class);
        DEFINE_MAP.put(DbType.TYPE_MYSQL.getValue(), MysqlBaseSource.class);
        DEFINE_MAP.put(DbType.TYPE_HIVE.getValue(), HiveBaseSource.class);
    }

    private Integer type;// 类型
    private String define;// 定义子类

    public BaseSource getDatasourceDefine() {
        String define = getDefine();
        if (StringUtils.isBlank(define)) {
            return null;
        } else {
            Class<? extends BaseSource> clazz = DEFINE_MAP.get(getType());
            return JSON.parseObject(define, clazz);
        }
    }


    public static void main(String[] args) {
        String de = "{\"ip\":\"192.168.15.200\",\"port\":\"3307\",\"dbName\":\"bi_task\",\"userName\":\"root\"," +
                "\"pwd\":\"qmDb8920T+-\"}";

        DatabaseSource databaseSource = DatabaseSource.builder()
                .define(de)
                .type(DbType.TYPE_MYSQL.getValue())
                .build();

        BaseSource baseSource = databaseSource.getDatasourceDefine();
        if(baseSource instanceof MysqlBaseSource){
            System.out.println("yes");
        }


    }
}
