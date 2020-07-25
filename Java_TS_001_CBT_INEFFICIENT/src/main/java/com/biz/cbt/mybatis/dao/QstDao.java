package com.biz.cbt.mybatis.dao;

import java.util.List;

import com.biz.cbt.persistence.QstDTO;

public interface QstDao {
	
	public QstDTO findByQCode(String q_code);
	public List<QstDTO> selectAll();
	
	public int insert(QstDTO qDTO);
	public int update(QstDTO qDTO);
	public int delete(String q_code);

}
