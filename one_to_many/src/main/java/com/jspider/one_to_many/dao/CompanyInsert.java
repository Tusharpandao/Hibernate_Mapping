package com.jspider.one_to_many.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.jspider.one_to_many.dto.CompanyDTO;
import com.jspider.one_to_many.dto.EmployeeDTO;

public class CompanyInsert {
	
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
		
		
		 
		CompanyDTO company=new CompanyDTO();
		company.setName("Mahindra");
		company.setLocation("Mumbai");
		
		EmployeeDTO employee1=new EmployeeDTO();
		employee1.setName("Ankit");
		employee1.setEmail("ankit@gmail.com");
		employee1.setMobile(7262944441l);
		
		EmployeeDTO employee2=new EmployeeDTO();
		employee2.setName("Mrunali");
		employee2.setEmail("Mrunali@gmail.com");
		employee2.setMobile(9223326462l);

		EmployeeDTO employee3=new EmployeeDTO();
		employee3.setName("Prachi");
		employee3.setEmail("Prachi@gmail.com");
		employee3.setMobile(9825416451l);

		
		
		List<EmployeeDTO> employees=new ArrayList<EmployeeDTO>();
		employees.add(employee1);
		employees.add(employee2);
		employees.add(employee3);
	
		
		company.setEmployees(employees);
		
		openConnection();
		entityTransaction.begin();
		entityManager.persist(employee1);
		entityManager.persist(employee2);
		entityManager.persist(employee3);
		entityManager.persist(company);
		entityTransaction.commit();
		closeConnection();
	}
	
	
	
	
	
}
