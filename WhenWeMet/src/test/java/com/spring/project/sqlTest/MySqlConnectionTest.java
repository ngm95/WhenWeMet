package com.spring.project.sqlTest;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

public class MySqlConnectionTest {
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/SPRING?serverTimezone=UTC";
	private static final String USER = "spring";
	private static final String PASSWORD = "project";
	
	@Test
	public void testConnection() throws Exception {
		Class.forName(DRIVER);
		try {
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println(conn);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
