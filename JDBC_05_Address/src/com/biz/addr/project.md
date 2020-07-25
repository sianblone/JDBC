# 주소록 프로젝트

* 오라클의 tbl_addr 테이블을 참조하여 주소록 프로젝트 수행
* base package : com.biz.addr
* ~.persistence : AddrDTO 클래스
* ~.dao : AddrDAO 클래스

* selectAll();
* findById(long id);
* findByName(String name);
* findByTel(String tel);
* findByChain(String chain);

* ~.service : main()에서 호출할 AddrServiceV1 클래스 생성

* 오라클 DBMS에 접속하기 위해 ojdbc 설정
* lombok 설정

* Test를 위하여 ~.exec : AddrEx_**