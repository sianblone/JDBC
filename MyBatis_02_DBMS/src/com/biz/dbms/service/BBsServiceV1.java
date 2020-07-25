package com.biz.dbms.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.apache.ibatis.session.SqlSession;

import com.biz.dbms.config.DBConnection;
import com.biz.dbms.dao.BBsDao;
import com.biz.dbms.persistence.BBsDTO;

public class BBsServiceV1 {
	
	SqlSession sqlSession;
	Scanner scan;
	
	public BBsServiceV1() {
		// TODO Auto-generated constructor stub
		sqlSession = DBConnection.getSqlSessionFactory().openSession(true);
		scan = new Scanner(System.in);
	}
	
	public void bbsMenu() {
		System.out.println("1.내용 2.작성 3.수정 4.삭제 0.종료");
		String strMenu = scan.nextLine();
		if(strMenu.equals("0")) {
			return;
		} else if(strMenu.equals("1")) {
			
		} else if(strMenu.equals("2")) {
			
		} else if(strMenu.equals("3")) {
			
		} else if(strMenu.equals("4")) {
			
		} else {
			System.out.println("0 또는 1~4까지 선택하세요");
		}
	}

	public void writeBBS() {
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
			System.out.println("글 작성!");
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
		System.out.printf("글번호\t제목\t내용\t작성자\t조회수\t날짜\t시각");
		for(BBsDTO dto : bbsList) {
			System.out.print(dto.getBbs_num() + "\t");
			System.out.print(dto.getBbs_sub() + "\t");
			System.out.print(dto.getBbs_cont() + "\t");
			System.out.print(dto.getBbs_user() + "\t");
			System.out.print(dto.getBbs_count() + "\t");
			System.out.print(dto.getBbs_date() + "\t");
			System.out.print(dto.getBbs_time() + "\n");
		}
		
	}
	
}
