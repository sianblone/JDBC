package com.biz.mybatis.exec;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.biz.mybatis.config.DBConnection;
import com.biz.mybatis.mapper.BookDAO;
import com.biz.mybatis.persistence.BookDTO;

public class MyBatisEx_03 {

	public static void main(String[] args) {
		
//		JDBC의 다양한 클래스를 대신하여
//		Java 어플리케이션과 DBMS간의 Connection을 대신 관리해줄 클래스
		SqlSession sqlSession = DBConnection.getSqlSessionFactory().openSession(true);
		
		BookDAO bookDAO = sqlSession.getMapper(BookDAO.class);
		
		BookDTO bookDTO = BookDTO.builder()
				.book_name("MyBatis 연습")
				.book_comp("출판사")
				.book_writer("작가")
				.book_price(10000)
				.build();
		
		bookDAO.insert(bookDTO);
		
		List<BookDTO> bookList = bookDAO.selectAll();
		for(BookDTO dto : bookList) {
			System.out.println(dto.toString());
		}

	}

}