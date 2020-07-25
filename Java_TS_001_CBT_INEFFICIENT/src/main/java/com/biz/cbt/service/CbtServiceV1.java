package com.biz.cbt.service;

import java.util.List;
import java.util.Scanner;

import com.biz.cbt.mybatis.DBConnection;
import com.biz.cbt.mybatis.dao.CbtDao;
import com.biz.cbt.mybatis.dao.QstDao;
import com.biz.cbt.persistence.CbtDTO;
import com.biz.cbt.persistence.QstDTO;

public class CbtServiceV1 {
	
	Scanner scan;
	CbtDao cbtDao;
	QstDao qstDao;
	
	public CbtServiceV1() {
		// TODO Auto-generated constructor stub
		scan = new Scanner(System.in);
		cbtDao = DBConnection.getSqlSessionFactory().openSession(true).getMapper(CbtDao.class);
		qstDao = DBConnection.getSqlSessionFactory().openSession(true).getMapper(QstDao.class);
	}
	
	public void CbtMenu() {
		while(true) {
			System.out.println("==============================");
			System.out.println("1.문제입력  2.문제풀기  0.종료");
			System.out.println("------------------------------");
			System.out.print("선택 >> ");
			String getScan = scan.nextLine();
			if(getScan.equals("1")) {
				this.insertMenu();
			} else if(getScan.equals("2")) {
				this.exam();
			} else if(getScan.equals("0")) {
				System.out.println("서비스를 종료합니다");
				return;
			}
		}
	}
	
	protected void insertMenu() {
		while(true) {
			System.out.println("==========================================");
			System.out.println("1.문제등록  2.문제수정  3.문제삭제  0.뒤로");
			System.out.println("------------------------------------------");
			System.out.print("선택 >> ");
			String getScan = scan.nextLine();
			if(getScan.equals("1")) {
				this.insert();
			} else if(getScan.equals("2")) {
				this.update();
			} else if(getScan.equals("3")) {
				this.delete();
			} else if(getScan.equals("0")) {
				System.out.println("서비스를 종료합니다");
				return;
			}
		}
	}
	
	protected void insert() {
		System.out.println("==========================================================");
		System.out.println("문제등록 (언제든지 -Q를 입력하면 문제등록을 중단합니다)");
		System.out.println("==========================================================");
		System.out.print("문제입력 >> ");
		String q_qst = scan.nextLine();
		if(q_qst.equalsIgnoreCase("-Q")) return;
		
		System.out.print("답안1번 >> ");
		String cb_select1 = scan.nextLine();
		if(cb_select1.equalsIgnoreCase("-Q")) return;
		
		System.out.print("답안2번 >> ");
		String cb_select2 = scan.nextLine();
		if(cb_select2.equalsIgnoreCase("-Q")) return;
		
		System.out.print("답안3번 >> ");
		String cb_select3 = scan.nextLine();
		if(cb_select3.equalsIgnoreCase("-Q")) return;
		
		System.out.print("답안4번 >> ");
		String cb_select4 = scan.nextLine();
		if(cb_select4.equalsIgnoreCase("-Q")) return;
		
		String ansNum = null;
//		정답 문항 유효성 검사
		while(true) {
			System.out.print("정답 문항 >> ");
			ansNum = scan.nextLine();
			if(ansNum.equalsIgnoreCase("-Q")) return;
			if( !(ansNum.equals("1") || ansNum.equals("2") || ansNum.equals("3") || ansNum.equals("4")) ) {
				System.out.println("1~4 사이의 숫자로 입력해주세요");
				continue;
			}
			break;
		}
		System.out.println("========================================");
		
		QstDTO qDTO = QstDTO.builder()
				.q_qst(q_qst).build();
		
		qstDao.insert(qDTO); // tbl_qst 테이블 등록 및 q_code 가져오기
		
		String cb_qcode = qDTO.getQ_code();
		String cb_ans = "N";
		if(ansNum.equals("1")) cb_ans = "Y";
		else cb_ans = "N";
		CbtDTO cbtDTO = CbtDTO.builder()
				.cb_qcode(cb_qcode)
				.cb_select(cb_select1)
				.cb_ans(cb_ans)
				.build();
		cbtDao.insert(cbtDTO);
		
		if(ansNum.equals("2")) cb_ans = "Y";
		else cb_ans = "N";
		cbtDTO.setCb_qcode(cb_qcode);
		cbtDTO.setCb_select(cb_select2);
		cbtDTO.setCb_ans(cb_ans);
		cbtDao.insert(cbtDTO);
		
		if(ansNum.equals("3")) cb_ans = "Y";
		else cb_ans = "N";
		cbtDTO.setCb_qcode(cb_qcode);
		cbtDTO.setCb_select(cb_select3);
		cbtDTO.setCb_ans(cb_ans);
		cbtDao.insert(cbtDTO);
		
		if(ansNum.equals("4")) cb_ans = "Y";
		else cb_ans = "N";
		cbtDTO.setCb_qcode(cb_qcode);
		cbtDTO.setCb_select(cb_select4);
		cbtDTO.setCb_ans(cb_ans);
		cbtDao.insert(cbtDTO);
		
		System.out.println("※ 등록되었습니다");
		
	}
	
