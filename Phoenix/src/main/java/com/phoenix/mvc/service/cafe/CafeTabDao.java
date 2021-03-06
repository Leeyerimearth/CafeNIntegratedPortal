package com.phoenix.mvc.service.cafe;

import java.util.List;
import java.util.Map;

import com.phoenix.mvc.common.Search;
import com.phoenix.mvc.service.domain.Board;
import com.phoenix.mvc.service.domain.Cafe;
import com.phoenix.mvc.service.domain.CafeGrade;
import com.phoenix.mvc.service.domain.CafeMember;


public interface CafeTabDao {
	///////////////////////////////준호시작///////////////////////////////////////
	public void addCafe(Cafe cafe)throws Exception;
	
	public void addMemberGrade(CafeGrade cafeGrade)throws Exception;
	
	public void addBoard(Board board)throws Exception;
	
	public int getChangeGrade(int cafeNo)throws Exception;
	
	public int getChangeBoard(int cafeNo)throws Exception;
	///////////////////////////////준호끝///////////////////////////////////////	
	
	///////////////////////////////////기황시작//////////////////////////////////
	public List searchCafe(Search search)throws Exception;
	
	public int cafeTotalCount(Search search) throws Exception;
		
	public List getMyCafeList(Search search) throws Exception;
	
	public int myCafeListTotalCount(Search search) throws Exception;
	
	public List getCafeApplicationListForUser(int userNo);

	public int countCafeApplicationListForUser(int userNo);
	
	public List getMyOwnCafeList(Search search) throws Exception;
	
	public int ownCafeTotalCount(Search search) throws Exception;
	
	public List getCategorizedCafeList(Search search) throws Exception;
	
	public int countCategorizedCafe(Search search) throws Exception;
	/////////////////////////////////기황끝/////////////////////////////////////
	
}
