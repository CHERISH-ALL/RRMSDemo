package com.rrmsbackend.mapper;

import com.rrmsbackend.eneity.auth.Account;
import com.rrmsbackend.eneity.user.AccountUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {
    @Select("select * from user where username = #{text} or email = #{text}")
    Account findAccountByNameOrEmail(String text);

    @Select("select * from user where username = #{text} or email = #{text}")
    AccountUser findAccountUserByNameOrEmail(String text);

    @Insert("insert into user (username , password , email) values(#{username} , #{password} , #{email})")
    int createAccount(String username, String password, String email);

    @Update("update user set password = #{password} where email = #{email}")
    int resetPasswordByEmail(String password, String email);
}
