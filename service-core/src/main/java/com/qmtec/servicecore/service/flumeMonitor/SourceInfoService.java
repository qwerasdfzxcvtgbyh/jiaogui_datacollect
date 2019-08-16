package com.qmtec.servicecore.service.flumeMonitor;

import com.qmtec.common.page.ListResult;
import com.qmtec.servicecore.entity.SourceInfo;
import com.qmtec.servicecore.model.dto.SourceInfoDto;

public interface SourceInfoService {

    //分页查询
    ListResult<SourceInfoDto> listSelectSoureceInfo(String searchParams, int page, int limit ,int type);

    //通过contextId修改状态
    void updateSoureceStateByContextId(Long contextId,Integer runstate);

    //获取：状态“正在运行”的监控内容
    SourceInfo getRunSoureceByContextId(Long contextId);
}
