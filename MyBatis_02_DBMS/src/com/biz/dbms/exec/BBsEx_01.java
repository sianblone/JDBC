package com.biz.dbms.exec;

import com.biz.dbms.service.BBsServiceV1;

public class BBsEx_01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BBsServiceV1 bbs = new BBsServiceV1();
		
		// 키보드를 사용해서 게시판 글쓰기를 구현
		bbs.writeBBS();
		
		bbs.viewBBsList();

	}

}
