package com.biz.dbms.dao;

import java.util.List;

import com.biz.dbms.persistence.BBsDTO;

public interface BBsDao {
	
	public List<BBsDTO> selectAll();
	public BBsDTO findByNum(long bbs_num);
	public List<BBsDTO> findByUser(BBsDTO bbsDTO);
	public List<BBsDTO> findBySub(BBsDTO bbsDTO);
	public int insert(BBsDTO bbsDTO);
	public int update(BBsDTO bbsDTO);
	public int delete(long bbs_num);

}
