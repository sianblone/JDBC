package com.biz.iolist.service;

import java.util.List;

import com.biz.iolist.persistence.ProductDTO;

public class ProductServiceV3 extends ProductServiceV2 {
	
	/*
	 * 상품코드를 입력받아서 insert 수행
	 * 상품코드를 입력받아서
	 * 있으면 다시 입력하도록
	 * 없으면 다음으로 진행
	 * 1. 상품코드를 어떻게 할 것인가?
	 * 	가. 자동으로 생성하기
	 * 	나. 직접 입력하기(이미 문서로 작성된 경우)
	 * 
	 * 2. 상품이름을 입력하는데, 완전히 일치하는 상품이 이미 있는 경우
	 *  가. 코드가 다르면 그냥 입력하기
	 *  나. 코드가 다르고 단가 부분이 다르면 그냥 입력하기
	 *  	상품정보 : 상품이름, 품목, 주매입처
	 *  다. 같은 상품이름 입력 금지
	 *  
	 * 3. 단가부분
	 * 	가. 매입단가를 입력하면, 표준 판매단가를 계산하기
	 * 	나. 매입단가, 매출단가를 별도 입력
	 * 	다. 매입단가일 경우 VAT 포함 여부
	 * 	라. 매출단가, 소매점에서는 VAT 포함이 기본
	 * 		도매점일 경우 VAT 포함여부
	 */
	
	public void insert() {
		while(true) {
			System.out.println("상품코드를 입력해주세요");
			System.out.println("아무 것도 입력하지 않으면 가장 높은 코드숫자+1로 자동생성 됩니다");
			System.out.print("추가할 상품코드(-Q:Quit)>>");
			String p_code = scan.nextLine().toUpperCase();
			if(p_code.equals("-Q")) return;
			if(p_code.isEmpty()) {
				int newPCode = Integer.valueOf(productDao.getMaxPCode().substring(1));
				newPCode++;
				p_code = productDao.getMaxPCode().substring(0,1);
				p_code += String.format("%04d", newPCode);
				System.out.println(p_code);
			} else if(p_code.length() != 5 || !p_code.startsWith("P")) {
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
			
			ProductDTO proDTO = productDao.findById(p_code);
			if(proDTO != null) {
				System.out.println("상품코드가 이미 존재합니다");
				continue;
			}
			
			System.out.print("추가할 상품이름(-Q:Quit)>>");
			String p_name = scan.nextLine();
			if(p_name.equals("-Q")) return;
			List<ProductDTO> proList = productDao.findBySName(p_name);
			if(proList.size() > 0) {
				System.out.println("===================================");
				System.out.println("같은 이름의 상품이 있습니다");
				System.out.println("===================================");
				this.infoAsList(proList);
				System.out.println("-----------------------------------");
				
			}
			
			/*
			 * 상품 매입단가 입력
			 * 1. VAT 여부 입력
			 * 2. 과세면 입력가격 / 1.1
			 * 3. 면세면 그대로
			 * 	면세 : 농산품(1차 식품, 쌀), 흰우유, 재포장 상품 중 농수식품 류
			 */
			String p_vat = "";
			while(true) {
				System.out.print("추가할 과세여부(1:과세, 0:면세, -Q:Quit)>>");
				p_vat = scan.nextLine();
				if(p_vat.equals("-Q")) return;
				if( !(p_vat.equals("1") || p_vat.equals("0")) ) {
					System.out.println("과세여부는 1 또는 0으로 입력해주세요");
					continue;
				}
				break;
			}
			
//			원래는 매입가격을 그대로 입력하고 매입 부가가치세를 따로 기록해야한다
//			여기선 연습용으로 매입 부가가치세를 바로 계산해보도록 한다
			int p_iprice = 0;
			while(true) {
				try {
					System.out.print("추가할 매입단가(-Q:Quit)>>");
					String str_iprice = scan.nextLine();
					if(str_iprice.equals("-Q")) return;
					p_iprice = Integer.valueOf(str_iprice);
					p_iprice = p_vat == "1" ? (int)(p_iprice / 1.1) : p_iprice ;
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
					System.out.print("추가할 매출단가(-Q:Quit)>>");
					String str_oprice = scan.nextLine();
					if(str_oprice.equals("-Q")) return;
					p_oprice = Integer.valueOf(str_oprice);
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("매출단가는 숫자만 입력해주세요");
					continue;
				}
				break;
			}
			
			
			proDTO = ProductDTO.builder()
					.p_code(p_code)
					.p_name(p_name)
					.p_iprice(p_iprice)
					.p_oprice(p_oprice)
					.p_vat(p_vat)
					.build();
			
			int sf = productDao.insert(proDTO);
			if(sf > 0) {
				System.out.println("데이터 추가 성공");
			} else {
				System.out.println("데이터 추가 실패");
			}
			break;
		}
	}

}
