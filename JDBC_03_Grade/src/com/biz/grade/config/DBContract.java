package com.biz.grade.config;

/*
 * DBMS에서 연결하고 사용할 SQL등과 관련된 정보를 등록할 static 클래스
 */
public class DBContract {
	
	public static class CONNECTION {
		public static final String JdbcDriver = "oracle.jdbc.OracleDriver";
		public static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
		public static final String USER = "grade";
		public static final String PASSWORD = "grade";
	}
	
	public static class SQL {
		public static final String SELECT_TBL_SCORE = " select s_id, s_score, s_rem, s_sub, s_std from tbl_score ";
		public static final String SELECT_VIEW_SCORE =
				" select "
				+ " S_ID,"
				+ " S_STD,"
				+ " ST_NAME,"
				+ " ST_GRADE,"
				+ " ST_DEPT,"
				+ " D_NAME,"
				+ " D_TEL,"
				+ " S_SUB,"
				+ " SB_NAME,"
				+ " S_SCORE from view_score ";
		
		public static final String INSERT_SCORE =
				" insert into tbl_score ("
				+ " S_ID,"
				+ " S_STD,"
				+ " S_SUB,"
				+ " S_SCORE,"
				+ " S_REM) "
				+ " values (?,?,?,?,?) ";
	}

}
