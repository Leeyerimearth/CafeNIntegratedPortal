package com.phoenix.mvc.service.shoppingmall;

import java.util.Map;

import com.phoenix.mvc.service.domain.ShoppingmallSearch;

public interface ShoppingmallDao {

	public Map<String,Object> getTmonProductList(ShoppingmallSearch search);
	
}
