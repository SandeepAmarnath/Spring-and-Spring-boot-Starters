package com.luv2code.springboot.cruddemo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springboot.cruddemo.entity.Employee;

@Repository
public class EmployeeDAOHibernateImpl implements EmployeeDAO {

	
	// define field for entity manager
	private EntityManager entityManager;
	
	
	// setup a field for construction injection
	
	@Autowired
	public EmployeeDAOHibernateImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}
	
	@Override
	public List<Employee> findAll() {

		// get the current hibernate session
		
		Session currentSession = entityManager.unwrap(Session.class);
		
		// create a query
		Query<Employee> theQuery = currentSession.createQuery("from Employee",Employee.class);
		
		
		// get the result set
		
		List<Employee> employees = theQuery.getResultList();
		
		
		// return the result
		
		
		return employees;
	}

	@Override
	public Employee findById(int theId) {
		
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		// get employee
		Employee theEmployee = currentSession.get(Employee.class,theId);
		
		return theEmployee;
		
	}

	@Override
	public void save(Employee theEmployee) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.saveOrUpdate(theEmployee);
	}

	@Override
	public void deleteById(int theId) {
		Session currentSession = entityManager.unwrap(Session.class);
		Query theQuery = currentSession.createQuery("delete from Employee where id=:employeeId");
		theQuery.setParameter("employeeId", theId);
		theQuery.executeUpdate();
		
	}

}
