package com.rba.security.auth.role;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class Role {
	
	@Id
	@Column(name="role_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Id;
	
	@Column(name="role_name")
	private String name;

	
	
	public Integer getId() {
		return Id;
	}

	public String getName() {
		return name;
	}
	
	

	public void setId(Integer id) {
		Id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Role() {
		super();
	}
	
	public Role(String name) {
		this.name = name;
	}
	
	
	
}
