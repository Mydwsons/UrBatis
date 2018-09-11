package com.yourBatis.mapper;

import java.util.ArrayList;
import java.util.List;

import com.yourBatis.anno.Insert;
import com.yourBatis.anno.Mapper;
import com.yourBatis.anno.Select;
import com.yourBatis.entity.User;

@Mapper
public interface UserMapper {
	@Insert("INSERT INTO user(name,age) VALUES(#{name},#{age})")
	void add(User user);
	
	@Select("SELECT * FROM user")
	ArrayList<User> find();
}
