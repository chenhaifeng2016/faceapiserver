package com.mycompany.faceapiserver.user.dao;

import com.mycompany.faceapiserver.group.model.Group;
import com.mycompany.faceapiserver.user.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
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
}
