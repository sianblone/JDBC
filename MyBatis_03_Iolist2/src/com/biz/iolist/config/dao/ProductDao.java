package com.biz.iolist.config.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.biz.iolist.persistence.ProductDTO;

public interface ProductDao {
	
	public String getMaxPCode(); // tbl_product 테이블의 p_code 최대값 가져오기
	public List<ProductDTO> selectAll();
	public ProductDTO findById(String p_code);
	public List<ProductDTO> findByName(String p_name);
	
//	상품정보에서 상품명이 정확히 일치하는 상품 검색하기
	public List<ProductDTO> findBySName(String p_name);
	
	public int insert(ProductDTO productDTO);
	public int update(ProductDTO productDTO);
	
//	매개변수가 1개일 경우에는 mapper로 값을 전달할 때 매개변수명이 일치하지 않아도
//	preparedStatement.setString의 SQL문에 ?가 들어있는 것처럼 잘 작동된다
//	하지만 매개변수가 2개 이상일 경우에는
//	매개변수에 @Param("parameter") Annotation을 반드시 명시해줘야 한다
	
//	public int delete(String p_code);
	public int delete(@Param("p_code") String p_code);
	public List<ProductDTO> findByIPrice(
			@Param("sprice") int sprice,
			@Param("eprice") int eprice);

}
