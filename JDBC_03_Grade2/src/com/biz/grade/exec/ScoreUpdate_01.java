package com.biz.grade.exec;

import java.util.List;

import com.biz.grade.persistence.domain.ScoreDTO;
import com.biz.grade.persistence.domain.ScoreVO;
import com.biz.grade.service.ScoreServiceV3;

/*
 * 1. 학생 이름 입력받기
 * 2. 학생정보 리스트 보여주기
 * 3. 학생정보 리스트를 보고 학번 입력하기
 * 4. 학번에 해당하는 성적 리스트 보이기
 * 5. 성적 리스트를 보고 ID 입력하기
 * 6. 칼럼별로 값 보여주기
 * 7. 잘못된 값 입력하면 원래 값 유지
 * 8. 제대로 된 새로운 값을 입력하면 새로운 값으로 변경
 */
public class ScoreUpdate_01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ScoreServiceV3 sc3 = new ScoreServiceV3();
		ScoreDTO scDTO = null;
		
		List<ScoreVO> scList = sc3.updateStudent();
		if(scList == null) return;
		
		for(ScoreVO vo : scList) {
			System.out.println(vo.toString());
		}
		
		ScoreVO scVO = sc3.selectScore();
		if(scVO == null) {
			System.out.println();
		}
		
		System.out.println(scVO.toString());
		
		int ret = sc3.updateStudent(scVO);
		if(ret > 0) {
			System.out.println("데이터 변경 완료");
		} else {
			System.out.println("데이터 변경 실패");
		}
		
//		String strNum = sc3.inputStudent();
//		if(strNum == null) return;
//		
//		String strSub = sc3.inputSub();
//		if(strSub == null) return;
//		
//		Integer intScore = sc3.inputScore();
//		if(intScore == null) return;
		

	}

}
