package com.qmtec.servicecore.service.resourceDB.backstageService;


import com.qmtec.dataSource.entity.ResourceDB;

public interface DbService {
    /**
     * 数据库测试连接
     * @param resourceDB
     * @return
     */
    public Boolean testConn(ResourceDB resourceDB) throws Exception;

}
