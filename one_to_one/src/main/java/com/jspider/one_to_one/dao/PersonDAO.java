package com.jspider.one_to_one.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.jspider.one_to_one.dto.Aadhar;
import com.jspider.one_to_one.dto.Person;

public class PersonDAO {
	
	
	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;
	private static EntityTransaction entityTransaction;

	private static void openConnection() {
		
		entityManagerFactory =Persistence.createEntityManagerFactory("person");
		entityManager=entityManagerFactory.createEntityManager();
		entityTransaction=entityManager.getTransaction();
	}
	
	private static void closeConnection() {
		
		if (entityManagerFactory != null) {
			entityManagerFactory.close();
		}
		if (entityManager != null) {
			entityManager.close();
		}
		if (entityTransaction != null) {
			if (entityTransaction.isActive()) {
				entityTransaction.rollback();
			}
		}
	}
	
	public static void main(String[] args) {
		openConnection();
		entityTransaction.begin();
		Person person =new Person();
		person.setName("rupesh");
		person.setEmail("rupesh@gmail.com");
		
		Aadhar aadhar=new Aadhar();
		aadhar.setAadharNumber(93485242552345345l);
		aadhar.setPerson(person);
		entityManager.persist(aadhar);
		
		person.setAadhar(aadhar);
		entityManager.persist(person);
		
		entityTransaction.commit();
		closeConnection();
	}

	
}
