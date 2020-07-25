package com.biz.grade.persistence.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*
 * DTO
 * CRUD 중에서
 * Create, Update 용으로 주로 사용한다
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

public class ScoreDTO {
	
	private long s_id; //	number
	private int s_score; //	number(3,0)
	private String s_rem; //	nvarchar2(50 char)
	private String s_sub; //	varchar2(4 byte)
	private String s_std; //	varchar2(5 byte)

}
