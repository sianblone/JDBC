package com.biz.iolist.exec;

import com.biz.iolist.service.DeptServiceV1;

public class DeptEx_01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DeptServiceV1 ds = new DeptServiceV1();
		ds.selectAll();
		ds.findByNameAndCEO();

	}

}
