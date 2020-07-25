package com.biz.grade.service;

import java.util.List;
import java.util.Scanner;

import com.biz.grade.persistence.dao.ScoreDAO;
import com.biz.grade.persistence.dao.StudentDAO;
import com.biz.grade.persistence.dao.SubDAO;
import com.biz.grade.persistence.dao.imp.ScoreDAOImp;
import com.biz.grade.persistence.dao.imp.StudentDAOImp;
import com.biz.grade.persistence.dao.imp.SubDAOImp;
import com.biz.grade.persistence.domain.ScoreDTO;
import com.biz.grade.persistence.domain.ScoreVO;
import com.biz.grade.persistence.domain.StudentDTO;
import com.biz.grade.persistence.domain.SubDTO;

public class ScoreServiceV3 {
	/*
	 * 키보드에서 학생정보(이름, 학번)를 입력받아서
	 * 학생테이블에 데이터가 있는지 조회하는 method
	 */
	StudentDAO stdDAO = null;
	SubDAO subDAO = null;
	ScoreDAO scDAO = null;
	
	Scanner scan = null;
	
	public ScoreServiceV3() {
		// TODO Auto-generated constructor stub
		scan = new Scanner(System.in);
		stdDAO = new StudentDAOImp();
		subDAO = new SubDAOImp();
		scDAO = new ScoreDAOImp();
	}
	
//	Service가 하는 일 : 모든 데이터를 처리하고 DAO에게 CRUD를 하도록 넘겨주는 일
	public int insert(ScoreDTO scDTO) {
		return scDAO.insert(scDTO);
	}
	
	public String inputStudent() {
		String strNum = null;
		while (true) {
			strNum = null; // clear
			
			System.out.print("학번(-Q:종료) >> ");
			strNum = scan.nextLine();
			if(strNum.equals("-Q")) break;
			StudentDTO stdDTO = stdDAO.findById(strNum);
			if(stdDTO == null) {
				System.out.println("학생정보에 학번이 없습니다");
				System.out.println("학생정보를 먼저 등록해야 합니다");
				continue;
			}
			System.out.println(stdDTO.toString());
			break;
		}
		if(strNum.equalsIgnoreCase("-q")) return null;
		return strNum;
		
	}
	
	public String inputSub() {
		String strSub = null;
		while (true) {
			System.out.print("과목코드(-Q:종료) >> ");
			strSub = scan.nextLine();
			if(strSub.equals("-Q")) break;
			SubDTO subDTO = subDAO.findById(strSub);
			if(subDTO == null) {
				System.out.println("과목코드가 없습니다");
				continue;
			}
			System.out.println(subDTO.toString());
			break;
			
		}
		if(strSub.equalsIgnoreCase("-q")) return null;
		return strSub;
	}
	
	/*
	 * method return type을 숫자형 int가 아닌 Integer 클래스로 선언
	 * int형 return은 0~9까지의 숫자형만 사용가능
	 * Integer 클래스형은 0~9까지 숫자형과 null 사용가능
	 */
	public Integer inputScore() {
		String strScore = null;
		Integer intScore = null;
		while (true) {
			System.out.print("점수(-Q:종료) >> ");
			strScore = scan.nextLine();
			if(strScore.equals("-Q")) break;
			try {
				intScore = Integer.valueOf(strScore);
				if(intScore < 0 || intScore > 100) {
					System.out.println("점수는 0~100까지만 입력");
					continue;
				}
				break;
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("성적은 숫자만 입력해주세요");
				continue;
			}
		}
		if(strScore.equalsIgnoreCase("-q")) return null;
		return intScore;
	}
	
	/*
	 * 학번을 입력하면 해당하는 성적 리스트 return
	 */
	public List<ScoreVO> updateStudent() {
		List<ScoreVO> scList = null;
		String strNum = null;
		while(true) {
			System.out.print("학번(-Q:QUIT) >> ");
			strNum = scan.nextLine();
			if(strNum.equals("-Q")) break;
			
			scList = scDAO.findByStNum(strNum);
		
			if(scList == null || scList.size() <1) {
				System.out.println("성적데이터 없음");
				continue;
			}
			break;
		}
		if(strNum.equals("-Q")) return null;
		return scList;
	}
	
//	score 테이블에서 수정(편집)할 데이터 선택
	public ScoreVO selectScore() {
		ScoreVO scVO = null;
		String strScore = null;
		while(true) {
			System.out.println("---------------------------------");
			System.out.print("수정할 ID(-Q:QUIT) >> ");
			strScore = scan.nextLine();
			if(strScore.equals("-Q")) break;
			
			try {
				scVO = scDAO.findById(Integer.valueOf(strScore));
				if(scVO == null) {
					System.out.println("찾는 ID값이 없습니다");
					continue;
				}
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("ID는 숫자만 입력해주세요");
				continue;
			}
			break;
		}
		if(strScore.equals("-Q")) return null;
		return scVO;
	}

	public int updateStudent(ScoreVO scVO) {
		// TODO Auto-generated method stub
//		학번 변경
		System.out.printf("변경할 학번:%s >> ", scVO.getS_std());
		String strNum = scan.nextLine();
//		학번을 입력하고 enter를 누르면 scVO에 저장, 그렇지 않으면 무시
		if(strNum.length() > 0) {
			scVO.setS_std(strNum);
		}
		
		
//		과목코드 변경
		System.out.printf("변경할 과목코드:%s >> ", scVO.getS_sub());
		String strSub = scan.nextLine();
//		과목코드를 입력하고 enter를 누르면 scVO에 저장, 그렇지 않으면 무시
		if(strSub.length() > 0) {
			scVO.setS_sub(strSub);
		}
		
		
//		점수 변경
		System.out.printf("변경할 점수:%d >> ", scVO.getS_score());
		String strScore = scan.nextLine();
		/*
		 * Integer.valueOf()
		 * 숫자 이외의 값이나 그냥 enter를 입력하면 exception이 발생한다
		 * 숫자 이외의 값이나 enter만 입력하면 원래 값을 그대로 유지하고
		 * 숫자를 입력하면 입력한 값으로 변경하도록 try-catch를 이용했다
		 */
		try {
			int intScore = Integer.valueOf(strScore);
			scVO.setS_score(intScore);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
//		scoreVO를 ScoreDTO 형으로 변환시켜서 update() method에 전달
		return scDAO.update(ScoreDTO.builder()
				.s_id(scVO.getS_id())
				.s_std(scVO.getS_std())
				.s_sub(scVO.getS_sub())
				.s_score(scVO.getS_score())
				.build()
				);
		
	}

}
