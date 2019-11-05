package com.qmtec.servicecore.service.resourceDB.backstageService.hive;

import com.qmtec.dataSource.dbModel.hive.HiveBaseSource;
import com.qmtec.dataSource.dbModel.mysql.MysqlBaseSource;
import com.qmtec.servicecore.service.resourceDB.backstageService.DbService;

import java.sql.Connection;

public interface HiveDbService extends DbService {


     Connection conn(HiveBaseSource hiveBaseSource) throws Exception;
}
