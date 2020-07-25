package com.biz.grade.persistence.dao;

import java.sql.Connection;
import java.util.List;

import com.biz.grade.config.DBConnection;
import com.biz.grade.persistence.domain.SubDTO;

public abstract class SubDAO {
	
	protected Connection conn = null;
	public SubDAO() {
		// TODO Auto-generated constructor stub
		this.conn = DBConnection.getConn();
	}
	
//	조건없이 모든 데이터를 조회하는 method
//	List<>형태의 데이터를 return
	public abstract List<SubDTO> selectAll();
	
//	PK를 조건으로 Data를 조회하는 method
//	PK로 조회한다는 것은 출력되는 데이터가 1개 record이다
//	보통 DTO나 VO 형태의 데이터를 return
	public abstract SubDTO findById(String sb_code);
//	학과이름으로 조회해서 결과를 return하는 method
	public abstract List<SubDTO> findByName(int sb_name);
	
//	DTO에 학생 데이터를 저장하여 method에 주입한 후 insert 수행
	public abstract int insert(SubDTO subDTO);
	public abstract int update(SubDTO subDTO);
	
//	delete 매개변수는 일반적으로 findById() 메소드의 매개변수와 형식, 값이 같다(=PK 값으로 지정한다)
	public abstract int delete(String sb_code);
	
}
