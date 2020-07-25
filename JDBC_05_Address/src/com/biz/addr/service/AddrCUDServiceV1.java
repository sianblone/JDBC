package com.biz.addr.service;

import java.util.Scanner;

import com.biz.addr.dao.AddrDAO;
import com.biz.addr.persistence.AddrDTO;

public class AddrCUDServiceV1 {
	
	private AddrDAO addrDAO = null;
	private Scanner scan = null;
	private String quit = null;
	
	public AddrCUDServiceV1() {
		// TODO Auto-generated constructor stub
		addrDAO = new AddrDAO();
		scan = new Scanner(System.in);
		quit = "뒤로";
	}
	
	public void insert() {
		AddrDTO addrDTO = new AddrDTO();
		System.out.printf("추가할 이름(-Q:%s) >> ", this.quit);
		String name = scan.nextLine();
		if(name.equalsIgnoreCase("-Q")) return;
		addrDTO.setName(name);
		
		System.out.printf("추가할 전화번호(-Q:%s) >> ", this.quit);
		String tel = scan.nextLine();
		if(tel.equalsIgnoreCase("-Q")) return;
		addrDTO.setTel(tel);
		
		System.out.printf("추가할 주소(-Q:%s) >> ", this.quit);
		String addr = scan.nextLine();
		if(addr.equalsIgnoreCase("-Q")) return;
		addrDTO.setAddr(addr);
		
		System.out.printf("추가할 관계(-Q:%s) >> ", this.quit);
		String chain = scan.nextLine();
		if(chain.equalsIgnoreCase("-Q")) return;
		addrDTO.setChain(chain);
		
		while(true) {
			System.out.printf("%s\t%s\t%s\t%s\n",name,tel,addr,chain);
			System.out.print("이대로 추가하시겠습니까?(y/n) >> ");
			String yn = scan.nextLine();
			if(yn.equalsIgnoreCase("y")) {
				int ret = addrDAO.insert(addrDTO);
				if(ret > 0) System.out.println("추가 성공!");
				else System.out.println("추가 실패!");
				break;
			} else if(yn.equalsIgnoreCase("n")) {
				break;
			}
		}
		
	}
	
	public void update() {
		AddrDTO addrDTO = new AddrDTO();
		while(true) {
			System.out.printf("수정할 ID(-Q:%s) >> ", this.quit);
			String strId = scan.nextLine();
			if(strId.equalsIgnoreCase("-Q")) return;
			long id = 0;
			try {
				id = Long.valueOf(strId);
				addrDTO = addrDAO.findById(id);
				if(addrDTO == null) {
					System.out.println("찾는 ID가 없습니다. 다시 입력해주세요");
					continue;
				}
				addrDTO.setId(id);
				break;
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("ID는 숫자만 입력해주세요");
				continue;
			}
		}
		
		System.out.printf("변경할 이름(Enter:그대로, -Q:%s) >> ", this.quit);
		String name = scan.nextLine().trim();
		if(name.equalsIgnoreCase("-Q")) return;
		if(name.length() > 0) {
			addrDTO.setName(name);
		}
		
		System.out.printf("변경할 전화번호(Enter:그대로, -Q:%s) >> ", this.quit);
		String tel = scan.nextLine().trim();
		if(tel.equalsIgnoreCase("-Q")) return;
		if(tel.length() > 0) {
			addrDTO.setTel(tel);
		}
		
		System.out.printf("변경할 주소(Enter:그대로, -Q:%s) >> ", this.quit);
		String addr = scan.nextLine().trim();
		if(addr.equalsIgnoreCase("-Q")) return;
		if(addr.length() > 0) {
			addrDTO.setAddr(addr);
		}
		
		System.out.printf("변경할 관계(Enter:그대로, -Q:%s) >> ", this.quit);
		String chain = scan.nextLine().trim();
		if(chain.equalsIgnoreCase("-Q")) return;
		if(chain.length() > 0) {
			addrDTO.setChain(chain);
		}
		
		while(true) {
			System.out.printf("%s\t%s\t%s\t%s\n",name,tel,addr,chain);
			System.out.print("이대로 수정하시겠습니까?(y/n) >> ");
			String yn = scan.nextLine();
			if(yn.equalsIgnoreCase("y")) {
				int ret = addrDAO.update(addrDTO);
				if (ret > 0) System.out.println("업데이트 성공!");
				else System.out.println("업데이트 실패!");
				break;
			} else if(yn.equalsIgnoreCase("n")) {
				break;
			}
		}
	}
	
	public void delete() {
		while(true) {
			System.out.printf("삭제할 ID(-Q:%s) >> ", this.quit);
			String strId = scan.nextLine();
			if(strId.equalsIgnoreCase("-Q")) return;
			long id = 0;
			try {
				id = Long.valueOf(strId);
				if(addrDAO.findById(id) == null) {
					System.out.println("ID가 없습니다. 다시 입력해주세요");
					continue;
				}
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("ID는 숫자만 입력해주세요");
				continue;
			}
			AddrDTO addrDTO = addrDAO.findById(id);
			
			while(true) {
				System.out.printf("%s\t%s\t%s\t%s\n", addrDTO.getName(), addrDTO.getTel(), addrDTO.getAddr(), addrDTO.getChain());
				System.out.print("정말 삭제하시겠습니까?(y/n) >> ");
				String yn = scan.nextLine();
				if(yn.equalsIgnoreCase("y")) {
					int ret = addrDAO.delete(id);
					if (ret > 0) System.out.println("삭제 성공!");
					else System.out.println("삭제 실패!");
					break;
				} else if(yn.equalsIgnoreCase("n")) {
					break;
				}
			}
		}
	}
	
	public void selectService() {
		AddrReadServiceV1 ar = new AddrReadServiceV1();
		while(true) {
			System.out.println("DB 추가/수정/삭제");
			System.out.printf("1.추가  2.수정  3.삭제  4.DB 검색  0.%s\n", this.quit);
			System.out.print(">> ");
			String getScan = scan.nextLine().trim();
			if(getScan.equals("1")) this.insert();
			else if(getScan.equals("2")) this.update();
			else if(getScan.equals("3")) this.delete();
			else if(getScan.equals("4")) ar.selectService();
			else if(getScan.equals("0")) return;
			else System.out.println("잘못 입력했습니다 다시 입력해주세요");
		}
	}

}
