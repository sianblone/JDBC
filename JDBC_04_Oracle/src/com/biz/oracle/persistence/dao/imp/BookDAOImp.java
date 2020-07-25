package com.biz.oracle.persistence.dao.imp;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.biz.oracle.config.DBContract;
import com.biz.oracle.persistence.dao.BookDAO;
import com.biz.oracle.persistence.dao.BookDTO;

public class BookDAOImp extends BookDAO {
	
	@Override
	public List<BookDTO> selectAll() {
		// TODO Auto-generated method stub
		String sql = DBContract.SQL.SELECT_BOOKS;
		PreparedStatement ps = null;
		List<BookDTO> bookList = new ArrayList<>();
		try {
//			sql 문자열을 JDBC 드라이버를 통해 DBMS로 전송하기 위한 데이터형으로 변환하는 절차
			ps = conn.prepareStatement(sql);
			
//			DBMS에게 보낸 SQL을 실행한 결과를 ResultSet 형으로 반환
			ResultSet rs = ps.executeQuery();
			
//			rs가 받은 데이터가 최소 1개의 record 이상이면
//			rs.next() method를 실행하여 반환값이 true가 되고
//			rs 내부에서는 record를 추출할 수 있도록 준비상태가 된다
			
			while(rs.next()) {
//				rs에서 칼럼별로 데이터 추출
//				추출한 데이터를 DTO에 저장하고
//				DTO를 List에 추가
				BookDTO dto = BookDTO.builder()
						.book_code(rs.getString("Book_CODE"))
						.book_name(rs.getString("Book_NAME"))
						.book_comp(rs.getString("Book_COMP"))
						.book_writer(rs.getString("Book_WRITER"))
						.book_price(rs.getInt("Book_PRICE"))
						.build();
				
//				DTO를 List에 추가
				bookList.add(dto);
				
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return bookList;
	}

	@Override
	public BookDTO findById(String book_code) {
		// TODO Auto-generated method stub
		String sql = DBContract.SQL.SELECT_BOOKS;
		sql += " where book_code = ? ";
		PreparedStatement ps = null;
		BookDTO bookDTO = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, book_code);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				bookDTO = this.rs_2_DTO(rs);
			}
			
			rs.close();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bookDTO;
	}

	@Override
	public List<BookDTO> findByName(String book_name) {
		// TODO Auto-generated method stub
		String sql = DBContract.SQL.SELECT_BOOKS;
		sql += " where book_name like '%' || ? || '%' ";
		PreparedStatement ps = null;
		List<BookDTO> bookList = new ArrayList<>();
		try {
			ps = conn.prepareStatement(sql);
			
//			sql 문자열 내의 첫번째 ? 위치에 book_name을 작은따옴표로 묶어서 보내기
			ps.setString(1, book_name);
			
//			sql을 실행하고 값을 반환받기
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				BookDTO dto = this.rs_2_DTO(rs);
//				DTO를 List에 추가
				bookList.add(dto);
			}
			rs.close();
			ps.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bookList;
	}

	@Override
	public List<BookDTO> findByComp(String book_comp) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BookDTO> findByWriter(String book_writer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BookDTO> findByPrice(int price) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BookDTO> findByPrice(int sPrice, int ePrice) {
		// TODO Auto-generated method stub
		String sql = DBContract.SQL.SELECT_BOOKS;
		sql += " where book_price between ? and ? ";
		PreparedStatement ps = null;
		List<BookDTO> bookList = new ArrayList<>();
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, sPrice);
			ps.setInt(2, ePrice);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				bookList.add(this.rs_2_DTO(rs));
			}
			rs.close();
			ps.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return bookList;
	}
	
	private BookDTO rs_2_DTO(ResultSet rs) throws SQLException {
		BookDTO dto = BookDTO.builder()
				.book_code(rs.getString("Book_CODE"))
				.book_name(rs.getString("Book_NAME"))
				.book_comp(rs.getString("Book_COMP"))
				.book_writer(rs.getString("Book_WRITER"))
				.book_price(rs.getInt("Book_PRICE"))
				.build();
		return dto;
			
	}

	@Override
	public int insert(BookDTO bookDTO) {
		// TODO Auto-generated method stub
		String sql = " insert into tbl_books(book_code, book_name, book_comp, book_writer, book_price) ";
		sql += "values( 'B' || LPAD(seq_books.nextval, 4, 0) ,?,?,?,?) ";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, bookDTO.getBook_name());
			ps.setString(2, bookDTO.getBook_comp());
			ps.setString(3, bookDTO.getBook_writer());
			ps.setInt(4, bookDTO.getBook_price());
			
			int ret =  ps.executeUpdate();
			ps.close();
			return ret;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}

	@Override
	public int update(BookDTO bookDTO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(String book_code) {
		// TODO Auto-generated method stub
		String sql = " delete from tbl_books where book_code = ? ";
		PreparedStatement ps = null;

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, book_code);
			int ret = ps.executeUpdate();
			ps.close();
			
			return ret;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}

}
