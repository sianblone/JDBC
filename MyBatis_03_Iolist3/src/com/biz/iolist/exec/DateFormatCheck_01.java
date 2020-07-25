package com.biz.iolist.exec;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateFormatCheck_01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		 * 입력하는 날짜 형식을 지정하고 싶을 때
		 * 입력하는 사람이 지정한 형식대로 입력하지 않으면 Exception이 발생한다
		 * 이 exception을 이용해 try-catch문으로 다시 입력받을 수 있다
		 */
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			sd.parse("20190101");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			System.out.println("날짜 형식이 잘못되었습니다");
		}

	}

}
