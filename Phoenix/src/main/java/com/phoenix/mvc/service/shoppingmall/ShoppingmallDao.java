package com.phoenix.mvc.service.shoppingmall;

import java.util.List;
import java.util.Map;

import com.phoenix.mvc.service.domain.Account;
import com.phoenix.mvc.service.domain.ShoppingmallSearch;
import com.phoenix.mvc.service.domain.User;

public interface ShoppingmallDao {

	public Map<String,Object> getTmonProductList(ShoppingmallSearch search);
	
	public List<Account> getShoppingmallAccount(int userNo);
	
	public Map<String,Object> getTmonPurchaseList(ShoppingmallSearch search, Account account);
	
	public int login(Account account, User user);
	
	public void addShoppingmallAccount(Account account, User user);
}