	protected void update() {
		
		String q_code = "";
		while(true) {
			System.out.print("수정할 문제 코드(Enter:전체 문제목록) >> ");
			q_code = scan.nextLine().toUpperCase();
			if(q_code.equalsIgnoreCase("-Q")) return;
			if(q_code.isEmpty()) {
				this.findAllQstsFromCbt();
				continue;
			}
			break;
		}
		QstDTO qDTO = qstDao.findByQCode(q_code);
		if(qDTO == null) {
			System.out.println("등록되지 않은 코드입니다");
			return;
		}
		
		List<String> seqList = cbtDao.findByQCodeForSeq(q_code);
		
		System.out.println("==========================================================");
		System.out.println("문제수정 (언제든지 -Q를 입력하면 문제수정을 중단합니다)");
		System.out.println("==========================================================");
		System.out.print("문제입력 >> ");
		String q_qst = scan.nextLine();
		if(q_qst.equalsIgnoreCase("-Q")) return;
		if(!q_qst.isEmpty()) {
			qDTO.setQ_qst(q_qst);
		}
		
		System.out.print("답안1번 >> ");
		String cb_select1 = scan.nextLine();
		if(cb_select1.equalsIgnoreCase("-Q")) return;
		
		System.out.print("답안2번 >> ");
		String cb_select2 = scan.nextLine();
		if(cb_select2.equalsIgnoreCase("-Q")) return;
		
		System.out.print("답안3번 >> ");
		String cb_select3 = scan.nextLine();
		if(cb_select3.equalsIgnoreCase("-Q")) return;
		
		System.out.print("답안4번 >> ");
		String cb_select4 = scan.nextLine();
		if(cb_select4.equalsIgnoreCase("-Q")) return;
		
		String ansNum = null;
//		정답 문항 유효성 검사
		while(true) {
			System.out.print("정답 문항 >> ");
			ansNum = scan.nextLine();
			if(ansNum.equalsIgnoreCase("-Q")) return;
			if( !(ansNum.equals("1") || ansNum.equals("2") || ansNum.equals("3") || ansNum.equals("4")) ) {
				System.out.println("1~4 사이의 숫자로 입력해주세요");
				continue;
			}
			break;
		}
		System.out.println("========================================");
		
		qstDao.update(qDTO);

		
		String cb_ans = null;
		CbtDTO cbtDTO = cbtDao.findBySeq( seqList.get(0) );
		if(cbtDTO == null) {
			System.out.println("등록된 문제가 없습니다");
		} else {
//			선택1번 등록
			if(ansNum.equals("1")) cb_ans = "Y";
			else cb_ans = "N";
			if(cb_select1.isEmpty()) {
				cbtDTO.setCb_ans(cb_ans);
				cbtDao.update(cbtDTO);
			} else {
				cbtDTO.setCb_select(cb_select1);
				cbtDTO.setCb_ans(cb_ans);
				cbtDao.update(cbtDTO);
			}
			
//			선택2번 등록
			cbtDTO = cbtDao.findBySeq( seqList.get(1) );
			if(ansNum.equals("2")) cb_ans = "Y";
			else cb_ans = "N";
			if(cb_select2.isEmpty()) {
				cbtDTO.setCb_ans(cb_ans);
				cbtDao.update(cbtDTO);
			} else {
				cbtDTO.setCb_select(cb_select2);
				cbtDTO.setCb_ans(cb_ans);
				cbtDao.update(cbtDTO);
			}
			
//			선택3번 등록
			cbtDTO = cbtDao.findBySeq( seqList.get(2) );
			if(ansNum.equals("3")) cb_ans = "Y";
			else cb_ans = "N";
			if(cb_select3.isEmpty()) {
				cbtDTO.setCb_ans(cb_ans);
				cbtDao.update(cbtDTO);
			} else {
				cbtDTO.setCb_select(cb_select3);
				cbtDTO.setCb_ans(cb_ans);
				cbtDao.update(cbtDTO);
			}
			
//			선택4번 등록
			cbtDTO = cbtDao.findBySeq( seqList.get(3) );
			if(ansNum.equals("4")) cb_ans = "Y";
			else cb_ans = "N";
			if(cb_select4.isEmpty()) {
				cbtDTO.setCb_ans(cb_ans);
				cbtDao.update(cbtDTO);
			} else {
				cbtDTO.setCb_select(cb_select4);
				cbtDTO.setCb_ans(cb_ans);
				cbtDao.update(cbtDTO);
			}
				
		}
		
		System.out.println("※ 수정되었습니다");
		
	}
	
