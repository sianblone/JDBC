package com.biz.grade.exec;

import java.util.List;
import java.util.Scanner;

import com.biz.grade.persistence.domain.ScoreVO;
import com.biz.grade.service.ScoreServiceV2;
import com.biz.grade.service.extend.ScoreServiceV2Ext;

/*
 * 1. 학생 이름을 입력받아서
 * 2. 성적 리스트를 보여주고
 * 3. 리스트를 보고 ID를 입력받아서
 * 4. 해당 ID의 score값을 삭제하기
 */
public class ScoreDelete_01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		ScoreServiceV2 sc = new ScoreServiceV2Ext();
		String strNum = null;
		List<ScoreVO> scList = null;
		
		while (true) {
			System.out.print("학번(-Q:종료) >> ");
			strNum = scan.nextLine();
			if(strNum.equals("-Q")) break;
			
			scList = sc.findByStNum(strNum);
			
			if(scList == null || scList.size() < 1) {
				System.out.println("학생정보에 학번이 없습니다");
				System.out.println("학생정보를 먼저 등록해야 합니다");
				continue;
			}
			
			break;
		}
		if(strNum.equalsIgnoreCase("-q")) return;
		
		while(true) {
			System.out.println("=================================");
			System.out.println("ID\t학생이름\t과목\t점수");
			System.out.println("=================================");
			
			for(ScoreVO vo : scList) {
				System.out.print(vo.getS_id() + "\t");
				System.out.print(vo.getSt_name() + "\t");
				System.out.print(vo.getSb_name() + "\t");
				System.out.print(vo.getS_score() + "\n");
			}
			System.out.println("=================================");
			System.out.print("삭제할 ID(-Q:종료) >> ");
			String strID = scan.nextLine();
			if(strID.equalsIgnoreCase("-q")) break;
			
			try {
				long longID = Long.valueOf(strID);
				int ret = sc.delete(longID);
				if(ret > 0) {
					System.out.println("삭제완료");
					scList = sc.findByStNum(strNum);
				} else {
					System.out.println("삭제실패");
				}
				continue;
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

	}

}
