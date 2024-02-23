package com.jspider.one_to_one.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.jspider.one_to_one.dto.Aadhar;
import com.jspider.one_to_one.dto.Person;

public class PersonDelete {

	
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
		if (entityManager !=null) {
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
		
		Person person = entityManager.find(Person.class, 2);
		Aadhar aadhar = person.getAadhar();

		entityManager.remove(person);
		entityManager.remove(aadhar);
		entityTransaction.commit();
		closeConnection();
	}
}







