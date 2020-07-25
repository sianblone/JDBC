package com.biz.addr.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.biz.addr.config.DBConnection;
import com.biz.addr.persistence.AddrDTO;

public class AddrDAO {
	
	private Connection conn = null;
	
	public AddrDAO() {
		// TODO Auto-generated constructor stub
		this.conn = DBConnection.getConn();
	}
	
	private AddrDTO rs_2_DTO(ResultSet rs) throws SQLException {
		AddrDTO addrDTO = AddrDTO.builder()
		.id(rs.getLong("id"))
		.name(rs.getString("name"))
		.tel(rs.getString("tel"))
		.addr(rs.getString("addr"))
		.chain(rs.getString("chain"))
		.build();
		
		return addrDTO;
	}
	
	public List<AddrDTO> selectAll() {
		List<AddrDTO> addrList = new ArrayList<>();
		String sql = " select * from tbl_addr ";
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				addrList.add( this.rs_2_DTO(rs) );
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return addrList;
	}
	
	public AddrDTO findById(long id) {
		AddrDTO addrDTO = new AddrDTO();
		String sql = " select * from tbl_addr where id = ? ";
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				addrDTO = this.rs_2_DTO(rs);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return addrDTO;
	}
	
	public List<AddrDTO> findByName(String name){
		List<AddrDTO> addrList = new ArrayList<>();
		String sql = " select * from tbl_addr where name like '%' || ? || '%' ";
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				addrList.add( this.rs_2_DTO(rs) );
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return addrList;
	}
	
	public List<AddrDTO> findByTel(String tel){
		List<AddrDTO> addrList = new ArrayList<>();
		String sql = " select * from tbl_addr where tel like '%' || ? || '%'  ";
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, tel);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				addrList.add( this.rs_2_DTO(rs) );
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return addrList;
	}
	
	public List<AddrDTO> findByAddr(String addr){
		List<AddrDTO> addrList = new ArrayList<>();
		String sql = " select * from tbl_addr where addr like '%' || ? || '%'  ";
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, addr);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				addrList.add( this.rs_2_DTO(rs) );
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return addrList;
	}
	
	public List<AddrDTO> findByChain(String chain){
		List<AddrDTO> addrList = new ArrayList<>();
		String sql = " select * from tbl_addr where chain like '%' || ? || '%'  ";
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, chain);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				addrList.add( this.rs_2_DTO(rs) );
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return addrList;
	}
	
	public int insert(AddrDTO addrDTO) {
		String sql = " insert into tbl_addr(id, name, tel, addr, chain) ";
		sql += " values(seq_addr.nextval, ?, ?, ?, ?) ";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, addrDTO.getName());
			ps.setString(2, addrDTO.getTel());
			ps.setString(3, addrDTO.getAddr());
			ps.setString(4, addrDTO.getChain());
			
			int ret = ps.executeUpdate();
			ps.close();
			return ret;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public int update(AddrDTO addrDTO) {
		String sql = " update tbl_addr set ";
		sql += " NAME = ?, TEL = ?, ADDR = ?, CHAIN = ? where ID = ? ";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, addrDTO.getName());
			ps.setString(2, addrDTO.getTel());
			ps.setString(3, addrDTO.getAddr());
			ps.setString(4, addrDTO.getChain());
			ps.setLong(5, addrDTO.getId());
			
			int ret = ps.executeUpdate();
			ps.close();
			return ret;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public int delete(long id) {
		String sql = " delete from tbl_addr where id = ? ";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, id);
			
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
