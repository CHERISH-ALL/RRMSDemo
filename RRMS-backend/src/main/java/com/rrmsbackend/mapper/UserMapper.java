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

    @Select("select * from user where id = #{id}")
    Account findAccountFromUserById(long id);

    @Select("select * from admin where id = #{id}")
    Account findAccountFromAdminById(long id);

    @Select("select * from teacher where id = #{id}")
    Account findAccountFromTeacherById(long id);

    @Select("select * from student where id = #{id}")
    Account findAccountFromStudentById(long id);

    @Insert("insert into user (id , identity , username , password , email) values(#{id} , #{identity} , #{username} , #{password} , #{email})")
    int createAccount(long id, String identity, String username, String password, String email);

    @Update("update user set password = #{password} where email = #{email}")
    int resetPasswordByEmail(String password, String email);
}
