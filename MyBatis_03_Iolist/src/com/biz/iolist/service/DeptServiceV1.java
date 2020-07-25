package com.biz.iolist.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.biz.iolist.config.DBConnection;
import com.biz.iolist.config.dao.DeptDao;
import com.biz.iolist.persistence.DeptDTO;

public class DeptServiceV1 {
	
	SqlSession sqlSession;
	DeptDao deptDao;
	
	public DeptServiceV1() {
		sqlSession = DBConnection.getSessionFactory().openSession(true);
		deptDao = sqlSession.getMapper(DeptDao.class);
	}
	
	public void selectAll() {
		List<DeptDTO> deptList = deptDao.selectAll();
		for(DeptDTO dto : deptList) {
			System.out.println(dto.toString());
		}
	}

}
