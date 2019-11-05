package com.qmtec.servicecore.service.resourceDB.backstageService.hdfs;

import com.qmtec.dataSource.dbModel.hdfs.HdfsBaseSource;
import com.qmtec.servicecore.service.resourceDB.backstageService.DbService;
import org.apache.hadoop.fs.FileSystem;

public interface HdfsService extends DbService {

    FileSystem conn(HdfsBaseSource hdfsBaseSource) throws Exception;
}
