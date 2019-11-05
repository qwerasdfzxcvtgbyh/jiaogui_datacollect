package com.qmtec.servicecore.service.dataModel;

import com.qmtec.common.page.ListResult;
import com.qmtec.servicecore.model.dto.DataModelDto;

import java.util.Map;

public interface DataModelService {

    /**
     * 添加 、修改
     *
     * @param data
     * @return
     */
    Boolean addOrUpdateDataMode(String data);

    /**
     * 分页查询
     *
     * @param searchParams
     * @param page
     * @param limit
     * @return
     */
    ListResult<DataModelDto> listSelectDataMode(String searchParams, int page, int limit);


    /**
     * 跳转修改页前处理
     *
     * @param id
     * @return
     */
    DataModelDto updateDataModeBefore(String id);

    /**
     * 删除文件模板
     *
     * @param data
     * @return
     */
    Boolean deleteDataMode(String data);

    DataModelDto getDataModeById(String id);

}
