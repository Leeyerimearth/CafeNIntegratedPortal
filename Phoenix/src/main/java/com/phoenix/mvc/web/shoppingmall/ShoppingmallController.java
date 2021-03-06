package com.phoenix.mvc.web.shoppingmall;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.phoenix.mvc.service.domain.ShoppingmallSearch;
import com.phoenix.mvc.service.domain.User;
import com.phoenix.mvc.service.shoppingmall.ShoppingmallService;

@Controller
@RequestMapping("/shopping/*")
public class ShoppingmallController {
	
	@Autowired
	@Qualifier("shoppingmallServiceImpl")
	private ShoppingmallService shoppingmallService;
	
	public ShoppingmallController() {
		System.out.println(getClass().getName() + "default Constuctor");
	}
	
	//일단 쇼핑몰 버튼 누르면... 어딜로 갈건지/?? my cartList controller타게 할건지
	
	@RequestMapping(value="myCartList") //여기가 메인. 이니까 -> 여기서 셀레니움 연결? 아니면 로그인 했을때 연결? 상품목록 가져오는거는
	public String getMyCartList()		//로그인은 포털로그인 했을때, 카트리스트가져오거나 상품목록 가져오는건 각자 controller에서
	{
		
		return "";
	}
	
	@RequestMapping(value="getProductList")
	public String getShoppingmallProductList(@ModelAttribute ShoppingmallSearch search ,Model model) 
	{
		System.out.println("shopping/getShoppingmallProductList 컨트롤러" );
		// 1.처음들어왔을때 -> searchKeyword없자낭
		if(search.getSearchKeyword()==null )
		{
			search.setSearchKeyword("자전거");
		}
		if(search.getDetectedItemCount()==0)
		{
			search.setDetectedItemCount(0); //일단 무한스크롤 위해서..
		}
		
		if(search.getSearchKeyword()!="") {} //널스트링 처리 꼭 해주기
		Map<String, Object> returnMap = shoppingmallService.getShoppingmallProductList(search);
		
		model.addAttribute("productList",returnMap.get("productList"));
		model.addAttribute("search", returnMap.get("search"));
		
		return "/shoppingMall/listShoppingmallProduct";
	}
	
	@RequestMapping(value="getPurchaseList") //URL 치고 들어올수 없도록 interceptor 등록
	public String getPurchaseList(@ModelAttribute ShoppingmallSearch search, Model model, HttpSession session)
	{
		System.out.println("shopping/getPurchaseList 컨트롤러");
		
		//살려야함
		User user = (User)session.getAttribute("user"); //항상 user있음
		
		Map<String,Object> returnMap = shoppingmallService.getPurchaseList(user, search);
		
		
		model.addAttribute("totalPurchaseList", returnMap.get("totalPurchaseList"));
		model.addAttribute("myAccountList", returnMap.get("myAccountList"));
		//return해주는걸로 if써서 model에 각각 담아주기
		
		return "/shoppingMall/listMyPurchase";
	}
	
}
