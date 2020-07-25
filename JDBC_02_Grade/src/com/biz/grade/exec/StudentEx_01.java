package com.biz.grade.exec;

import java.util.List;
import java.util.Scanner;

import com.biz.grade.persistence.ScoreDTO;
import com.biz.grade.persistence.StudentDTO;
import com.biz.grade.service.ScoreService;
import com.biz.grade.service.ScoreServiceV2;
import com.biz.grade.service.StudentService;
import com.biz.grade.service.StudentServiceV1;

public class StudentEx_01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StudentService st = new StudentServiceV1();
		ScoreService sc = new ScoreServiceV2();
		Scanner scan = new Scanner(System.in);
		
		while(true) {
			System.out.println("========================================");
			System.out.println("성적처리 v2");
			System.out.println("========================================");
			System.out.print("학생이름 >> ");
			String strName = scan.nextLine();
			List<StudentDTO> stdList = st.findByName(strName);
			
//			= new ArrayList<>(); 로 생성하면 null이 나오지 않고
//			size가 0인 값이 return 되기 때문에
//			null이거나 size() < 1인 경우를 검사한다
			if(stdList == null || stdList.size() < 1) {
				System.out.println("찾는 학생이 없습니다");
				continue;
			}
			for(StudentDTO sDTO : stdList) {
				
				System.out.println(sDTO.toString()); // stdList의 현재 인덱스 값을 검사하는 debugging 코드
				
				List<ScoreDTO> scList = sc.findByName(sDTO.getSt_num());
				if(scList != null) {
					for(ScoreDTO scDTO : scList) {
						System.out.println(scDTO.toString());
					}
				}
			}
			
		}

	}

}
