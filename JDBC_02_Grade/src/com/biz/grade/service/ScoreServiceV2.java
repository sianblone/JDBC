package com.biz.grade.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.biz.grade.persistence.ScoreDTO;
import com.biz.grade.utils.DBContract;

public class ScoreServiceV2 extends ScoreService {

	@Override
	public int insert(ScoreDTO sDTO) {
		// TODO Auto-generated method stub
		this.dbConnection();
		PreparedStatement pps = null;
		String sql = " insert into tbl_score( ";
		sql += " S_ID," + 
				" S_STD," + 
				" S_SUB," + 
				" S_SCORE," + 
				" S_REM)" +
				" values(?,?,?,?,?) ";
		
		try {
			pps = dbConn.prepareStatement(sql);
			pps.setLong(1, sDTO.getS_id());
			pps.setString(2, sDTO.getS_std());
			pps.setString(3, sDTO.getS_subject());
			pps.setInt(4, sDTO.getS_score());
			pps.setString(5, sDTO.getS_rem());
			
//			만약 insert가 정상적으로 수행되면 ret > 0
//			그렇지 않으면 0
//			간혹 < 0 의 값이 나타나는 경우도 있다
			int ret = pps.executeUpdate();
//			ResultSet이 아닌 int로 받을 수 있는 이유
//			CRUD중 Read를 제외한 C,U,D는 값이 int형이며
//			Read만 ResultSet형이다
			
			dbConn.close();
			return ret;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<ScoreDTO> selectAll() {
		// TODO Auto-generated method stub
		
		this.dbConnection(); // dbConn 생성
		PreparedStatement pStr = null;
//		String sql = DBContract.SQL.SCORE_SELECT;
//		sql += DBContract.TABLE.SCORE;
		
		try {
			pStr = dbConn.prepareStatement(DBContract.SQL.SCORE_SELECT + DBContract.TABLE.SCORE);
			ResultSet rst = pStr.executeQuery();
			List<ScoreDTO> scoreList = new ArrayList<>();
			
			/*
			 * ResultSet으로부터 데이터를 getter할 때
			 * 칼럼의 위치값(숫자)으로 사용하던 것을
			 * 자바 일정 버전 이후부터 칼럼의 이름으로 사용할 수 있다
			 */
			
			while(rst.next()) {
				scoreList.add(ScoreDTO.builder()
						.s_id(rst.getLong("s_id"))
						.s_std(rst.getString("s_std"))
						.s_subject(rst.getString("s_sub"))
						.s_score(rst.getInt("s_score"))
						.s_rem(rst.getString("s_rem"))
						.build()
						);
			}
			rst.close();
			dbConn.close();
			return scoreList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public ScoreDTO findById(long id) {
		// TODO Auto-generated method stub
		this.dbConnection();
		PreparedStatement pStr = null;
		
		String sql = DBContract.SQL.SCORE_SELECT;
		sql += " where s_id = ? ";
		
		try {
			pStr = dbConn.prepareStatement(sql);
//			PreparedStatement 클래스의 setLong(a, b), setInt(a, b), setString(a, b), .. 메소드
//			prepareStatement(sql)에 들어가는 sql값에 a번째 물음표 대신 b값을 넣는다
			pStr.setLong(1, id);
			ScoreDTO scoreDTO = null;
			ResultSet rst = pStr.executeQuery();
			if(rst.next()) {
				scoreDTO = ScoreDTO.builder()
						.s_id(rst.getLong("s_id"))
						.s_std(rst.getString("s_std"))
						.s_score(rst.getInt("s_score"))
						.s_subject(rst.getString("s_sub"))
						.s_rem(rst.getString("s_rem"))
						.build();
			}
			rst.close();
			dbConn.close();
			return scoreDTO;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return null;
	}

	@Override
	public List<ScoreDTO> findByName(String name) {
		// TODO Auto-generated method stub
		this.dbConnection();
		PreparedStatement pStr = null;
		
		String sql = DBContract.SQL.SCORE_SELECT;
		sql += " where s_std = ? ";
		
		try {
			pStr = dbConn.prepareStatement(sql);
			pStr.setString(1, name);
			ResultSet rst = pStr.executeQuery();
			
			List<ScoreDTO> scoreList = new ArrayList<>();
			while (rst.next()) {
				scoreList.add(
						ScoreDTO.builder()
						.s_id(rst.getLong("s_id"))
						.s_std(rst.getString("s_std"))
						.s_subject(rst.getString("s_sub"))
						.s_score(rst.getInt("s_score"))
						.s_rem(rst.getString("s_rem"))
						.build()
						);
			}
			rst.close();
			dbConn.close();
			return scoreList;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<ScoreDTO> findBySubject(String subject) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(ScoreDTO scoreDTO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(long id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
