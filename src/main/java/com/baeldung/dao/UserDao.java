package com.baeldung.dao;


import com.baeldung.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserDao {

    @Select("select * from t_user")
    List<Map<String, Object>> selectUser();

    @Select("select * from t_user where id =#{id}")
    Map<String, Object> selectUserById(Integer id);

    @Insert("INSERT INTO t_user (name, email) VALUES (#{name}, #{email})")
    Integer insertUser(User user);

    @Update("UPDATE `t_user` SET `name`= #{name}, `email`=#{email} WHERE `id`=#{id}")
    Integer updateUser(User user);

    @Delete("delete from `t_user` WHERE `id`=#{id}")
    Integer deleteUser(Integer id);

}