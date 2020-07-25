package com.biz.dbms.config;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DBConnection {
	
	private static SqlSessionFactory sqlSessionFactory;
	
	static {
//		*-config.xml 파일을 읽어서 MyBatis 초기 설정값 가져오기
		String configFile = "com/biz/dbms/config/mybatis-config.xml";
		try {
//			xml파일을 읽어서 Stream으로 변환
			InputStream inputStream = Resources.getResourceAsStream(configFile);
//			sqlSessionFactory를 싱글톤으로 생성하기 위한 절차
			SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
			if(sqlSessionFactory == null) {
				sqlSessionFactory = builder.build(inputStream);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} // end static
	
	public static SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}

}
