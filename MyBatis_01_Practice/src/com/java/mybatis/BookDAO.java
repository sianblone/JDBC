package com.java.mybatis;

import java.util.List;

public interface BookDAO {
	
	public List<BookDTO> selectAll();
	public BookDTO findById();
	public List<BookDTO> findByName();
	
	public int insert();
	public int update();
	public int delete();
}
