package com.jspider.one_to_one.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class Person {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false,unique = true)
	private String email;

	@OneToOne
	private Aadhar aadhar;
	@Override
	public String toString() {
	    return "Person{" +
	            "id=" + id +
	            ", name='" + name + '\'' +
	            ", email='" + email + '\'' +
	            " , aadharId ="+(aadhar!=null?aadhar.getId():null)+
	            ", aadharNumber=" + (aadhar != null ? aadhar.getAadharNumber():null) +
	            
	            '}';
	}

	
}
