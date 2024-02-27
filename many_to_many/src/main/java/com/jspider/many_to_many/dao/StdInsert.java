package com.jspider.many_to_many.dao;

import java.util.Arrays;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.jboss.jandex.Main;

import com.jspider.many_to_many.dto.Course;
import com.jspider.many_to_many.dto.Student;

public class StdInsert {
	
	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;
	private static EntityTransaction entityTransaction;
	
	private static void openConnection() {
		
		entityManagerFactory =Persistence.createEntityManagerFactory("student");
		entityManager=entityManagerFactory.createEntityManager();
		entityTransaction=entityManager.getTransaction();
		
	}
	private static void closeConnection() {
		
		if (entityManagerFactory !=null) {
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
		
		Student student1 = new Student();
		student1.setName("tushar");
		student1.setEmail("tushar@gmail.com");
		
		Student student2 = new Student();
		student2.setName("rupesh");
		student2.setEmail("rupesh@gmail.com");
		
		Student student3 = new Student();
		student3.setName("kartik");
		student3.setEmail("kartik@gmail.com");
		
		Course sql=new Course();
		sql.setName("sql");
		sql.setPrice(1200.21);
		
		Course java=new Course();
		java.setName("java");
		java.setPrice(1500.21);
		
		Course webTech=new Course();
		webTech.setName("Web Tech");
		webTech.setPrice(2500.21);
		
		student1.setCourse(Arrays.asList(sql,java));
		student2.setCourse(Arrays.asList(webTech,java));
		student3.setCourse(Arrays.asList(java,sql));
		openConnection();
		entityTransaction.begin();
		
		entityManager.persist(student1);
		entityManager.persist(student2);
		entityManager.persist(student3);
		
		entityTransaction.commit();
		closeConnection();
	}
	
	
	
	
	
	
	
	

}
