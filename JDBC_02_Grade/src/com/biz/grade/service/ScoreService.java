package com.biz.grade.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import com.biz.grade.persistence.ScoreDTO;
import com.biz.grade.utils.DBContract;

public abstract class ScoreService {
	
	protected Connection dbConn = null;
	
	protected void dbConnection() {
		try {
			Class.forName(DBContract.DbConn.JdbcDriver);
			dbConn = DriverManager.getConnection(
					DBContract.DbConn.URL, DBContract.DbConn.USER, DBContract.DbConn.PASSWORD);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	CRUD
	public abstract int insert(ScoreDTO scoreDTO);
//	모든 레코드
//	1개 이상의 레코드
	public abstract List<ScoreDTO> selectAll();
	
//	id값을 매개변수로 받아서 1개의 레코드만 조회하는 method
//	id값은 DB에서 PK이므로 리턴값이 무조건 1개이다
//	즉 리스트<DTO>에 따로 담아줄 필요 없이 DTO 한개만 받으면 된다
	public abstract ScoreDTO findById(long id);
	public abstract List<ScoreDTO> findByName(String name);
	public abstract List<ScoreDTO> findBySubject(String subject);
	
	public abstract int update(ScoreDTO scoreDTO);
	public abstract int delete(long id);

}
