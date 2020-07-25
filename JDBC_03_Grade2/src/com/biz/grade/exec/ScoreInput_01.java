package com.biz.grade.exec;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.biz.grade.persistence.domain.ScoreDTO;
import com.biz.grade.persistence.domain.ScoreVO;
import com.biz.grade.service.ScoreServiceV2;
import com.biz.grade.service.extend.ScoreServiceV2Ext;

// 학생 성적 데이터 입력
// 1. 학번을 입력받고
// 2. 학번이 학생 테이블에 있으면 성적 입력
//    학번이 없으면 학번 다시 입력
// 3. 과목코드를 입력받고
// 4. 과목코드가 과목테이블에 있으면 점수 입력
//    과목테이블에 없으면 과목코드 다시 입력
// 5. 점수를 입력받고
// 6. 점수가 0~100 범위 내에 있으면 DB에 저장
//    그렇지 않으면 점수 다시 입력
public class ScoreInput_01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		학번을 입력받아서 학번이 학생테이블에 있는지 조회하고
//		있으면 진행, 그렇지 않으면 반복문 다시 실행
		Scanner scan = new Scanner(System.in);
		ScoreServiceV2 sc = new ScoreServiceV2Ext();
		String strNum = null;
		String strSub = null;
		String strScore = null;
		
		
		while (true) {
			System.out.print("학번(-Q:종료) >> ");
			strNum = scan.nextLine();
			if(strNum.equals("-Q")) break;
			List<ScoreVO> scList = sc.findByStNum(strNum);
			if(scList == null || scList.size() < 1) {
				System.out.println("학생정보에 학번이 없습니다");
				System.out.println("학생정보를 먼저 등록해야 합니다");
				continue;
			}
			for(ScoreVO vo : scList) {
				System.out.println(vo.toString());
			}
			break;
		}
		if(strNum.equalsIgnoreCase("-q")) return;
		
//		과목코드처리
		while (true) {
			System.out.print("과목코드(-Q:종료) >> ");
			strSub = scan.nextLine();
			if(strSub.equals("-Q")) break;
			List<ScoreVO> scList = sc.findByStSub(strSub);
			if(scList == null || scList.size() < 1) {
				System.out.println("과목코드가 없습니다");
				continue;
			}
			for(ScoreVO vo : scList) {
				System.out.println(vo.toString());
			}
			break;
			
		}
		if(strSub.equalsIgnoreCase("-q")) return;
		
//		점수처리
		while (true) {
			System.out.print("점수(-Q:종료) >> ");
			strScore = scan.nextLine();
			if(strScore.equals("-Q")) break;
			try {
				int intScore = Integer.valueOf(strScore);
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
		if(strScore.equalsIgnoreCase("-q")) return;
		
		Random rnd = new Random();
		ScoreDTO scDTO = ScoreDTO.builder()
				.s_id(rnd.nextLong())
				.s_std(strNum)
				.s_sub(strSub)
				.s_score(Integer.valueOf(strScore))
				.build();
		
		int intReturn = sc.insert(scDTO);
		if(intReturn > 0) {
			System.out.println("데이터 추가 성공");
		} else {
			System.out.println("데이터 추가 실패");
		}
		
				
		

	}

}
