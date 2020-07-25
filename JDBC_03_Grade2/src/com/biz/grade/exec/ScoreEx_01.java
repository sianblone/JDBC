package com.biz.grade.exec;

import java.util.List;

import com.biz.grade.persistence.domain.ScoreVO;
import com.biz.grade.service.ScoreServiceV2;
import com.biz.grade.service.extend.ScoreServiceV2Ext;

public class ScoreEx_01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ScoreServiceV2 sc = new ScoreServiceV2Ext();
		
		List<ScoreVO> scList = sc.selectAll();
		scList = sc.findByStName("원성빈");
		if(scList == null || scList.size() < 1) {
			System.out.println("데이터가 없습니다");
			return;
		}
		for(ScoreVO vo : scList) {
			System.out.println(vo.toString());
		}

	}

}
