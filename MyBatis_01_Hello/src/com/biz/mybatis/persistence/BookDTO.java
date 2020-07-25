package com.biz.mybatis.persistence;

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

public class BookDTO {
	
	private String book_code; //	varchar2(5 byte)
	private String book_name; //	nvarchar2(50 char)
	private String book_comp; //	nvarchar2(50 char)
	private String book_writer; //	nvarchar2(50 char)
	private int book_price; //	number

}
