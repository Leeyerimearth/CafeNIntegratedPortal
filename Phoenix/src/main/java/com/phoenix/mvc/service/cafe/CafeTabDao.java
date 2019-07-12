package com.phoenix.mvc.service.cafe;

import java.util.List;
import java.util.Map;

import com.phoenix.mvc.common.Search;
import com.phoenix.mvc.service.domain.Cafe;


public interface CafeTabDao {
	///////////////////////////////준호시작///////////////////////////////////////
	public void addCafe(Cafe cafe)throws Exception;
	///////////////////////////////준호끝///////////////////////////////////////	
	
	///////////////////////////////////기황시작//////////////////////////////////
	public List searchCafe(Search search)throws Exception;
	
	public int cafeTotalCount(Search search) throws Exception;
	
	public int postTotalCount(Search search) throws Exception;
	
	public List getCategorizedCafeList(Search search)throws Exception;
	
	public int countCategorizedCafe(Search search) throws Exception;
	/////////////////////////////////기황끝/////////////////////////////////////
}
