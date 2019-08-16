package com.qmtec.servicecore.service.flileTemplate.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qmtec.common.exception.CustomException;
import com.qmtec.common.page.ListResult;
import com.qmtec.common.web.HttpCode;
import com.qmtec.servicecore.dao.FileTemplateMapper;
import com.qmtec.servicecore.entity.FileTemplate;
import com.qmtec.servicecore.entity.example.FileTemplateExample;
import com.qmtec.servicecore.model.dto.FileTemplateDto;
import com.qmtec.servicecore.service.flileTemplate.FileTemplateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

@Slf4j
@Service
public class FileTemplateServiceImpl implements FileTemplateService {

    @Autowired
    private FileTemplateMapper fileTemplateMapper;

    /**
     * 添加FileTemplate的Dao操作
     */
    private int addFileTemplateCore(FileTemplateDto fileTemplateDto, String id) {
        return fileTemplateMapper.insertSelective(FileTemplate
                        .builder()
                        .id(id)
                        .name(fileTemplateDto.getName())
                        .code(fileTemplateDto.getCode())
                        .fileConfigContent(fileTemplateDto.getFileConfigContent())
                        .remark(fileTemplateDto.getRemark())
                        .createTime(new Date())
                        .modifyTime(new Date())
                        .filetype(fileTemplateDto.getFiletype())
                        .build()
                , FileTemplate.Column.id
                , FileTemplate.Column.name
                , FileTemplate.Column.code
                , FileTemplate.Column.fileConfigContent
                , FileTemplate.Column.remark
                , FileTemplate.Column.createTime
                , FileTemplate.Column.modifyTime
                , FileTemplate.Column.filetype
        );
    }

    /**
     * 修改FlumeConfig的Dao操作
     */
    private int updateaddFileTemplateCore(FileTemplateDto fileTemplateDto) {
        return fileTemplateMapper.updateByExampleSelective(FileTemplate
                        .builder()
                        .fileConfigContent(fileTemplateDto.getFileConfigContent())
                        .remark(fileTemplateDto.getRemark())
                        .modifyTime(new Date())
                        .build(),
                FileTemplateExample.newAndCreateCriteria()
                        .andIdEqualTo(fileTemplateDto.getId()).example()
                , FileTemplate.Column.fileConfigContent
                , FileTemplate.Column.remark
                , FileTemplate.Column.modifyTime
        );
    }


    @Override
    public Boolean addOrUpdateFlumeTemplate(String data) {
        Boolean falg = false;

        try {
            FileTemplateDto fileTemplateDto = JSONObject.parseObject(data, FileTemplateDto.class);
            if (StringUtils.isEmpty(fileTemplateDto.getId())) {

                FileTemplate fileTemplate = this.getFileTemplateByCode(fileTemplateDto.getCode());
                if (fileTemplate != null) {
                    throw new CustomException("code : " + fileTemplate.getCode() + "Existing");
                }

                String id = UUID.randomUUID().toString().trim().replaceAll("-", "");
                if (this.addFileTemplateCore(fileTemplateDto, id) > 0) {
                    falg = true;
                }
            } else {//修改

                FileTemplate fileTemplate = this.getFileTemplateByCode(fileTemplateDto.getCode());
                if (fileTemplate != null && !fileTemplate.getId().equals(fileTemplateDto.getId())) {
                    throw new CustomException("code : " + fileTemplate.getCode() + "Used");
                }

                if (this.updateaddFileTemplateCore(fileTemplateDto) > 0) {
                    falg = true;
                }
            }
        } catch (Exception e) {
            log.debug("add fail : [{}] ", e.getMessage());
            throw new CustomException("add fail : " + e.getMessage(), HttpCode.CODE_500);
        }
        return falg;
    }

    /**
     * 通过Code获取
     */
    private FileTemplate getFileTemplateByCode(String code) {
        FileTemplate fileTemplate = null;

        Optional<FileTemplate> fileTemplateOptional = null;
        try {
            fileTemplateOptional = Optional.ofNullable(fileTemplateMapper.selectOneByExample(
                    FileTemplateExample.newAndCreateCriteria()
                            .andCodeEqualTo(code)
                            .example()
            ));

            if (fileTemplateOptional.isPresent()) {
                fileTemplate = fileTemplateOptional.get();
            }

        } catch (Exception e) {
            e.printStackTrace();
            log.error("Data Query Failure >>> [{}]", JSON.toJSONString(e.getMessage()));
            throw new CustomException("Data Query Failure", HttpCode.CODE_500);
        }

        return fileTemplate;
    }


