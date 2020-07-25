package com.biz.grade.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.biz.grade.config.DBContract;
import com.biz.grade.persistence.domain.ScoreDTO;
import com.biz.grade.persistence.domain.ScoreVO;

public abstract class ScoreServiceV1 {
	
	protected Connection conn = null;
	
	protected void connection() {
		// DB 연결로 conn 초기화하기
		try {
			Class.forName(DBContract.CONNECTION.JdbcDriver);
			conn = DriverManager.getConnection(
					DBContract.CONNECTION.URL,
					DBContract.CONNECTION.USER,
					DBContract.CONNECTION.PASSWORD);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public abstract List<ScoreVO> selectAll();
	public abstract ScoreVO findById(long id);
	
	public abstract int insert(ScoreDTO scDTO);
	public abstract int update(ScoreDTO scDTO);
	public abstract int delete(long id);

}
