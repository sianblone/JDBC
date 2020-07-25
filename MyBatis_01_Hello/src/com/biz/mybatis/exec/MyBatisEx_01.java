package com.biz.mybatis.exec;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.biz.mybatis.config.DBConnection;
import com.biz.mybatis.mapper.BookDAO;
import com.biz.mybatis.persistence.BookDTO;

public class MyBatisEx_01 {

	public static void main(String[] args) {
		
//		JDBC의 다양한 클래스를 대신하여
//		Java 어플리케이션과 DBMS간의 Connection을 대신 관리해줄 클래스
		SqlSession sqlSession = DBConnection.getSqlSessionFactory().openSession(true);
		
		BookDAO bookDAO = sqlSession.getMapper(BookDAO.class);
		
		List<BookDTO> bookList = bookDAO.selectAll();
		
		for(BookDTO dto : bookList) {
			System.out.println(dto.toString());
		}
		

	}

}
