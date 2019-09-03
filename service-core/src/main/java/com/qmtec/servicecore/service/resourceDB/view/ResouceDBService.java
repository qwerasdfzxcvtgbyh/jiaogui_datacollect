package com.qmtec.servicecore.service.resourceDB.view;

import com.qmtec.common.page.ListResult;
import com.qmtec.common.web.PageResultModel;
import com.qmtec.servicecore.model.dto.ResourceDbDto;

public interface ResouceDBService {

    ListResult<ResourceDbDto> listSelectResouceDB(String searchParams, int page, int limit);

}
