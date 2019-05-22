package com.mycode.springbootsecuritydemo.dao;

import javax.persistence.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mycode.springbootsecuritydemo.model.User;


@Repository
public class UserDaoImpl implements UserDao {

	// need to inject the session factory
	@Autowired
	private EntityManager entityManager;

	@Override
	public User findByUserName(String theUserName) {
	

		// now retrieve/read from database using username
		Query theQuery = entityManager.createQuery("from User where userName=:uName", User.class);
		theQuery.setParameter("uName", theUserName);
		User theUser = null;
		try {
			theUser = (User) theQuery.getSingleResult();
		} catch (Exception e) {
			theUser = null;
		}

		return theUser;
	}

	@Override
	public void save(User theUser) {
		

		// create the user ... finally LOL
		entityManager.merge(theUser);
	}

}
