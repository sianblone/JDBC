package com.biz.cbt.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.biz.cbt.persistence.CbtDTO;
import com.biz.cbt.persistence.ExamVO;

public class CbtServiceV1Ext extends CbtServiceV1 {

	@Override
	protected void exam() {
		if(cbtDao.selectAll().size() < 1) {
			System.out.println("등록된 문제가 없습니다");
		}
		
		List<String> cbtQCodesList = cbtDao.selectAllQCode();
		Collections.shuffle(cbtQCodesList);
		
		List<ExamVO> examList = new ArrayList<ExamVO>(); // 문제,정답,오답 저장할 리스트
		
		int curNum = 1; // 문제번호
		int maxNum = cbtQCodesList.size(); // 문제 최대 번호
		
		for(String q_code : cbtQCodesList) {
			
			List<CbtDTO> cbtListByQCode = cbtDao.findByJoinQCode(q_code);
			/*
			 * QCODE 기준으로 검색한 cbt테이블의 리스트에는 4개 레코드가 들어있다
			 * S00001	Q00001	ERASE	N
			 * S00002	Q00001	KILL	N
			 * S00003	Q00001	DROP	Y
			 * S00004	Q00001	DELETE	N
			 */
			
			Collections.shuffle(cbtListByQCode); // 4개 레코드 순서 섞기
			
//			스코프 밖에서 사용할 변수 스코프 밖으로
			List<String> myAnsList = new ArrayList<String>(); // 내 선택 저장할 리스트
			String question = "";
			String ans = "";
			String myAns = "";
			String ansOX = "X";
			int score = 0;
			int exit = 0;
			
			question = qstDao.findByQCode(q_code).getQ_qst(); // TBL_QST에서 문제코드로 문제내용 가져오기
			System.out.println("==========================================================");
			System.out.println("언제든지 -Q를 입력하면 시험을 끝냅니다");
			System.out.println("==========================================================");
			System.out.printf("%d. %s\n", curNum, question);
			System.out.println("----------------------------------------------------------");
			
			int sctOrder = 0; // 선택지 번호
			String[] arrSct = {"①","②","③","④"};
			
			for(CbtDTO cbtDTO : cbtListByQCode) {
				if(cbtDTO.getCb_ans().equalsIgnoreCase("Y")) {
					ans = (sctOrder + 1) + ""; 
				}
				System.out.println( arrSct[sctOrder++] + " " + cbtDTO.getCb_select() );
			}
			
			System.out.println("----------------------------------------------------------");
			int reCount = 0;
				
			while(true) {
				
//				선택 유효성 검사
				while(true) {
					System.out.print("선택 >> ");
					myAns = scan.nextLine().trim();
					if(myAns.equalsIgnoreCase("-Q")) {
						exit = 2;
						break;
					}
					if(!( myAns.equals("1") || myAns.equals("2") || myAns.equals("3") || myAns.equals("4") ) ) {
						System.out.println("정답은 1~4의 숫자로 입력해주세요");
						continue;
					}
					break;
				}
				
				if(exit == 2) break; // -Q를 입력하면 바로 while문 빠져나감, 2면 저장 안함
				
				myAnsList.add(myAns);

				String getScan = ""; // 스코프 밖에서 한 번만 선언하기
				if(myAns.equals(ans)) {
					while(true) {
						ansOX = "O";
						score += 5;
						System.out.print("※ 정답입니다 (Enter:다음문제) ");
						getScan = scan.nextLine();
						if(getScan.equalsIgnoreCase("-Q")) {
							exit = 1;
							break;
						}
						if(getScan.isEmpty()) break;
					}
					
				} else if (reCount < 1 ){
					while(true) {
						System.out.print("※ 오답입니다 (R:다시풀기 N:다음문제) ");
						getScan = scan.nextLine();
						if(getScan.equalsIgnoreCase("-Q")) {
							exit = 1;
							break;
						}
						if(getScan.equalsIgnoreCase("R")) {
							reCount++;
							break;
						} else if(getScan.equalsIgnoreCase("N")) {
							break;
						}
					}
					
				} else {
					while(true) {
						System.out.print("※ 오답입니다 (Enter:다음문제) ");
						getScan = scan.nextLine();
						if(getScan.equalsIgnoreCase("-Q")) {
							exit = 1;
							break;
						}
						if(getScan.isEmpty()) break;
					}
				}
				if(exit == 1 || exit == 2) break; // -Q를 입력하면 바로 while문 빠져나감, 1이면 저장함
				if(reCount++ == 1) continue; // 다시 풀기의 경우 값을 하나 올리고 처음으로
				else break; // 다음 문제
				
			} // while() end
			
			if(exit != 2) {
				ExamVO examVO = ExamVO.builder()
						.question(question)
						.ans(ans)
						.myAnsList(myAnsList)
						.ansOX(ansOX)
						.build();
				
				examList.add(examVO);
			}
			
			if(curNum % 5 == 0 || curNum == maxNum || exit == 1 || exit == 2) {
//				문제 5개마다 or 마지막 문제일 때 or exit가 1이나 2일 때
//				문제,정답,오답 리스트 보이기
				int qNum = 1;
				for(ExamVO vo : examList) {
					System.out.printf("문제%d(%s):%s 정답:%s, 내선택:",qNum++ , vo.getAnsOX(), vo.getQuestion(), vo.getAns());
					
					int size = vo.getMyAnsList().size(); // 다시 풀었을 경우를 감안해 myAnsList 사이즈 계산
					for(int i = 0 ; i < size ; i++) {
						System.out.print( vo.getMyAnsList().get(i) ); // myAnsList의 0번 값부터
						if(i != (size - 1)) { // 인덱스가 size-1이 아니면 뒤에 컴마 붙이기, 인덱스가 size-1이면 뉴라인
							System.out.print(", ");
						} else {
							System.out.println();
						}
					}
				}
				
			}
			
			if(exit == 1 || exit == 2) {
				return;
			}
			
			if(curNum == maxNum) {
				System.out.println("점수:" + score);
				System.out.println("모든 문제를 풀었습니다");
			}
			curNum++; // 현재 문제 번호 1증가
			
		}
	} // for(String q_code : cbtQCodeList) end
		
}
