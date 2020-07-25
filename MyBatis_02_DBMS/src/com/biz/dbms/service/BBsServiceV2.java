package com.biz.dbms.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.apache.ibatis.session.SqlSession;

import com.biz.dbms.config.DBConnection;
import com.biz.dbms.dao.BBsDao;
import com.biz.dbms.persistence.BBsDTO;

public class BBsServiceV2 {
	
	SqlSession sqlSession;
	Scanner scan;
	
	public BBsServiceV2() {
		// TODO Auto-generated constructor stub
		sqlSession = DBConnection.getSqlSessionFactory().openSession(true);
		scan = new Scanner(System.in);
	}
	
	public void bbsMenu() {
		while(true) {
			System.out.println("===============================================");
			System.out.println("글번호:내용보기  W:작성  U:수정  D:삭제  Q:종료");
			System.out.println("===============================================");
			System.out.print(">>");
			String strMenu = scan.nextLine();
			if(strMenu.equalsIgnoreCase("Q")) {
				return;
			} else if(strMenu.equals("W")) {
				this.bbsWrite();
			} else if(strMenu.equals("U")) {
				
			} else if(strMenu.equals("D")) {
				
			} else {
				this.showCont(Long.valueOf(strMenu));
			}
		}
	}
	
	public void showCont(long bbs_num) {
		BBsDao bbsDao = sqlSession.getMapper(BBsDao.class);
		BBsDTO bbsDTO = bbsDao.findByNum(bbs_num);
		if(bbsDTO == null)
			System.out.println("글이 없습니다");
		else {
			System.out.println("----------- 글내용 ----------");
			System.out.println(bbsDTO.getBbs_cont());
		}
	}

	public void bbsWrite() {
		// TODO Auto-generated method stub
		String user;
		String sub;
		String cont;
		
		while(true) {
			System.out.print("작성자>>");
			user = scan.nextLine();
			if(user.trim().length() < 1) {
				System.out.println("작성자를 입력해주세요");
				continue;
			}
			break;
		}
		
		while(true) {
			System.out.print("제목>>");
			sub = scan.nextLine();
			if(sub.trim().length() < 1) {
				System.out.println("제목을 입력해주세요");
				continue;
			}
			break;
		}
		
		while(true) {
			System.out.print("내용>>");
			cont = scan.nextLine();
			if(cont.trim().length() < 1) {
				System.out.println("내용을 입력해주세요");
				continue;
			}
			break;
		}
		
		Date date = new Date(System.currentTimeMillis());
//		현재 시각 가져오기
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//		date 날짜형 값을 "2019-10-24"의 문자열형으로 변환
		
		SimpleDateFormat tf = new SimpleDateFormat("HH:mm:ss");
//		date 날짜형 값을 "14:00:00"의 문자열형으로 변환
		
		String curDate = df.format(date);
		String curTime = tf.format(date);
		
//		입력받은 데이터와 날짜, 시각을 DTO에 담기
		BBsDTO bbsDTO = BBsDTO.builder()
				.bbs_date(curDate)
				.bbs_time(curTime)
				.bbs_user(user)
				.bbs_sub(sub)
				.bbs_cont(cont)
				.build();
		
		BBsDao bbsDao = sqlSession.getMapper(BBsDao.class);
		// 설명 : 예를 들어, getMapper()에게 BBsDao 인터페이스를 넘겨주고 mapper의 SQL로 DaoImp 클래스를 만듦
		//        BBsDao bbsDao = new BBsDaoImp(); 형태가 된다
		int ret = bbsDao.insert(bbsDTO);
		
		if(ret > 0) {
			System.out.println("글 작성 완료!");
		} else {
			System.out.println("글 작성 실패!");
		}
	
	}
	
	public void viewBBsList() {
		BBsDao bbsDao = sqlSession.getMapper(BBsDao.class);
		List<BBsDTO> bbsList = bbsDao.selectAll();
		System.out.println("=================================================");
		System.out.println("슈퍼 BBS v1");
		System.out.println("=================================================");
		System.out.println("글번호\t\t제목\t\t작성자\t\t조회수\t\t날짜\t\t시각");
		for(BBsDTO dto : bbsList) {
			System.out.print(dto.getBbs_num() + "\t\t");
			System.out.print(dto.getBbs_cont() + "\t\t");
			System.out.print(dto.getBbs_user() + "\t\t");
			System.out.print(dto.getBbs_count() + "\t\t");
			System.out.print(dto.getBbs_date() + "\t\t");
			System.out.print(dto.getBbs_time() + "\n");
		}
		
	}
	
}