    /**
     * 分页查询
     *
     * @param searchParams
     * @param page
     * @param limit
     * @return
     */
    @Override
    public ListResult<FileTemplateDto> listSelectFileTemplate(String searchParams, int page, int limit) {
        ListResult<FileTemplateDto> page1 = new ListResult<FileTemplateDto>();

        Map<String, Object> paramMap = JSONObject.parseObject(searchParams, Map.class);

        Optional<List<FileTemplate>> flumeConfigOptional = null;

        FileTemplateExample.Criteria criteria = FileTemplateExample.newAndCreateCriteria();
        if (paramMap != null) {

            String name = (String) paramMap.get("name");
            if (!StringUtils.isEmpty(name)) {
                criteria.andNameLike("%" + name + "%");
            }

            String code = (String) paramMap.get("code");
            if (!StringUtils.isEmpty(code)) {
                //criteria.andCodeEqualTo(code);
                criteria.andCodeLike("%" + code + "%");
            }

            String filetype = (String)paramMap.get("filetype");
            if (!StringUtils.isEmpty(filetype)) {
                Integer filetype2 = Integer.valueOf(filetype);
                //criteria.andCodeEqualTo(code);
                criteria.andFiletypeEqualTo(filetype2);
            }

        }

        FileTemplateExample fileTemplateExample = criteria.example();
        fileTemplateExample.page((page - 1), limit);
        fileTemplateExample.setOrderByClause("create_time desc");

        flumeConfigOptional = Optional.ofNullable(fileTemplateMapper.selectByExample(fileTemplateExample));
        if (flumeConfigOptional.isPresent()) {

            List<FileTemplate> flumeConfigList = flumeConfigOptional.get();
            if (flumeConfigList.size() > 0) {
                long count = fileTemplateMapper.countByExample(criteria.example());
                page1.setTotal(count);

                List<FileTemplateDto> fileTemplateDtoList = new ArrayList<FileTemplateDto>();

                flumeConfigList.forEach(fileTemplate -> {
                    FileTemplateDto fileTemplateDto = FileTemplateDto.builder()
                            .id(fileTemplate.getId())
                            .name(fileTemplate.getName())
                            .code(fileTemplate.getCode())
                            .createTime(fileTemplate.getCreateTime())
                            .modifyTime(fileTemplate.getModifyTime())
                            .build();

                    FileTemplate.Filetype[] filetypes = FileTemplate.Filetype.values();
                    for (FileTemplate.Filetype filetype : filetypes) {
                        if (filetype.getValue().equals(fileTemplate.getFiletype())) {
                            fileTemplateDto.setFiletype(filetype.getValue());
                            fileTemplateDto.setFiletypename(filetype.getName());
                            break;
                        }
                    }

                    fileTemplateDtoList.add(fileTemplateDto);
                });

                page1.setResult(fileTemplateDtoList);
            } else {
                page1.setMessage("no data");
            }
        } else {
            throw new CustomException("Data does not exist", HttpCode.CODE_400);
        }
        return page1;
    }

    @Override
    public FileTemplateDto updateFileTemplateBefore(String id) {
        FileTemplateDto fileTemplateDto = null;
        Optional<FileTemplate> fileTemplateOptional = null;
        try {
            fileTemplateOptional = Optional.ofNullable(
                    fileTemplateMapper.selectOneByExampleWithBLOBs(
                            FileTemplateExample.newAndCreateCriteria().andIdEqualTo(id).example())
            );

            if (fileTemplateOptional.isPresent()) {
                FileTemplate fileTemplate = fileTemplateOptional.get();
                fileTemplateDto = FileTemplateDto.builder()
                        .id(fileTemplate.getId())
                        .name(fileTemplate.getName())
                        .code(fileTemplate.getCode())
                        .fileConfigContent(fileTemplate.getFileConfigContent())
                        .filetype(fileTemplate.getFiletype())
                        .remark(fileTemplate.getRemark())
                        .build();
            } else {
                throw new CustomException("Data does not exist", HttpCode.CODE_400);
            }

        } catch (Exception e) {
            e.printStackTrace();
            log.error("Data Query Failure >>> [{}]", JSON.toJSONString(e.getMessage()));
            throw new RuntimeException("Data Query Failure");
        }
        return fileTemplateDto;
    }

