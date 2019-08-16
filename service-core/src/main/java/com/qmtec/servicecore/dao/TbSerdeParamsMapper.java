package com.qmtec.servicecore.dao;

import com.qmtec.servicecore.entity.TbSerdeParams;
import com.qmtec.servicecore.entity.example.TbSerdeParamsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbSerdeParamsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SERDE_PARAMS
     *
     * @mbg.generated
     */
    long countByExample(TbSerdeParamsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SERDE_PARAMS
     *
     * @mbg.generated
     */
    int deleteByExample(TbSerdeParamsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SERDE_PARAMS
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(@Param("serdeId") Long serdeId, @Param("paramKey") String paramKey);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SERDE_PARAMS
     *
     * @mbg.generated
     */
    int insert(TbSerdeParams record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SERDE_PARAMS
     *
     * @mbg.generated
     */
    int insertSelective(@Param("record") TbSerdeParams record, @Param("selective") TbSerdeParams.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SERDE_PARAMS
     *
     * @mbg.generated
     */
    TbSerdeParams selectOneByExample(TbSerdeParamsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SERDE_PARAMS
     *
     * @mbg.generated
     */
    TbSerdeParams selectOneByExampleSelective(@Param("example") TbSerdeParamsExample example, @Param("selective") TbSerdeParams.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SERDE_PARAMS
     *
     * @mbg.generated
     */
    TbSerdeParams selectOneByExampleWithBLOBs(TbSerdeParamsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SERDE_PARAMS
     *
     * @mbg.generated
     */
    List<TbSerdeParams> selectByExampleSelective(@Param("example") TbSerdeParamsExample example, @Param("selective") TbSerdeParams.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SERDE_PARAMS
     *
     * @mbg.generated
     */
    List<TbSerdeParams> selectByExampleWithBLOBs(TbSerdeParamsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SERDE_PARAMS
     *
     * @mbg.generated
     */
    List<TbSerdeParams> selectByExample(TbSerdeParamsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SERDE_PARAMS
     *
     * @mbg.generated
     */
    TbSerdeParams selectByPrimaryKeySelective(@Param("serdeId") Long serdeId, @Param("paramKey") String paramKey, @Param("selective") TbSerdeParams.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SERDE_PARAMS
     *
     * @mbg.generated
     */
    TbSerdeParams selectByPrimaryKey(@Param("serdeId") Long serdeId, @Param("paramKey") String paramKey);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SERDE_PARAMS
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") TbSerdeParams record, @Param("example") TbSerdeParamsExample example, @Param("selective") TbSerdeParams.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SERDE_PARAMS
     *
     * @mbg.generated
     */
    int updateByExampleWithBLOBs(@Param("record") TbSerdeParams record, @Param("example") TbSerdeParamsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SERDE_PARAMS
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") TbSerdeParams record, @Param("example") TbSerdeParamsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SERDE_PARAMS
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(@Param("record") TbSerdeParams record, @Param("selective") TbSerdeParams.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SERDE_PARAMS
     *
     * @mbg.generated
     */
    int updateByPrimaryKeyWithBLOBs(TbSerdeParams record);
}