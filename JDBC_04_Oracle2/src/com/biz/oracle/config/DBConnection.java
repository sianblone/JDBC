package com.biz.oracle.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	private static Connection conn = null;
	
	static {
		
		String jdbcDriver = "oracle.jdbc.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "user4";
		String password = "user4";
		
		// JDBC 드라이버를 메모리에 load하여 사용 준비
		try {
			Class.forName(jdbcDriver);
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("DB Connected!");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Connection getConn() {
		return conn;
	}

}
