package com.biz.oracle.persistence.dao;

import java.sql.Connection;
import java.util.List;

import com.biz.oracle.config.DBConnection;

/*
 * 추상클래스로 선언
 * tbl_books 테이블의 CRUD 구현
 */
public abstract class BookDAO {
	
	protected Connection conn = null;
	
	public BookDAO() {
		// TODO Auto-generated constructor stub
		this.conn = DBConnection.getConn();
	}
	
	/*
	 * BOOK_CODE : PK 기준으로 값 검색 findById(String book_code)
	 * BOOK_NAME : findByName(String book_name)
	 * BOOK_COMP : findByComp(String book_comp)
	 * BOOK_WRITER : findByWriter(String book_writer)
	 * BOOK_PRICE : findByPrice(int price), findByPrice(int sPrice, int ePrice)
	 */

	public abstract List<BookDTO> selectAll();
	public abstract BookDTO findById(String book_code);
	
	public abstract List<BookDTO> findByName(String book_name);
	public abstract List<BookDTO> findByComp(String book_comp);
	public abstract List<BookDTO> findByWriter(String book_writer);
	public abstract List<BookDTO> findByPrice(int price); // 값 일치
	public abstract List<BookDTO> findByPrice(int sPrice, int ePrice); // 범위 내 값
	
	public abstract int insert(BookDTO bookDTO);
	public abstract int update(BookDTO bookDTO);
	public abstract int delete(String book_code);

}