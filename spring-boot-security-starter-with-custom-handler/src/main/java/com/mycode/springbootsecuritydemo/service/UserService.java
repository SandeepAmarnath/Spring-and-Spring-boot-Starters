package com.mycode.springbootsecuritydemo.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.mycode.springbootsecuritydemo.model.CrmUser;
import com.mycode.springbootsecuritydemo.model.User;

public interface UserService extends UserDetailsService {

    User findByUserName(String userName);

    void save(CrmUser crmUser);
}
