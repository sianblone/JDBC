package com.biz.dbms.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.apache.ibatis.session.SqlSession;

import com.biz.dbms.config.DBConnection;
import com.biz.dbms.dao.BBsDao;
import com.biz.dbms.persistence.BBsDTO;

public class BBsServiceV3 {
	
	SqlSession sqlSession;
	Scanner scan;
	
	public BBsServiceV3() {
		// TODO Auto-generated constructor stub
		sqlSession = DBConnection.getSqlSessionFactory().openSession(true);
		scan = new Scanner(System.in);
	}
	
	public void bbsMenu() {
		while(true) {
			System.out.println("===========================================================");
			System.out.println("글번호:내용보기  W:작성  U:수정  D:삭제  L:목록보기  Q:종료");
			System.out.println("===========================================================");
			System.out.print(">>");
			String strMenu = scan.nextLine();
			if(strMenu.equalsIgnoreCase("Q")) {
				System.out.println("서비스를 종료합니다");
				return;
			} else if(strMenu.equalsIgnoreCase("W")) {
				this.bbsWrite();
			} else if(strMenu.equalsIgnoreCase("U")) {
				this.viewBBsList();
				this.bbsUpdate();
				this.viewBBsList();
			} else if(strMenu.equalsIgnoreCase("D")) {
				this.viewBBsList();
				this.bbsDelete();
				this.viewBBsList();
			} else if(strMenu.equalsIgnoreCase("L")) {
				this.viewBBsList();
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
	
	public void bbsUpdate() {
		// 수정할 게시판 번호를 입력받고 해당 번호의 내용을 보여주고 각 항목을 입력받고 Enter를 입력하면 원래 데이터 유지 새로 입력하면 새로운 값으로 변경
		System.out.println("==================================");
		System.out.print("수정할 글번호 >> ");
		String str_num = scan.nextLine();
		long bbs_num = 0;
		try {
			bbs_num = Long.valueOf(str_num);
			this.showCont(bbs_num);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		System.out.print("변경할 제목 >> ");
		String bbs_sub = scan.nextLine();
		System.out.print("변경할 내용 >> ");
		String bbs_cont = scan.nextLine();
		System.out.print("변경할 작성자 >> ");
		String bbs_user = scan.nextLine();
		
		BBsDTO bbsDTO = BBsDTO.builder()
				.bbs_num(bbs_num)
				.bbs_sub(bbs_sub)
				.bbs_cont(bbs_cont)
				.bbs_user(bbs_user)
				.build();
		BBsDao bbsDao = sqlSession.getMapper(BBsDao.class);
		bbsDao.update(bbsDTO);
	}
	
	public void bbsDelete() {
		// 삭제할 게시판 번호를 입력받고 해당 번호의 내용을 보여주고 삭제할지 물어본 다음 삭제
		System.out.println("==================================");
		System.out.print("삭제할 글번호 >> ");
		String str_num = scan.nextLine();
		try {
			long bbs_num = Long.valueOf(str_num);
			this.showCont(bbs_num);
			System.out.print("삭제하시겠습니까?(y/n) >> ");
			String yn = scan.nextLine();
			if(yn.equalsIgnoreCase("y")) {
				BBsDao bbsDao = sqlSession.getMapper(BBsDao.class);
				int ret = bbsDao.delete(bbs_num);
				if(ret > 0)
					System.out.println("삭제 성공");
				else
					System.out.println("삭제 실패");
			}
		} catch (Exception e) {
			// TODO: handle exception
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
			System.out.print(dto.getBbs_sub() + "\t\t");
			System.out.print(dto.getBbs_user() + "\t\t");
			System.out.print(dto.getBbs_count() + "\t\t");
			System.out.print(dto.getBbs_date() + "\t\t");
			System.out.print(dto.getBbs_time() + "\n");
		}
		
	}
	
}
