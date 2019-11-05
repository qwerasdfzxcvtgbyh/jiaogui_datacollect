package com.qmtec.servicecore.service.resourceDB.backstageService;


import com.qmtec.common.page.ListResult;
import com.qmtec.dataSource.entity.DatabaseSource;

import java.util.List;
import java.util.Map;

public interface DbService {
    /**
     * 数据库测试连接
     * @param resourceDB
     * @return
     */
    public Boolean testConn(DatabaseSource resourceDB) throws Exception;

    List<String> getTableHeader(DatabaseSource resourceDB ,String tableName) throws Exception ;

    ListResult<Map<String, Object>> listSelectTableDataPreview(DatabaseSource resourceDB ,String tableName)throws Exception ;

}
