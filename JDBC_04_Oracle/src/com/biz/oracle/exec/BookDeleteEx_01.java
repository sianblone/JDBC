package com.biz.oracle.exec;

import com.biz.oracle.service.BookCUDServiceV1;
import com.biz.oracle.service.BookServiceV1;

public class BookDeleteEx_01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		전체 리스트를 확인하고 book_code값을 입력받아서 데이터 삭제하기
		BookServiceV1 bs = new BookServiceV1();
		BookCUDServiceV1 bcs = new BookCUDServiceV1();
		
		bcs.deleteBook();
		

	}

}
