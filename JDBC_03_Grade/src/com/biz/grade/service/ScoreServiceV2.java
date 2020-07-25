package com.biz.grade.service;

import java.sql.Connection;
import java.util.List;

import com.biz.grade.config.DBConnection;
import com.biz.grade.persistence.ScoreDTO;
import com.biz.grade.persistence.ScoreVO;

public abstract class ScoreServiceV2 {
	
	protected Connection conn = null;
	
//	ScoreServiceV2 생성자
	public ScoreServiceV2() {
		this.conn = DBConnection.getConn();
	}
	
	public abstract List<ScoreVO> selectAll();
	public abstract ScoreVO findById(long id);
	public abstract List<ScoreVO> findByStName(String stName);
	
	public abstract int insert(ScoreDTO scDTO);
	public abstract int update(ScoreDTO scDTO);
	public abstract int delete(long id);

	public abstract List<ScoreVO> findByStNum(String strNum);

	public abstract List<ScoreVO> findByStSub(String strSub);

}
