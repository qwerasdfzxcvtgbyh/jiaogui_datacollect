package com.qmtec.servicecore.service.resourceDB.backstageService.hive;

import com.alibaba.fastjson.JSON;
import com.qmtec.common.exception.CustomException;
import com.qmtec.common.page.ListResult;
import com.qmtec.dataSource.Enum.DbType;
import com.qmtec.dataSource.constant.DriveConstant;
import com.qmtec.dataSource.dbModel.hive.HiveBaseSource;
import com.qmtec.dataSource.entity.DatabaseSource;
import com.qmtec.servicecore.util.hive.HiveDBOperator;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.*;

@Component
public class HiveDbServiceImpl implements HiveDbService {


    @Override
    public Connection conn(HiveBaseSource hiveBaseSource) throws Exception {
        Connection conn = null;
        Properties props =new Properties();
        props.setProperty("user", hiveBaseSource.getUserName());
        props.setProperty("password", hiveBaseSource.getPwd());
        props.setProperty("remarks", "true");
        String url = hiveBaseSource.getUrl();
        Class.forName(DriveConstant.MysqlDriver).newInstance();
        conn = DriverManager.getConnection(url, props);
        return conn;
    }

    @Override
    public Boolean testConn(DatabaseSource resourceDB) throws Exception {
        HiveBaseSource hiveBaseSource  = null;
        Connection conn = null;
        boolean flag = false;
        try {
            hiveBaseSource  = JSON.parseObject(resourceDB.getDefine(),HiveBaseSource.class);
            conn = this.conn(hiveBaseSource);
        }finally {
            if (conn != null) {
                conn.close();
                flag = true;
            }
        }
        return flag;
    }

    @Override
    public List<String> getTableHeader(DatabaseSource resourceDB, String tableName) throws Exception {
        List<String> list = new ArrayList<String>();

        Connection conn = null;
        try {
            HiveBaseSource hiveBaseSource  = JSON.parseObject(resourceDB.getDefine(),HiveBaseSource.class);
            conn = this.conn(hiveBaseSource);
            String sql = "desc " + hiveBaseSource.getDbName()+"."+ tableName;

            if (conn != null && !StringUtils.isEmpty(sql)) {
                HiveDBOperator.HiveQueryResult result = HiveDBOperator.query(conn, sql);

                result.getResult().forEach(stringObjectMap -> {
                    String name = (String) stringObjectMap.get("col_name");
                    list.add(name);
                });

            } else {
                throw new CustomException("connection fail");
            }
        }finally {
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

    @Override
    public ListResult<Map<String, Object>> listSelectTableDataPreview(DatabaseSource resourceDB, String tableName) throws Exception {
        ListResult<Map<String, Object>> listResult = new ListResult<Map<String, Object>>();
        Connection conn = null;
        try {
            HiveBaseSource hiveBaseSource  = JSON.parseObject(resourceDB.getDefine(),HiveBaseSource.class);
            conn = this.conn(hiveBaseSource);
            String sql = "select * from  " + hiveBaseSource.getDbName()+"."+ tableName + " limit 20";

            if (conn != null && !StringUtils.isEmpty(sql)) {
                HiveDBOperator.HiveQueryResult result = HiveDBOperator.query(conn, sql);
                listResult.setResult(result.getResult());
                listResult.setTotal(Long.valueOf(String.valueOf(result.getResult().size())));
            } else {
                throw new CustomException("connection fail");
            }
        }finally {
            if (conn != null) {
                conn.close();
            }
        }
        return listResult;
    }


}
