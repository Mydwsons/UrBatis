package com.yourBatis;

import java.lang.reflect.Proxy;
import java.util.List;

import com.yourBatis.entity.User;
import com.yourBatis.mapper.UserMapper;
import com.yourBatis.mapper.UserMapperImpl;
import com.yourBatis.proxy.MapperHandler;

public class App {
	public static void main(String[] args) {
		UserMapper userMapper = new UserMapperImpl();
		MapperHandler handler = new MapperHandler();
		userMapper = (UserMapper) Proxy.newProxyInstance(
				userMapper.getClass().getClassLoader(), 
				userMapper.getClass().getInterfaces(), 
				handler);
//		userMapper.add(new User());
		List<User> list = userMapper.find();
		System.out.println(list);
	}
}
