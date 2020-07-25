package com.biz.grade.persistence.dao.imp;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.biz.grade.config.DBContract;
import com.biz.grade.persistence.dao.SubDAO;
import com.biz.grade.persistence.domain.StudentDTO;
import com.biz.grade.persistence.domain.SubDTO;

public class SubDAOImp extends SubDAO {

	@Override
	public List<SubDTO> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SubDTO findById(String sb_code) {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		String sql = DBContract.SQL.SELECT_SUB;
		sql += " where sb_code = ? ";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, sb_code.toUpperCase());
			ResultSet rs = ps.executeQuery();
			
			SubDTO subDTO = null;
			if(rs.next()) {
				subDTO = this.rs_2_DTO(rs);
			}
			rs.close();
			ps.close();
			
			return subDTO;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	private SubDTO rs_2_DTO (ResultSet rs) throws SQLException {
		return SubDTO.builder()
				.sb_code(rs.getString("SB_CODE"))
				.sb_name(rs.getString("SB_NAME"))
				.sb_pro(rs.getString("SB_PRO"))
				.build();
	}

	@Override
	public List<SubDTO> findByName(int sb_name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(SubDTO subDTO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(SubDTO subDTO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(String sb_code) {
		// TODO Auto-generated method stub
		return 0;
	}

}
