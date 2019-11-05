package com.qmtec.servicecore.util.dataBaseUtil;

import com.alibaba.fastjson.JSONObject;
import com.qmtec.dataSource.Enum.DbType;
import com.qmtec.dataSource.RequestParams;
import com.qmtec.dataSource.dbModel.BaseSource;
import com.qmtec.dataSource.dbModel.hive.HiveBaseSource;
import com.qmtec.dataSource.dbModel.mysql.MysqlBaseSource;
import com.qmtec.dataSource.entity.DatabaseSource;
import com.qmtec.dataSource.table.Element;
import com.qmtec.dataSource.table.Schema;
import com.qmtec.dataSource.table.Table;
import com.qmtec.servicecore.service.resourceDB.backstageService.hive.HiveDbServiceImpl;
import com.qmtec.servicecore.service.resourceDB.backstageService.mysql.MySqlDbServiceImpl;
import org.mortbay.util.ajax.JSON;
import org.springframework.util.StringUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataBaseUtil {

    public final static String DBTYPE_ORACLE = "oracle";
    public final static String DBTYPE_MYSQL = "mysql";
    public final static String DBTYPE_SQLSERVER = "mssql";
    public final static String DBTYPE_POSTGRESQL = "greenplum";
    public final static String DBTYPE_DB2 = "db2";
    public final static String DBTYPE_HIVE = "hive";

    /**
     * 获取库中所有表及其表结构
     *
     * @param conn
     * @param dbType
     * @return
     */
    public static List<Table> getRealTableList(Connection conn, String dbType, boolean isGetColumn, String owner)
            throws Exception {
        List<Table> realTables = new ArrayList<Table>();
        //String[] types={"TABLE","VIEW"};
        String[] types = {"TABLE", "VIEW"};
        String catalog = null;
        String schemaPattern = null;
        String schema = getSchema(conn, dbType, owner);
        if (dbType.equals(DBTYPE_ORACLE)) {
            schemaPattern = schema;
        } else if (dbType.equals(DBTYPE_SQLSERVER)) {
            catalog = conn.getCatalog();
            schemaPattern = "dbo";
        } else if (dbType.equals(DBTYPE_MYSQL)) {
            catalog = schema;
        } else if (dbType.equals(DBTYPE_DB2)) {
            schemaPattern = schema;
        } else if (dbType.equals(DBTYPE_HIVE)) {
            catalog = null;
        }
        DatabaseMetaData metadata = conn.getMetaData();

        ResultSet rs = metadata.getTables(catalog, schemaPattern, "%", types);
        //System.out.println(JSONObject.toJSONString(rs));
        while (rs.next()) {
            System.out.println("DataBaseUtil,rs.next()不为空");

            String tablenameString = rs.getString("TABLE_NAME");
            if (!StringUtils.isEmpty(owner)) {
                tablenameString = owner + "." + tablenameString;
            }
            String typeString = rs.getString("TABLE_TYPE");
            typeString = typeString != null ? typeString : "VIEW";

            String descrString = rs.getString(5) != null ? rs.getString("REMARKS") : "";

            Table tableMap = new Table(tablenameString, typeString, descrString);

            if (isGetColumn) {
                Schema schemaObj = null;
                if (typeString.equals("TABLE")) {
                    schemaObj = getClumnByTableName(metadata, catalog,
                            schemaPattern, rs.getString(3));

                } else if (typeString.equals("VIEW")) {
                    schemaObj = getClumnByViewName(conn, dbType, tablenameString);
                }
                tableMap.setSchema(schemaObj);
            }
            realTables.add(tableMap);
        }
        return realTables;
    }

    /**
     * 获取单个表结构
     *
     * @param conn
     * @param dbType
     * @param tableName
     * @return Table
     */
    public static Table getTable(Connection conn, String dbType, String tableName, String owner) throws Exception {
        String[] types = {"TABLE", "VIEW"};
        Table table = new Table(tableName);
        String typeString = "VIEW";
        String catalog = null;
        String schemaPattern = null;
        String schema = getSchema(conn, dbType, owner);
        if (dbType.equals(DBTYPE_ORACLE)) {
            schemaPattern = schema;
        } else if (dbType.equals(DBTYPE_SQLSERVER)) {
            catalog = conn.getCatalog();
            //schemaPattern =schema;
            schemaPattern = "dbo";
        } else if (dbType.equals(DBTYPE_MYSQL)) {
            catalog = schema;
        } else if (dbType.equals(DBTYPE_DB2)) {

        }
        DatabaseMetaData metadata = conn.getMetaData();
        ResultSet rs = metadata.getTables(catalog, schemaPattern, tableName, types);
        boolean isExit = false;
        while (rs.next()) {
            isExit = true;
            typeString = rs.getString("TABLE_TYPE");
            String descrString = rs.getString(5) != null ? rs.getString("REMARKS") : "";
            table.setDescr(descrString);

        }
        if (!isExit) {
            String[] viewType = {"VIEW"};
            rs = metadata.getTables(catalog, schemaPattern, tableName, viewType);
            while (rs.next()) {
                typeString = rs.getString("TABLE_TYPE");
                String descrString = rs.getString(5) != null ? rs.getString("REMARKS") : "";
                table.setDescr(descrString);
                table.setType(typeString);
            }
        }
        Schema schemaObj = null;
        if ("TABLE".equals(typeString)) {
            schemaObj = getClumnByTableName(metadata, catalog,
                    schemaPattern, tableName);

        } else {
            schemaObj = getClumnByViewName(conn, dbType, tableName);
        }
        table.setType(typeString);
        table.setSchema(schemaObj);
        //conn.close();

        return table;
    }

    public static Schema getClumnByTableName(DatabaseMetaData metadata, String catalog,
                                             String schemaPattern, String tableName) throws Exception {
        Schema schema = new Schema(tableName);
        List<Element> eList = new ArrayList<Element>();
        ResultSet tableSet = null;
        ResultSet pkSet = null;
        ResultSet indexSet = null;
        //获取主键
        pkSet = metadata.getPrimaryKeys(catalog, schemaPattern, tableName);
        List<String> pkNameLst = new ArrayList<String>();
        if (pkSet != null) {
            while (pkSet.next()) {
                String pkName = pkSet.getString("COLUMN_NAME");
                pkNameLst.add(pkName);
            }
            pkSet.close();
        }

        //获取索引
        indexSet = metadata.getIndexInfo(catalog, schemaPattern, tableName, false, false);
        List<String> indexList = new ArrayList<String>();
        if (indexSet != null) {
            while (indexSet.next()) {
                String indexName = indexSet.getString("COLUMN_NAME");
                if (indexName != null && !indexName.equals("")) {
                    indexList.add(indexName);
                }

            }
            indexSet.close();
        }
        //获取列
        tableSet = metadata.getColumns(catalog, schemaPattern, tableName,
                null);
        if (tableSet != null) {
            while (tableSet.next()) {
                Element e = new Element();
                String name = tableSet.getString("COLUMN_NAME");
                String commnents = tableSet.getString("REMARKS");// 获取描述
                String defaultValue = tableSet.getString("COLUMN_DEF");
                boolean isNull = tableSet.getString("IS_NULLABLE").equals("YES") ? true : false;
                int length = tableSet.getInt("COLUMN_SIZE");
                String type = tableSet.getString("TYPE_NAME");
                boolean isPk = isPkorIndex(pkNameLst, name);
                boolean isIndex = isPkorIndex(indexList, name);
                e.setLength("" + length);
                e.setCname(commnents != null ? commnents : "");
                e.setName(name != null ? name : "");
                e.setNull(isNull);
                e.setType(type);
                e.setDefaultValue(defaultValue != null ? defaultValue.replace(" ", "") : "");
                e.setPrimarykey(isPk);
                e.setIndex(isIndex);
                e.setFiledsource("0");
                eList.add(e);
            }
            tableSet.close();
        }
        schema.setElements(eList);

        return schema;
    }

    public static boolean isPkorIndex(List<String> pkorIndex, String coclumnName) {
        for (String s : pkorIndex) {
            if (s.equals(coclumnName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param dbType
     * @return 返回scheme
     */
    public static String getSchema(Connection conn, String dbType, String owner) {
        if (dbType.equals(DBTYPE_ORACLE)) {
            if (StringUtils.isEmpty(owner)) {
                try {
                    return conn.getMetaData().getUserName().trim().toUpperCase();
                } catch (SQLException sqle) {
                    return "";
                }
            } else {
                return owner;
            }

        } else if (dbType.equals(DBTYPE_MYSQL)) {
            try {
                return conn.getCatalog();
            } catch (SQLException sqle) {
                return "";
            }

        } else if (dbType.equals(DBTYPE_SQLSERVER)) {
            try {

                ResultSet rs = conn.getMetaData().getSchemas(conn.getCatalog(), null);
                List<String> rList = new ArrayList<String>();
                if (rs != null) {
                    while (rs.next()) {
                        rList.add(rs.getString(1).trim().toUpperCase());

                    }
                    return rList.size() > 0 ? rList.get(0) : "";
                }
            } catch (SQLException sqle) {
                return "";
            }
        } else if (dbType.equals(DBTYPE_DB2)) {
            try {
                return conn.getMetaData().getUserName().trim().toUpperCase();
            } catch (SQLException sqle) {
                return "";
            }
        } else if (dbType.equals(DBTYPE_HIVE)) {
            try {
                return conn.getMetaData().getUserName().trim().toUpperCase();
            } catch (SQLException sqle) {
                return "";
            }
        }
        return "";
    }

    /**
     * 取数据库类型名称
     *
     * @param dataType
     * @return
     */
    public static String getTypeNameByTypeCode(String dataType) {
        try {
            return getTypeNameByTypeCode(Integer.parseInt(dataType));
        } catch (Exception e) {
            return dataType;
        }
    }

    /**
     * 取数据库类型名称
     *
     * @param dataType
     * @return
     */
    public static String getTypeNameByTypeCode(int dataType) {

        switch (dataType) {
            case Types.LONGNVARCHAR:
                return "LONGNVARCHAR";
            case Types.NCHAR:
                return "NCHAR";
            case Types.NVARCHAR:
                return "NVARCHAR";
            case Types.ROWID:
                return "ROWID";
            case Types.BIT:
                return "BIT";
            case Types.TINYINT:
                return "TINYINT";
            case Types.BIGINT:
                return "BIGINT";
            case Types.LONGVARBINARY:
                return "LONGVARBINARY";
            case Types.VARBINARY:
                return "VARBINARY";
            case Types.BINARY:
                return "BINARY";
            case Types.LONGVARCHAR:
                return "LONGVARCHAR";
            case Types.CHAR:
                return "CHAR";
            case Types.NUMERIC:
                return "NUMBER";
            case Types.DECIMAL:
                return "DECIMAL";
            case Types.INTEGER:
                return "INTEGER";
            case Types.SMALLINT:
                return "SMALLINT";
            case Types.FLOAT:
                return "FLOAT";
            case Types.REAL:
                return "REAL";
            case Types.DOUBLE:
                return "DOUBLE";
            case Types.VARCHAR:
                return "VARCHAR2";
            case Types.BOOLEAN:
                return "BOOLEAN";
            case Types.DATE:
            case Types.TIMESTAMP:
            case Types.TIME:
                return "DATE";
            case Types.BLOB:
                return "BLOB";
            case Types.CLOB:
                return "CLOB";
        }
        return "unknow";
    }

    public static String getPageSql(String dbType, String tableName, List<Element> list, int length, String timeFild, String starttime, String endtime, String fildType) {
        String sqlString = "";
        String rowString = "";//列
        if (list != null) {
            for (Element e : list) {
                if (!"1".equals(e.getFiledsource()) && !"2".equals(e.getFiledsource())) {
                    if (rowString.equals("")) {
                        rowString = e.getName();
                    } else {
                        rowString = rowString + " ," + e.getName();
                    }
                }
            }
        }
        if (rowString.equals("")) {
            rowString = "*";
        }

        if (dbType.equals(DBTYPE_ORACLE)) {
            sqlString = "select " + rowString + " from (";
            sqlString += "select " + rowString + " from " + tableName;
            if (timeFild != null && !"".equals(timeFild)) {
                sqlString += " order by " + timeFild + " desc)";
            } else {
                sqlString += ")";
            }
//			else {
//				sqlString += "lastupdatedtime desc)";
//			}
            sqlString += " where 1=1 ";
            if (timeFild != null && !"".equals(timeFild)) {
                if (!"".equals(fildType) && fildType != null && !StringUtils.isEmpty(starttime) && !StringUtils.isEmpty(endtime)) {
                    if ("TIMESTAMP".equals(fildType)) {
                        sqlString += " and " + timeFild + " between to_timestamp('" + starttime + "','yyyy-MM-dd hh24:mi:ss') and to_timestamp('" + endtime + "','yyyy-MM-dd hh24:mi:ss') ";
                    } else {
                        sqlString += " and " + timeFild + " between to_date('" + starttime + "','yyyy-MM-dd hh24:mi:ss') and to_date('" + endtime + "','yyyy-MM-dd hh24:mi:ss') ";
                    }
                }
            }
            sqlString += " and ROWNUM<=" + length;
        } else if (dbType.equals(DBTYPE_SQLSERVER)) {
            sqlString = "select top " + length + " " + rowString + " from " + tableName;
        } else if (dbType.equals(DBTYPE_MYSQL)) {
            sqlString = "select " + rowString + " from " + tableName + " limit 0," + length;
        } else if (dbType.equals(DBTYPE_DB2)) {
            sqlString = "select " + rowString + " from " + tableName + " fetch first " + length + " rows only";
        } else if (dbType.equals(DBTYPE_POSTGRESQL)) {
            sqlString = "select " + rowString + " from " + tableName + " limit " + length + " offset 0";
        }
        return sqlString;
    }


    public static List<Map<String, Object>> getData(String dbType, Connection conn, String tableName, List<Element> list
            , RequestParams requestParams) throws Exception {
        List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();

        String sqlString = getPageSql(dbType, tableName, list, requestParams.getSize(), requestParams.getTimeFild(),
                requestParams.getCreateTimeStart(), requestParams.getCreateTimeEnd(), requestParams.getFildType());
        Statement stmt = null;
        ResultSet res = null;
        System.out.println(sqlString);
        stmt = conn.createStatement();
        res = stmt.executeQuery(sqlString);
        while (res.next()) {
            Map<String, Object> map = new HashMap<String, Object>();
            for (int i = 0; i < list.size(); i++) {
                if (list != null) {
                    for (Element e : list) {
                        if (!"1".equals(e.getFiledsource()) && !"2".equals(e.getFiledsource())) {
                            map.put(e.getName(), res.getString(e.getName()));
                        }
                    }
                }

            }
            datas.add(map);
        }
        return datas;
    }

    public static Schema getClumnByViewName(Connection conn, String dbType, String tableName) throws Exception {
        Schema schema = new Schema(tableName);
        List<Element> eList = new ArrayList<Element>();
        ResultSet tableSet = null;
        Statement smt = null;
        String sql = "";
        if (dbType.equals(DBTYPE_ORACLE)) {
            sql = "SELECT * FROM " + tableName + " where rownum<1";
        } else if (dbType.equals(DBTYPE_SQLSERVER)) {
            sql = "SELECT top 1 * FROM " + tableName;
        } else if (dbType.equals(DBTYPE_MYSQL) || DBTYPE_POSTGRESQL.equals(dbType)) {
            sql = "SELECT * FROM " + tableName + " limit 0,1";
        } else if (dbType.equals(DBTYPE_DB2)) {
            sql = "SELECT * FROM " + tableName + " fetch first 1 rows only";
        }
        if (!sql.equals("")) {
            smt = conn.createStatement();
            tableSet = smt.executeQuery(sql);
            ResultSetMetaData rsmd = tableSet.getMetaData();
            for (int i = 0; i < rsmd.getColumnCount(); i++) {
                Element e = new Element();
                String name = rsmd.getColumnLabel(i + 1);
                String typeCode = rsmd.getColumnTypeName(i + 1);
                int length = rsmd.getPrecision(i + 1);
                e.setName(name);
                e.setType(typeCode);
                e.setLength("" + length);
                e.setNull(false);
                e.setPrimarykey(false);
                e.setIndex(false);
                eList.add(e);
            }
        }
        schema.setElements(eList);
        return schema;

    }

	public static Table getTableBaseInfo(Connection conn, String dbType, String tableName, String owner) throws Exception {
		String[] types = {"TABLE", "VIEW"};
		Table table = new Table(tableName);
		String typeString = "VIEW";
		String catalog = null;
		String schemaPattern = null;
		String schema = getSchema(conn, dbType, owner);
		if (dbType.equals(DBTYPE_ORACLE)) {
			schemaPattern = schema;
		} else if (dbType.equals(DBTYPE_SQLSERVER)) {
			catalog = conn.getCatalog();
			//schemaPattern =schema;
			schemaPattern = "dbo";
		} else if (dbType.equals(DBTYPE_MYSQL)) {
			catalog = schema;
		} else if (dbType.equals(DBTYPE_DB2)) {

		}
		DatabaseMetaData metadata = conn.getMetaData();
		ResultSet rs = metadata.getTables(catalog, schemaPattern, tableName, types);
		while (rs.next()) {

			typeString = rs.getString("TABLE_TYPE");
			table.setType(typeString);

			String descrString = rs.getString(5) != null ? rs.getString("REMARKS") : "";
			table.setDescr(descrString);

			table.setTabCat(rs.getString("TABLE_CAT"));
			table.setTabSchem(rs.getString("TABLE_SCHEM"));
			table.setTypeCat(rs.getString("TYPE_CAT"));
			table.setTypeSchem(rs.getString("TYPE_SCHEM"));
			table.setTypeName(rs.getString("TYPE_NAME"));
			table.setSELF_REFERENCING_COL_NAME(rs.getString("SELF_REFERENCING_COL_NAME"));
			table.setRefGeneration(rs.getString("REF_GENERATION"));
		}
		Schema  schemaObj = getClumnByTableName(metadata, catalog,schemaPattern, tableName);
		table.setColSize(schemaObj.getElements().size());
		return table;
	}





    public static void main(String[] args) {
        Connection conn = null;
        String url = "";
        try {
//			url="jdbc:oracle:thin:@192.168.111.149:1521:orcl";
//			conn=ConnUtils.getConn(url,"oracle","zfgxpt","sunshine");
//			List<Table> list=getRealTableList(conn,"oracle");
//
//			System.out.println("================================================");
//			url="jdbc:mysql://192.168.111.249:3306/dataplus";
//			conn=ConnUtils.getConn(url,"mysql","dataplus","sunshine");
//			List<Table> Mylist=getRealTableList(conn,"mysql");

            /*String de = "{\"ip\":\"192.168.15.200\",\"port\":\"3307\",\"dbName\":\"bi_task\",\"userName\":\"root\"," +
                    "\"pwd\":\"qmDb8920T+-\"}";

            DatabaseSource databaseSource = DatabaseSource.builder()
                    .define(de)
                    .type(DbType.TYPE_MYSQL.getValue())
                    .build();
            MysqlBaseSource baseSource = (MysqlBaseSource) databaseSource.getDatasourceDefine();
            MySqlDbServiceImpl mySqlDbService = new MySqlDbServiceImpl();*/

            String d = "{\"ip\":\"192.168.15.61\",\"port\":\"10000\",\"dbName\":\"sys\",\"userName\":\"hive\",\"pwd\":\"hive\"}";

            DatabaseSource databaseSource = DatabaseSource.builder()
                    .define(d)
                    .type(DbType.TYPE_HIVE.getValue())
                    .build();

            HiveBaseSource hiveBaseSource = (HiveBaseSource) databaseSource.getDatasourceDefine();
            HiveDbServiceImpl hiveDbService = new HiveDbServiceImpl();

            conn = hiveDbService.conn(hiveBaseSource);
            List<Table> Mylist=getRealTableList(conn,"hive",false,"");
            //Table table = getTableBaseInfo(conn, "hive", "QRTZ_BLOB_TRIGGERS", "");
            System.out.println(JSONObject.toJSONString(Mylist));

        } catch (Exception e) {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e1) {
                }
            }
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }
    }

}


