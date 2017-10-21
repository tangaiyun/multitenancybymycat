package com.wym.mycatdemo;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wym.mycatdemo.dao.UserDao;
import com.wym.mycatdemo.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringBootJdbcDemoApplication.class) // 指定spring-boot的启动类

public class SpringBootJdbcDemoApplicationTests {

	@Autowired
	private UserDao userDao;

	@Test
	public void findAllUsers() {
		List<User> users = userDao.findAll("JG1DB");
		System.out.println(users);

	}

	@Test
	public void findUserById() {
		User user = userDao.findUserById("JG2DB",1);
		System.out.println(user);
	}

	@Test
	public void updateById() {
		User user = userDao.findUserById("JG3DB",2);
		System.out.println(user);
		User newUser = new User(2, "JackChen", "JackChen@qq.com");
		userDao.update("JG3DB", newUser);
		User newUser2 = userDao.findUserById("JG3DB",newUser.getId());
		System.out.println(newUser2);
	}

	@Test
	public void createUser() {
		User user = new User("rose", "rose@gmail.com");
		User savedUser = userDao.create("JG4DB",user);
		user = userDao.findUserById("JG4DB",savedUser.getId());
		System.out.println(user);
	}
	
	@Test
	public void findAllUsers1() {
		List<User> users = userDao.findAll("JG5DB");
		System.out.println("----------------------------------"+users);

	}

}
