package com.java.mybatis;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

/*
 * sqlSession.getMapper(DAO.class); 로 Dao interface를 넘겨주고 DaoImp를 생성할 때
 * Mapper에서 보낸 쿼리를 반환받아 DTO에 담을 때 칼럼이름과 동일한 이름의 변수에 담아준다
 * 즉 CRUD중 Read에서 반환값을 받기 위해 DTO 이름을 칼럼 이름과 똑같이 만들어주는 것이다 
 */
public class BookDTO {
	
	private String book_code;
	private String book_name;
	private String book_comp;
	private String book_writer;
	private int book_price;

}
