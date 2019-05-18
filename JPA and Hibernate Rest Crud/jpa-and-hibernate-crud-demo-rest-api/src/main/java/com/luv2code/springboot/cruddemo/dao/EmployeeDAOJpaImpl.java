package com.luv2code.springboot.cruddemo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springboot.cruddemo.entity.Employee;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO {

	@Autowired
	private EntityManager entityManager;

	@Override
	public List<Employee> findAll() {

		// create query
		Query theQuery = entityManager.createQuery("from Employee");

		// execute query
		List<Employee> employees = theQuery.getResultList();

		// return results
		return employees;
	}

	@Override
	public Employee findById(int theId) {
		

		// get employee
		Employee theEmployee = entityManager.find (Employee.class, theId);

		return theEmployee; 
	}

	@Override
	public void save(Employee theEmployee) {
		
		Employee dbEmployee = entityManager.merge(theEmployee);
		
		theEmployee.setId(dbEmployee.getId());
		
		// If you observe here we are doing theEmployee.setId and the same is not done in hibernate.
		// This is because, in hibernate instance, theemployee parameter we pass is also updated by saveOrUpdate
		// method in its lifecycle, where as in entitymanager this doesnt happen because theEmployee actually
		// should not be affected because its an extenal parameter and entityManager has no right to touch it.
		// Though its easy in hibernate, I feel entityManger does a good job by not touching the parameter we
		// pass. 
		
		// For better understanding..... Hibernate instance:
		//     Once user sends parameter theEmployee into RestController, its send to service layer and then 
		// when it comes to DAO layer, the saveOrupdate() method automatically sets the id of parameter theEmployee
		// and when we return it from DAO to service to Rest to Client, he now has the id set
		
		
		// For better understanding..... EntityManager instance:
		//     Once user sends parameter theEmployee into RestController, its send to service layer and then 
		// when it comes to DAO layer, the merge() method DOES NOT automatically sets the id of parameter theEmployee
		// and when we return it from DAO to service to Rest to Client, he now DOES NOT have the id set. 
		// For this reason, for the client to have the id set we explicitly do it after the merge() method in DAO

		
	}

	@Override
	public void deleteById(int theId) {

		
		Query theQuery = entityManager.createQuery("delete from Employee where id=:employeeId");
		theQuery.setParameter("employeeId", theId);
		theQuery.executeUpdate();
		
	}

}
