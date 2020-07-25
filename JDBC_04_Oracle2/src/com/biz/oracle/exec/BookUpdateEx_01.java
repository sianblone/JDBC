package com.biz.oracle.exec;

import com.biz.oracle.service.BookCUDServiceV1;
import com.biz.oracle.service.BookServiceV2;

public class BookUpdateEx_01 {
	/*
	 * 도서명을 입력받아서 리스트를 보여주고 수정할 도서코드를 입력받고 해당하는 도서를 수정
	 * 1. 각 항목을 보여주고 새로운 값을 입력하면 수정, 그냥 Enter 입력하면 유지
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		select를 위한 서비스 클래스
		BookServiceV2 bs = new BookServiceV2();
		
//		insert,update,delete를 위한 서비스 클래스
		BookCUDServiceV1 bcs = new BookCUDServiceV1();
		
//		bs에서 도서명을 입력했을 때 List를 보여주는 method 호출
		String bookName = bs.searchBookName();
		if(!bookName.equals("-Q")) {
			
//			도서코드를 입력받고 update하기
			bcs.updateBook();
			
			bs.searchBookName("도서명");			
		}
		


	}

}
