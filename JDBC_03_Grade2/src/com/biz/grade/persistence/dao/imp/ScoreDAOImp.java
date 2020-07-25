package com.biz.grade.persistence.dao.imp;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.biz.grade.config.DBContract;
import com.biz.grade.persistence.dao.ScoreDAO;
import com.biz.grade.persistence.domain.ScoreDTO;
import com.biz.grade.persistence.domain.ScoreVO;

public class ScoreDAOImp extends ScoreDAO {

	@Override
	public List<ScoreVO> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ScoreVO findById(long id) {
		// TODO Auto-generated method stub
		String sql = DBContract.SQL.SELECT_VIEW_SCORE;
		sql += " where s_id = ? ";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			ScoreVO scVO = null;
			if(rs.next()) { // while이 아닌 if : PK이므로 한 개 밖에 없다 
				scVO = this.rs_2_VO(rs);
			}
			rs.close();
			ps.close();
			return scVO;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}

	@Override
	public List<ScoreVO> findByStName(String stName) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private ScoreVO rs_2_VO(ResultSet rs) throws SQLException {
		return ScoreVO.builder()
				.s_id(rs.getLong("S_ID"))
				.s_score(rs.getInt("S_SCORE"))
				.s_std(rs.getString("S_STD"))
				.s_sub(rs.getString("S_SUB"))
				.sb_name(rs.getString("sb_name"))
				.st_dept(rs.getString("st_dept"))
				.st_grade(rs.getInt("st_grade"))
				.st_name(rs.getString("st_name"))
				.d_name(rs.getString("d_name"))
				.d_tel(rs.getString("d_tel"))
				.build();
	}

	@Override
	public int insert(ScoreDTO scDTO) {
		// TODO Auto-generated method stub
		String sql = DBContract.SQL.INSERT_SCORE;
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, scDTO.getS_id());
			ps.setString(2, scDTO.getS_std());
			ps.setString(3, scDTO.getS_sub());
			ps.setInt(4, scDTO.getS_score());
			ps.setString(5, scDTO.getS_rem());
			
			int intReturn = ps.executeUpdate();
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
		String sql = " update tbl_score set "
				+ " s_score = ?,"
				+ " s_rem = ?,"
				+ " s_sub = ?,"
				+ " s_std = ? where s_id = ? ";
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1,scDTO.getS_score());
			ps.setString(2,scDTO.getS_rem());
			ps.setString(3,scDTO.getS_sub());
			ps.setString(4,scDTO.getS_std());
			ps.setLong(5,scDTO.getS_id());
			
			int ret = ps.executeUpdate();
			ps.close();
			return ret;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}

	@Override
	public int delete(long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ScoreVO> findByStNum(String strNum) {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		String sql = DBContract.SQL.SELECT_VIEW_SCORE;
		sql += " where s_std = ? ";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, strNum);
			
			ResultSet rset = ps.executeQuery();
			
			List<ScoreVO> scList = new ArrayList<>();
			while(rset.next()) {
				scList.add(this.rs_2_VO(rset));
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
		return null;
	}

}
