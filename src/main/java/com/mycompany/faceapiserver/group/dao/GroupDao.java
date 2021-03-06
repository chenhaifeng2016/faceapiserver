package com.mycompany.faceapiserver.group.dao;

import com.mycompany.faceapiserver.group.model.Group;
import org.apache.ibatis.annotations.*;
import org.springframework.dao.DataAccessException;

import java.util.List;

@Mapper
public interface GroupDao {

    @Insert("insert into face.group(GroupID) values(#{groupID})")
    int createGroup(Group group) throws DataAccessException;


    @Select("select GroupID, status, CreateTime, UpdateTime from face.group")
    List<Group> selectAll();

    @Select("select ID, GroupID, status, CreateTime, UpdateTime from face.group where UpdateTime > #{updateTime}")
    List<Group> getUpdatedGroups(@Param("updateTime") String updateTime);

    @Update("upate face.group set status=1 where GroupID=#{groupID}")
    int delGroup(@Param("groupID") String groupID);
}
