package com.yourBatis.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbUtil {

	private static final String URL = "jdbc:mysql://127.0.0.1:3306/mybatis?useSSL=false&serverTimezone=GMT%2B8";
	private static final String USER = "xzlin";
	private static final String PASSWORD = "xzlin";

	static {
		// 静态代码块类加载之后才会执行，只执行一次
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 创建一个数据库连接（TCP 套接字）
	 * 
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, USER, PASSWORD);
	}

	/**
	 * 释放资源
	 * 
	 * @param conn 连接
	 * @param stat 语句
	 * @param rs   结果集
	 * @throws SQLException
	 */
	public static void close(Connection conn, Statement stat, ResultSet rs) throws SQLException {
		if (rs != null)
			rs.close();
		if (stat != null)
			stat.close();
		if (conn != null)
			conn.close();

	}

}
