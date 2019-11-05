package com.qmtec.servicecore.dao;

import com.qmtec.servicecore.entity.TbIDXS;
import com.qmtec.servicecore.entity.example.TbIDXSExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbIDXSMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IDXS
     *
     * @mbg.generated
     */
    long countByExample(TbIDXSExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IDXS
     *
     * @mbg.generated
     */
    int deleteByExample(TbIDXSExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IDXS
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long indexId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IDXS
     *
     * @mbg.generated
     */
    int insert(TbIDXS record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IDXS
     *
     * @mbg.generated
     */
    int insertSelective(@Param("record") TbIDXS record, @Param("selective") TbIDXS.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IDXS
     *
     * @mbg.generated
     */
    TbIDXS selectOneByExample(TbIDXSExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IDXS
     *
     * @mbg.generated
     */
    TbIDXS selectOneByExampleSelective(@Param("example") TbIDXSExample example, @Param("selective") TbIDXS.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IDXS
     *
     * @mbg.generated
     */
    List<TbIDXS> selectByExampleSelective(@Param("example") TbIDXSExample example, @Param("selective") TbIDXS.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IDXS
     *
     * @mbg.generated
     */
    List<TbIDXS> selectByExample(TbIDXSExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IDXS
     *
     * @mbg.generated
     */
    TbIDXS selectByPrimaryKeySelective(@Param("indexId") Long indexId, @Param("selective") TbIDXS.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IDXS
     *
     * @mbg.generated
     */
    TbIDXS selectByPrimaryKey(Long indexId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IDXS
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") TbIDXS record, @Param("example") TbIDXSExample example, @Param("selective") TbIDXS.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IDXS
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") TbIDXS record, @Param("example") TbIDXSExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IDXS
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(@Param("record") TbIDXS record, @Param("selective") TbIDXS.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IDXS
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(TbIDXS record);
}