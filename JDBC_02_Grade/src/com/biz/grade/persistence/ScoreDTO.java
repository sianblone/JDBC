package com.biz.grade.persistence;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*
 * DBMS Table과 연관된 Class
 * 보통은 VO와 DTO를 구분하지 않지만 간혹 구분하여 사용하는 경우가 있다
 * 그런 경우는
 * VO(Value Object), DTO(Data Transfer Object)
 * - 공통기능
 * 		Table과 연관하여 CRUD를 수행할 때 데이터를 담아서 method간에 이동할 때 사용
 * - DTO
 * 		물리적 Table과 연관(매핑)하여 완전한 CRUD를 수행할 때
 * 		= getter setter 사용
 * - VO
 * 		VIEW Table, Join된 SQL과 연관하여 주로 READ(=Retrieve 인출)용으로 사용
 * 		= getter만 사용
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

public class ScoreDTO {
	
	long s_id;
	String s_std;
	String s_subject;
	int s_score;
	String s_rem;

}
