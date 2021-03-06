package com.phoenix.mvc.common.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.phoenix.mvc.common.Event;
import com.phoenix.mvc.common.Search;
import com.phoenix.mvc.service.cafe.CafeManageDao;
import com.phoenix.mvc.service.cafe.CafeManageService;
import com.phoenix.mvc.service.cafe.CafeMemberService;
import com.phoenix.mvc.service.cafe.CafePostService;
import com.phoenix.mvc.service.domain.Account;
import com.phoenix.mvc.service.domain.Board;
import com.phoenix.mvc.service.domain.Cafe;
import com.phoenix.mvc.service.domain.CafeApplication;
import com.phoenix.mvc.service.domain.CafeGrade;
import com.phoenix.mvc.service.domain.CafeMember;
import com.phoenix.mvc.service.domain.Post;
import com.phoenix.mvc.service.domain.User;
import com.phoenix.mvc.service.user.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Map;

@Component
public class UserInterceptor extends HandlerInterceptorAdapter {
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;

	public UserInterceptor() {
		System.out.println("Mail Interceptor 생성");
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)	throws Exception {
		System.out.println("\n\n================================ UserInterceptor > preHandle START ================================");
		String requestURI = request.getRequestURI();
		System.out.println(">>>>>>>>>>> 요청URL : " + requestURI);

		// pathVariables 사용하기 위한 선언
		Map<String, String> pathVariables = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);

		// 세션의 로그인 정보
		User user = (User) request.getSession().getAttribute("user");

		//회원가입
		if (requestURI.contains("/user/addUser") || requestURI.contains("/user/json/checkUserIdDuplication") || requestURI.contains("/user/json/checkUserPwDuplication") || requestURI.contains("/user/json/sendSms") || requestURI.contains("/user/json/checkUserNicknameDuplication")) {
			if(user == null) {
				return true;
			}
		}
//		if(requestURI.contains("/user/listUser")) {
//			if(user.getUserRoleCode().equals("ur100")) {
//				return true;
//			}else {
//				System.out.println(">>>listUser 관리자 페이지 접근");
//				response.sendRedirect("/error/400");
//				return false;
//			}
//				
//		}
		if(requestURI.contains("/user/listUser")) {
			if(user == null) {
				System.out.println(">>>>>비회원이 페이지 접근");
				response.sendRedirect("/user/loginView");
				return false;
			}else if(user.getUserRoleCode().equals("ur100")){
				return true;
			}else {
				System.out.println(">>>listUser 관리자 페이지 접근");
				response.sendRedirect("/error/400");				
			}
		if(requestURI.contains("/user/getUser") || requestURI.contains("/user/updateUser")) {
			System.out.println(">>>비회원이 페이지 접근");
			response.sendRedirect("/user/loginView");
			return false;
		}	
			
		}
		// 로그인 여부 확인
		if (user == null) {
			System.out.println(">>> User없음");
			response.sendRedirect(request.getContextPath() + "/user/loginView?targetURL=" + request.getRequestURI());
			return false;
		}
		
		// 모든 경우가 만족
		System.out.println("================================ UserInterceptor > preHandle END ================================\n\n");
		return true;
	}
}