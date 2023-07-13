package com.rrmsbackend.mapper;

import com.rrmsbackend.eneity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdminMapper {
    @Select("select * from admin where aname = #{text}")
    Admin findAdminByAname(String text);
}
