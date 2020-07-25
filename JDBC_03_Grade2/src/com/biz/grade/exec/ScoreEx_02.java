package com.biz.grade.exec;

import java.util.List;
import java.util.Scanner;

import com.biz.grade.persistence.domain.ScoreVO;
import com.biz.grade.service.ScoreServiceV2;
import com.biz.grade.service.extend.ScoreServiceV2Ext;

public class ScoreEx_02 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ScoreServiceV2 sc = new ScoreServiceV2Ext();
		Scanner scan = new Scanner(System.in);
		
		while(true) {
			System.out.println("=================================");
			System.out.println("이름으로 성적표 검색");
			System.out.println("=================================");
			System.out.print("이름(-Q:Quit) >> ");
			String strName = scan.nextLine();
			if(strName.equalsIgnoreCase("-q")) break;
			
			List<ScoreVO> scList = sc.findByStName(strName);
			if(scList == null || scList.size() < 1) {
				System.out.println("데이터가 없습니다");
				System.out.println("학생 이름을 다시 입력해주세요");
				continue;
			}
			for(ScoreVO vo : scList) {
				System.out.println(vo.toString());
			}
		}
		System.out.println("검색을 종료합니다");

	}

}
