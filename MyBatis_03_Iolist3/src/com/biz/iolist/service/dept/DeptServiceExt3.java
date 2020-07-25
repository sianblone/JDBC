package com.biz.iolist.service.dept;

import com.biz.iolist.persistence.DeptDTO;

public class DeptServiceExt3 extends DeptServiceExt2 {
	
	public void insert() {
		
		
		System.out.print("추가할 코드(Enter:자동으로 가장 높은 코드+1 생성)>>");
		String d_code = scan.nextLine().toUpperCase();
		
		if(d_code.trim().isEmpty()) {
			d_code = "D" + String.format("%04d", (Integer.valueOf(deptDao.getMaxDCode().substring(1)) + 1));
			System.out.println("거래처코드 : " + d_code);
		}
		if(!d_code.startsWith("D") || d_code.length() != 5) {
			System.out.println("거래처코드는 'D'+'4자리 숫자' 형태여야 합니다");
			return;
		}
		
		try {
			Integer.valueOf(d_code.substring(1));
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("거래처코드 형식이 잘못되었습니다");
			return;
		}
		
		DeptDTO deptDTO = deptDao.findById(d_code);
		if(deptDTO != null) {
			System.out.println("거래처코드가 이미 존재합니다");
			return;
		}
		
		
		String d_name = "";
		while(true) {
			System.out.print("추가할 거래처명>>");
			d_name = scan.nextLine();
			if(d_code.trim().isEmpty()) {
				System.out.println("거래처명을 입력해주세요");
				continue;
			}
			break;
		}
		
		String d_ceo = "";
		while(true) {
			System.out.print("추가할 대표자>>");
			d_ceo = scan.nextLine();
			if(d_ceo.trim().isEmpty()) {
				System.out.println("대표자를 입력해주세요");
				continue;
			}
			if(deptDao.findByNameAndCEO(d_name, d_ceo).size() > 0) {
				System.out.println("거래처명&대표자가 이미 존재합니다");
				return;
			}
			break;
		}
		
		deptDTO = DeptDTO.builder()
				.d_code(d_code)
				.d_name(d_name)
				.d_ceo(d_ceo)
				.build();
		
		this.infoAsDTO(deptDTO);
		
		while (true) {
			System.out.print("정말로 추가하시겠습니까?(y/n)>>");
			String yn = scan.nextLine();
			if(yn.equalsIgnoreCase("y")) {
				int ret = deptDao.insert(deptDTO);
				if(ret > 0) {
					System.out.println("데이터 추가 성공");
					break;
				} else {
					System.out.println("데이터 추가 실패");
					break;
				}
			} else if(yn.equalsIgnoreCase("n")){
				System.out.println("데이터 추가 취소");
				break;
			}
		}
		
	}

}
