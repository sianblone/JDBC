package com.biz.jdbc.exec;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcEx_01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hello 오라클");
		String jdbcDriver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String userName = "grade";
		String password = "grade";
		
		try {
//			jdbcDriver(oracle.jdbc.driver.OracleDriver.class 파일)을 사용할 수 있도록 JVM에 등록(loading)하라는 지시
			Class.forName(jdbcDriver);
			Connection dbConn = null;
			dbConn = DriverManager.getConnection(url,userName,password);
			System.out.println("Connecton Done!");
			
//			DBMS에게 SQL 전달 시작
//			JDBC와 우리의 App간의 데이터 교환이 이루어지는 영역
			PreparedStatement pStr = null;
			
//			연결된 통로를 통해서 SQL문을 전달하고 그 결과를 pStr 변수에 받는다
			String sql = " select * from tbl_score ";
			pStr = dbConn.prepareStatement(sql);
			
//			sql(select)문을 실행하도록 지시하고
//			select 된 결과를 ResultSet에 받아라
			ResultSet rst = pStr.executeQuery();
			/*
			 * 이제 rst 객체에는 DB로 읽어온 리스트가 ResultSet이라는 데이터 type으로 저장되어 있는 것이다
			 * 이 ResultSet은 일종의 Iterator처럼 취급할 수 있다
			 * 
			 *  rst.next() method가 한 번 실행될 때마다 데이터 리스트의 앞쪽부터
			 *  한 개씩 꺼내서 읽을 수 있도록 준비한다
			 *  
			 *  또 rst.next() method가 실행되면 현재 위치의 다음 번 리스트 요소를 읽을 수 있도록 준비
			 *  
			 *  마지막 리스트를 모두 읽을 후 또 rst.next()가 실행되면 false를 return하여 while을 끝내도록 해준다
			 */
			while(rst.next()) {
//				읽을 준비가 된 리스트 요소의 1번 칼럼의 값을 꺼내라
				System.out.print(rst.getString(1) + "\t");
				
//				저장된 데이터 칼럼의 type이 숫자형이면 숫자 get() method로 읽을 수 있다
				System.out.print(rst.getInt(1) + "\t");
				System.out.print(rst.getLong(1) + "\t");
				
//				읽을 준비가 된 리스트 요소의 2번 칼럼의 값을 꺼내라
				System.out.print(rst.getString(2) + "\t");
//				읽을 준비가 된 리스트 요소의 3번 칼럼의 값을 꺼내라
				System.out.print(rst.getString(3) + "\n");
			}
			rst.close();
			dbConn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