    @Override
    public Boolean deleteFileTemplate(String data) {
        Boolean falg = false;
        try {
            List<String> ids = (List<String>) JSONArray.parseObject(data, List.class);
            if (ids.size() > 0) {
                Optional<List<FileTemplate>> fileTemplateList = Optional.ofNullable(
                        fileTemplateMapper.selectByExample(
                                FileTemplateExample.newAndCreateCriteria().andIdIn(ids).example()
                        )
                );

                if (fileTemplateList.isPresent()) {
                    /*List<FileTemplate> fileTemplates = fileTemplateList.get();
                    for (FileTemplate fileTemplate : fileTemplates) {


                    }*/

                    if (fileTemplateMapper.deleteByExample(
                            FileTemplateExample.newAndCreateCriteria().andIdIn(ids).example()) > 0) {
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
    public List<FileTemplateDto> SelectAllFileTemplate() {
        List<FileTemplateDto> listResult = new ArrayList<FileTemplateDto>();
        Optional<List<FileTemplate>> optionalFileTemplateList = null;
        try {
            optionalFileTemplateList = Optional.ofNullable(
                    fileTemplateMapper.selectByExample(FileTemplateExample.newAndCreateCriteria().example())
            );

            if (optionalFileTemplateList.isPresent()) {
                List<FileTemplate> fileTemplates = optionalFileTemplateList.get();
                fileTemplates.forEach(fileTemplate -> {
                    FileTemplateDto fileTemplateDto = FileTemplateDto.builder()
                            .id(fileTemplate.getId())
                            .name(fileTemplate.getName())
                            .code(fileTemplate.getCode())
                            .build();
                    listResult.add(fileTemplateDto);
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Data Query Failure >>> [{}]", JSON.toJSONString(e.getMessage()));
            throw new RuntimeException("Data Query Failure");
        }
        return listResult;
    }

    @Override
    public List<FileTemplateDto> getFileTemplateByFileType(Integer fileType) {
        List<FileTemplateDto> listResult = new ArrayList<FileTemplateDto>();
        Optional<List<FileTemplate>> optionalFileTemplateList = null;
        try {
            optionalFileTemplateList = Optional.ofNullable(
                    fileTemplateMapper.selectByExample(
                            FileTemplateExample.newAndCreateCriteria()
                                    .andFiletypeEqualTo(fileType)
                                    .example())
            );

            if (optionalFileTemplateList.isPresent()) {
                List<FileTemplate> fileTemplates = optionalFileTemplateList.get();
                fileTemplates.forEach(fileTemplate -> {
                    FileTemplateDto fileTemplateDto = FileTemplateDto.builder()
                            .id(fileTemplate.getId())
                            .name(fileTemplate.getName())
                            .code(fileTemplate.getCode())
                            .build();
                    listResult.add(fileTemplateDto);
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Data Query Failure >>> [{}]", JSON.toJSONString(e.getMessage()));
            throw new RuntimeException("Data Query Failure");
        }
        return listResult;
    }

    @Override
    public String getFileTemplateContentById(String id) {
        String content = null;

        Optional<FileTemplate> optionalFileTemplate = null;
        try {
            optionalFileTemplate = Optional.ofNullable(
                    fileTemplateMapper.selectOneByExampleWithBLOBs(FileTemplateExample.newAndCreateCriteria()
                            .andIdEqualTo(id)
                            .example())
            );

            if (optionalFileTemplate.isPresent()) {
                FileTemplate fileTemplate = optionalFileTemplate.get();
                content = fileTemplate.getFileConfigContent();
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Data Query Failure >>> [{}]", JSON.toJSONString(e.getMessage()));
            throw new RuntimeException("Data Query Failure");
        }
        return content;
    }
}
