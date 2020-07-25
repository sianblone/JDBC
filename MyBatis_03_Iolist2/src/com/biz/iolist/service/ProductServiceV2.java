package com.biz.iolist.service;

import com.biz.iolist.persistence.ProductDTO;

public class ProductServiceV2 extends ProductServiceV1 {
	
	public void update() {
		System.out.print("수정할 코드(-Q:Quit)>>");
		String p_code = scan.nextLine().toUpperCase();
		if(p_code.equals("-Q")) return;
		// 입력한 코드를 대문자로 변경
		ProductDTO proDTO = productDao.findById(p_code);
		if(proDTO == null) {
			System.out.println("상품 정보가 없습니다");
			return;
		}
		this.infoAsDTO(proDTO);
		
		// 삼항 연산자
		// 변수 = 조건식 ? true값 : false값
		// -> 변수는 조건식이 true면 true값을, false면 false값을 갖는다
		String vat = proDTO.getP_vat().equals("1") ? "과세" : "면세";
		System.out.println("과세여부 : " + vat);
		System.out.println("===========================================");
		
		System.out.print("변경할 상품이름>>");
		String p_name = scan.nextLine();
		if(!p_name.trim().isEmpty()) proDTO.setP_name(p_name);
		
		System.out.print("변경할 매입단가>>");
		String p_iprice = scan.nextLine();
		try {
			proDTO.setP_iprice(Integer.valueOf(p_iprice));
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		System.out.print("변경할 매출단가>>");
		String p_oprice = scan.nextLine();
		try {
			proDTO.setP_iprice(Integer.valueOf(p_oprice));
		} catch (Exception e) {
			// TODO: handle exception
		}
		int sf = productDao.update(proDTO);
		if(sf > 0)
			System.out.println("데이터 변경 성공");
		else
			System.out.println("데이터 변경 실패");
		
		ProductDTO newProDTO = productDao.findById(p_code);
		System.out.println("---------------------------------------------");
		System.out.println("수정된 상품코드 : " + newProDTO.getP_code());
		System.out.println("수정된 상품이름 : " + newProDTO.getP_name());
		System.out.println("수정된 매입단가 : " + newProDTO.getP_iprice());
		System.out.println("수정된 매출단가 : " + newProDTO.getP_oprice());
	}
	
	public void delete() {
		System.out.print("삭제할 상품의 코드(-Q:Quit)>>");
		String p_code = scan.nextLine().toUpperCase();
		if(p_code.equalsIgnoreCase("-Q")) return;
		ProductDTO proDTO = productDao.findById(p_code);
		if(proDTO == null) {
			System.out.println("상품 정보가 없습니다");
			return;
		}
		this.infoAsDTO(proDTO);
		System.out.println("==================================================");
		while(true) {
			System.out.println("정말로 이 상품을 삭제하시겠습니까?(y/n)");
			String yn = scan.nextLine();
			if(yn.equalsIgnoreCase("y")) {
				int ret = productDao.delete(p_code);
				if(ret > 0) {
					System.out.println("삭제 성공");
				} else {
					System.out.println("삭제 실패");
				}
				break;
			} else if(yn.equalsIgnoreCase("n")) {
				System.out.println("삭제 취소");
			} else {
				continue;
			}
		}
		
	}

}
