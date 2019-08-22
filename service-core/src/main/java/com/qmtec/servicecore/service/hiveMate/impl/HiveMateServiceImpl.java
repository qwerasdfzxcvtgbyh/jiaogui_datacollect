package com.qmtec.servicecore.service.hiveMate.impl;

import com.alibaba.fastjson.JSON;
import com.qmtec.common.exception.CustomException;
import com.qmtec.common.page.ListResult;
import com.qmtec.common.tree.Tree;
import com.qmtec.common.web.HttpCode;
import com.qmtec.servicecore.config.mysql.DataBase;
import com.qmtec.servicecore.dao.*;
import com.qmtec.servicecore.entity.*;
import com.qmtec.servicecore.entity.example.*;
import com.qmtec.servicecore.model.dto.hive.*;
import com.qmtec.servicecore.service.hiveMate.HiveMateService;
import com.qmtec.servicecore.util.hive.CommonUtil;
import com.qmtec.servicecore.util.hive.HiveDBOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


@Slf4j
@Service
public class HiveMateServiceImpl implements HiveMateService {

    @Autowired
    private TbDBSMapper tbDBSMapper;
    @Autowired
    private TbTBLSMapper tbTBLSMapper;
    @Autowired
    private TbSDSMapper tbSDSMapper;
    @Autowired
    private TbColumnsMapper tbColumnsMapper;
    @Autowired
    private TbPartitionKeysMapper tbPartitionKeysMapper;
    @Autowired
    private TbPartitionsMapper tbPartitionsMapper;
    @Autowired
    private TbTableParamsMapper tbTableParamsMapper;
    @Autowired
    private TbSerdeParamsMapper tbSerdeParamsMapper;
    @Autowired
    private TbIDXSMapper tbIDXSMapper;


