package com.biz.iolist.config;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DBConnection {
	
	private static SqlSessionFactory sqlSessionFactory;
	
	static {
		String configFile = "com/biz/iolist/config/mybatis-config.xml";
		try {
			InputStream inputStream = Resources.getResourceAsStream(configFile);
			SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
			
			/*
			 * 멀티쓰레드 환경에서 싱글톤을 보장하기 위한 if문
			 */
			if(sqlSessionFactory == null) {
				sqlSessionFactory = builder.build(inputStream);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static SqlSessionFactory getSessionFactory() {
		return sqlSessionFactory;
	}

}
