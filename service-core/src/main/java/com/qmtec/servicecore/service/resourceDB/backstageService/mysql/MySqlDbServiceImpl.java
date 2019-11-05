package com.qmtec.servicecore.service.resourceDB.backstageService.mysql;

import com.alibaba.fastjson.JSON;
import com.qmtec.common.exception.CustomException;
import com.qmtec.common.page.ListResult;
import com.qmtec.dataSource.Enum.DbType;
import com.qmtec.dataSource.constant.DriveConstant;
import com.qmtec.dataSource.dbModel.hive.HiveBaseSource;
import com.qmtec.dataSource.dbModel.mysql.MysqlBaseSource;
import com.qmtec.dataSource.entity.DatabaseSource;
import com.qmtec.dataSource.table.Element;
import com.qmtec.dataSource.table.Table;
import com.qmtec.servicecore.util.dataBaseUtil.DataBaseUtil;
import com.qmtec.servicecore.util.hive.HiveDBOperator;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@Component
public class MySqlDbServiceImpl implements MySqlDbService {

    @Override
    public Boolean testConn(DatabaseSource resourceDB) throws Exception {
        MysqlBaseSource mysqlBaseSource = null;
        Connection conn = null;
        boolean flag = false;
        try {
            mysqlBaseSource = JSON.parseObject(resourceDB.getDefine(), MysqlBaseSource.class);
            conn = this.conn(mysqlBaseSource);
        } finally {
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
            MysqlBaseSource mysqlBaseSource = JSON.parseObject(resourceDB.getDefine(), MysqlBaseSource.class);
            conn = this.conn(mysqlBaseSource);
            Table table = DataBaseUtil.getTable(conn, DbType.TYPE_MYSQL.getType(), tableName, "");
            List<Element> elementList = table.getSchema().getElements();
            if (elementList.size() > 0) {
                elementList.forEach(element -> {
                    list.add(element.getName());
                });
            }
        } finally {
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
            MysqlBaseSource mysqlBaseSource = JSON.parseObject(resourceDB.getDefine(), MysqlBaseSource.class);
            conn = this.conn(mysqlBaseSource);
            String sql = "select * from  " + mysqlBaseSource.getDbName()+"."+ tableName + " limit 20";

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


    @Override
    public Connection conn(MysqlBaseSource mysqlBaseSource) throws Exception {
        Connection conn;
        Properties props = new Properties();
        props.setProperty("user", mysqlBaseSource.getUserName());
        props.setProperty("password", mysqlBaseSource.getPwd());
        props.setProperty("remarks", "true");
        String url = mysqlBaseSource.getUrl();
        Class.forName(DriveConstant.MysqlDriver).newInstance();
        conn = DriverManager.getConnection(url, props);
        return conn;
    }


}
