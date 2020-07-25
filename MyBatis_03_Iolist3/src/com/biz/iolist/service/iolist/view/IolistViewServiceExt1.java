package com.biz.iolist.service.iolist.view;

import java.util.List;
import java.util.Scanner;

import org.apache.ibatis.session.SqlSession;

import com.biz.iolist.config.DBConnection;
import com.biz.iolist.config.dao.IolistViewDao;
import com.biz.iolist.persistence.IolistVO;

public class IolistViewServiceExt1 {
	
	SqlSession sqlSession;
	IolistViewDao iolistViewDao;
	Scanner scan;
	
	public IolistViewServiceExt1() {
		sqlSession = DBConnection.getSessionFactory().openSession(true);
		iolistViewDao = sqlSession.getMapper(IolistViewDao.class);
		scan = new Scanner(System.in);
	}
	
	public void selectAll() {
		List<IolistVO> iolistList = iolistViewDao.selectAll();
		this.showList(iolistList);
	}
	
	public IolistVO findById() {
		while(true) {
			System.out.println("SEQ>>");
			String getScan = scan.nextLine();
			long io_seq = 0;
			try {
				io_seq = Long.valueOf(getScan);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("SEQ 형식이 잘못되었습니다 숫자로 입력해주세요");
			}
		
		
			IolistVO iolistVO = iolistViewDao.findById(io_seq);
			if(iolistVO == null) {
				System.out.println("검색 결과가 없습니다");
				return null;
			}
			this.showVO(iolistVO);
			return iolistVO;
		}
	}
	
	public List<IolistVO> findByPCode() {
		while(true) {
			System.out.println("상품코드>>");
			String io_pcode = scan.nextLine().toUpperCase();
			
//			유효성검사
			if(!io_pcode.startsWith("P") || io_pcode.length() != 5) {
				System.out.println("상품코드 형식이 잘못되었습니다");
				continue;
			}
			try {
				Integer.valueOf(io_pcode.substring(1));
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("상품코드 형식이 잘못되었습니다");
				continue;
			}
			
			List<IolistVO> iolistList = iolistViewDao.findByPCode(io_pcode);
			if(iolistList == null || iolistList.size() < 1) {
				System.out.println("검색 결과가 없습니다");
				return null;
			}
			this.showList(iolistList);
			return iolistList;
		}
	}
	
	public List<IolistVO> findByDCode() {
		while(true) {
			System.out.println("거래처코드>>");
			String io_dcode = scan.nextLine().toUpperCase();
			
			if(!io_dcode.startsWith("D") || io_dcode.length() != 5) {
				System.out.println("거래처코드 형식이 잘못되었습니다");
				continue;
			}
			try {
				Integer.valueOf(io_dcode.substring(1));
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("거래처코드 형식이 잘못되었습니다");
				continue;
			}
			
			
			List<IolistVO> iolistList = iolistViewDao.findByDCode(io_dcode);
			if(iolistList == null || iolistList.size() < 1) {
				System.out.println("검색 결과가 없습니다");
				return null;
			}
			this.showList(iolistList);
			return iolistList;
		}
	}
	
	public List<IolistVO> findByPName() {
		System.out.println("상품명>>");
		String io_pname = scan.nextLine();
		
		List<IolistVO> iolistList = iolistViewDao.findByPName(io_pname);
		if(iolistList == null || iolistList.size() < 1) {
			System.out.println("검색 결과가 없습니다");
			return null;
		}
		this.showList(iolistList);
		return iolistList;
	}
	
	public List<IolistVO> findByDName() {
		System.out.println("거래처명>>");
		String io_dname = scan.nextLine();
		
		List<IolistVO> iolistList = iolistViewDao.findByDName(io_dname);
		if(iolistList == null || iolistList.size() < 1) {
			System.out.println("검색 결과가 없습니다");
			return null;
		}
		this.showList(iolistList);
		return iolistList;
	}
	
	protected void showList(List<IolistVO> iolistList) {
		
		List<IolistVO> viewList = iolistViewDao.selectAll();
		System.out.println("==================================================================================================");
		System.out.println("매입매출 정보");
		System.out.println("==================================================================================================");
		System.out.println("SEQ\t거래일자\t구분\t거래처\t상품명\t수량\t단가\t합계");
		System.out.println("--------------------------------------------------------------------------------------------------");
		for(IolistVO vo : viewList) {
			this.showVO(vo);
		}
		System.out.println("==================================================================================================");
	}
	
	protected void showVO(IolistVO iolistVO) {
		System.out.print(iolistVO.getIo_seq() + "\t");
		System.out.print(iolistVO.getIo_date() + "\t");
		System.out.print(iolistVO.getIo_inout() + "\t");
		System.out.print("[" + iolistVO.getIo_dcode() + "]" + iolistVO.getIo_dname() + "\t");
		System.out.print("[" + iolistVO.getIo_pcode() + "]" + iolistVO.getIo_pname() + "\t");
		System.out.print(iolistVO.getIo_qty() + "\t");
		System.out.print(iolistVO.getIo_price() + "\t");
		System.out.print(iolistVO.getIo_total() + "\n");
	}
	

}
