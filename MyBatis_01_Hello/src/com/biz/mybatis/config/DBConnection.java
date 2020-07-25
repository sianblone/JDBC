package com.biz.mybatis.config;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DBConnection {
	
//	sqlSessionFactory
//	sqlSession을 필요에 따라 생성, 삭제 등을 관리할 클래스
	private static SqlSessionFactory sqlSessionFactory;
	
	static {
		String configFile = "com/biz/mybatis/config/MyBatis-config.xml";
		try {
			InputStream inputStream = Resources.getResourceAsStream(configFile);
			if(sqlSessionFactory == null) {
				SqlSessionFactoryBuilder ssfb = new SqlSessionFactoryBuilder();
				sqlSessionFactory = ssfb.build(inputStream);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static SqlSessionFactory getSqlSessionFactory () {
		return sqlSessionFactory;
	}

}
