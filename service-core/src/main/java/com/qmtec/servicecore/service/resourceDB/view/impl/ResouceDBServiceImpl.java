package com.qmtec.servicecore.service.resourceDB.view.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qmtec.common.exception.CustomException;
import com.qmtec.common.page.ListResult;
import com.qmtec.common.tree.Tree;
import com.qmtec.common.web.HttpCode;
import com.qmtec.dataSource.Enum.DbType;
import com.qmtec.dataSource.dbModel.hive.HiveBaseSource;
import com.qmtec.dataSource.dbModel.mysql.MysqlBaseSource;
import com.qmtec.dataSource.entity.DatabaseSource;
import com.qmtec.dataSource.table.Element;
import com.qmtec.dataSource.table.Table;
import com.qmtec.servicecore.dao.ResourceDBMapper;
import com.qmtec.servicecore.entity.ResourceDB;
import com.qmtec.servicecore.entity.example.ResourceDBExample;
import com.qmtec.servicecore.model.dto.ResourceDbDto;
import com.qmtec.servicecore.service.resourceDB.backstageService.DbService;
import com.qmtec.servicecore.service.resourceDB.backstageService.DbServiceWrapper;
import com.qmtec.servicecore.service.resourceDB.backstageService.hive.HiveDbService;
import com.qmtec.servicecore.service.resourceDB.backstageService.mysql.MySqlDbService;
import com.qmtec.servicecore.service.resourceDB.view.ResouceDBService;
import com.qmtec.servicecore.util.dataBaseUtil.DataBaseUtil;
import com.qmtec.servicecore.util.hive.HiveDBOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

@Slf4j
@Service
public class ResouceDBServiceImpl implements ResouceDBService {

    @Autowired
    private ResourceDBMapper resourceDBMapper;
    @Autowired
    private DbServiceWrapper dbServiceWrapper;

    @Override
    public ListResult<ResourceDbDto> listSelectResouceDB(String searchParams, int page, int limit) {
        ListResult<ResourceDbDto> page1 = new ListResult<ResourceDbDto>();

        Map<String, Object> paramMap = JSONObject.parseObject(searchParams, Map.class);

        Optional<List<ResourceDB>> optionalResourcedbs = null;

        ResourceDBExample.Criteria criteria = ResourceDBExample.newAndCreateCriteria();
        if (paramMap != null) {

            String name = (String) paramMap.get("name");
            if (!StringUtils.isEmpty(name)) {
                criteria.andNameLike("%" + name + "%");
            }

            String databaseType = (String) paramMap.get("databaseType");
            if (!StringUtils.isEmpty(databaseType)) {
                Integer ty = Integer.valueOf(databaseType);
                //criteria.andCodeEqualTo(code);
                criteria.andDatabaseTypeEqualTo(ty);
            }
        }

        ResourceDBExample resourceDBExample = criteria.example();
        resourceDBExample.page((page - 1), limit);
        resourceDBExample.setOrderByClause("create_time desc");

        optionalResourcedbs = Optional.ofNullable(resourceDBMapper.selectByExample(resourceDBExample));
        if (optionalResourcedbs.isPresent()) {

            List<ResourceDB> resourceDBS = optionalResourcedbs.get();
            if (resourceDBS.size() > 0) {
                long count = resourceDBMapper.countByExample(criteria.example());
                page1.setTotal(count);

                List<ResourceDbDto> resourceDbDtoArrayList = new ArrayList<ResourceDbDto>();

                resourceDBS.forEach(resourceDB -> {
                    ResourceDbDto fileTemplateDto = ResourceDbDto.builder()
                            .id(resourceDB.getId())
                            .name(resourceDB.getName())
                            .createTime(resourceDB.getCreateTime())
                            .modifyTime(resourceDB.getModifyTime())
                            .build();

                    DbType[] dbtypes = DbType.values();
                    for (DbType dbType : dbtypes) {
                        if (dbType.getValue().equals(resourceDB.getDatabaseType())) {
                            fileTemplateDto.setDatabaseType(dbType.getValue());
                            fileTemplateDto.setDatabaseTypeName(dbType.getType());
                            break;
                        }
                    }

                    resourceDbDtoArrayList.add(fileTemplateDto);
                });

                page1.setResult(resourceDbDtoArrayList);
            } else {
                page1.setMessage("no data");
            }
        } else {
            throw new CustomException("Data does not exist", HttpCode.CODE_400);
        }
        return page1;
    }

