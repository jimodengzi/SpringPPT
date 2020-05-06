package com.baeldung.entity;


import java.io.Serializable;

public class User implements Serializable {
    private Integer id ;//int auto_increment comment '主键id' primary key,
    private String  name  ; //varchar(50) not null comment '姓名',
    private String  email  ;//varchar(50) null comment '邮箱'

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
