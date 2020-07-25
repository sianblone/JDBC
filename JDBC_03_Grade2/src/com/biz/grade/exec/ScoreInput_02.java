package com.biz.grade.exec;

import java.util.Random;

import com.biz.grade.persistence.domain.ScoreDTO;
import com.biz.grade.service.ScoreServiceV3;

public class ScoreInput_02 {
	
	public static void main(String[] args) {
		
		ScoreServiceV3 sc3 = new ScoreServiceV3();
		
		/*
		
		String strNum = sc.inputStudent();
		if(strNum != null) {
			String strSub = sc.inputSub();
			if(strSub != null) {
				String strScore = sc.inputScore();
				if(strScore != null) {
					// input 처리
				}
			}
		}
		
		*/
		String strNum = sc3.inputStudent();
		if(strNum == null) return;
		String strSub = sc3.inputSub();
		if(strSub == null) return;
		Integer intScore = sc3.inputScore();
		if(intScore == null) return;
		
		Random rnd = new Random();
		ScoreDTO scDTO = ScoreDTO.builder()
				.s_id(rnd.nextLong())
				.s_std(strNum.toUpperCase())
				.s_sub(strSub.toUpperCase())
				.s_score(intScore)
				.build();
		
		int intReturn = sc3.insert(scDTO);
		if(intReturn > 0) {
			System.out.println("데이터 추가 성공");
		} else {
			System.out.println("데이터 추가 실패");
		}
		
//		int형은 만약 값을 입력하지 않는 경우 일반적으로 0을 return할 것이다
//		그런 경우 값을 입력하지 않아서 0인지 다른 이유에서 0이 입력된 것인지 판단하기 어려워진다
//		이럴때 Integer형을 사용하게 되면 null을 return 할 수 있어서 0값과 구분하여 사용할 수 있다
		
	}

}
