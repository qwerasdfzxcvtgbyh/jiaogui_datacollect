package com.qmtec.servicecore.service.flumeMonitor;

import com.qmtec.common.page.ListResult;
import com.qmtec.servicecore.entity.SinkInfo;
import com.qmtec.servicecore.model.dto.SinkInfoDto;

public interface SinkInfoService {

    //分页查询
    ListResult<SinkInfoDto> listSelectSinkInfo(String searchParams,int page, int limit ,int type);

    //通过contextId修改状态
    void updateSinkStateByContextId(Long contextId,Integer runstate);

    //获取：状态“正在运行”的监控内容
    SinkInfo getRunSinkByContextId(Long contextId);
}
