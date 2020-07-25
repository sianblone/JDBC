package com.biz.oracle.exec;

import com.biz.oracle.service.BookServiceV1;

public class BookPriceEx_01 {
	
//	금액을 2개 입력 받아서 해당 범위 내의 도서목록 보이기
	public static void main(String[] args) {
		
		BookServiceV1 bs = new BookServiceV1();
		
		bs.searchBookPrice();
	}

}
