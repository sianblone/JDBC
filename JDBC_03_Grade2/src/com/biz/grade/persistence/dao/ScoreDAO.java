package com.biz.grade.persistence.dao;

import java.sql.Connection;

import java.util.List;

import com.biz.grade.config.DBConnection;
import com.biz.grade.persistence.domain.ScoreDTO;
import com.biz.grade.persistence.domain.ScoreVO;
/*
 * Service 클래스
 * main()에서 호출하여 다양한 연산을 수행하는 용도
 * file 읽기, file 쓰기, file 읽은 후 연산처리
 * JDBC를 연결하여 데이터 연동
 * 
 * DAO(Data Access Object) 클래스 : 데이터베이스 접근 객체
 * Service 클래스의 연산기능 중
 * JDBC와 연동하여
 * 직접 DB를 읽고(SELECT)
 * DB를 UPDATE(INSERT, UPDATE, DELETE) 수행하는 기능을
 * Service로부터 분리
 * 
 * 이제부터 Service 클래스는 비즈니스 로직만 담당하는 역할 수행
 * 
 * 비즈니스 로직
 * 사용자로부터 어떤 데이터를 입력받고 결과를 보여주는 용도
 * main()과 DAO 클래스 사이에서 연산을 주도적으로 수행한다
 * main() 입력된 데이터 -> Service에서 가공, 검증
 *        -> DAO로 보내서 Update 수행
 *        
 * main() 명령실행하면
 * DAO에서 SELECT한 data -> Service에서 다양한 방법으로 가공 후 view 수행
 */
public abstract class ScoreDAO {
	
	protected Connection conn = null;
	
//	ScoreServiceV2 생성자
	public ScoreDAO() {
		this.conn = DBConnection.getConn();
	}
	
	public abstract List<ScoreVO> selectAll();
	public abstract ScoreVO findById(long id);
	public abstract List<ScoreVO> findByStName(String stName);
	
	public abstract int insert(ScoreDTO scDTO);
	public abstract int update(ScoreDTO scDTO);
	public abstract int delete(long id);

	public abstract List<ScoreVO> findByStNum(String strNum);

	public abstract List<ScoreVO> findByStSub(String strSub);

}
