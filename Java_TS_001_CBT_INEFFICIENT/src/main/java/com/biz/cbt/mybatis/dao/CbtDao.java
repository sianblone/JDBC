package com.biz.cbt.mybatis.dao;

import java.util.List;

import com.biz.cbt.persistence.CbtDTO;

public interface CbtDao {
	
	public CbtDTO findBySeq(String cb_seq);
	public List<CbtDTO> selectAll();
	
	public List<CbtDTO> findByJoinQCode(String cb_qcode);
	public List<String> findByQCodeForSeq(String cb_qcode);
	public List<String> selectAllQCode();
	
	public int insert(CbtDTO cbtDTO);
	public int update(CbtDTO cbtDTO);
	public int deleteBySeq(String cb_seq);
	public int deleteByQCode(String cb_qcode);
	

}
