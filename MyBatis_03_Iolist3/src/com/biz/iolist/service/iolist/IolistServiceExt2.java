package com.biz.iolist.service.iolist;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.biz.iolist.config.DBConnection;
import com.biz.iolist.config.dao.DeptDao;
import com.biz.iolist.config.dao.ProductDao;
import com.biz.iolist.persistence.IolistDTO;

public class IolistServiceExt2 extends IolistServiceExt1 {
	
	public void update() {
		
		
		ProductDao proDao = DBConnection.getSessionFactory().openSession(true).getMapper(ProductDao.class);
		DeptDao deptDao = DBConnection.getSessionFactory().openSession(true).getMapper(DeptDao.class);
		
		System.out.print("SEQ>>");
		String getScan = scan.nextLine();
		long io_seq = 0;
		try {
			io_seq = Long.valueOf(getScan);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("SEQ 형식이 잘못되었습니다 숫자로 입력해주세요");
		}
		IolistDTO iolistDTO = iolistDao.findById(io_seq);
		
		System.out.println("아무것도 입력하지 않으면 기존 값이 그대로 유지됩니다");
		
		while(true) {
			System.out.print("수정할 거래일자>>");
			String io_date = scan.nextLine();
			if(io_date.trim().isEmpty()) break;
			
			if(io_date.trim().isEmpty()) {
				io_date = localDateTime.toLocalDate() + "";
			} else {
				SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
				try {
					sd.parse(io_date);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					System.out.println("날짜 형식이 잘못되었습니다");
					continue;
				}
			iolistDTO.setIo_date(io_date);
			break;
			}
		}
		
		while(true) {
			System.out.print("수정할 구분(1:매입,2:매출)>>");
			String io_inout = scan.nextLine();
			if(io_inout.trim().isEmpty()) break;
			
			if(io_inout.equals("1")) {
				io_inout = "매입";
			} else if(io_inout.equals("2")) {
				io_inout = "매출";
			} else {
				System.out.println("매입매출 구분은 1 또는 2로 입력해주세요");
				continue;
			}
			iolistDTO.setIo_inout(io_inout);
			break;
		}
		
		int io_qty = 0;
		while(true) {
			System.out.print("수정할 수량>>");
			String str_qty = scan.nextLine();
			if(str_qty.trim().isEmpty()) break;
			
			try {
				io_qty = Integer.valueOf(str_qty);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("수량은 숫자로 입력해주세요");
				continue;
			}
			iolistDTO.setIo_qty(io_qty);
			break;
		}

		int io_price = 0;
		while(true) {
			System.out.print("수정할 가격>>");
			String str_price = scan.nextLine();
			if(str_price.trim().isEmpty()) break;
			
			try {
				io_price = Integer.valueOf(str_price);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("가격은 숫자로 입력해주세요");
				continue;
			}
			iolistDTO.setIo_price(io_price);
			break;
		}
		int io_total = io_price * io_qty;
		iolistDTO.setIo_total(io_total);
		
		
		while(true) {
			System.out.print("수정할 상품코드>>");
			String io_pcode = scan.nextLine().toUpperCase();
			if(io_pcode.trim().isEmpty()) break;
			
			if(!io_pcode.startsWith("P") || io_pcode.length() != 5) {
				System.out.println("상품코드 형식이 잘못되었습니다");
				continue;
			}
			try {
				Integer.valueOf(io_pcode.substring(1));
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("상품코드 형식이 잘못되었습니다");
				continue;
			}
			
			if(proDao.findById(io_pcode) == null) {
				System.out.println("상품코드 정보가 없습니다 다시 입력해주세요");
				continue;
			}
			
			iolistDTO.setIo_pcode(io_pcode);
			break;
		}
		
		
		
		while(true) {
			System.out.print("수정할 거래처코드>>");
			String io_dcode = scan.nextLine().toUpperCase();
			if(io_dcode.trim().isEmpty()) break;
			
			if(!io_dcode.startsWith("D") || io_dcode.length() != 5) {
				System.out.println("거래처코드 형식이 잘못되었습니다");
				continue;
			}
			try {
				Integer.valueOf(io_dcode.substring(1));
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("거래처코드 형식이 잘못되었습니다");
				continue;
			}
			
			if(deptDao.findById(io_dcode) == null) {
				System.out.println("거래처코드 정보가 없습니다 다시 입력해주세요");
				continue;
			}
			
			iolistDTO.setIo_dcode(io_dcode);
			break;
		}
		
		this.showDTO(iolistDTO);
		
		while(true) {
			System.out.print("정말로 변경하시겠습니까?(y/n)>>");
			String yn = scan.nextLine();
			if(yn.equalsIgnoreCase("y")) {
				int ret = iolistDao.update(iolistDTO);
				if(ret > 0) {
					System.out.println("데이터 수정 성공");
				} else {
					System.out.println("데이터 수정 실패");
				}
			} else if(yn.equalsIgnoreCase("n")) {
				System.out.println("데이터 수정 취소");
			} else continue;
			break;
		}
	}

}
