package com.biz.oracle.service;

import java.util.Scanner;

import com.biz.oracle.persistence.dao.BookDAO;
import com.biz.oracle.persistence.dao.BookDTO;
import com.biz.oracle.persistence.dao.imp.BookDAOImp;

public class BookCUDServiceV1 {
	
	private BookDAO bookDAO = null;
	private Scanner scan = null;
	private BookServiceV1 bs = null;
	
	public BookCUDServiceV1() {
		// TODO Auto-generated constructor stub
		bookDAO = new BookDAOImp();
		scan = new Scanner(System.in);
		bs = new BookServiceV1();
	}
	
	public void inputBook() {
		
		while(true) {
			System.out.println("===========================");
			System.out.println("도서정보 등록");
			System.out.println("===========================");
			
			String bookName = null;
			while(true) {
				System.out.print("1.도서명(-Q:Quit) >> ");
				bookName = scan.nextLine();
				if(bookName.equalsIgnoreCase("-Q")) {
					System.out.println("서비스를 종료합니다");
					break;
				}
				if(bookName.isEmpty()) {
					System.out.println("도서명은 반드시 입력해야 합니다");
					continue;
				}
				break;
			}
			if(bookName.equalsIgnoreCase("-Q")) {
				break;
			}
			
			System.out.print("2.출판사(-Q:Quit) >> ");
			String bookComp = scan.nextLine();
			if(bookComp.equalsIgnoreCase("-Q")) {
				System.out.println("서비스를 종료합니다");
				break;
			}
			
			System.out.print("3.저자(-Q:Quit) >> ");
			String bookWriter = scan.nextLine();
			if(bookWriter.equalsIgnoreCase("-Q")) {
				System.out.println("서비스를 종료합니다");
				break;
			}
			
			String bookPrice = null;
			int intBookPrice = 0;
			while(true) {
				try {
					System.out.print("4.가격(-Q:Quit) >> ");
					bookPrice = scan.nextLine();
					if(bookPrice.equalsIgnoreCase("-Q")) {
						System.out.println("서비스를 종료합니다");
						break;
					}
					intBookPrice = Integer.valueOf(bookPrice);
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("가격은 숫자만 입력해주세요");
					continue;
				}
				break;
			}
			if(bookPrice.equalsIgnoreCase("-Q")) {
				break;
			}
			
			System.out.println("도서명\t출판사\t저자\t가격");
			
			BookDTO bookDTO = BookDTO.builder()
					.book_name(bookName)
					.book_comp(bookComp)
					.book_writer(bookWriter)
					.book_price(intBookPrice)
					.build();
			
			int intReturn = bookDAO.insert(bookDTO);
			if(intReturn > 0)
				System.out.println("도서정보 저장 완료");
			else
				System.out.println("도서정보 저장 실패");
			
		}
	}

	public void deleteBook() {
		// TODO Auto-generated method stub
		bs.viewBookList();
		while(true) {
			System.out.println("=============================");
			System.out.print("삭제할 코드(-Q:Quit / -L:도서확인) >> ");
			String bookCode = scan.nextLine();
			if(bookCode.equalsIgnoreCase("-Q")) {
				System.out.println("서비스를 종료합니다");
				break;
			}
			else if(bookCode.equalsIgnoreCase("-L")) bs.viewBookList(); 
			
			BookDTO bookDTO = bookDAO.findById(bookCode);
			if(bookDTO == null) {
				System.out.println("도서코드가 없습니다");
				continue;
			}
			bookDAO.delete(bookCode);
			System.out.println(bookCode + " 도서가 삭제되었습니다");
			
		}
		
	}

}
