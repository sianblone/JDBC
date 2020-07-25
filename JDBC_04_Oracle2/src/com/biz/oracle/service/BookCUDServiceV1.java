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

	public boolean updateBook() {
		// TODO Auto-generated method stub
		System.out.println("===================================");
		System.out.println("도서정보 수정");
		System.out.println("===================================");
		System.out.print("수정할 도서코드 >> ");
		String bookCode = scan.nextLine();
		if(bookCode.equalsIgnoreCase("-Q")) {
			System.out.println("서비스를 종료합니다");
			return false;
		}
//		키보드로 입력받은 코드에 해당하는 도서정보를 가져오기
		BookDTO bookDTO = bookDAO.findById(bookCode);
		
		/*
		 * PK 칼럼은 가급적 수정하지 않도록 하자
		 * PK를 수정해야 할 경우는
		 * 기존의 Data를 삭제 후 새로운 Data를 INSERT 하거나
		 * 기존의 Data는 그대로 유지하고 새로운 Data를 INSERT 한다
		 */
		
//		각 항목별로 값 수정하기
		System.out.printf("변경할 도서명(%s) >> ", bookDTO.getBook_name());
		String newBookName = scan.nextLine().trim();
		
//		새로운 도서명을 입력했을 때는 bookDTO의 도서명 필드를 새로운 도서명으로 대체하고
//		그냥 Enter만 입력했을 때는 변경하지 않는다
//		실수로 공백(space)이 입력될 때를 대비하여
//		입력된 도서명(newBookName)의 앞 뒤 공백을 제거한 후 검사
		if(newBookName.length() > 0) {
			bookDTO.setBook_name(newBookName);
		}
		
		System.out.printf("변경할 출판사(%s) >> ", bookDTO.getBook_comp());
		String newBookComp = scan.nextLine().trim();
		if(newBookComp.length() > 0) {
			bookDTO.setBook_comp(newBookComp);
		}
		
		System.out.printf("변경할 저자(%s) >> ", bookDTO.getBook_writer());
		String newBookWriter = scan.nextLine().trim();
		if(newBookWriter.length() > 0) {
			bookDTO.setBook_writer(newBookWriter);
		}
		
		while(true) {
			
			System.out.printf("변경할 가격(%d) >> ", bookDTO.getBook_price());
			try {
				int newBookPrice = Integer.valueOf(scan.nextLine().trim());
				// 만약 값을 입력하지 않거나 숫자가 아닌 값을 입력하면
				// NumberFormatException이 발생할 것이고
				// 그렇게 되면 catch로, 제대로 입력되면 DTO에 저장한다
				bookDTO.setBook_price(newBookPrice);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("가격은 숫자만 입력해주세요");
				continue;
			}
			break;
			
		}
//		여기 도착했을 때 bookDTO에 담긴 값은 처음에 table에서 읽은 값이 저장되어 있거나
//		중간에 키보드로 입력한 값으로 변경되었을 것이다
		
		System.out.println("이대로 업데이트 하시겠습니까?");
		System.out.println("1:업데이트  2:업데이트 하지 않고 종료");
		
		int ret = bookDAO.update(bookDTO);
		if(ret > 0) {
			System.out.println("업데이트 완료!");
		} else {
			System.out.println("업데이트 실패!");
		}
		
		return true;
	}
	
	public void updateBook(boolean boolContinue) {
		while(true) {
			this.updateBook();
		}
	}

}
