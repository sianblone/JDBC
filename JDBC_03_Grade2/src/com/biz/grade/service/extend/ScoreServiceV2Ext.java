package com.biz.grade.service.extend;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.biz.grade.config.DBContract;
import com.biz.grade.persistence.domain.ScoreDTO;
import com.biz.grade.persistence.domain.ScoreVO;
import com.biz.grade.service.ScoreServiceV2;

public class ScoreServiceV2Ext extends ScoreServiceV2 {

	@Override
	public List<ScoreVO> selectAll() {
		PreparedStatement ps = null;
		String sql = DBContract.SQL.SELECT_VIEW_SCORE;
		List<ScoreVO> scList = new ArrayList<>();
		try {
			ps = conn.prepareStatement(sql);
			ResultSet resultSet = ps.executeQuery();
			
			
			while(resultSet.next()) {
				scList.add(this.resultSetToScoreVO(resultSet));
			}
			resultSet.close();
			ps.close();
//			프로젝트가 끝날 때까지 사용할 것이므로 conn은 닫지 않는다
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return scList;
	}
	
	private ScoreVO resultSetToScoreVO (ResultSet resultSet) throws SQLException {
		ScoreVO scoreVO = ScoreVO.builder()
				.s_id(resultSet.getLong("S_ID"))
				.s_score(resultSet.getInt("S_SCORE"))
				.s_std(resultSet.getString("S_STD"))
				.s_sub(resultSet.getString("S_SUB"))
				.sb_name(resultSet.getString("sb_name"))
				.st_dept(resultSet.getString("st_dept"))
				.st_grade(resultSet.getInt("st_grade"))
				.st_name(resultSet.getString("st_name"))
				.d_name(resultSet.getString("d_name"))
				.d_tel(resultSet.getString("d_tel"))
				.build();
		return scoreVO;
	}
	
//	stName 변수를 매개변수로 받아서 학생이름으로 검색한 다음 List로 return
	@Override
	public List<ScoreVO> findByStName(String stName) {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		String sql = DBContract.SQL.SELECT_VIEW_SCORE;
		sql += " where st_name = ? ";
		List<ScoreVO> scList = new ArrayList<>();
		
		try {
			
			ps = conn.prepareCall(sql);
			ps.setString(1, stName);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				scList.add(this.resultSetToScoreVO(rs));
			}
			rs.close();
			ps.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return scList;
	}

	@Override
	public ScoreVO findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(ScoreDTO scDTO) {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		String sql = DBContract.SQL.INSERT_SCORE;
		try {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, scDTO.getS_id());
			ps.setString(2, scDTO.getS_std());
			ps.setString(3, scDTO.getS_sub());
			ps.setInt(4, scDTO.getS_score());
			ps.setString(5, scDTO.getS_rem());
			
//			CRUD 중 Read는 excuteQuery()를 사용하며 이 메소드는 ResultSet 형을 반환한다
//			Create, Update, Delete는 excuteUpdate() method를 사용하며 이 메소드는 int형을 반환한다
//			쿼리가 정상적으로 수행되면 intReturn은 0보다 큰 값을 갖는다
			int intReturn = ps.executeUpdate();
			System.out.println(intReturn);
			
			ps.close();
			return intReturn;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int update(ScoreDTO scDTO) {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		String sql = " update table tbl_score set ? = ? where ? = ? ";
		try {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, scDTO.getS_id());
			ps.setLong(2, scDTO.getS_id());
			ps.setLong(3, scDTO.getS_id());
			ps.setLong(4, scDTO.getS_id());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}

	@Override
	public int delete(long id) {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		String sql = " delete from tbl_score where s_id = ? ";
		try {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, id);
			int ret = ps.executeUpdate();
			
			ps.close();
			return ret;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return 0;
	}

	/*
	 * 학번을 매개변수로 받아서 성적리스트 return
	 */
	@Override
	public List<ScoreVO> findByStNum(String strNum) {
		PreparedStatement ps = null;
		String sql = DBContract.SQL.SELECT_VIEW_SCORE;
		sql += " where s_std = ? ";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, strNum);
			
			ResultSet rset = ps.executeQuery();
			
			List<ScoreVO> scList = new ArrayList<>();
			while(rset.next()) {
				scList.add(this.resultSetToScoreVO(rset));
			}
			rset.close();
			ps.close();
			return scList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}

	@Override
	public List<ScoreVO> findByStSub(String strSub) {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		String sql = DBContract.SQL.SELECT_VIEW_SCORE;
		sql += " where s_sub = ? ";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, strSub);
			
			ResultSet rset = ps.executeQuery();
			
			List<ScoreVO> scList = new ArrayList<>();
			while(rset.next()) {
				scList.add(this.resultSetToScoreVO(rset));
			}
			rset.close();
			ps.close();
			return scList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
