package com.biz.iolist.service;

import java.util.List;
import java.util.Scanner;

import org.apache.ibatis.session.SqlSession;

import com.biz.iolist.config.DBConnection;
import com.biz.iolist.config.dao.ProductDao;
import com.biz.iolist.persistence.ProductDTO;

public class ProductServiceV1 {
	
	protected SqlSession sqlSession;
	protected ProductDao productDao;
	protected Scanner scan;
	
	public ProductServiceV1() {
		sqlSession = DBConnection.getSessionFactory().openSession(true);
		productDao = sqlSession.getMapper(ProductDao.class);
//		productDao = DBConnection.getSessionFactory().openSession(true).getMapper(ProductDao.class);
//		어차피 SqlSession형을 반환하므로 이렇게 한 줄로 써도 된다
		scan = new Scanner(System.in);
	}
	
	public void selectMenu() {
		while(true) {
			System.out.println("======================================");
			System.out.println("쇼핑몰 상품관리 시스템");
			System.out.println("======================================");
			System.out.println("1.등록  2.수정  3.삭제  4.검색  0.종료");
			System.out.print("업무선택>>");
			String strMenu = scan.nextLine();
			if(strMenu.equals("1")) {
				this.insert();
			} else if(strMenu.equals("2")) {
				this.update();
			} else if(strMenu.equals("3")) {
	//			this.delete();
			} else if(strMenu.equals("4")) {
				System.out.println("검색할 내용");
				System.out.println("1.전체  2.이름  0.종료");
				String strMenu2 = scan.nextLine();
				if(strMenu2.equals("1")) {
					this.selectAll();
				} else if(strMenu2.equals("2")) {
					this.findByName();
				} else if(strMenu2.equals("0")) {
					System.out.println("서비스를 종료합니다");
					return;
				}
				
			} else if(strMenu.equals("0")) {
				System.out.println("서비스를 종료합니다");
				return;
			}
		}
	}
	
	public void selectAll() {
		List<ProductDTO> productList = productDao.selectAll();
		for(ProductDTO dto : productList) {
			System.out.println(dto.toString());
		}
	}
	
	public void findByName() {
		System.out.print("검색할 상품명>>");
		String pName = scan.nextLine();
		List<ProductDTO> productList = productDao.findByName(pName);
		if(productList.size() < 1) {
			System.out.println("상품 정보가 없습니다");
			return;
		}
		System.out.println("상품 정보가 있습니다");
		for(ProductDTO dto : productList) {
			System.out.println(dto.toString());
		}
		
	}
	
	public void insert() {
		while(true) {
			System.out.print("추가할 상품코드(-Q:Quit)>>");
			String p_code = scan.nextLine().toUpperCase();
			if(p_code.equals("-Q")) return;
			if(!(p_code.length() == 5) || !p_code.startsWith("P")) {
				System.out.println("상품코드가 잘못되었습니다");
				continue;
			}
			try {
				Integer.valueOf(p_code.substring(1));
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("상품코드가 잘못되었습니다");
				continue;
			}
			
			ProductDTO pDTO = productDao.findById(p_code);
			if(!(pDTO == null)) {
				System.out.println("상품코드가 이미 존재합니다");
				continue;
			}
			
			System.out.print("추가할 상품이름>>");
			String p_name = scan.nextLine();
			
			int p_iprice = 0;
			while(true) {
				try {
					System.out.print("추가할 매입단가>>");
					p_iprice = Integer.valueOf(scan.nextLine());
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("매입단가는 숫자만 입력해주세요");
					continue;
				}
				break;
			}
			
			int p_oprice = 0;
			while(true) {
				try {
					System.out.print("추가할 매출단가>>");
					p_oprice = Integer.valueOf(scan.nextLine());
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("매출단가는 숫자만 입력해주세요");
					continue;
				}
				break;
			}
			
			String p_vat = "";
			while(true) {
				System.out.print("추가할 과세여부>>");
				p_vat = scan.nextLine();
				if( !(p_vat.equals("1") || p_vat.equals("2")) ) {
					System.out.println("과세여부는 1 또는 2로 입력해주세요");
					continue;
				}
				break;
			}
			
			pDTO = ProductDTO.builder()
					.p_code(p_code)
					.p_name(p_name)
					.p_iprice(p_iprice)
					.p_oprice(p_oprice)
					.p_vat(p_vat)
					.build();
			
			int sf = productDao.insert(pDTO);
			if(sf > 0) {
				System.out.println("데이터 추가 성공");
			} else {
				System.out.println("데이터 추가 실패");
			}
			break;
		}
	}
	
	public void update() {
		System.out.print("수정할 코드(-Q:Quit)>>");
		String pCode = scan.nextLine().toUpperCase();
		if(pCode.equals("-Q")) return;
		// 입력한 코드를 대문자로 변경
		ProductDTO proDTO = productDao.findById(pCode);
		if(proDTO == null) {
			System.out.println("상품 정보가 없습니다");
			return;
		}
		System.out.println("상품코드 : " + proDTO.getP_code());
		System.out.println("상품이름 : " + proDTO.getP_name());
		System.out.println("매입단가 : " + proDTO.getP_iprice());
		System.out.println("매출단가 : " + proDTO.getP_oprice());
		
		// 삼항 연산자
		// 변수 = 조건식 ? true값 : false값
		// -> 변수는 조건식이 true면 true값을, false면 false값을 갖는다
		String vat = proDTO.getP_vat().equals("1") ? "과세" : "면세";
		System.out.println("과세여부 : " + vat);
		System.out.println("===========================================");
		
		System.out.print("변경할 상품이름>>");
		String pName = scan.nextLine();
		if(!pName.trim().isEmpty()) proDTO.setP_name(pName);
		
		System.out.print("변경할 매입단가>>");
		String pIprice = scan.nextLine();
		try {
			proDTO.setP_iprice(Integer.valueOf(pIprice));
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		System.out.print("변경할 매출단가>>");
		String pOprice = scan.nextLine();
		try {
			proDTO.setP_iprice(Integer.valueOf(pOprice));
		} catch (Exception e) {
			// TODO: handle exception
		}
		int sf = productDao.update(proDTO);
		if(sf > 0)
			System.out.println("데이터 변경 성공");
		else
			System.out.println("데이터 변경 실패");
		
		ProductDTO newProDTO = productDao.findById(pCode);
		System.out.println("---------------------------------------------");
		System.out.println("수정된 상품코드 : " + newProDTO.getP_code());
		System.out.println("수정된 상품이름 : " + newProDTO.getP_name());
		System.out.println("수정된 매입단가 : " + newProDTO.getP_iprice());
		System.out.println("수정된 매출단가 : " + newProDTO.getP_oprice());
	}

}
