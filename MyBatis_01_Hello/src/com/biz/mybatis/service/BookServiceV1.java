package com.biz.mybatis.service;

import java.util.List;
import java.util.Scanner;

import org.apache.ibatis.session.SqlSession;

import com.biz.mybatis.config.DBConnection;
import com.biz.mybatis.mapper.BookDAO;
import com.biz.mybatis.persistence.BookDTO;

public class BookServiceV1 {
	
	SqlSession sqlSession = null;
	Scanner scan = null;
	
	public BookServiceV1() {
		// TODO Auto-generated constructor stub
		this.sqlSession = DBConnection.getSqlSessionFactory().openSession();
		scan = new Scanner(System.in);
	}
	
	public void findByName() {
		
		while(true) {
			System.out.println("==================================================");
			System.out.println("도서검색 with MyBatis");
			System.out.println("==================================================");
			System.out.print("도서명(-Q:Quit) >> ");
			String bookName = scan.nextLine();
			if(bookName.equals("-Q")) break;
			BookDAO bookDAO = sqlSession.getMapper(BookDAO.class);
			
			List<BookDTO> bookList = bookDAO.findByName(bookName);
			for(BookDTO dto : bookList) {
				System.out.println(dto.toString());
			}
		}
	}

}
