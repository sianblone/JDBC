package com.biz.addr.exec;

import java.util.Scanner;

import com.biz.addr.service.AddrCUDServiceV1;
import com.biz.addr.service.AddrReadServiceV1;

public class AddrEx_01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		AddrReadServiceV1 ar = new AddrReadServiceV1();
		AddrCUDServiceV1 ac = new AddrCUDServiceV1();
		Scanner scan = new Scanner(System.in);
		
		while(true) {
			System.out.println("서비스를 선택해주세요");
			System.out.println("1.DB 검색  2.DB 추가/수정/삭제  0.종료");
			System.out.print(">> ");
			String getScan = scan.nextLine();
			if(getScan.equals("1"))	ar.selectService();
			else if(getScan.equals("2")) ac.selectService();
			else if(getScan.equals("0")) {
				System.out.println("서비스를 종료합니다.");
				break;
			}
			else System.out.println("잘못 입력하였습니다");
		}
		
	}

}
