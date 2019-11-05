package com.qmtec.servicecore.util.hive;


import com.alibaba.fastjson.JSON;
import com.qmtec.common.util.PropertiesUtil;
import org.springframework.util.StringUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class HiveJDBCUtil {
    private static String hiveJdbcDriverName = "org.apache.hive.jdbc.HiveDriver";

    public HiveJDBCUtil() {
    }

    public static Connection getHiveJDBCConnection(String dbName) throws SQLException {
        try {
            Class.forName(hiveJdbcDriverName);
        } catch (ClassNotFoundException var8) {
            var8.printStackTrace();
        }

        Connection con = null;
        Properties prop = PropertiesUtil.getProperties(HiveJDBCUtil.class, "hive.properties");
        String hiveJdbcUrlPre = prop.getProperty("hive.jdbc.url.pre");
        if (hiveJdbcUrlPre.endsWith("/")) {
            hiveJdbcUrlPre = hiveJdbcUrlPre.substring(0, hiveJdbcUrlPre.length() - 1);
        }

        String user = prop.getProperty("hive.user", "hadoop");
        String password = prop.getProperty("hive.password", "");

        try {
            con = DriverManager.getConnection(hiveJdbcUrlPre + "/" + dbName, user, password);
            return con;
        } catch (SQLException var7) {
            var7.printStackTrace();
            throw new HiveConnectException();
        }
    }

    public static void main(String[] args) throws Exception {
        //String s = "as first_register_time";
        //String d = "as pay_count";


        //String tabname = "st_newcrm_014_brand_day";
        //String tabname  = "st_newcrm_014_brand_month";
        //String tabname  = "st_newcrm_014_shop_day";
        //String tabname  = "st_newcrm_014_shop_month";
        //String tabname  = "st_newcrm_014_channel_day";
        //String tabname  = "st_newcrm_014_channel_month";

        String[] tabnames  = {"st_new_crm_025_shop_space_id",
                "st_new_crm_025_channel_type"
        };



        for(String tabname : tabnames){
            //dim_crm   dwm_crm
            HiveDBOperator.HiveQueryResult result = HiveDBOperator.query(
                    "dwm_crm", "desc " + tabname);
            String colum = "";
            for (Map<String, Object> mapList : result.getResult()) {
                String c = (String) mapList.get("col_name");
                if (!StringUtils.isEmpty(c)) {
                    colum = colum + c + ",";
                } else {
                    break;
                }
            }
            colum = colum.substring(0, colum.lastIndexOf(","));
            System.out.println(tabname + " ::==>");
            System.out.println(colum);
            System.out.println("====================================");
        }
    }



}