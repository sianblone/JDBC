package com.biz.iolist.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.biz.iolist.config.DBConnection;
import com.biz.iolist.config.dao.IolistDao;
import com.biz.iolist.persistence.IolistDTO;

public class IolistServiceV1 {
	
	SqlSession sqlSession;
	IolistDao iolistDao;
	
	public IolistServiceV1() {
		sqlSession = DBConnection.getSessionFactory().openSession(true);
		iolistDao = sqlSession.getMapper(IolistDao.class);
	}
	
	public void selectAll() {
		List<IolistDTO> iolistList = iolistDao.selectAll();
		for(IolistDTO dto : iolistList) {
			System.out.println(dto.toString());
		}
	}
	

}
