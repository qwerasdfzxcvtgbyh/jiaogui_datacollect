package com.qmtec.servicecore.service.colum;

import com.qmtec.common.page.ListResult;
import com.qmtec.servicecore.entity.ColumnInfo;
import com.qmtec.servicecore.model.dto.ColumInfoDto;

import java.util.List;
import java.util.Map;

public interface ColumService {

    /**
     * 添加 、修改
     *
     * @param data
     * @return
     */
    Map<String,Object> addOrUpdateColumInfo(String data);

    /**
     * 分页查询
     *
     * @param searchParams
     * @param page
     * @param limit
     * @return
     */
    ListResult<ColumInfoDto> listSelectColumInfo(String searchParams, int page, int limit);


    /**
     * 跳转修改页前处理
     *
     * @param id
     * @return
     */
    ColumInfoDto updateColumInfoBefore(String id);

    /**
     * 删除文件模板
     *
     * @param data
     * @return
     */
    Boolean deleteColumInfo(String data);


    /**
     *
     * @param id
     * @return
     */
    ColumInfoDto getColumInfoById(String id);

    ColumnInfo getColumInfoByName(String name);

    List<ColumInfoDto> getColumnInfoByIds(List<String> list);

}