	protected void delete() {
		String q_code = "";
		while(true) {
			System.out.print("삭제할 문제 코드(Enter:전체 문제목록) >> ");
			q_code = scan.nextLine().toUpperCase();
			if(q_code.equalsIgnoreCase("-Q")) return;
			if(q_code.isEmpty()) {
				this.findAllQstsFromCbt();
				continue;
			}
			break;
		}
		QstDTO qDTO = qstDao.findByQCode(q_code);
		if(qDTO == null) {
			System.out.println("등록되지 않은 코드입니다");
			return;
		}
		if(cbtDao.deleteByQCode(q_code) > 0 && qstDao.delete(q_code) > 0) {
			System.out.println("※ 삭제되었습니다");
		}
		
	}
	
	protected void findAllQstsFromCbt() {
		
		List<String> cbtQCodesList = cbtDao.selectAllQCode();
		for(String q_code : cbtQCodesList) {
			System.out.println(q_code + "," + qstDao.findByQCode(q_code).getQ_qst());
		}
		
		
	}
	/*
	protected void findQstByQCode(String qcode) {
		
		List<String> cbtQCodesList = ;
		String question = "";
		String ans = "";
		
		for(String q_code : cbtQCodesList) {
			List<CbtDTO> cbtListByQCode = cbtDao.findByJoinQCode(q_code);
			question = qstDao.findByQCode(q_code).getQ_qst();
			System.out.println("==========================================================");
			System.out.printf("%d. %s\n", question);
			System.out.println("----------------------------------------------------------");
			
			int sctOrder = 0; // 선택지 번호
			String[] arrSct = {"①","②","③","④"};
			
			for(CbtDTO cbtDTO : cbtListByQCode) {
				if(cbtDTO.getCb_ans().equalsIgnoreCase("Y")) {
					ans = (sctOrder + 1) + ""; 
				}
				System.out.println( arrSct[sctOrder++] + " " + cbtDTO.getCb_select() );
			}
			
			String[] ansSct = {"❶","❷","❸","❹"};
			System.out.println("정답:" + ansSct[sctOrder - 1]);
			
			System.out.println("----------------------------------------------------------");
		}
	}
	*/
	protected void exam() {
		// TODO Auto-generated method stub
		
	}

}
