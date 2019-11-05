package com.qmtec.agent.comm;

import org.springframework.stereotype.Component;

/**
 * 获取自定义配置常量类
 */
@Component
public class Constant {

    //DataX json文件后缀
    public static final String DATAX_JSON_SUFFIX = ".json";

    //DataX python脚本后缀
    public static final String DATAX_PYTHON_SUFFIX = ".py";

    //启动脚本的日志输出路径
    public static final String DATAX_ETLLOG = "etl_log";

}
