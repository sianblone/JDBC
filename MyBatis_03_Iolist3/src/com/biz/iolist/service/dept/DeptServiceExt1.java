package com.biz.iolist.service.dept;

import java.util.List;
import java.util.Scanner;

import com.biz.iolist.config.DBConnection;
import com.biz.iolist.config.dao.DeptDao;
import com.biz.iolist.persistence.DeptDTO;

public class DeptServiceExt1 {
	
	protected DeptDao deptDao;
	protected Scanner scan;
	
	public DeptServiceExt1() {
		deptDao = DBConnection.getSessionFactory().openSession(true).getMapper(DeptDao.class);
		scan = new Scanner(System.in);
		
	}
	
	public void selectMenu() {
		while(true) {
			System.out.println("======================================");
			System.out.println("검색");
			System.out.println("======================================");
			System.out.println("1.검색  2.추가  3.수정  4.삭제  0.종료");
			System.out.println("--------------------------------------");
			System.out.print(">>");
			String getScan = scan.nextLine();
			if(getScan.equals("1")) {
				System.out.println("=====================================================");
				System.out.println("1.전체검색  2.코드  3.거래처  4. 대표자  5.거래처&대표자  0.종료");
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
					this.findByCEO();
				} else if(getScan.equals("5")) {
					this.findByNameAndCEO();
				} else if(getScan.equals("0")) {
					System.out.println("서비스를 종료합니다");
					return;
				}
			} else if(getScan.equals("2")) {
				this.insert();
			} else if(getScan.equals("3")) {
				this.update();
			} else if(getScan.equals("4")) {
				this.delete();
			} else if(getScan.equals("0")) {
				System.out.println("서비스를 종료합니다");
				return;
			}
		}
	}
	
	public void delete() {
		// TODO Auto-generated method stub
		
	}

	public void update() {
		// TODO Auto-generated method stub
		
	}

	public void insert() {
		// TODO Auto-generated method stub
		
	}

	public void selectAll() {
		List<DeptDTO> deptList = deptDao.selectAll();
		if(deptList == null || deptList.size() <1) {
			System.out.println("리스트가 없습니다");
		} else
			this.infoAsList(deptList);
	}
	
	public DeptDTO findById() {
		System.out.print("거래처코드>>");
		String d_code = scan.nextLine().toUpperCase();
		if(d_code.equalsIgnoreCase("-Q")) return null;
		if(!d_code.startsWith("D")) {
			System.out.println("코드는 D로 시작되야 합니다");
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
		return deptDTO;
		
	}
	
	public List<DeptDTO> findByName() {
		System.out.print("거래처명>>");
		String d_name = scan.nextLine();
		List<DeptDTO> deptList = deptDao.findByName(d_name);
		if(deptList.size() > 0)	{
			this.infoAsList(deptList);
		}
		else {
			System.out.println("검색 결과가 없습니다");
			return null;
		}
		
		return deptList;
	}
	
	public void findByCEO() {
		System.out.print("대표자>>");
		String d_ceo = scan.nextLine();
		this.infoAsList(deptDao.findByCEO(d_ceo));
	}
	
	public void findByNameAndCEO() {
		System.out.print("거래처명>>");
		String d_name = scan.nextLine();
		System.out.print("대표자>>");
		String d_ceo = scan.nextLine();
		this.infoAsList(deptDao.findByNameAndCEO(d_name, d_ceo));
	}
	
	protected void infoAsDTO(DeptDTO deptDTO) {
		System.out.println("=======================================================");
		System.out.println("거래처코드:" + deptDTO.getD_code());
		System.out.println("거래처명:" + deptDTO.getD_name());
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
