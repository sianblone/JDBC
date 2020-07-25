package com.biz.addr.service;

import java.util.List;
import java.util.Scanner;

import com.biz.addr.dao.AddrDAO;
import com.biz.addr.persistence.AddrDTO;

public class AddrReadServiceV1 {
	
	private AddrDAO addrDAO = null;
	private Scanner scan = null;
	private String quit = null;
	
	public AddrReadServiceV1() {
		// TODO Auto-generated constructor stub
		addrDAO = new AddrDAO();
		scan = new Scanner(System.in);
		quit = "뒤로";
	}
	
	private void printResult(List<AddrDTO> addrList) {
		
		if(addrList == null) {
			System.out.println("검색 결과가 없습니다");
		} else {
			System.out.println("=====================================================");
			System.out.println("ID\t이름\t전화번호\t주소\t관계");
			System.out.println("=====================================================");
			for(AddrDTO dto : addrList) {
				System.out.printf("%d\t%s\t%s\t%s\t%s\n", dto.getId(), dto.getName(), dto.getTel(), dto.getAddr(), dto.getChain());
			}
			System.out.println("=====================================================");
		}
		
	}
	
	public void selectAll() {
		List<AddrDTO> addrList = addrDAO.selectAll();
		this.printResult(addrList);
	}
	
	public void findById() {
		long id = 0;
		while(true) {
			System.out.printf("검색할 ID(-Q:%s) >> ", this.quit);
			String strId = scan.nextLine().trim();
			if(strId.equalsIgnoreCase("-Q")) {
				return;
			}
			
			try {
				id = Long.valueOf(strId);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("ID는 숫자만 입력해주세요");
				continue;
			}
			break;
		}
		
		AddrDTO addrDTO = addrDAO.findById(id);
		System.out.println("=====================================================");
		System.out.println("ID\t이름\t전화번호\t주소\t관계");
		System.out.println("=====================================================");
		System.out.printf("%d\t%s\t%s\t%s\t%s\n", addrDTO.getId(), addrDTO.getName(), addrDTO.getTel(), addrDTO.getAddr(), addrDTO.getChain());
		System.out.println("=====================================================");
		
	}
	
	public void findByName() {
		String name = null;
		System.out.printf("검색할 이름(-Q:%s) >> ", this.quit);
		name = scan.nextLine().trim();
		if(name.equalsIgnoreCase("-Q")) return;
		List<AddrDTO> addrList = addrDAO.findByName(name);
		this.printResult(addrList);
	}
	
	public void findByTel() {
		String tel = null;
		System.out.printf("검색할 전화번호(-Q:%s) >> ", this.quit);
		tel = scan.nextLine().trim();
		if(tel.equalsIgnoreCase("-Q")) return;
		List<AddrDTO> addrList = addrDAO.findByTel(tel);
		this.printResult(addrList);
	}
	
	public void findByAddr() {
		String addr = null;
		System.out.printf("검색할 주소(-Q:%s) >> ", this.quit);
		addr = scan.nextLine().trim();
		if(addr.equalsIgnoreCase("-Q")) return;
		List<AddrDTO> addrList = addrDAO.findByAddr(addr);
		this.printResult(addrList);
	}
	
	public void findByChain() {
		String chain = null;
		System.out.printf("검색할 관계(-Q:%s) >> ", this.quit);
		chain = scan.nextLine().trim();
		if(chain.equalsIgnoreCase("-Q")) return;
		List<AddrDTO> addrList = addrDAO.findByChain(chain);
		this.printResult(addrList);
	}
	
	public void selectService() {
		while(true) {
			System.out.println("무슨 내용으로 검색하시겠습니까?");
			System.out.printf("1.전체  2.ID  3.이름  4.전화번호  5.주소  6.관계  0.%s\n", this.quit);
			System.out.print(">> ");
			String getScan = scan.nextLine().trim();
			if(getScan.equals("1")) this.selectAll();
			else if(getScan.equals("2")) this.findById();
			else if(getScan.equals("3")) this.findByName();
			else if(getScan.equals("4")) this.findByTel();
			else if(getScan.equals("5")) this.findByAddr();
			else if(getScan.equals("6")) this.findByChain();
			else if(getScan.equals("0")) return;
			else System.out.println("잘못 입력하였습니다 다시 입력해주세요");
		}
	}
}
