package com.rrmsbackend.mapper;

import com.rrmsbackend.eneity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("select * from user where username = #{text} or email = #{text}")
    User findAdminByUsernameOrEmail(String text);

    @Insert("insert into user (username , password , email) values(#{username} , #{password} , #{email})")
    int createUser(String username, String password, String email);
}
