package com.baeldung.service;

import com.baeldung.dao.UserDao;
import com.baeldung.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserDao userDao;
//    查询所有
    public List<Map<String,Object>> findUser(){
        return userDao.selectUser();
    }
//    根据id查询用户信息
    public Map<String,Object> findUserById(Integer id){
        return userDao.selectUserById(id);
    }
//    新增一个用户
    public String addUser(User user){
        return userDao.insertUser(user) > 0 ? "新增成功" : "新增失败";
    }
//    修改用户
    public String updateUser(User user){
        return userDao.updateUser(user) > 0 ? "修改成功" : "修改失败";
    }
//    删除用户
    public String delUser(Integer id){
        return userDao.deleteUser(id) > 0 ? "删除成功" : "删除成功";
    }
//
//    public List<Map<String, Object>> findUser() {
//        return userDao.selectUser();
//    }
//
//    public Map<String, Object> findUserById(Integer id) {
//        return userDao.selectUserById(id);
//    }
//
//    public String addUser(User user) {
//        Integer insert = userDao.insertUser(user);
//        if (insert > 0) {
//            return "新增成功!";
//        }
//        return "新增失败!";
//    }
//
//    public String updateUser(User user) {
//        Integer update = userDao.updateUser(user);
//        if (update > 0) {
//            return "修改成功!";
//        }
//        return "修改失败!";
//    }
//
//
//    public String delUser(Integer id) {
//        Integer delete = userDao.deleteUser(id);
//        if (delete > 0) {
//            return "删除成功!";
//        }
//        return "删除失败!";
//    }
}