    /**
     * 获取树形菜单
     *
     * @return
     */
    @DataBase("hiveDataSource")
    public List<Tree> getHiveTree() {
        List<Tree> list = new ArrayList<>();

        try {
            List<TbDBS> dbsList = tbDBSMapper.selectByExample(TbDBSExample.newAndCreateCriteria().example());
            for (TbDBS dbs : dbsList) {
                Tree dbTree = Tree.builder()
                        .id(UUID.randomUUID().toString().trim().replaceAll("-", ""))
                        .nodeData(dbs.getDbId().toString())
                        .title(dbs.getName())
                        .Parent(true)
                        .build();

                List<TbTBLS> tbTBLSList = tbTBLSMapper.selectByExample(TbTBLSExample.newAndCreateCriteria()
                        .andDbIdEqualTo(dbs.getDbId())
                        .example());

                if (tbTBLSList.size() > 0) {
                    List<Tree> children = new ArrayList<>();
                    for (TbTBLS tbs : tbTBLSList) {
                        Tree tbTree = Tree.builder()
                                .id(UUID.randomUUID().toString().trim().replaceAll("-", ""))
                                .nodeData(tbs.getTblId().toString())
                                .title(tbs.getTblName())
                                .Parent(false)
                                .build();

                        children.add(tbTree);
                    }
                    dbTree.setChildren(children);
                }
                list.add(dbTree);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException("查询失败", HttpCode.CODE_500);
        }
        return list;
    }

    @DataBase("hiveDataSource")
    @Override
    public DbInfoDto readDBInfo(String dbId) {
        DbInfoDto dbInfoDto = new DbInfoDto();

        Optional<TbDBS> optionalTbDBS = null;
        try {
            optionalTbDBS = Optional.ofNullable(
                    tbDBSMapper.selectOneByExample(TbDBSExample.newAndCreateCriteria()
                            .andDbIdEqualTo(Long.valueOf(dbId))
                            .example())
            );

            if (optionalTbDBS.isPresent()) {
                TbDBS tbDBS = optionalTbDBS.get();

                List<TbTBLS> tbTBLSList = tbTBLSMapper.selectByExample(TbTBLSExample.newAndCreateCriteria()
                        .andDbIdEqualTo(tbDBS.getDbId())
                        .example());

                dbInfoDto = DbInfoDto.builder()
                        .dbId(tbDBS.getDbId())
                        .name(tbDBS.getName())
                        .ownerName(tbDBS.getOwnerName())
                        .ownerType(tbDBS.getOwnerType())
                        .dbLocationUri(tbDBS.getDbLocationUri())
                        .ctlgName(tbDBS.getCtlgName())
                        .tabCount(tbTBLSList.size())
                        .desc(tbDBS.getDesc())
                        .build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException("查询失败", HttpCode.CODE_500);
        }
        return dbInfoDto;
    }

    @DataBase("hiveDataSource")
    @Override
    public TableBaseInfoDto readTableBaseInfo(String TabId) {
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        TableBaseInfoDto tableBaseInfo = null;
        try {
            TbTBLS tbTBLS = tbTBLSMapper.selectOneByExample(TbTBLSExample.newAndCreateCriteria()
                    .andTblIdEqualTo(Long.valueOf(TabId))
                    .example());
            TbSDS tbSDS = tbSDSMapper.selectOneByExample(TbSDSExample.newAndCreateCriteria()
                    .andSdIdEqualTo(tbTBLS.getSdId())
                    .example());
            TbDBS tbDBS = tbDBSMapper.selectOneByExample(TbDBSExample.newAndCreateCriteria()
                    .andDbIdEqualTo(tbTBLS.getDbId())
                    .example());
            long partitionsFieldsCount = tbPartitionKeysMapper.countByExample(TbPartitionKeysExample.newAndCreateCriteria()
                    .andTblIdEqualTo(tbTBLS.getTblId())
                    .example());
            long tableFieldsCount = tbColumnsMapper.countByExample(TbColumnsExample.newAndCreateCriteria()
                    .andCdIdEqualTo(tbSDS.getCdId())
                    .example());

            long fieldsCount = partitionsFieldsCount + tableFieldsCount;

            Map<String, Object> tableParmasMap = this.getTableParams(tbTBLS.getDbId());

            String inputFormat = tbSDS.getInputFormat();

            long sdID = tbSDS.getSdId();
            List<TbSerdeParams> tbSerdeParamsList = tbSerdeParamsMapper.selectByExample(TbSerdeParamsExample.newAndCreateCriteria()
                    .andSerdeIdEqualTo(sdID)
                    .example());

            Map<String, Object> SerdeParamsMap = new HashMap<String, Object>();
            for (TbSerdeParams tbSerdeParams : tbSerdeParamsList) {
                SerdeParamsMap.put(tbSerdeParams.getParamKey(), tbSerdeParams.getParamValue());
            }
            String fieldDelimiter = (String) SerdeParamsMap.get("field.delim");
            if (fieldDelimiter == null) {
                fieldDelimiter = "\\u0001";
            }
            tableBaseInfo = TableBaseInfoDto.builder()
                    .tableID(tbTBLS.getTblId())
                    .tableName(tbTBLS.getTblName())
                    .owner(tbTBLS.getOwner())
                    .createTime(df.format(CommonUtil.convertHiveDateTime(tbTBLS.getCreateTime())))
                    .location(tbSDS.getLocation())
                    .inputFormat(inputFormat)
                    .dbName(tbDBS.getName())
                    .tableType((tbTBLS.getTblType().equals("MANAGED_TABLE") ? "内部表" : "外部表"))
                    .hasPartition((partitionsFieldsCount == 0 ? "false" : "true"))
                    .fieldsCount(fieldsCount)
                    .isCompress(tableParmasMap.containsKey("orc.compress"))
                    .compressFormat((tableParmasMap.get("orc.compress") == null ? "无" : (String) tableParmasMap.get("orc.compress")))
                    .comment((tableParmasMap.get("comment") == null ? "" : (String) tableParmasMap.get("comment")))
                    .fieldDelimiter(fieldDelimiter)
                    .build();

            if(null != tableParmasMap.get("transient_lastDdlTime")){
                tableBaseInfo.setLastUpdateTime(df.format(CommonUtil.convertHiveDateTime(Integer.parseInt(tableParmasMap.get("transient_lastDdlTime").toString()))));
            }

        } catch (NumberFormatException e) {
            e.printStackTrace();
            throw new CustomException("查询失败", HttpCode.CODE_500);
        }
        return tableBaseInfo;
    }

    /**
     * 通过tableID获取表的建立的参数信息
     *
     * @param tableID
     * @return
     */
    @DataBase("hiveDataSource")
    public Map<String, Object> getTableParams(Long tableID) {
        List<TbTableParams> list = null;
        Map<String, Object> map = null;
        try {
            list = tbTableParamsMapper.selectByExample(TbTableParamsExample.newAndCreateCriteria()
                    .andTblIdEqualTo(tableID)
                    .example());

            map = new HashMap<String, Object>();
            for (TbTableParams tbTableParams : list) {
                map.put(tbTableParams.getParamKey(), tbTableParams.getParamValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException("查询失败", HttpCode.CODE_500);
        }
        return map;
    }


    @DataBase("hiveDataSource")
    @Override
    public ListResult<TableFieldInfoDto> listSelectTableFieldInfo(Long TabId, int page, int limit) {
        ListResult<TableFieldInfoDto> listResult = new ListResult<TableFieldInfoDto>();
        List<TableFieldInfoDto> tableFieldInfoDtoArrayList = new ArrayList<TableFieldInfoDto>();
        Set<String> indexFields = new HashSet<String>();//建立了索引字段的集合

        try {
            List<TbIDXS> tbIDXSList = tbIDXSMapper.selectByExample(TbIDXSExample.newAndCreateCriteria()
                    .andOrigTblIdEqualTo(TabId)
                    .example());

            tbIDXSList.forEach(tbIDXS -> {
                TbSDS tbSDS = tbSDSMapper.selectOneByExample(TbSDSExample.newAndCreateCriteria()
                        .andSdIdEqualTo(tbIDXS.getSdId())
                        .example());
                if (tbSDS != null) {
                    TbColumns tbColumns = tbColumnsMapper.selectOneByExample(TbColumnsExample.newAndCreateCriteria()
                            .andCdIdEqualTo(tbSDS.getCdId())
                            .example());
                    indexFields.add(tbColumns.getColumnName());
                }
            });

            List<TbColumns> columns = this.getColumnsListByTableID(TabId);//获取表本身的字段值

            List<TbPartitionKeys> tbPartitionKeysList = tbPartitionKeysMapper.selectByExample(TbPartitionKeysExample.newAndCreateCriteria()
                    .andTblIdEqualTo(TabId)
                    .example()); //获取当前表哪些字段做了分区

            //获取总数
            listResult.setTotal(Long.valueOf(String.valueOf(columns.size() + tbPartitionKeysList.size())));

            columns.forEach(tbColumns -> {
                String comment = tbColumns.getComment();
                comment = comment == null || "null".equals(comment) ? "" : comment;
                TableFieldInfoDto tableFieldInfoDto = TableFieldInfoDto.builder()
                        .field(tbColumns.getColumnName())
                        .fieldID(tbColumns.getIntegerIdx())
                        .tableID(TabId)
                        .type(tbColumns.getTypeName())
                        .isPartitionFiled(false)
                        .isIndexFiled(indexFields.contains(tbColumns.getColumnName()) ? true : false)
                        .comment(comment)
                        .build();
                tableFieldInfoDtoArrayList.add(tableFieldInfoDto);
            });

            tbPartitionKeysList.forEach(tbPartitionKeys -> {
                String comment = tbPartitionKeys.getPkeyComment();
                comment = comment == null || "null".equals(comment) ? "" : comment;
                comment = CommonUtil.filterSpecialCharactersForJson(comment);
                TableFieldInfoDto tableFieldInfoDto = TableFieldInfoDto.builder()
                        .field(tbPartitionKeys.getPkeyName())
                        .fieldID(tbPartitionKeys.getIntegerIdx() + 10000)
                        .tableID(TabId)
                        .type(tbPartitionKeys.getPkeyType())
                        .isPartitionFiled(true)
                        .isIndexFiled(indexFields.contains(tbPartitionKeys.getPkeyName()) ? true : false)
                        .comment(comment)
                        .build();
                tableFieldInfoDtoArrayList.add(tableFieldInfoDto);
            });
            listResult.setResult(tableFieldInfoDtoArrayList);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            throw new CustomException("查询失败", HttpCode.CODE_500);
        }
        return listResult;
    }

    /**
     * 通过表ID 获取 字段集合
     *
     * @param tableID
     * @return
     */
    @DataBase("hiveDataSource")
    public List<TbColumns> getColumnsListByTableID(Long tableID) {
        List<TbColumns> columns = null;
        Optional<TbTBLS> tbTBLSOptional = null;
        try {
            tbTBLSOptional = Optional.ofNullable(
                    tbTBLSMapper.selectOneByExample(TbTBLSExample.newAndCreateCriteria()
                            .andTblIdEqualTo(tableID)
                            .example())
            );
            if (tbTBLSOptional.isPresent()) {
                TbTBLS tbTBLS = tbTBLSOptional.get();
                TbSDS tbSDS = tbSDSMapper.selectOneByExample(TbSDSExample.newAndCreateCriteria()
                        .andSdIdEqualTo(tbTBLS.getSdId())
                        .example());

                if (tbSDS != null) {
                    columns = tbColumnsMapper.selectByExampleWithBLOBs(TbColumnsExample.newAndCreateCriteria()
                            .andCdIdEqualTo(tbSDS.getCdId())
                            .example());
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            throw new CustomException("查询失败", HttpCode.CODE_500);
        }
        return columns;
    }

    @DataBase("hiveDataSource")
    @Override
    public List<TbColumnsDto> getColumnsDtoViewListByTableID(Long tableID) {
        List<TbColumnsDto> list = new ArrayList<>();
        try {
            List<TbColumns> tbColumnsList = getColumnsListByTableID(tableID);
            if (tbColumnsList.size() > 0) {
                tbColumnsList.forEach(tbColumns -> {
                    TbColumnsDto tbColumnsDto = TbColumnsDto.builder()
                            .cdId(tbColumns.getCdId())
                            .columnName(tbColumns.getColumnName())
                            .integerIdx(tbColumns.getIntegerIdx())
                            .typeName(tbColumns.getTypeName())
                            .comment(tbColumns.getComment())
                            .build();
                    list.add(tbColumnsDto);
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException("查询失败", HttpCode.CODE_500);
        }
        return list;
    }


    @DataBase("hiveDataSource")
    @Override
    public ListResult<TableIndexInfoDto> listSelectTableIndexInfo(Long TabId, int page, int limit) {
        ListResult<TableIndexInfoDto> listResult = new ListResult<TableIndexInfoDto>();
        List<TableIndexInfoDto> tableIndexInfoDtoList = new ArrayList<TableIndexInfoDto>();

        try {
            List<TbIDXS> tbIDXSList = tbIDXSMapper.selectByExample(TbIDXSExample.newAndCreateCriteria()
                    .andOrigTblIdEqualTo(TabId)
                    .example());

            listResult.setTotal(Long.valueOf(String.valueOf(tbIDXSList.size())));

            tbIDXSList.forEach(tbIDXS -> {
                TbSDS tbSDS = tbSDSMapper.selectOneByExample(TbSDSExample.newAndCreateCriteria()
                        .andSdIdEqualTo(tbIDXS.getSdId())
                        .example());
                if (tbSDS != null) {
                    TbColumns tbColumns = tbColumnsMapper.selectOneByExample(TbColumnsExample.newAndCreateCriteria()
                            .andCdIdEqualTo(tbSDS.getCdId())
                            .example());

                    String handlerClass = tbIDXS.getIndexHandlerClass();
                    if (handlerClass != null && handlerClass.split("\\.").length != 0) {
                        handlerClass = handlerClass.split("\\.")[handlerClass.split("\\.").length - 1];
                    }

                    TableIndexInfoDto tableIndexInfoDto = TableIndexInfoDto.builder()
                            .indexID(tbIDXS.getIndexId())
                            .indexName(tbIDXS.getIndexName())
                            .fieldName(tbColumns.getColumnName())
                            .fieldType(tbColumns.getTypeName())
                            .handlerClass(handlerClass)
                            .createTime(new Date(CommonUtil.convertHiveDateTime(tbIDXS.getCreateTime())))
                            .build();
                    tableIndexInfoDtoList.add(tableIndexInfoDto);
                }
            });
            listResult.setResult(tableIndexInfoDtoList);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            throw new CustomException("查询失败", HttpCode.CODE_500);
        }
        return listResult;
    }

    @DataBase("hiveDataSource")
    @Override
    public ListResult<TablePartitionInfoDto> listSelectTablePartitionInfo(String TabId) {
        ListResult<TablePartitionInfoDto> listResult = new ListResult<TablePartitionInfoDto>();
        List<TablePartitionInfoDto> tablePartitionInfoDtoList = new ArrayList<TablePartitionInfoDto>();

        DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        int size = 0;
        try {
            if (!StringUtils.isEmpty(TabId)) {

                TbTBLS tbTBLS = tbTBLSMapper.selectOneByExample(TbTBLSExample.newAndCreateCriteria()
                        .andTblIdEqualTo(Long.valueOf(TabId))
                        .example());

                Optional<List<TbPartitionKeys>> tbPartitionKeysOptional = null;
                tbPartitionKeysOptional = Optional.ofNullable(tbPartitionKeysMapper.selectByExample(TbPartitionKeysExample.newAndCreateCriteria()
                        .andTblIdEqualTo(tbTBLS.getTblId())
                        .example()));

                if (tbPartitionKeysOptional.isPresent()) {

                    //分区指定的key 字段
                    List<TbPartitionKeys> tbPartitionKeysList = tbPartitionKeysOptional.get();

                    String name = "";
                    if (tbPartitionKeysList.size() > 0) {
                        for (TbPartitionKeys tbPartitionKeys : tbPartitionKeysList) {
                            name = name  + tbPartitionKeys.getPkeyName() + "|";
                        }
                        name = name.substring(0,name.lastIndexOf("|"));

                        //开始组装： 父级
                        TablePartitionInfoDto tablePartitionInfoDto = TablePartitionInfoDto.builder().build();
                        tablePartitionInfoDto.setAuthorityId(tbTBLS.getTblId());

                        TbSDS tbSDS = tbSDSMapper.selectOneByExample(TbSDSExample.newAndCreateCriteria()
                                .andSdIdEqualTo(tbTBLS.getSdId())
                                .example());

                        tablePartitionInfoDto.setAuthorityName(name);
                        tablePartitionInfoDto.setCreateTime(df.format(CommonUtil.convertHiveDateTime(tbTBLS.getCreateTime())));
                        tablePartitionInfoDto.setParentId(-1L);
                        tablePartitionInfoDto.setMenuUrl(tbSDS.getLocation());
                        tablePartitionInfoDto.setChecked("0");

                        //添加父级
                        tablePartitionInfoDtoList.add(tablePartitionInfoDto);

                        //查询所以子集
                        List<TbPartitions> tbPartitionsList = tbPartitionsMapper.selectByExample(TbPartitionsExample.newAndCreateCriteria()
                                .andTblIdEqualTo(tbTBLS.getTblId())
                                .example());

                        tbPartitionsList.forEach(tbPartitions -> {

                            TbSDS tbSDS1 = tbSDSMapper.selectOneByExample(TbSDSExample.newAndCreateCriteria()
                                    .andSdIdEqualTo(tbPartitions.getSdId())
                                    .example());

                            TablePartitionInfoDto tablePartitionInfoDto1 = TablePartitionInfoDto.builder()
                                    .authorityId(tbPartitions.getPartId())
                                    .authorityName(tbPartitions.getPartName())
                                    .parentId(tbTBLS.getTblId())
                                    .checked("0")
                                    .createTime(df.format(CommonUtil.convertHiveDateTime(tbPartitions.getCreateTime())))
                                    .menuUrl(tbSDS1.getLocation())
                                    .build();

                            tablePartitionInfoDtoList.add(tablePartitionInfoDto1);
                        });

                        //所有总数
                        size = size + tbPartitionKeysList.size() + tbPartitionsList.size();
                    }
                }
            }
            listResult.setTotal(Long.valueOf(String.valueOf(size)));
            listResult.setResult(tablePartitionInfoDtoList);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            throw new CustomException("查询失败", HttpCode.CODE_500);
        }
        return listResult;
    }

    @DataBase("hiveDataSource")
    @Override
    public ListResult<Map<String, Object>> hiveDataPreview(Long TabId, int page, int limit) {
        ListResult<Map<String, Object>> listResult = new ListResult<Map<String, Object>>();

        try {
            TbTBLS tbTBLS = tbTBLSMapper.selectOneByExample(TbTBLSExample.newAndCreateCriteria()
                    .andTblIdEqualTo(Long.valueOf(TabId))
                    .example());

            TbDBS tbDBS = tbDBSMapper.selectOneByExample(TbDBSExample.newAndCreateCriteria()
                    .andDbIdEqualTo(tbTBLS.getDbId())
                    .example());

            log.info("hive数据表预览》dbName:" + tbDBS.getName() + ",tableName:" + tbTBLS.getTblName());

            String sql = null;
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("select * from ").append(tbTBLS.getTblName()).append(" limit ").append(200);
            sql = stringBuffer.toString();
            log.info("sql :==>" + stringBuffer.toString());

            HiveDBOperator.HiveQueryResult result = HiveDBOperator.query(tbDBS.getName(), sql);
            listResult.setResult(result.getResult());
            listResult.setTotal(Long.valueOf(String.valueOf(result.getResult().size())));
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException("查询失败", HttpCode.CODE_500);
        }
        return listResult;
    }


}
