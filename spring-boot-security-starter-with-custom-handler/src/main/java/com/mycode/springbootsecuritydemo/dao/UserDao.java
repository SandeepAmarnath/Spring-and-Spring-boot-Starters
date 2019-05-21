package com.mycode.springbootsecuritydemo.dao;

import org.springframework.stereotype.Repository;

import com.mycode.springbootsecuritydemo.model.User;


public interface UserDao {

    User findByUserName(String userName);
    
    void save(User user);
    
}
