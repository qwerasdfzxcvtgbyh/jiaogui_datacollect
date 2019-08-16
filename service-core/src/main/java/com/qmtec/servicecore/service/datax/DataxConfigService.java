package com.qmtec.servicecore.service.datax;

import com.qmtec.common.page.ListResult;
import com.qmtec.servicecore.model.dto.DataxConfigDto;

public interface DataxConfigService {

    /**
     * 添加 、修改
     *
     * @param data
     * @return
     */
    Boolean addOrUpdateDataxConfig(String data);

    /**
     * 分页查询
     *
     * @param searchParams
     * @param page
     * @param limit
     * @return
     */
    ListResult<DataxConfigDto> listSelectDataxConfig(String searchParams, int page, int limit);


    /**
     * 跳转修改页前处理
     *
     * @param id
     * @return
     */
    DataxConfigDto updateDataxConfigBefore(String id);

    /**
     * 删除文件模板
     *
     * @param data
     * @return
     */
    Boolean deleteDataxConfig(String data);

}
