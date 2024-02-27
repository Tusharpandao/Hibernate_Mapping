package com.jspiders.onetoone_by.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.jspiders.onetoone_by.dto.Aadhar;
import com.jspiders.onetoone_by.dto.Person;

public class Insert {

	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;
	private static EntityTransaction entityTransaction;

	public static void main(String[] args) {

		openConnection();
		entityTransaction.begin();

		Person person = new Person();
		person.setName("tushar");
		person.setEmail("tushar@gmail.com");

		Aadhar aadharCard = new Aadhar();
		aadharCard.setAadharnumber(9876512321221l);
		aadharCard.setPerson(person);
		entityManager.persist(aadharCard);

		person.setAadharCard(aadharCard);
		entityManager.persist(person);

		entityTransaction.commit();
		closeConnection();

	}

	private static void openConnection() {

		entityManagerFactory = Persistence.createEntityManagerFactory("company");
		entityManager = entityManagerFactory.createEntityManager();
		entityTransaction = entityManager.getTransaction();

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

	
	
	
}
