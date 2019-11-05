package com.qmtec.servicecore.dao;

import com.qmtec.servicecore.entity.SourceInfo;
import com.qmtec.servicecore.entity.example.SourceInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SourceInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_flume_sourcesInfo
     *
     * @mbg.generated
     */
    long countByExample(SourceInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_flume_sourcesInfo
     *
     * @mbg.generated
     */
    int deleteByExample(SourceInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_flume_sourcesInfo
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_flume_sourcesInfo
     *
     * @mbg.generated
     */
    int insert(SourceInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_flume_sourcesInfo
     *
     * @mbg.generated
     */
    int insertSelective(@Param("record") SourceInfo record, @Param("selective") SourceInfo.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_flume_sourcesInfo
     *
     * @mbg.generated
     */
    SourceInfo selectOneByExample(SourceInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_flume_sourcesInfo
     *
     * @mbg.generated
     */
    SourceInfo selectOneByExampleSelective(@Param("example") SourceInfoExample example, @Param("selective") SourceInfo.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_flume_sourcesInfo
     *
     * @mbg.generated
     */
    List<SourceInfo> selectByExampleSelective(@Param("example") SourceInfoExample example, @Param("selective") SourceInfo.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_flume_sourcesInfo
     *
     * @mbg.generated
     */
    List<SourceInfo> selectByExample(SourceInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_flume_sourcesInfo
     *
     * @mbg.generated
     */
    SourceInfo selectByPrimaryKeySelective(@Param("id") String id, @Param("selective") SourceInfo.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_flume_sourcesInfo
     *
     * @mbg.generated
     */
    SourceInfo selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_flume_sourcesInfo
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") SourceInfo record, @Param("example") SourceInfoExample example, @Param("selective") SourceInfo.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_flume_sourcesInfo
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") SourceInfo record, @Param("example") SourceInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_flume_sourcesInfo
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(@Param("record") SourceInfo record, @Param("selective") SourceInfo.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_flume_sourcesInfo
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(SourceInfo record);
}