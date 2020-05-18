package com.allen.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.allen.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	// need to inject the session factory, name should match the bean id
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Customer> getCustomers() {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// create a query
		// Query<Customer> theQuery = currentSession.createQuery("from Customer", Customer.class);
		
		// create a query sort by last name
		Query<Customer> theQuery = currentSession.createQuery("from Customer order by lastName", Customer.class);
		
		// execute the query
		List<Customer> customers = theQuery.getResultList();
		
		// return the results
		return customers;
	}

	@Override
	public void saveCustomer(Customer theCustomer) {
		
		// get current session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// save the customer
		currentSession.save(theCustomer);
	}

}
