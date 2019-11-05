package com.qmtec.servicecore.service.resourceDB.backstageService.mysql;

import com.qmtec.dataSource.dbModel.mysql.MysqlBaseSource;
import com.qmtec.servicecore.service.resourceDB.backstageService.DbService;

import java.sql.Connection;

public interface MySqlDbService extends DbService {


     Connection conn(MysqlBaseSource mysqlBaseSource) throws Exception;
}
