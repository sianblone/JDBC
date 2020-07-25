package com.biz.grade.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import com.biz.grade.persistence.StudentDTO;
import com.biz.grade.utils.DBContract;

public abstract class StudentService {
	
	protected Connection dbConn = null;
	
	protected void dbConnection() {
		try {
			Class.forName(DBContract.DbConn.JdbcDriver);
			dbConn = DriverManager.getConnection(
					DBContract.DbConn.URL, DBContract.DbConn.USER, DBContract.DbConn.PASSWORD);
			System.out.println("DB Connected");
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("DB Connect Failed");
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("DB Connect Failed");
			e.printStackTrace();
		}
	}
	
//	CRUD
	public abstract int insert(StudentDTO studentDTO);
//	모든 레코드
//	1개 이상의 레코드
	public abstract List<StudentDTO> selectAll();
	
//	id값을 매개변수로 받아서 1개의 레코드만 조회하는 method
//	id값은 DB에서 PK이므로 리턴값이 무조건 1개이다
//	즉 리스트<DTO>에 따로 담아줄 필요 없이 DTO 한개만 받으면 된다
	public abstract StudentDTO findById(String id);
	public abstract List<StudentDTO> findByName(String name);
	public abstract List<StudentDTO> findBySubject(String subject);
	
	public abstract int update(StudentDTO studentDTO);
	public abstract int delete(long id);

}
