package com.biz.oracle.config;

public class DBContract {
	
	public static class SQL {
		public static final String SELECT_BOOKS = " select BOOK_CODE, BOOK_NAME, BOOK_COMP, BOOK_WRITER, BOOK_PRICE from tbl_books ";
	}

}
