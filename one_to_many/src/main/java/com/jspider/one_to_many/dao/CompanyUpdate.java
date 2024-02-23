package com.jspider.one_to_many.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.jspider.one_to_many.dto.CompanyDTO;
import com.jspider.one_to_many.dto.EmployeeDTO;

public class CompanyUpdate {

	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;
	private static EntityTransaction entityTransaction;
	
private static void openConnection() {
		
		entityManagerFactory =Persistence.createEntityManagerFactory("company");
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
		
		
		EmployeeDTO employee = entityManager.find(EmployeeDTO.class, 8);
		employee.setMobile(7262944490l);
		
		entityManager.persist(employee);
		
		
		entityTransaction.commit();
		closeConnection();
	}
	
	
	
	
	
}
