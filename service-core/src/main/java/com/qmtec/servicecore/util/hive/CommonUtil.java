package com.qmtec.servicecore.util.hive;

import java.util.Map;

/**
 * Created by caesar on 2016/7/15.
 */
public class CommonUtil {

    /**
     * 注意：hive中的日期字段为10位的int型，标准是13位的long型，需要将其乘以1000，否则查询的时间都是1970年的1月18日
     * @param datetime
     * @return
     */
    public static long convertHiveDateTime(int datetime){
        return datetime*1000L;
    }

    public static boolean equalsDB(String content,String targetDBName){
        content = content.replaceAll("\\n"," ").toLowerCase();
        targetDBName = targetDBName.toLowerCase();
        boolean containsDB =
                content.contains("use "+targetDBName)
                        || content.contains(targetDBName+".");
//        boolean bool = Pattern.compile("^.+from.{1,4}db\\.[.]+$").matcher("select * from db.test").matches();
        return containsDB;
    }

    public static boolean equalsTable(String content,String targetTableName){
        content = content.toLowerCase();
        targetTableName = targetTableName.toLowerCase();
        boolean contains = content.contains(targetTableName);
        return contains;
    }

    public static boolean equalsField(String content,String targetFieldName){
        content = content.toLowerCase();
        targetFieldName = targetFieldName.toLowerCase();
        boolean contains = content.contains(targetFieldName);
        return contains;
    }

    public static String assemblyResult(HiveDBOperator.HiveQueryResult result, String tableName){
        //组装字段
//        List<TbColumns> columns = CommonDML.getColumnsListByTableID(tableID);
        StringBuffer rootJson= new StringBuffer("{");
        StringBuffer hiveTableModelJson= new StringBuffer("\"");
        StringBuffer hiveTableColumns= new StringBuffer("[");
        StringBuffer hiveTableTableContent= new StringBuffer("[");
        //用于前台表格数据显示时字段的排序问题，因此要唯一标识，不能和表里本来的字段名重复按照字段的id进行排序
//        Map<String,Integer> columsMap = new HashMap<String, Integer>();
//        for(TbColumns tbColumns : columns){
//            columsMap.put(tbColumns.getColumnName(),tbColumns.getIntegerIDX());
//        }
//        columsMap=CompartorUtil.sortMap(columsMap, Sort.ASC);
//        for(String columnName:columsMap.keySet()){
//            hiveTableModelJson.append("'" + columnName+ "',");
//        }

        for(String column :result.getMetaData()){
            hiveTableModelJson.append("'" + column+ "',");
        }
        if(",".equals(hiveTableModelJson.charAt(hiveTableModelJson.length()-1)+"")){
            hiveTableModelJson.deleteCharAt(hiveTableModelJson.length()-1);
        }
        hiveTableModelJson.append("\"");
        rootJson.append("\"hiveTableModelJson\":"+hiveTableModelJson);
        hiveTableColumns.append("{ \"xtype\":\"rownumberer\", \"text\": \"序号\", \"width\":50 },");
//        for(TbColumns tbColumns : columns){
//            String columnName = tbColumns.getColumnName();
////            {text: '文档ID', dataIndex: 'docID',width:50 },
//            hiveTableColumns.append("{\"text\":\"" + columnName + "\",\"dataIndex\":\"" + columnName + "\"},");
//        }
        for(String column :result.getMetaData()){
//            {text: '文档ID', dataIndex: 'docID',width:50 },
            hiveTableColumns.append("{\"text\":\"" + column + "\",\"dataIndex\":\"" + column + "\"},");
        }
        if(",".equals(hiveTableColumns.charAt(hiveTableColumns.length()-1)+"")){
            hiveTableColumns.deleteCharAt(hiveTableColumns.length()-1);
        }
        hiveTableColumns.append("]");
        rootJson.append(",\"hiveTableColumns\":"+hiveTableColumns);
        for(Map<String,Object> resultMap : result.getResult()){
            hiveTableTableContent.append("{");
            for(String column:resultMap.keySet()){
                Object value = resultMap.get(column);
                String value2;
                if(value!=null){
                    value2=value.toString();
                }else{
                    value2="";
                }
                hiveTableTableContent.append("\""+column+"\":\""+CommonUtil.filterSpecialCharactersForJson(value2) + "\",");
            }
            if(",".equals(hiveTableTableContent.charAt(hiveTableTableContent.length()-1)+"")){
                hiveTableTableContent.deleteCharAt(hiveTableTableContent.length()-1);
            }
            hiveTableTableContent.append("},");
        }
        if(",".equals(hiveTableTableContent.charAt(hiveTableTableContent.length()-1)+"")){
            hiveTableTableContent.deleteCharAt(hiveTableTableContent.length()-1);
        }
        hiveTableTableContent.append("]");
        rootJson.append(",\"hiveTableTableContent\":"+hiveTableTableContent);
        rootJson.append(",\"tableName\":\""+tableName+"\"");
        rootJson.append("}");
        System.out.println(rootJson.toString());
        return rootJson.toString();
    }

    public static String getUnicode(char c) {
        String s = Integer.toHexString(c);
        if (s.length() == 1) {
            s = "\\u000" + s;
        } else if (s.length() == 2) {
            s = "\\u00" + s;
        } else if (s.length() == 3) {
            s = "\\u0" + s;
        } else {
            s = "\\u" + s;
        }
        return s;
    }
    /**
     * 将字符串转成unicode
     * @param str 待转字符串
     * @return unicode字符串
     */
    public static String convert(String str)
    {
        str = (str == null ? "" : str);
        String tmp;
        StringBuffer sb = new StringBuffer(1000);
        char c;
        int i, j;
        sb.setLength(0);
        for (i = 0; i < str.length(); i++)
        {
            c = str.charAt(i);
            sb.append("\\u");
            j = (c >>>8); //取出高8位
            tmp = Integer.toHexString(j);
            if (tmp.length() == 1)
                sb.append("0");
            sb.append(tmp);
            j = (c & 0xFF); //取出低8位
            tmp = Integer.toHexString(j);
            if (tmp.length() == 1)
                sb.append("0");
            sb.append(tmp);

        }
        return (new String(sb));
    }

    public static String filterSpecialCharactersForJson(String content){
        return content=content.replaceAll("\\r|\\n|\"", " ");//替换换行符和反斜杠(\)
    }


}
