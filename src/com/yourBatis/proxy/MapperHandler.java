package com.yourBatis.proxy;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yourBatis.anno.Select;
import com.yourBatis.entity.User;
import com.yourBatis.util.DbUtil;

public class MapperHandler implements InvocationHandler {

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Object object = null;
		Annotation[] annos = method.getAnnotations();
		if (annos != null) {
			for (Annotation annotation : annos) {
				String type = annotation.annotationType().getSimpleName();
				System.out.println("type: " + type);
				System.out.println(method.getReturnType());
				switch (type) {
				case "Select":
					object = select(method.getAnnotation(Select.class).value(), null, method.getReturnType());
					break;
				case "Insert":
					object = select(method.getAnnotation(Select.class).value(), null, method.getReturnType());
					break;
				default:
					break;
				}
			}
		}
		return object;
	}

	private static Object select(String sql, Object[] objects, Class r)
			throws InstantiationException, IllegalAccessException {

		try {
			Object o = r.getDeclaredConstructor().newInstance();
		} catch (IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		ArrayList<User> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet rs = null;
		try {
			conn = DbUtil.getConnection();
			stat = conn.prepareStatement(sql);

			rs = stat.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setAge(rs.getString("age"));
				list.add(user);
//				System.out.println(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			DbUtil.close(conn, stat, rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
//		Method[] methods = r.getDeclaredMethods();
//		for (Method method : methods) {
//			method.invoke(o, 1);
//		}

		return list;
	}
	
	
}
