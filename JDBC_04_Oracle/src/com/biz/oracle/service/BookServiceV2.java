package com.biz.oracle.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.biz.oracle.persistence.dao.BookDAO;
import com.biz.oracle.persistence.dao.BookDTO;
import com.biz.oracle.persistence.dao.imp.BookDAOImp;

public class BookServiceV2 {

//	객체 선언(아직 사용준비 전 단계)
	BookDAO bookDAO = null;
	Scanner scan = null;
	
//	클래스 생성자
	public BookServiceV2 () {
		
//		선언된 객체를 사용할 수 있도록 초기화
//		초기화 된 클래스 = 인스턴스(화 되었다)
		scan = new Scanner(System.in);
		bookDAO = new BookDAOImp();
	}
	
	public void viewBookList() {
//		도서정보 전체 리스트를 DB로부터 읽어서 console에 보이기
		List<BookDTO> bookList = bookDAO.selectAll();
		
		this.printBooks(bookList);
	} // end viewBookList
	
	
	/*
	 * bookList를 매개변수로 받아서 console에 보이기
	 */
	private void printBooks(List<BookDTO> bookList) {
		System.out.println("==========================================");
		System.out.println("전체 도서 리스트");
		System.out.println("==========================================");
		System.out.println("코드\t도서명\t출판사\t저자\t가격");
		System.out.println("------------------------------------------");
		for(BookDTO dto : bookList) {
			System.out.printf("%s\t%s\t%s\t%s\t%d\n",
					dto.getBook_code(),
					dto.getBook_name(),
					dto.getBook_comp(),
					dto.getBook_writer(),
					dto.getBook_price());
		}
		System.out.println("==========================================");
	}
	
//	키보드에서 도서 이름을 입력받아서 리스트를 콘솔에 보이기
	public boolean searchBookName() {
		
			System.out.println("===================================");
			System.out.println("도서검색");
			System.out.println("===================================");
			System.out.print("도서명(-Q:Quit) >> ");
			String bookName = scan.nextLine();
			if(bookName.equalsIgnoreCase("-Q")) {
				System.out.println("서비스를 종료합니다");
				return true;
			}
			List<BookDTO> bookList = bookDAO.findByName(bookName);
			if(bookList == null || bookList.size() < 1) {
				System.out.println("찾는 도서명이 없습니다");
				return false;
			}
//			bookList에는 입력한 도서명에 해당하는 리스트가 담겨 있다
//			viewList()는 검색조건에 맞는 리스트만 보일 것이다
			this.printBooks(bookList);
			return true;
		
	}
	
	public void searchBookName(boolean boolContinue) {
		
		while(true) {
			if(this.searchBookName()) break;;
		}
		
	}

	public void searchBookPrice() {
		// TODO Auto-generated method stub
		List<BookDTO> bookList = new ArrayList<>();
		while(true) {
			System.out.println("===================================");
			System.out.println("도서검색");
			System.out.println("===================================");
			
			try {
				System.out.print("최저가격(-Q:Quit) >> ");
				String sPrice = scan.nextLine();
				if(sPrice.equalsIgnoreCase("-Q")) {
					System.out.println("서비스를 종료합니다");
					break;
				}
				int intSPrice = Integer.valueOf(sPrice);
				
				System.out.print("최대가격(-Q:Quit) >> ");
				String ePrice = scan.nextLine();
				if(ePrice.equalsIgnoreCase("-Q")) {
					System.out.println("서비스를 종료합니다");
					break;
				}
				int intEPrice = Integer.valueOf(ePrice);
				
				bookList = bookDAO.findByPrice(intSPrice, intEPrice);
				if(bookList == null || bookList.size() < 1) {
					System.out.println("찾는 도서명이 없습니다");
					continue;
				}
				
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("가격은 숫자만 입력해주세요");
				continue;
			}
			
//			bookList에는 입력한 도서명에 해당하는 리스트가 담겨 있을 것
//			viewList()는 검색조건에 맞는 리스트만 보일 것
			this.printBooks(bookList);
		}
	}
}
