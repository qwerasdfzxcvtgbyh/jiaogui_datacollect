package com.qmtec.servicecore.dao;

import com.qmtec.servicecore.entity.ColumnInfo;
import com.qmtec.servicecore.entity.example.ColumnInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ColumnInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_columnInfo
     *
     * @mbg.generated
     */
    long countByExample(ColumnInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_columnInfo
     *
     * @mbg.generated
     */
    int deleteByExample(ColumnInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_columnInfo
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_columnInfo
     *
     * @mbg.generated
     */
    int insert(ColumnInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_columnInfo
     *
     * @mbg.generated
     */
    int insertSelective(@Param("record") ColumnInfo record, @Param("selective") ColumnInfo.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_columnInfo
     *
     * @mbg.generated
     */
    ColumnInfo selectOneByExample(ColumnInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_columnInfo
     *
     * @mbg.generated
     */
    ColumnInfo selectOneByExampleSelective(@Param("example") ColumnInfoExample example, @Param("selective") ColumnInfo.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_columnInfo
     *
     * @mbg.generated
     */
    List<ColumnInfo> selectByExampleSelective(@Param("example") ColumnInfoExample example, @Param("selective") ColumnInfo.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_columnInfo
     *
     * @mbg.generated
     */
    List<ColumnInfo> selectByExample(ColumnInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_columnInfo
     *
     * @mbg.generated
     */
    ColumnInfo selectByPrimaryKeySelective(@Param("id") String id, @Param("selective") ColumnInfo.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_columnInfo
     *
     * @mbg.generated
     */
    ColumnInfo selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_columnInfo
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") ColumnInfo record, @Param("example") ColumnInfoExample example, @Param("selective") ColumnInfo.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_columnInfo
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") ColumnInfo record, @Param("example") ColumnInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_columnInfo
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(@Param("record") ColumnInfo record, @Param("selective") ColumnInfo.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_columnInfo
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(ColumnInfo record);
}