package com.biz.iolist.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.biz.iolist.config.DBConnection;
import com.biz.iolist.config.dao.IolistViewDao;
import com.biz.iolist.persistence.IolistVO;

public class IolistViewServiceV1 {
	
	SqlSession sqlSession;
	IolistViewDao iolistViewDao;
	
	public IolistViewServiceV1() {
		sqlSession = DBConnection.getSessionFactory().openSession(true);
		iolistViewDao = sqlSession.getMapper(IolistViewDao.class);
	}
	
	public void selectAll() {
		List<IolistVO> viewList = iolistViewDao.selectAll();
		for(IolistVO dto : viewList) {
			System.out.println(dto.toString());
		}
	}
	

}
