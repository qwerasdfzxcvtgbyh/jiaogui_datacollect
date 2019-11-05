package com.qmtec.servicecore.service.flumeMonitor;

import com.qmtec.common.page.ListResult;
import com.qmtec.servicecore.entity.ChannelInfo;
import com.qmtec.servicecore.model.dto.ChannelInfoDto;

public interface ChannleInfoService {

    //分页查询
    ListResult<ChannelInfoDto> listSelectChannelInfo(String searchParams,int page, int limit ,int type);

    //通过contextId修改状态
    void updateChanelStateByContextId(Long contextId,Integer runstate);

    //获取：状态“正在运行”的监控内容
    ChannelInfo getRunChannelByContextId(Long contextId);

}
