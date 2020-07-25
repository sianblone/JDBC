package com.biz.iolist.service.iolist;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import com.biz.iolist.config.DBConnection;
import com.biz.iolist.config.dao.IolistDao;
import com.biz.iolist.persistence.DeptDTO;
import com.biz.iolist.persistence.IolistDTO;
import com.biz.iolist.persistence.ProductDTO;
import com.biz.iolist.service.dept.DeptServiceExt3;
import com.biz.iolist.service.iolist.view.IolistViewServiceExt1;
import com.biz.iolist.service.product.ProductServiceExt3;

/*
 * 매입매출 등록
 * 
 * 날짜 현재날짜로 자동등록
 * 
 * 거래처명검색
 * 거래처코드 입력
 * 입력한코드 검증
 * 
 * 상품명검색
 * 상품코드입력
 * 입력한코드 검증
 * 
 * 매입,매출 구분 입력
 * 매입,매출에 따라서
 * 매입단가, 매출단가 입력하기
 * 
 * 매입합계 또는 매출합계 계산하기
 * insert()
 * 
 * 추가된 데이터 보여주기(VO)
 */

public class IolistServiceExt1 {
	
	protected IolistDao iolistDao;
	protected Scanner scan;
	
	DeptServiceExt3 ds3;
	ProductServiceExt3 ps3;
	IolistViewServiceExt1 ivs;
	
	LocalDateTime localDateTime;
	
	public IolistServiceExt1() {
		iolistDao = DBConnection.getSessionFactory().openSession(true).getMapper(IolistDao.class);
		scan = new Scanner(System.in);
		
		ds3 = new DeptServiceExt3();
		ps3 = new ProductServiceExt3();
		ivs = new IolistViewServiceExt1();
		
		localDateTime = LocalDateTime.now();
	}
	
	public void iolistMenu() {
		while(true) {
			System.out.println("=============================================================");
			System.out.println("새나라 마트 매입매출 관리 V1");
			System.out.println("=============================================================");
			System.out.println("1.검색  2.추가  3.수정  4.삭제  0.종료");
			System.out.println("-------------------------------------------------------------");
			System.out.print(">>");
			String strMenu = scan.nextLine();
			if(strMenu.equals("1")) {
				System.out.println("-----------------------------------------------");
				System.out.println("1.전체검색  2.SEQ  3.상품명  4.거래처명  0.종료");
				System.out.println("-----------------------------------------------");
				strMenu = scan.nextLine();
				if(strMenu.equals("1")) {
					ivs.selectAll();
				} else if(strMenu.equals("2")) {
					ivs.findById();
				} else if(strMenu.equals("3")) {
					ivs.findByPName();
				} else if(strMenu.equals("4")) {
					ivs.findByDName();
				} else if(strMenu.equals("0")) {
					System.out.println("서비스를 종료합니다");
					return;
				}
			} else if(strMenu.equals("2")) {
				this.insert();
			} else if(strMenu.equals("3")) {
				this.update();
			} else if(strMenu.equals("4")) {
				this.delete();
			} else if(strMenu.equals("0")) {
				System.out.println("서비스를 종료합니다");
				return;
			}
		}
		
	}
	
	public void selectAll() {
		ivs.selectAll();
	}
	
	public void insert() {
		// TODO 매입매출 등록 method
		
		DeptServiceExt3 ds3 = new DeptServiceExt3();
		ProductServiceExt3 ps3 = new ProductServiceExt3();
		
		LocalDateTime localDateTime = LocalDateTime.now();
		String io_date = localDateTime.toLocalDate() + "";
		
		if(ps3.findByName() == null) return;
		
		ProductDTO proDTO = ps3.findById();
		if(proDTO == null) return;
		
		if(ds3.findByName() == null) return;
		
		DeptDTO deptDTO = ds3.findById();
		if(deptDTO == null) return;
		
		String io_inout = "";
		int io_price = 0;
		while(true) {
			System.out.print("1:매입,2:매출>>");
			io_inout = scan.nextLine();
			if(io_inout.equals("1")) {
				io_inout = "매입";
				io_price = proDTO.getP_iprice();
			} else if(io_inout.equals("2")) {
				io_inout = "매출";
				io_price = proDTO.getP_oprice();
			} else {
				System.out.println("매입매출 구분은 1 또는 2로 입력해주세요");
				continue;
			}

			break;
		}
		
		int io_qty = (int)(Math.random() * 100) + 51;
		int io_total = io_price * io_qty;
		String io_pcode = proDTO.getP_code();
		String io_dcode = deptDTO.getD_code();
		
		IolistDTO iolistDTO = IolistDTO.builder()
				.io_date(io_date)
				.io_inout(io_inout)
				.io_qty(io_qty)
				.io_price(io_price)
				.io_total(io_total)
				.io_pcode(io_pcode)
				.io_dcode(io_dcode)
				.build();
		
		this.showDTO(iolistDTO);
		
		while(true) {
			System.out.print("정말로 추가하시겠습니까?(y/n)>>");
			String yn = scan.nextLine();
			if(yn.equalsIgnoreCase("y")) {
				int ret = iolistDao.insert(iolistDTO);
				if(ret > 0) {
					System.out.println("데이터 추가 성공");
				} else {
					System.out.println("데이터 추가 실패");
				}
			} else if(yn.equalsIgnoreCase("n")) {
				System.out.println("데이터 추가 취소");
			} else continue;
			break;
		}
		
	}
	
	public void update() {
		
	}
	
	protected void showDTO(IolistDTO iolistDTO) {
		
		System.out.print(iolistDTO.getIo_date() + "\t");
		System.out.print(iolistDTO.getIo_inout() + "\t");
		System.out.print(iolistDTO.getIo_qty() + "\t");
		System.out.print(iolistDTO.getIo_price() + "\t");
		System.out.print(iolistDTO.getIo_total() + "\t");
		System.out.print(iolistDTO.getIo_pcode() + "\t");
		System.out.print(iolistDTO.getIo_dcode() + "\n");
	}
	

	private void delete() {
		// TODO Auto-generated method stub
	}


	private void select() {
		// TODO Auto-generated method stub
	}
	
}
