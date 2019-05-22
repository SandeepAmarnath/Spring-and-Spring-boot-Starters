package com.mycode.springbootsecuritydemo.dao;

import org.springframework.stereotype.Repository;

import com.mycode.springbootsecuritydemo.model.Role;

public interface RoleDao {

	public Role findRoleByName(String theRoleName);
	
}
