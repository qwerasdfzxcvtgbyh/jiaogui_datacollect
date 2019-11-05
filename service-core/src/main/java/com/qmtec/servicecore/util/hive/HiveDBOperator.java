package com.qmtec.servicecore.util.hive;


import com.alibaba.fastjson.JSON;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HiveDBOperator {

    public HiveDBOperator() {
    }

    public static HiveDBOperator.HiveQueryResult query(String hiveDBName, String sql) throws SQLException {
        return HiveDBOperator.query(HiveJDBCUtil.getHiveJDBCConnection(hiveDBName),sql);
    }

    public static HiveDBOperator.HiveQueryResult query(Connection conection,String sql) throws SQLException {
        List<Map<String, Object>> resultList = new ArrayList();
        List<String> metaData = new ArrayList();
        try {
            Statement stmt = conection.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql);
            ResultSetMetaData data = resultSet.getMetaData();
            int i = 1;

            label110:
            while (true) {
                if (i > data.getColumnCount()) {
                    while (true) {
                        if (!resultSet.next()) {
                            break label110;
                        }

                        Map<String, Object> resultMap = new HashMap();

                        for (int g = 1; g <= data.getColumnCount(); ++g) {
                            String columnName = data.getColumnName(g);
                            Object fieldValue = resultSet.getObject(columnName);
                            if (columnName.contains(".")) {
                                columnName = columnName.split("\\.")[1];
                            }

                            resultMap.put(columnName, fieldValue);
                        }
                        resultList.add(resultMap);
                    }
                }

                String columnName = data.getColumnName(i);
                if (columnName.contains(".")) {
                    columnName = columnName.split("\\.")[1];
                }

                metaData.add(columnName);
                ++i;
            }
        } catch (SQLException var19) {
            var19.printStackTrace();
            throw var19;
        } finally {
            try {
                conection.close();
            } catch (SQLException var18) {
                var18.printStackTrace();
            }
        }
        HiveDBOperator.HiveQueryResult result = new HiveDBOperator.HiveQueryResult();
        result.setResult(resultList);
        result.setMetaData(metaData);
        return  result;
    }

    public static boolean update(String sql) throws SQLException {
        Connection conection = HiveJDBCUtil.getHiveJDBCConnection("");

        boolean var3;
        try {
            Statement stmt = conection.createStatement();
            stmt.executeUpdate(sql);
            var3 = true;
        } catch (SQLException var12) {
            var12.printStackTrace();
            throw var12;
        } finally {
            try {
                conection.close();
            } catch (SQLException var11) {
                var11.printStackTrace();
            }
        }

        return var3;
    }

    public static class HiveQueryResult {
        private List<Map<String, Object>> result;
        private List<String> metaData;

        public HiveQueryResult() {
        }

        public List<Map<String, Object>> getResult() {
            return this.result;
        }

        public void setResult(List<Map<String, Object>> result) {
            this.result = result;
        }

        public List<String> getMetaData() {
            return this.metaData;
        }

        public void setMetaData(List<String> metaData) {
            this.metaData = metaData;
        }
    }
}