package com.qmtec.servicecore.service.resourceDB.backstageService;

import com.qmtec.common.exception.CustomException;
import com.qmtec.common.page.ListResult;
import com.qmtec.dataSource.Enum.DbType;
import com.qmtec.dataSource.entity.DatabaseSource;
import com.qmtec.servicecore.entity.ResourceDB;
import com.qmtec.servicecore.service.resourceDB.backstageService.hdfs.HdfsService;
import com.qmtec.servicecore.service.resourceDB.backstageService.hive.HiveDbService;
import com.qmtec.servicecore.service.resourceDB.backstageService.mysql.MySqlDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class DbServiceWrapper implements DbService {

    private Map<Integer, DbService> map = new HashMap<Integer, DbService>();//缓存

    @Autowired
    private MySqlDbService mySqlDbService;
    @Autowired
    private HdfsService hdfsService;
    @Autowired
    private HiveDbService hiveDbService;

    @PostConstruct
    public void init() {
        map.put(DbType.TYPE_MYSQL.getValue(), mySqlDbService);
        map.put(DbType.TYPE_HDFS.getValue(), hdfsService);
        map.put(DbType.TYPE_HIVE.getValue(), hiveDbService);
    }

    public DbService getDbService(DatabaseSource datasource) {
        DbService service = map.get(datasource.getType());
        Assert.notNull(service, datasource.getType() + "not found implements!");
        return service;
    }

    public DbService getDbService(ResourceDB resourceDB) {
        DbService service = map.get(resourceDB.getDatabaseType());
        Assert.notNull(service, resourceDB.getDatabaseType() + "not found implements!");
        return service;
    }

    @Override
    public Boolean testConn(DatabaseSource resourceDB) throws Exception {
        Boolean flag = false;
        try {
            flag = getDbService(resourceDB).testConn(resourceDB);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException("异常：" + e.getMessage());
        }
        return flag;
    }

    @Override
    public List<String> getTableHeader(DatabaseSource resourceDB, String tableName)throws Exception  {
        return getDbService(resourceDB).getTableHeader(resourceDB,tableName);
    }

    @Override
    public ListResult<Map<String, Object>> listSelectTableDataPreview(DatabaseSource resourceDB, String tableName) throws Exception {
        return getDbService(resourceDB).listSelectTableDataPreview(resourceDB,tableName);
    }


}
