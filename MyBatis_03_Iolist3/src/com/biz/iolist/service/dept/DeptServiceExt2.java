package com.biz.iolist.service.dept;

import com.biz.iolist.persistence.DeptDTO;

public class DeptServiceExt2 extends DeptServiceExt1 {
	
	public void update() {
		
		System.out.print("수정할 거래처코드>>");
		String d_code = scan.nextLine().toUpperCase();
		DeptDTO deptDTO = deptDao.findById(d_code);
		if(deptDTO == null) {
			System.out.println("거래처코드가 없습니다");
			return;
		}
		
		System.out.print("수정할 거래처명>>");
		String d_name = scan.nextLine();
		if(!d_name.trim().isEmpty()) {
			deptDTO.setD_name(d_name);
		}
		
		System.out.print("수정할 대표자>>");
		String d_ceo = scan.nextLine();
		if(!d_ceo.trim().isEmpty()) {
			deptDTO.setD_ceo(d_ceo);
		}
		
		System.out.println("=============================================================");
		System.out.println("거래처코드 : " + deptDTO.getD_code());
		System.out.println("거래처명 : " + deptDTO.getD_name() + " -> " + d_name);
		System.out.println("대표자 : " + deptDTO.getD_ceo() + " -> " + d_ceo);
		System.out.println("전화번호 : " + deptDTO.getD_tel());
		System.out.println("주소: " + deptDTO.getD_addr());
		System.out.println("=============================================================");
		while(true) {
			System.out.print("정말로 바꾸시겠습니까?(y/n)>>");
			String yn = scan.nextLine();
			if(yn.equalsIgnoreCase("y")) {
				int ret = deptDao.update(deptDTO);
				if(ret > 0) {
					System.out.println("업데이트 성공");
					break;
				} else {
					System.out.println("업데이트 실패");
					continue;
				}
			} else if(yn.equalsIgnoreCase("n")) {
				System.out.println("업데이트 취소");
				break;
			}
		}
		
		
	}
	
	public void delete() {
		
		System.out.print("삭제할 거래처코드>>");
		String d_code = scan.nextLine().toUpperCase();
		DeptDTO deptDTO = deptDao.findById(d_code);
		if(deptDTO == null) {
			System.out.println("거래처코드가 없습니다");
			return;
		}
		
		System.out.println("=============================================================");
		System.out.println("거래처코드 : " + deptDTO.getD_code());
		System.out.println("거래처명 : " + deptDTO.getD_name());
		System.out.println("대표자 : " + deptDTO.getD_ceo());
		System.out.println("전화번호 : " + deptDTO.getD_tel());
		System.out.println("주소: " + deptDTO.getD_addr());
		System.out.println("=============================================================");
		while(true) {
			System.out.print("정말로 삭제하시겠습니까?(y/n)>>");
			String yn = scan.nextLine();
			if(yn.equalsIgnoreCase("y")) {
				int ret = deptDao.delete(d_code);
				if(ret > 0) {
					System.out.println("삭제 성공");
				} else {
					System.out.println("삭제 실패");
				}
			} else if(yn.equalsIgnoreCase("n")) {
				System.out.println("삭제 취소");
			} else continue;
			break;
		}
		
	}

}
