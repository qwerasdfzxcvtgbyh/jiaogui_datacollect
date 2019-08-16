package com.qmtec.servicecore.dao;

import com.qmtec.servicecore.entity.TbTableParams;
import com.qmtec.servicecore.entity.example.TbTableParamsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbTableParamsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TABLE_PARAMS
     *
     * @mbg.generated
     */
    long countByExample(TbTableParamsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TABLE_PARAMS
     *
     * @mbg.generated
     */
    int deleteByExample(TbTableParamsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TABLE_PARAMS
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(@Param("tblId") Long tblId, @Param("paramKey") String paramKey);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TABLE_PARAMS
     *
     * @mbg.generated
     */
    int insert(TbTableParams record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TABLE_PARAMS
     *
     * @mbg.generated
     */
    int insertSelective(@Param("record") TbTableParams record, @Param("selective") TbTableParams.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TABLE_PARAMS
     *
     * @mbg.generated
     */
    TbTableParams selectOneByExample(TbTableParamsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TABLE_PARAMS
     *
     * @mbg.generated
     */
    TbTableParams selectOneByExampleSelective(@Param("example") TbTableParamsExample example, @Param("selective") TbTableParams.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TABLE_PARAMS
     *
     * @mbg.generated
     */
    List<TbTableParams> selectByExampleSelective(@Param("example") TbTableParamsExample example, @Param("selective") TbTableParams.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TABLE_PARAMS
     *
     * @mbg.generated
     */
    List<TbTableParams> selectByExample(TbTableParamsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TABLE_PARAMS
     *
     * @mbg.generated
     */
    TbTableParams selectByPrimaryKeySelective(@Param("tblId") Long tblId, @Param("paramKey") String paramKey, @Param("selective") TbTableParams.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TABLE_PARAMS
     *
     * @mbg.generated
     */
    TbTableParams selectByPrimaryKey(@Param("tblId") Long tblId, @Param("paramKey") String paramKey);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TABLE_PARAMS
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") TbTableParams record, @Param("example") TbTableParamsExample example, @Param("selective") TbTableParams.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TABLE_PARAMS
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") TbTableParams record, @Param("example") TbTableParamsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TABLE_PARAMS
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(@Param("record") TbTableParams record, @Param("selective") TbTableParams.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TABLE_PARAMS
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(TbTableParams record);
}