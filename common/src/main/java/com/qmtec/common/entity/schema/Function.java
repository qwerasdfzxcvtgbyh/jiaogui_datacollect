package com.qmtec.common.entity.schema;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by chiqin on 2018/12/6
 * 函数查询实体
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Function {
    /**
     * 主键
     */
    private Integer id;
    /**
     * 函数名
     */
    private String name;
    /**
     * 函数描述
     */
    private String description;
    /**
     * 表达式
     */
    private String expression;
    /**
     * 示例
     */
    private String example;
    /**
     * 函数参数数据类型
     */
    private String datatype;
    /**
     * 函数类型,base为基础函数，costom为自定义函数
     */
    private String ftype;
}
