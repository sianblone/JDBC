package com.biz.addr.persistence;

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

public class AddrDTO {
	
	private Long id; //	number
	private String name; //	nvarchar2(50 char)
	private String tel; //	varchar2(50 byte)
	private String addr; //	nvarchar2(125 char)
	private String chain; //	nvarchar2(10 char)

}
