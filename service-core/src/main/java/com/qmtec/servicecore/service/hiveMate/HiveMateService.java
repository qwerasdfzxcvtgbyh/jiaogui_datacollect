package com.qmtec.servicecore.service.hiveMate;

import com.qmtec.common.page.ListResult;
import com.qmtec.common.tree.Tree;
import com.qmtec.servicecore.model.dto.hive.*;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

public interface HiveMateService {

    //获取菜单树
    List<Tree> getHiveTree();

    //获取数据库的基本信息
    DbInfoDto readDBInfo(String dbId);

    //获取表的基本信息
    TableBaseInfoDto readTableBaseInfo(String TabId);

    //查询表的字段信息
    ListResult<TableFieldInfoDto> listSelectTableFieldInfo(Long TabId,int page, int limit);

    //查询表的索引信息
    ListResult<TableIndexInfoDto> listSelectTableIndexInfo(Long TabId, int page, int limit);

    ListResult<TablePartitionInfoDto> listSelectTablePartitionInfo(String TabId);

    //获取表头
    List<TbColumnsDto> getColumnsDtoViewListByTableID(Long tableID);

    //表数据预览
    ListResult<Map<String, Object>> hiveDataPreview(Long TabId, int page, int limit);
}
