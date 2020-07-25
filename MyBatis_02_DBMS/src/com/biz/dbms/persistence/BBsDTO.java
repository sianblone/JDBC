package com.biz.dbms.persistence;

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

public class BBsDTO {
	/*
	 * DTO, VO를 생성할 때 필드(멤버변수) 이름을 table의 칼럼 이름과 같게 설정해야 한다
	 * 그래야 mybatis 자동 setter, getter 호출 기능이 잘 작동한다
	 */
	
	private long bbs_num; //	number
	private String bbs_sub; //	nvarchar2(125 char)
	private String bbs_cont; //	nvarchar2(2000 char)
	private String bbs_user; //	nvarchar2(20 char)
	private int bbs_count; //	number
	private String bbs_date; //	varchar2(10 byte)
	private String bbs_time; //	varchar2(10 byte)

}
