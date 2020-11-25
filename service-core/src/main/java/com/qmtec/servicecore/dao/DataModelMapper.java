package com.qmtec.servicecore.dao;

import com.qmtec.servicecore.entity.DataModel;
import com.qmtec.servicecore.entity.example.DataModelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DataModelMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_dataModel
     *
     * @mbg.generated
     */
    long countByExample(DataModelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_dataModel
     *
     * @mbg.generated
     */
    int deleteByExample(DataModelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_dataModel
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_dataModel
     *
     * @mbg.generated
     */
    int insert(DataModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_dataModel
     *
     * @mbg.generated
     */
    int insertSelective(@Param("record") DataModel record, @Param("selective") DataModel.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_dataModel
     *
     * @mbg.generated
     */
    DataModel selectOneByExample(DataModelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_dataModel
     *
     * @mbg.generated
     */
    DataModel selectOneByExampleSelective(@Param("example") DataModelExample example, @Param("selective") DataModel.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_dataModel
     *
     * @mbg.generated
     */
    List<DataModel> selectByExampleSelective(@Param("example") DataModelExample example, @Param("selective") DataModel.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_dataModel
     *
     * @mbg.generated
     */
    List<DataModel> selectByExample(DataModelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_dataModel
     *
     * @mbg.generated
     */
    DataModel selectByPrimaryKeySelective(@Param("id") String id, @Param("selective") DataModel.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_dataModel
     *
     * @mbg.generated
     */
    DataModel selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_dataModel
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") DataModel record, @Param("example") DataModelExample example, @Param("selective") DataModel.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_dataModel
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") DataModel record, @Param("example") DataModelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_dataModel
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(@Param("record") DataModel record, @Param("selective") DataModel.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_dataModel
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(DataModel record);
}