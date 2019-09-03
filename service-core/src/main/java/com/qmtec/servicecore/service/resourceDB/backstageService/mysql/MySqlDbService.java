package com.qmtec.servicecore.service.resourceDB.backstageService.mysql;

import com.qmtec.dataSource.entity.ResourceDB;
import com.qmtec.servicecore.service.resourceDB.backstageService.DbService;

public class MySqlDbService implements DbService {

    @Override
    public Boolean testConn(ResourceDB resourceDB) throws Exception {
        return null;
    }
}
