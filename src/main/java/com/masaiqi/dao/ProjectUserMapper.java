package com.masaiqi.dao;

import com.masaiqi.entity.ProjectUser;
import com.masaiqi.entity.ProjectUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectUserMapper {
    long countByExample(ProjectUserExample example);

    int deleteByExample(ProjectUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProjectUser record);

    int insertSelective(ProjectUser record);

    List<ProjectUser> selectByExample(ProjectUserExample example);

    ProjectUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProjectUser record, @Param("example") ProjectUserExample example);

    int updateByExample(@Param("record") ProjectUser record, @Param("example") ProjectUserExample example);

    int updateByPrimaryKeySelective(ProjectUser record);

    int updateByPrimaryKey(ProjectUser record);
}