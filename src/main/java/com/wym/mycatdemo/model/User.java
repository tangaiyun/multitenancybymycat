package com.wym.mycatdemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	private Integer id;
	private String name;
	private String password;
	
	public User(String name, String password) {
		this.name = name;
		this.password  = password;
	}
}
