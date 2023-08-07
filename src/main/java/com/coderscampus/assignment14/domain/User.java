package com.coderscampus.assignment14.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity // Class name = User, DB Table name = user
@Table(name = "users")
public class User {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long userId;
//		added column 7/30
//		@Column(nullable = false,unique = true)
		private String username;
		private String password;
		private String name;
		
		
		public Long getUserId() {
			return userId;
		}
		public void setUserId(Long userId) {
			this.userId = userId;
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
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		@Override
		public String toString() {
			return "User [userId=" + userId + ", username=" + username + ", password=" + password + ", name=" + name
					+ "]";
		}
		
		
	}

