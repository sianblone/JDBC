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
			System.out.println("1.추가  2.수정  3.삭제  4.검색  0.종료");
			System.out.println("--------------------------------------");
			System.out.print("선택>>");
			String strMenu = scan.nextLine();
			if(strMenu.equals("1")) {
				this.insert();
			} else if(strMenu.equals("2")) {
				this.update();
			} else if(strMenu.equals("3")) {
				this.delete();
			} else if(strMenu.equals("4")) {
				System.out.println("======================================");
				System.out.println("검색할 내용");
				System.out.println("======================================");
				System.out.println("1.전체보기  2.상품코드  3.이름  0.종료");
				System.out.println("--------------------------------------");
				System.out.print("선택>>");
				String strMenu2 = scan.nextLine();
				if(strMenu2.equals("1")) {
					this.selectAll();
				} else if(strMenu2.equals("2")) {
					this.findById();
				} else if(strMenu2.equals("3")) {
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
	
	public void insert() {
		// TODO Auto-generated method stub	
	}
	
	public void update() {
		// TODO Auto-generated method stub
	}
	
	public void delete() {
		// TODO Auto-generated method stub
	}

	public void selectAll() {
		List<ProductDTO> productList = productDao.selectAll();
		this.infoAsList(productList);
	}
	
	public void findById() {
		System.out.print("검색할 상품코드>>");
		String p_code = scan.nextLine().toUpperCase();
		ProductDTO proDTO = productDao.findById(p_code);
		if(proDTO == null) {
			System.out.println("상품 정보가 없습니다");
			return;
		}
		this.infoAsDTO(proDTO);
	}
	
	public void findByName() {
		System.out.print("검색할 상품명>>");
		String p_name = scan.nextLine();
		List<ProductDTO> productList = productDao.findByName(p_name);
		if(productList.size() < 1) {
			System.out.println("상품 정보가 없습니다");
			return;
		}
		this.infoAsList(productList);
		
	}
	
	
	protected void infoAsDTO(ProductDTO proDTO) {
		System.out.println("상품코드 : " + proDTO.getP_code());
		System.out.println("상품이름 : " + proDTO.getP_name());
		System.out.println("매입단가 : " + proDTO.getP_iprice());
		System.out.println("매출단가 : " + proDTO.getP_oprice());
		System.out.println("과세여부 : " + proDTO.getP_vat());
	}
	
	protected void infoAsList(List<ProductDTO> proList ) {
		for(ProductDTO dto : proList) {
			System.out.print(dto.getP_code() + "\t");
			System.out.print(dto.getP_name() + "\t");
			System.out.print(dto.getP_iprice() + "\t");
			System.out.print(dto.getP_oprice() + "\t");
			System.out.print(dto.getP_vat() +  "\n");
		}
	}

}
