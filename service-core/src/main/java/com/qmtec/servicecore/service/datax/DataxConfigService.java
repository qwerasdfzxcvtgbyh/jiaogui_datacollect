package com.qmtec.servicecore.service.datax;

import com.qmtec.common.page.ListResult;
import com.qmtec.servicecore.model.bo.DataxConfigBo;
import com.qmtec.servicecore.model.dto.DataxConfigDto;
import com.qmtec.servicecore.model.vo.DataxConfigVo;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

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

    /**-----------------------------------------------------------**/

    DataxConfigBo selectOneDataxConfigById(DataxConfigVo dataxConfigVo);

    Boolean updateDataXConfigByIdAndRunstate(DataxConfigVo dataxConfigVo);

    Boolean receiveCheckDataXConfigById(@RequestBody DataxConfigVo dataxConfigVo);

    Boolean pushCheckDataXConfigById(String id);

    Map<String, Object> startDataX(String data);

    Map<String, Object> restartDataX(String data);

    Map<String, Object> stopDataX(String data);

    Boolean updateDataXConfigByProcesspidAndRunstateAndId(DataxConfigVo dataxConfigVo);

    DataxConfigBo queryPartialFieldsDataXConfigById(DataxConfigVo dataxConfigVo);

    List<DataxConfigBo> queryDataXConfigByServerIpAndRunstate(DataxConfigVo dataxConfigVo);

}
