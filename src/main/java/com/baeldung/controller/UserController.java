package com.baeldung.controller;

import com.baeldung.entity.User;
import com.baeldung.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
/*@RequestMapping("/api/user")*/
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/findUser")
    public List<Map<String, Object>> findUser() {
        return userService.findUser();
    }

    @GetMapping("/findUserById")
    public Map<String, Object> findUserById(Integer id) {
        return userService.findUserById(id);
    }

    @GetMapping("/addUser")
    public String addUser(User user) {
        return userService.addUser(user);
    }

    @GetMapping("/updateUser")
    public String updateUser(User user) {
        return userService.updateUser(user);
    }

    @GetMapping("/delUser")
    public String delUser(Integer id) {
        return userService.delUser(id);
    }

}
