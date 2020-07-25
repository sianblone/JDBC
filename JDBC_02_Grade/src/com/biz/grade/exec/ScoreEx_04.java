package com.biz.grade.exec;

import java.util.Random;
import java.util.Scanner;

import com.biz.grade.persistence.ScoreDTO;
import com.biz.grade.persistence.StudentDTO;
import com.biz.grade.service.ScoreService;
import com.biz.grade.service.ScoreServiceV2;
import com.biz.grade.service.StudentService;
import com.biz.grade.service.StudentServiceV1;

public class ScoreEx_04 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		StudentService st = new StudentServiceV1();
		ScoreService sc = new ScoreServiceV2();
		Scanner scan = new Scanner(System.in);
		Random rnd = new Random();
		
		while(true) {
			System.out.println("==========================");
			System.out.println("성적입력");
			System.out.println("==========================");
			System.out.print("아이디 >> ");
			String strNum = scan.nextLine();
			
			/*
			 * 성적정보를 추가하려 하는데 학번을 입력했을 때
			 * 학생정보 테이블에서 학번을 조회하여 아직 등록되지 않은 학번이라면
			 * 성적정보를 입력할 수 없도록 한다(오류가 발생할 것이므로)
			 * 
			 * 오류가 발생하는 이유는 tbl_score와 tbl_student가 참조무결성(FK)으로 설정이 되어있기 때문에
			 * DB에서 오류가 발생하기 이전에 먼저 알려주고 방지하는 역할을 한다
			 * => Validation (검증)
			 */
			StudentDTO stDTO = st.findById(strNum);
			if(stDTO == null) {
				System.out.println("학생정보에 없는 학번입니다");
				System.out.println("학생정보에 먼저 등록해야 성적을 입력할 수 있습니다");
				continue;
			}
			
			System.out.print("과목코드 >> ");
			String strCode = scan.nextLine();
			
			System.out.print("점수 >> ");
			String strScore = scan.nextLine();
			int intScore = Integer.valueOf(strScore);
			long s_id = rnd.nextInt();
			
			ScoreDTO scDTO = ScoreDTO.builder()
					.s_id(s_id)
					.s_std(strNum)
					.s_subject(strCode)
					.s_score(intScore)
					.build();
			
			int ret = sc.insert(scDTO);
			if(ret > 0) {
				System.out.println("데이터 추가 완료");
			} else {
				System.out.println("데이터 추가 실패");
			}
			
		}
		

	}

}
