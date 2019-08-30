package com.qmtec.servicecore.util.hive;


import com.alibaba.fastjson.JSON;
import com.qmtec.common.util.PropertiesUtil;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

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

    public static void main(String[] args) throws SQLException {
       /* try {
            Class.forName(hiveJdbcDriverName);
        } catch (ClassNotFoundException var5) {
            var5.printStackTrace();
            System.exit(1);
        }

        Connection con = DriverManager.getConnection("jdbc:hive2://192.168.15.61:10000", "hive", "hive");
        System.err.println(con);
        Statement stmt = con.createStatement();
        String sql = "select * from ceshi.test1 limit 10";
        stmt.executeQuery(sql);*/

        System.out.println(JSON.toJSONString(HiveDBOperator.query("default","select * from t_hm_category limit 200")));
    }
}