package com.phoenix.mvc.service.cafe.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.phoenix.mvc.service.cafe.CafeTabDao;
import com.phoenix.mvc.service.domain.Cafe;

@Repository("cafeDaoImpl")
public class CafeTabDaoImpl implements CafeTabDao{
	
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	public CafeTabDaoImpl() {
		System.out.println(getClass().getName() + "default Constuctor");
	}
	
	public void addCafe(Cafe cafe) throws Exception {
		sqlSession.insert("CafeMapper.addCafe", cafe);
	}
}