    @Override
    public ResourceDbDto updateResourceDbBefore(String id) {
        ResourceDbDto resourceDbDto = null;
        Optional<ResourceDB> resourceDBOptional = null;
        try {
            resourceDBOptional = Optional.ofNullable(
                    resourceDBMapper.selectOneByExampleWithBLOBs(
                            ResourceDBExample.newAndCreateCriteria().andIdEqualTo(id).example())
            );

            if (resourceDBOptional.isPresent()) {
                ResourceDB resourceDB = resourceDBOptional.get();
                resourceDbDto = ResourceDbDto.builder()
                        .id(resourceDB.getId())
                        .name(resourceDB.getName())
                        .databaseType(resourceDB.getDatabaseType())
                        .define(resourceDB.getDefine())
                        .remark(resourceDB.getRemark())
                        .build();
            } else {
                throw new CustomException("Data does not exist", HttpCode.CODE_400);
            }

        } catch (Exception e) {
            e.printStackTrace();
            log.error("Data Query Failure >>> [{}]", JSON.toJSONString(e.getMessage()));
            throw new CustomException("Data Query Failure");
        }
        return resourceDbDto;
    }

    /**
     * 添加ResourceDb的Dao操作
     */
    private int addResourceDbCore(ResourceDbDto resourceDbDto, String id) {
        return resourceDBMapper.insertSelective(ResourceDB
                        .builder()
                        .id(id)
                        .name(resourceDbDto.getName())
                        .remark(resourceDbDto.getRemark())
                        .databaseType(resourceDbDto.getDatabaseType())
                        .define(resourceDbDto.getDefine())
                        .createTime(new Date())
                        .modifyTime(new Date())
                        .build()
                , ResourceDB.Column.id
                , ResourceDB.Column.name
                , ResourceDB.Column.remark
                , ResourceDB.Column.databaseType
                , ResourceDB.Column.define
                , ResourceDB.Column.createTime
                , ResourceDB.Column.modifyTime
        );
    }

    /**
     * 修改ResourceDb的Dao操作
     */
    private int updateResourceCore(ResourceDbDto resourceDbDto) {
        return resourceDBMapper.updateByExampleSelective(ResourceDB
                        .builder()
                        .name(resourceDbDto.getName())
                        .databaseType(resourceDbDto.getDatabaseType())
                        .define(resourceDbDto.getDefine())
                        .remark(resourceDbDto.getRemark())
                        .modifyTime(new Date())
                        .build(),
                ResourceDBExample.newAndCreateCriteria()
                        .andIdEqualTo(resourceDbDto.getId()).example()
                , ResourceDB.Column.name
                , ResourceDB.Column.databaseType
                , ResourceDB.Column.define
                , ResourceDB.Column.remark
                , ResourceDB.Column.modifyTime
        );
    }

