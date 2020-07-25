package com.biz.oracle.exec;

import com.biz.oracle.service.BookCUDServiceV1;
import com.biz.oracle.service.BookServiceV2;

/*
 * 1. 도서명을 입력받아서 검색한 리스트를 보여주기
 * 2. 도서코드를 입력받아서 해당 도서를 삭제하기
 * 3. 결과를 다시 리스트로 보여주기
 */
public class BookDeleteEx_02 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BookServiceV2 bs = new BookServiceV2();
		BookCUDServiceV1 bcs = new BookCUDServiceV1();
		
		bs.searchBookName();
		bcs.deleteBook();
		bs.viewBookList();

	}

}
