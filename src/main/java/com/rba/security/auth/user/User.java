package com.rba.security.auth.user;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.rba.security.auth.role.Role;



@Entity
@Table(name = "user")
public class User{
	
	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "username",unique = true,nullable = false)
	private String username;
	@Column(name = "password",unique = true,nullable = false)
	private String password;
	
	@Column(name="isAccountNonExpired",nullable = false)
	private boolean isAccountNonExpired;
	
	@Column(name="isAccountNonLocked",nullable = false)
	private boolean isAccountNonLocked;
	
	@Column(name="isCredentialsNonExpired",nullable = false)
	private boolean isCredentialsNonExpired;
	
	@Column(name="isEnabled",nullable = false)
	private boolean isEnabled;

	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinTable(name = "user_roles",
				joinColumns = @JoinColumn(name = "user_id"),
				inverseJoinColumns = @JoinColumn(name  = "role_id")
	)
	private Set<Role> roles = new HashSet<Role>();

	
	
	
	
	public User(String username, String password, boolean isAccountNonExpired, boolean isAccountNonLocked,
			boolean isCredentialsNonExpired, boolean isEnabled, Set<Role> roles) {
		super();
		this.username = username;
		this.password = password;
		this.isAccountNonExpired = isAccountNonExpired;
		this.isAccountNonLocked = isAccountNonLocked;
		this.isCredentialsNonExpired = isCredentialsNonExpired;
		this.isEnabled = isEnabled;
		this.roles = roles;
	}

	public User() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public boolean isAccountNonExpired() {
		return isAccountNonExpired;
	}

	public boolean isAccountNonLocked() {
		return isAccountNonLocked;
	}

	public boolean isCredentialsNonExpired() {
		return isCredentialsNonExpired;
	}

	public boolean isEnabled() {
		return isEnabled;
	}

	public Set<Role> getRoles() {
		return roles;
	}
	
	
	
	

}
