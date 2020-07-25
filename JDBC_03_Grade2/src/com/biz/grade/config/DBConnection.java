package com.biz.grade.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	private static Connection conn = null;

//	static 생성자 : static { }
//	프로젝트가 시작됨과 동시에 JVM에 의해 자동으로 실행되는,
//	클래스와 무관한 전역(전체에서 접근 가능한) 생성자 method
	static {
		// DB 연결로 conn 초기화하기
		try {
			Class.forName(DBContract.CONNECTION.JdbcDriver);
			conn = DriverManager.getConnection(DBContract.CONNECTION.URL, DBContract.CONNECTION.USER,
					DBContract.CONNECTION.PASSWORD);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	프로젝트가 시작하면서 생성된 conn(연결객체, 인스턴스)를 필요할 때 가져가는 통로 method
//	conn 변수를 직접 접근하지 않고 getter method를 호출해서 가져간다
//	singletone 기법과 유사함
	public static Connection getConn() {
		return conn;
	}

}
