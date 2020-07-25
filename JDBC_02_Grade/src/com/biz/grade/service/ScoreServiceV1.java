package com.biz.grade.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.biz.grade.persistence.ScoreDTO;
import com.biz.grade.utils.DBContract;

public class ScoreServiceV1 extends ScoreService {

	@Override
	public int insert(ScoreDTO scoreDTO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ScoreDTO> selectAll() {
		// TODO Auto-generated method stub
		
		this.dbConnection(); // dbConn 생성
		PreparedStatement pStr = null;
		String sql = " select s_sid, s_std, s_sub, s_score, s_rem from ";
		sql += DBContract.TABLE.SCORE;
		
		try {
			pStr = dbConn.prepareStatement(sql);
			ResultSet rst = pStr.executeQuery();
			List<ScoreDTO> scoreList = new ArrayList<>();
			
			while(rst.next()) {
				scoreList.add(ScoreDTO.builder()
						.s_id(rst.getLong(1))
						.s_std(rst.getString(2))
						.s_subject(rst.getString(3))
						.s_score(rst.getInt(4))
						.s_rem(rst.getString(5))
						.build()
						);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public ScoreDTO findById(long id) {
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

	@Override
	public List<ScoreDTO> findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ScoreDTO> findBySubject(String subject) {
		// TODO Auto-generated method stub
		return null;
	}

}
