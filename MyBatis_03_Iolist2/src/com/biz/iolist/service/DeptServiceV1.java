package com.biz.iolist.service;

import java.util.List;
import java.util.Scanner;

import com.biz.iolist.config.DBConnection;
import com.biz.iolist.config.dao.DeptDao;
import com.biz.iolist.persistence.DeptDTO;

public class DeptServiceV1 {
	
	protected DeptDao deptDao;
	protected Scanner scan;
	
	public DeptServiceV1() {
		deptDao = DBConnection.getSessionFactory().openSession(true).getMapper(DeptDao.class);
		scan = new Scanner(System.in);
		
	}
	
	public void selectMenu() {
		System.out.println("======================================");
		System.out.println("검색");
		System.out.println("======================================");
		System.out.println("1.검색  2.추가  3.수정  4.삭제  0.종료");
		System.out.println("--------------------------------------");
		System.out.print(">>");
		String getScan = scan.nextLine();
		if(getScan.equals("1")) {
			System.out.println("=====================================================");
			System.out.println("1.전체검색  2.코드  3.거래처  4.거래처&대표자  0.종료");
			System.out.println("-----------------------------------------------------");
			System.out.print(">>");
			getScan = scan.nextLine();
			if(getScan.equals("1")) {
				this.selectAll();
			} else if(getScan.equals("2")) {
				this.findById();
			} else if(getScan.equals("3")) {
				this.findByName();
			} else if(getScan.equals("4")) {
				this.findByNameAndCEO();
			} else if(getScan.equals("0")) {
				return;
			}
		} else if(getScan.equals("2")) {
//			this.insert();
		} else if(getScan.equals("3")) {
//			this.update();
		} else if(getScan.equals("4")) {
//			this.delete();
		} else if(getScan.equals("0")) {
			System.out.println("서비스를 종료합니다");
			return;
		}
	}
	
	public void selectAll() {
		List<DeptDTO> deptList = deptDao.selectAll();
		if(deptList == null || deptList.size() <1) {
			System.out.println("리스트가 없습니다");
		} else
			this.infoAsList(deptList);
	}
	
	public void findById() {
		System.out.print("검색할 거래처코드>>");
		String d_code = scan.nextLine().toUpperCase();
		if(d_code.equalsIgnoreCase("-Q")) return;
		if(!d_code.startsWith("D")) {
			System.out.println("코드는 D로 시작되어야 합니다");
		}
		try {
			Integer.valueOf(d_code.substring(1));
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("코드 형식이 잘못되었습니다");
		}
		DeptDTO deptDTO = deptDao.findById(d_code);
		if(deptDTO == null) {
			System.out.println("검색 결과가 없습니다");
		}
		
		this.infoAsDTO(deptDao.findById(d_code));
		
	}
	
	public void findByName() {
		System.out.print("검색할 거래처>>");
		String d_name = scan.nextLine();
		this.infoAsList(deptDao.findByName(d_name));
	}
	
	public void findByNameAndCEO() {
		System.out.print("검색할 거래처>>");
		String d_name = scan.nextLine();
		System.out.print("검색할 대표자>>");
		String d_ceo = scan.nextLine();
		this.infoAsList(deptDao.findByNameAndCEO(d_name, d_ceo));
	}
	
	protected void infoAsDTO(DeptDTO deptDTO) {
		System.out.println("=======================================================");
		System.out.println("거래처코드:" + deptDTO.getD_code());
		System.out.println("거래처:" + deptDTO.getD_name());
		System.out.println("대표자:" + deptDTO.getD_ceo());
		System.out.println("전화번호:" + deptDTO.getD_tel());
		System.out.println("주소:" + deptDTO.getD_addr());
		System.out.println("=======================================================");
	}
	protected void infoAsList(List<DeptDTO> deptList) {
		System.out.println("=======================================================");
		System.out.println("코드\t상호\t대표\t전화\t주소");
		System.out.println("-------------------------------------------------------");
		for(DeptDTO dto : deptList) {
			System.out.print(dto.getD_code() + "\t");
			System.out.print(dto.getD_name() + "\t");
			System.out.print(dto.getD_ceo() + "\t");
			System.out.print(dto.getD_tel() + "\t");
			System.out.print(dto.getD_addr() + "\n");
		}
		System.out.println("=======================================================");
	}

}
