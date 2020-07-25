package com.biz.oracle.persistence.dao;

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
	
	String book_code;
	String book_name;
	String book_comp;
	String book_writer;
	int book_price;

}
