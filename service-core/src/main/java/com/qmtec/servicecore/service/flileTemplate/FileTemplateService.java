package com.qmtec.servicecore.service.flileTemplate;

import com.qmtec.common.page.ListResult;
import com.qmtec.servicecore.model.dto.FileTemplateDto;

import java.util.List;

public interface FileTemplateService {

    /**
     * 添加 、修改
     *
     * @param data
     * @return
     */
    Boolean addOrUpdateFlumeTemplate(String data);

    /**
     * 分页查询
     *
     * @param searchParams
     * @param page
     * @param limit
     * @return
     */
    ListResult<FileTemplateDto> listSelectFileTemplate(String searchParams, int page, int limit);


    /**
     * 跳转修改页前处理
     *
     * @param id
     * @return
     */
    FileTemplateDto updateFileTemplateBefore(String id);

    /**
     * 删除文件模板
     *
     * @param data
     * @return
     */
    Boolean deleteFileTemplate(String data);

    /**
     * 查询数据，供Flume下拉
     *
     * @return
     */
    List<FileTemplateDto> SelectAllFileTemplate();

    List<FileTemplateDto> getFileTemplateByFileType(Integer fileType);


    String getFileTemplateContentById(String id);
}
