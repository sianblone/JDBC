package com.biz.iolist.exec;

import java.util.Scanner;

import org.apache.ibatis.session.SqlSession;

import com.biz.iolist.config.DBConnection;
import com.biz.iolist.config.dao.IolistDao;
import com.biz.iolist.service.DeptServiceV1;
import com.biz.iolist.service.IolistServiceV1;
import com.biz.iolist.service.IolistViewServiceV1;
import com.biz.iolist.service.ProductServiceV1;

public class IolistEx_01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IolistServiceV1 is = new IolistServiceV1();
		IolistViewServiceV1 ivs = new IolistViewServiceV1();
		DeptServiceV1 ds = new DeptServiceV1();
		ProductServiceV1 vs = new ProductServiceV1();
		
		vs.selectAll();
		

	}

}
