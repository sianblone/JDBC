package com.biz.grade.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.biz.grade.persistence.StudentDTO;
import com.biz.grade.utils.DBContract;

public class StudentServiceV1 extends StudentService {

	@Override
	public int insert(StudentDTO sDTO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<StudentDTO> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StudentDTO findById(String id) {
		// TODO Auto-generated method stub
		this.dbConnection();
		PreparedStatement pps = null;
		String sql = DBContract.SQL.STUDENT_SELECT;
		sql += " where st_num = ? ";
		
		try {
			pps = dbConn.prepareStatement(sql);
			pps.setString(1, id);
			ResultSet rs = pps.executeQuery();
			
			StudentDTO sDTO = null;
			if(rs.next()) {
				sDTO = this.rs2dto(rs);
			}
			rs.close();
			dbConn.close();
			return sDTO;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<StudentDTO> findByName(String name) {
		// TODO Auto-generated method stub
		this.dbConnection();
		PreparedStatement pps = null;
		
		String sql = DBContract.SQL.STUDENT_SELECT;
		sql += " where st_name = ? ";
		try {
			pps = dbConn.prepareStatement(sql);
			pps.setString(1, name);
			ResultSet rs = pps.executeQuery();
			
			List<StudentDTO> stdList = new ArrayList<StudentDTO>();
			while(rs.next()) {
				stdList.add(
						StudentDTO.builder()
						.st_num(rs.getString("st_num"))
						.st_name(rs.getString("st_name"))
						.st_addr(rs.getString("st_addr"))
						.st_grade(rs.getInt("st_grade"))
						.st_tel(rs.getString("st_tel"))
						.st_dept(rs.getString("st_dept"))
						.build()
						);
			}
			rs.close();
			pps.close();
			dbConn.close();
			
			return stdList;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<StudentDTO> findBySubject(String subject) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(StudentDTO studentDTO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(long id) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	private StudentDTO rs2dto(ResultSet rs) throws SQLException {
		return StudentDTO.builder()
				.st_num(rs.getString("st_num"))
				.st_name(rs.getString("st_name"))
				.st_addr(rs.getString("st_addr"))
				.st_grade(rs.getInt("st_grade"))
				.st_tel(rs.getString("st_tel"))
				.st_dept(rs.getString("st_dept"))
				.build();				
	}

}
