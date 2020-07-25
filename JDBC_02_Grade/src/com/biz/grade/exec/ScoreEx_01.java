package com.biz.grade.exec;

import java.util.List;

import com.biz.grade.persistence.ScoreDTO;
import com.biz.grade.service.ScoreService;
import com.biz.grade.service.ScoreServiceV2;

public class ScoreEx_01 {

	public static void main(String[] args) {
		
//		추상클래스로 선언
//						  구현클래스로 생성,초기화
		ScoreService sc = new ScoreServiceV2();
		
		List<ScoreDTO> scoreList = sc.selectAll();
		
		if(scoreList == null) {
			System.out.println("성적 데이터를 읽을 수 없습니다");
			return;
//			main() method에서 return;하면 프로젝트를 종료한다
		}
		for(ScoreDTO dto : scoreList) {
			System.out.println(dto.toString());
		}

	}

}
