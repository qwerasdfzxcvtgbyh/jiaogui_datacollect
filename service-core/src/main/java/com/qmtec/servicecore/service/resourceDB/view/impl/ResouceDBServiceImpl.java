package com.qmtec.servicecore.service.resourceDB.view.impl;

import com.qmtec.common.page.ListResult;
import com.qmtec.common.web.PageResultModel;
import com.qmtec.servicecore.model.dto.ResourceDbDto;
import com.qmtec.servicecore.service.resourceDB.view.ResouceDBService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ResouceDBServiceImpl implements ResouceDBService {

    @Override
    public ListResult<ResourceDbDto> listSelectResouceDB(String searchParams, int page, int limit) {
        return null;
    }
}
