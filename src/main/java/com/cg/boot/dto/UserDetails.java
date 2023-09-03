package com.cg.boot.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
/*
create table user_details(username varchar(30) primary key, password varchar(50), userrole varchar(30));
insert into user_details(username,password,userrole) values('divya','div123','admin');
insert into user_details(username,password,userrole) values('jansi','jan123','customer');
 */
@Entity
@Table(name = "user_details")
public class UserDetails {
	@Id
	private String username;
	private String password;
	@Column(name = "userrole")
	private String userRole;
	public UserDetails() {
	}
	public UserDetails(String username, String password, String userRole) {
		super();
		this.username = username;
		this.password = password;
		this.userRole = userRole;
	}
	@Override
	public String toString() {
		return "UserDetails [username=" + username + ", password=" + password + ", userRole=" + userRole + "]";
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	
}
