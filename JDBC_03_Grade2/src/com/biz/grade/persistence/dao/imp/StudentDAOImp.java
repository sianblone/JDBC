package com.biz.grade.persistence.dao.imp;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.biz.grade.config.DBContract;
import com.biz.grade.persistence.dao.StudentDAO;
import com.biz.grade.persistence.domain.StudentDTO;

public class StudentDAOImp extends StudentDAO {

	@Override
	public List<StudentDTO> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StudentDTO findById(String st_num) {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		String sql = DBContract.SQL.SELECT_STUDENT;
		sql += " where st_num = ? ";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, st_num);
			ResultSet rs = ps.executeQuery();
			
			StudentDTO stdDTO = null;
			if(rs.next()) {
				stdDTO = this.rs_2_DTO(rs);
			}
			rs.close();
			ps.close();
			return stdDTO;
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	private StudentDTO rs_2_DTO(ResultSet rs) throws SQLException {
		return StudentDTO.builder()
				.st_num(rs.getString("ST_NUM"))
				.st_name(rs.getString("ST_NAME"))
				.st_grade(rs.getInt("ST_GRADE"))
				.st_dept(rs.getString("ST_DEPT"))
				.st_tel(rs.getString("ST_TEL"))
				.st_addr(rs.getString("ST_ADDR"))
				.build();
	}

	@Override
	public List<StudentDTO> findByName(String st_name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StudentDTO> findByGrade(int st_grade) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(StudentDTO stDTO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(StudentDTO stDTO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(String st_num) {
		// TODO Auto-generated method stub
		return 0;
	}

}
