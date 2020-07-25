package com.biz.cbt.persistence;

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

public class CbtDTO {
	
	private String cb_seq;//	varchar2(6 byte)
	private String cb_qcode;//	varchar2(6 byte)
	private String cb_select;//	nvarchar2(256 char)
	private String cb_ans;//	varchar2(1 byte)

}
