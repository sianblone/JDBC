package com.biz.mybatis.mapper;

import java.util.List;

import com.biz.mybatis.persistence.BookDTO;

public interface BookDAO {
	
	public List<BookDTO> selectAll();
	
	public BookDTO findById(String book_code);
	public List<BookDTO> findByName(String book_name);
	
	public int insert(BookDTO bookDTO);
	public int update(BookDTO bookDTO);
	public int delete(String book_code);

}
