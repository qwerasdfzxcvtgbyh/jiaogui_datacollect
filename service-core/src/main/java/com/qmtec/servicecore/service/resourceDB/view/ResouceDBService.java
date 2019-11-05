package com.qmtec.servicecore.service.resourceDB.view;

import com.qmtec.common.page.ListResult;
import com.qmtec.common.tree.Tree;
import com.qmtec.dataSource.table.Element;
import com.qmtec.dataSource.table.Table;
import com.qmtec.servicecore.model.dto.ResourceDbDto;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

public interface ResouceDBService {

    ListResult<ResourceDbDto> listSelectResouceDB(String searchParams, int page, int limit);

    ResourceDbDto updateResourceDbBefore(String id);

    Boolean addOrUpdateResourceDb(String data );

    Boolean deleteResourceDb(String data );

    Boolean testConn(String data );


    List<Tree> getRelationDataBaseTableTree(String id);

    Table getTabInfo(String id ,String name);

    ListResult<Element> listSelectTableFieldInfo(String dbId,String tabName, int page, int limit);


    List<Tree> getHiveDataBaseTableTree(String id);

    ListResult<Map<String, Object>> listSelectHiveTableFieldInfo(String dbId, String tabName, int page, int limit);



    List<String> jumpDataPreview(String id,String name);

    ListResult<Map<String, Object>> listSelectTableDataPreview(String dbId,String tabName,int page, int limit);

}