    private ResourceDB getResourceDBByNameAndDatabaseType(String name, Integer databaseType) {
        ResourceDB resourceDB = null;

        Optional<ResourceDB> resourceDBOptional = null;
        try {
            resourceDBOptional = Optional.ofNullable(resourceDBMapper.selectOneByExample(
                    ResourceDBExample.newAndCreateCriteria()
                            .andNameEqualTo(name)
                            .andDatabaseTypeEqualTo(databaseType)
                            .example()
            ));

            if (resourceDBOptional.isPresent()) {
                resourceDB = resourceDBOptional.get();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resourceDB;
    }


    @Override
    public Boolean addOrUpdateResourceDb(String data) {
        Boolean falg = false;

        try {
            ResourceDbDto resourceDbDto = JSONObject.parseObject(data, ResourceDbDto.class);
            if (StringUtils.isEmpty(resourceDbDto.getId())) {

                ResourceDB resourceDB = this.getResourceDBByNameAndDatabaseType(resourceDbDto.getName(),
                        new Integer(resourceDbDto.getDatabaseType()));
                if (resourceDB != null) {
                    throw new CustomException("name : '" + resourceDB.getName() + "' Existing");
                }
                String id = UUID.randomUUID().toString().trim().replaceAll("-", "");
                if (this.addResourceDbCore(resourceDbDto, id) > 0) {
                    falg = true;
                }
            } else {//修改
                ResourceDB resourceDB = this.getResourceDBByNameAndDatabaseType(resourceDbDto.getName(),
                        new Integer(resourceDbDto.getDatabaseType()));
                if (resourceDB != null && !resourceDB.getId().equals(resourceDbDto.getId())) {
                    throw new CustomException("name : '" + resourceDB.getName() + "' Used");
                }
                if (this.updateResourceCore(resourceDbDto) > 0) {
                    falg = true;
                }
            }
        } catch (Exception e) {
            log.debug("add fail : [{}] ", e.getMessage());
            throw new CustomException("add fail : " + e.getMessage(), HttpCode.CODE_500);
        }
        return falg;
    }

    @Override
    public Boolean deleteResourceDb(String data) {
        Boolean falg = false;
        try {
            List<String> ids = (List<String>) JSONArray.parseObject(data, List.class);
            if (ids.size() > 0) {
                Optional<List<ResourceDB>> optionalResourceDBList = Optional.ofNullable(
                        resourceDBMapper.selectByExample(
                                ResourceDBExample.newAndCreateCriteria().andIdIn(ids).example()
                        )
                );

                if (optionalResourceDBList.isPresent()) {
                    if (resourceDBMapper.deleteByExample(
                            ResourceDBExample.newAndCreateCriteria().andIdIn(ids).example()) > 0) {
                        falg = true;
                    }
                } else {
                    throw new CustomException("No data queried ", HttpCode.CODE_400);
                }
            } else {
                throw new CustomException("Parametric anomaly ", HttpCode.CODE_400);
            }
        } catch (Exception e) {
            log.debug("delete fail : [{}] ", e.getMessage());
            throw new CustomException("delete fail : " + e.getMessage(), HttpCode.CODE_500);
        }
        return falg;
    }

    @Override
    public Boolean testConn(String data) {
        Boolean falg = false;
        try {
            ResourceDbDto resourceDbDto = JSONObject.parseObject(data, ResourceDbDto.class);
            DatabaseSource databaseSource = DatabaseSource.builder()
                    .define(resourceDbDto.getDefine())
                    .type(resourceDbDto.getDatabaseType())
                    .build();

            falg = dbServiceWrapper.testConn(databaseSource);

        } catch (Exception e) {
            log.debug("add fail : [{}] ", e.getMessage());
            throw new CustomException("test connect : " + e.getMessage(), HttpCode.CODE_500);
        }
        return falg;
    }

    @Override
    public List<String> jumpDataPreview(String id,String name){
        List<String> list = new ArrayList<>();

        Optional<ResourceDB> resourceDBOptional = null;
        try {
            resourceDBOptional = Optional.ofNullable(
                    resourceDBMapper.selectOneByExampleWithBLOBs(
                            ResourceDBExample.newAndCreateCriteria().andIdEqualTo(id).example())
            );

            if (resourceDBOptional.isPresent()) {
                ResourceDB resourceDB = resourceDBOptional.get();

                DatabaseSource databaseSource = DatabaseSource.builder()
                        .type(resourceDB.getDatabaseType())
                        .define(resourceDB.getDefine())
                        .build();

                list = dbServiceWrapper.getTableHeader(databaseSource,name);

            } else {
                throw new CustomException("Data does not exist", HttpCode.CODE_400);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Data Query Failure >>> [{}]", JSON.toJSONString(e.getMessage()));
            throw new CustomException("异常：" + e.getMessage());
        }
        return list;
    }


    @Override
    public ListResult<Map<String, Object>> listSelectTableDataPreview(String dbId, String tabName, int page, int limit) {
        ListResult<Map<String, Object>> listResult = new ListResult<Map<String, Object>>();
        Optional<ResourceDB> resourceDBOptional = null;
        try {
            resourceDBOptional = Optional.ofNullable(
                    resourceDBMapper.selectOneByExampleWithBLOBs(
                            ResourceDBExample.newAndCreateCriteria().andIdEqualTo(dbId).example())
            );

            if (resourceDBOptional.isPresent()) {
                ResourceDB resourceDB = resourceDBOptional.get();

                DatabaseSource databaseSource = DatabaseSource.builder()
                        .type(resourceDB.getDatabaseType())
                        .define(resourceDB.getDefine())
                        .build();

                listResult = dbServiceWrapper.listSelectTableDataPreview(databaseSource,tabName);

            } else {
                throw new CustomException("Data does not exist", HttpCode.CODE_400);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Data Query Failure >>> [{}]", JSON.toJSONString(e.getMessage()));
            throw new CustomException("异常：" + e.getMessage());
        }
        return listResult;
    }


    /**
     * --------------------------------------------------------------------------------------
     */
    @Override
    public List<Tree> getRelationDataBaseTableTree(String id) {
        List<Tree> list = new ArrayList<>();

        Optional<ResourceDB> resourceDBOptional = null;
        Connection connection = null;
        try {
            resourceDBOptional = Optional.ofNullable(
                    resourceDBMapper.selectOneByExampleWithBLOBs(
                            ResourceDBExample.newAndCreateCriteria().andIdEqualTo(id).example())
            );

            if (resourceDBOptional.isPresent()) {
                ResourceDB resourceDB = resourceDBOptional.get();

                DatabaseSource databaseSource = DatabaseSource.builder()
                        .type(resourceDB.getDatabaseType())
                        .define(resourceDB.getDefine())
                        .build();

                DbService dbService = dbServiceWrapper.getDbService(resourceDB);

                if (DbType.TYPE_MYSQL.getValue().equals(resourceDB.getDatabaseType())) {
                    MySqlDbService mySqlDbService = (MySqlDbService) dbService;
                    MysqlBaseSource mysqlBaseSource = (MysqlBaseSource) databaseSource.getDatasourceDefine();
                    connection = mySqlDbService.conn(mysqlBaseSource);
                }
                List<Table> tableList = null;
                if (connection != null) {
                    if (DbType.TYPE_MYSQL.getValue().equals(resourceDB.getDatabaseType())) {
                        tableList = DataBaseUtil.getRealTableList(connection
                                , DbType.TYPE_MYSQL.getType(), false, "");
                    }
                    if (tableList != null && tableList.size() > 0) {
                        tableList.forEach(table -> {
                            Tree tbTree = Tree.builder()
                                    .id(UUID.randomUUID().toString().trim().replaceAll("-", ""))
                                    .nodeData(table.getName())
                                    .title(table.getName())
                                    .Parent(true)
                                    .build();
                            list.add(tbTree);
                        });
                    }
                } else {
                    throw new CustomException("connection fail");
                }

            } else {
                throw new CustomException("Data does not exist", HttpCode.CODE_400);
            }

        } catch (Exception e) {
            e.printStackTrace();
            log.error("Data Query Failure >>> [{}]", JSON.toJSONString(e.getMessage()));
            throw new CustomException("异常：" + e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new CustomException("异常：" + e.getMessage());
            }
        }
        return list;
    }

    @Override
    public Table getTabInfo(String id, String name) {
        Table table = null;
        Optional<ResourceDB> resourceDBOptional = null;
        Connection connection = null;
        try {
            resourceDBOptional = Optional.ofNullable(
                    resourceDBMapper.selectOneByExampleWithBLOBs(
                            ResourceDBExample.newAndCreateCriteria().andIdEqualTo(id).example())
            );

            if (resourceDBOptional.isPresent()) {
                ResourceDB resourceDB = resourceDBOptional.get();

                DatabaseSource databaseSource = DatabaseSource.builder()
                        .type(resourceDB.getDatabaseType())
                        .define(resourceDB.getDefine())
                        .build();

                DbService dbService = dbServiceWrapper.getDbService(resourceDB);

                if (DbType.TYPE_MYSQL.getValue().equals(resourceDB.getDatabaseType())) {
                    MySqlDbService mySqlDbService = (MySqlDbService) dbService;
                    MysqlBaseSource mysqlBaseSource = (MysqlBaseSource) databaseSource.getDatasourceDefine();
                    connection = mySqlDbService.conn(mysqlBaseSource);
                }

                if (connection != null) {
                    if (DbType.TYPE_MYSQL.getValue().equals(resourceDB.getDatabaseType())) {
                        table = DataBaseUtil.getTableBaseInfo(connection
                                , DbType.TYPE_MYSQL.getType(), name, "");
                    }
                } else {
                    throw new CustomException("connection fail");
                }

            } else {
                throw new CustomException("Data does not exist", HttpCode.CODE_400);
            }

        } catch (Exception e) {
            e.printStackTrace();
            log.error("Data Query Failure >>> [{}]", JSON.toJSONString(e.getMessage()));
            throw new CustomException("异常：" + e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new CustomException("异常：" + e.getMessage());
            }
        }
        return table;
    }

    @Override
    public ListResult<Element> listSelectTableFieldInfo(String dbId, String tabName, int page, int limit) {
        ListResult<Element> page1 = new ListResult<Element>();

        Optional<ResourceDB> resourceDBOptional = null;
        Connection connection = null;
        try {
            resourceDBOptional = Optional.ofNullable(
                    resourceDBMapper.selectOneByExampleWithBLOBs(
                            ResourceDBExample.newAndCreateCriteria().andIdEqualTo(dbId).example())
            );

            if (resourceDBOptional.isPresent()) {
                ResourceDB resourceDB = resourceDBOptional.get();

                DatabaseSource databaseSource = DatabaseSource.builder()
                        .type(resourceDB.getDatabaseType())
                        .define(resourceDB.getDefine())
                        .build();

                DbService dbService = dbServiceWrapper.getDbService(resourceDB);

                if (DbType.TYPE_MYSQL.getValue().equals(resourceDB.getDatabaseType())) {
                    MySqlDbService mySqlDbService = (MySqlDbService) dbService;
                    MysqlBaseSource mysqlBaseSource = (MysqlBaseSource) databaseSource.getDatasourceDefine();
                    connection = mySqlDbService.conn(mysqlBaseSource);
                }

                if (connection != null) {
                    Table table = null;
                    if (DbType.TYPE_MYSQL.getValue().equals(resourceDB.getDatabaseType())) {
                        table = DataBaseUtil.getTable(connection
                                , DbType.TYPE_MYSQL.getType(), tabName, "");
                    }
                    page1.setResult(table.getSchema().getElements());
                    page1.setTotal(Long.valueOf(String.valueOf(table.getSchema().getElements().size())));
                } else {
                    throw new CustomException("connection fail");
                }

            } else {
                throw new CustomException("Data does not exist", HttpCode.CODE_400);
            }

        } catch (Exception e) {
            e.printStackTrace();
            log.error("Data Query Failure >>> [{}]", JSON.toJSONString(e.getMessage()));
            throw new CustomException("异常：" + e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new CustomException("异常：" + e.getMessage());
            }
        }
        return page1;
    }

    /**
     * ------------------------------hive-------------------------------------------
     */
    @Override
    public List<Tree> getHiveDataBaseTableTree(String id) {
        List<Tree> list = new ArrayList<>();

        Optional<ResourceDB> resourceDBOptional = null;
        Connection connection = null;
        try {
            resourceDBOptional = Optional.ofNullable(
                    resourceDBMapper.selectOneByExampleWithBLOBs(
                            ResourceDBExample.newAndCreateCriteria().andIdEqualTo(id).example())
            );

            if (resourceDBOptional.isPresent()) {
                ResourceDB resourceDB = resourceDBOptional.get();
                DatabaseSource databaseSource = DatabaseSource.builder()
                        .type(resourceDB.getDatabaseType())
                        .define(resourceDB.getDefine())
                        .build();

                DbService dbService = dbServiceWrapper.getDbService(resourceDB);
                String sql = "";
                if (DbType.TYPE_HIVE.getValue().equals(resourceDB.getDatabaseType())) {
                    HiveDbService hiveDbService = (HiveDbService) dbService;
                    HiveBaseSource hiveBaseSource = (HiveBaseSource) databaseSource.getDatasourceDefine();
                    connection = hiveDbService.conn(hiveBaseSource);
                    sql = "show tables in  " + hiveBaseSource.getDbName();
                }

                List<Table> tableList = null;
                if (connection != null && !StringUtils.isEmpty(sql)) {
                    HiveDBOperator.HiveQueryResult result = HiveDBOperator.query(connection, sql);
                    result.getResult().forEach(stringObjectMap -> {
                        Iterator<Map.Entry<String, Object>> iterator = stringObjectMap.entrySet().iterator();
                        while (iterator.hasNext()) {
                            String name = (String) iterator.next().getValue();
                            Tree tbTree = Tree.builder()
                                    .id(UUID.randomUUID().toString().trim().replaceAll("-", ""))
                                    .nodeData(name)
                                    .title(name)
                                    .Parent(true)
                                    .build();
                            list.add(tbTree);
                        }
                    });
                } else {
                    throw new CustomException("connection fail");
                }

            } else {
                throw new CustomException("Data does not exist", HttpCode.CODE_400);
            }

        } catch (Exception e) {
            e.printStackTrace();
            log.error("Data Query Failure >>> [{}]", JSON.toJSONString(e.getMessage()));
            throw new CustomException("异常：" + e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new CustomException("异常：" + e.getMessage());
            }
        }
        return list;
    }

    @Override
    public ListResult<Map<String, Object>> listSelectHiveTableFieldInfo(String dbId, String tabName, int page, int limit) {
        ListResult<Map<String, Object>> page1 = new ListResult<Map<String, Object>>();

        Optional<ResourceDB> resourceDBOptional = null;
        Connection connection = null;
        try {
            resourceDBOptional = Optional.ofNullable(
                    resourceDBMapper.selectOneByExampleWithBLOBs(
                            ResourceDBExample.newAndCreateCriteria().andIdEqualTo(dbId).example())
            );

            if (resourceDBOptional.isPresent()) {
                ResourceDB resourceDB = resourceDBOptional.get();

                DatabaseSource databaseSource = DatabaseSource.builder()
                        .type(resourceDB.getDatabaseType())
                        .define(resourceDB.getDefine())
                        .build();

                DbService dbService = dbServiceWrapper.getDbService(resourceDB);
                String sql = "";
                if (DbType.TYPE_HIVE.getValue().equals(resourceDB.getDatabaseType())) {
                    HiveDbService hiveDbService = (HiveDbService) dbService;
                    HiveBaseSource hiveBaseSource = (HiveBaseSource) databaseSource.getDatasourceDefine();
                    connection = hiveDbService.conn(hiveBaseSource);
                    sql = "desc " + hiveBaseSource.getDbName()+"."+ tabName;
                }

                if (connection != null && !StringUtils.isEmpty(sql)) {
                    HiveDBOperator.HiveQueryResult result = HiveDBOperator.query(connection, sql);
                    page1.setResult(result.getResult());
                    page1.setTotal(Long.valueOf(String.valueOf(result.getResult().size())));
                } else {
                    throw new CustomException("connection fail");
                }

            } else {
                throw new CustomException("Data does not exist", HttpCode.CODE_400);
            }

        } catch (Exception e) {
            e.printStackTrace();
            log.error("Data Query Failure >>> [{}]", JSON.toJSONString(e.getMessage()));
            throw new CustomException("异常：" + e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new CustomException("异常：" + e.getMessage());
            }
        }
        return page1;
    }

}
