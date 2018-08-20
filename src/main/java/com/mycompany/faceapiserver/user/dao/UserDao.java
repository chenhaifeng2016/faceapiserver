package com.mycompany.faceapiserver.user.dao;

import com.mycompany.faceapiserver.group.model.Group;
import com.mycompany.faceapiserver.user.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.dao.DataAccessException;

import java.util.List;

@Mapper
public interface UserDao {

    @Insert("insert into face.user(UserID, Phone, GroupID) values(#{userID}, #{phone}, #{groupID})")
    int insertUser(User user) throws DataAccessException;

    @Select("select ID, UserID, Phone, GroupID, status, CreateTime, UpdateTime from face.user")
    List<User> selectAll();

    @Select("select ID, UserID, Phone, GroupID, status, CreateTime, UpdateTime from face.user where UserID=#{userid_phone_email} or Phone=#{userid_phone_email}")
    User login(@Param("userid_phone_email") String userid_phone_email, @Param("password") String password);


    @Select("select u.ID, u.UserID, u.Phone, u.GroupID, u.status, u.CreateTime, u.UpdateTime from face.user u, face.group g where u.GroupID=g.GroupID and g.status=0 and u.UpdateTime > #{updateTime}")
    List<User> getUpdatedUsers(@Param("updateTime") String updateTime);

    @Select("select u.ID, u.UserID, u.Phone, u.GroupID, u.status, u.CreateTime, u.UpdateTime from face.user u, face.group g where u.GroupID=g.GroupID and g.status=0")
    List<User> getAllUsers();

    @Update("update face.user set status=1 where GroupID=#{groupID}")
    int delUserByGroupID(@Param("groupID") String groupID);

}
