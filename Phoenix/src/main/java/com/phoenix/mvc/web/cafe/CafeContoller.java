package com.phoenix.mvc.web.cafe;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.phoenix.mvc.common.Search;
import com.phoenix.mvc.service.cafe.CafeManageService;
import com.phoenix.mvc.service.cafe.CafeMemberService;
import com.phoenix.mvc.service.cafe.CafeTabService;
import com.phoenix.mvc.service.domain.Board;
import com.phoenix.mvc.service.domain.Cafe;
import com.phoenix.mvc.service.domain.CafeApplication;
import com.phoenix.mvc.service.domain.CafeMember;
import com.phoenix.mvc.service.domain.CafeMemberBlock;
import com.phoenix.mvc.service.domain.User;

@Controller
@RequestMapping("/cafe/*")
public class CafeContoller {
	@Autowired
	@Qualifier("cafeTabServiceImpl")
	private CafeTabService cafeTabService;
	
	@Autowired
	@Qualifier("cafeMemberServiceImpl")
	private CafeMemberService cafeMemberService;
	
	@Autowired
	@Qualifier("cafeManageServiceImpl")
	private CafeManageService cafeManageService;
	
	public CafeContoller() {
		System.out.println(getClass().getName() + "default Constuctor");
	}
	
	@RequestMapping("/{cafeURL}")
	public String getCafeMain(@PathVariable String cafeURL, HttpSession session,Model model) throws Exception
	{
		System.out.println("/cafe/{cafeURL}");
		
		User user = (User)session.getAttribute("user");

		Map map = cafeTabService.getCafeMain(user, cafeURL);
		
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>cafemain cafemember: " + map.get("cafeMember"));
		
		model.addAttribute("cafe", map.get("cafe"));
		model.addAttribute("cafeURL", cafeURL);
		model.addAttribute("noticePostList", map.get("noticePostList"));//공지게시글리스트
		model.addAttribute("cafeMember", map.get("cafeMember"));//내정보
		model.addAttribute("boardList", map.get("boardList"));//카페게시판리스트
		model.addAttribute("cafeApplication",map.get("cafeApplication"));//가입신청
		
		return "cafe/mainCafe";
	}
	
	///////////////////////////////예림시작///////////////////////////////////
//	@RequestMapping("{cafeURL}/getCafeGrade")
//	public String getCafeGrade(@PathVariable String cafeURL, Model model, HttpSession session) throws Exception
//	{
//		System.out.println("/{cafeURL}/manage/updateCafeGradeView : GET");
//
//		//int cafeNo = cafeMemberService.getCafeNo(cafeURL);
//		
//		
//		if(session.getAttribute("user")!=null)
//		{
//			User user = (User) session.getAttribute("user");
//			Map map = cafeTabService.getCafeMain(user, cafeURL); //getCafemember랑 비교 해서 cafeMember정보 심으세여
//			model.addAttribute("cafe",map.get("cafe"));
//			model.addAttribute("cafeMember", map.get("cafeMember"));// 내정보
//		}
//		
//		model.addAttribute("cafeGradeList", cafeGrade);
//		model.addAttribute("cafeURL", cafeURL);
//		
//		return "cafe/getCafeGrade";
//	}
	
	//////////////////////////////예림끝/////////////////////////////////////
	
	@RequestMapping("/{cafeURL}/closedCafe")
	public String ClosedCafe(@PathVariable String cafeURL, HttpServletRequest req, Map<String, Object> map) throws Exception {
		User user = (User) req.getSession().getAttribute("user");
		Cafe cafe = cafeManageService.getCafeInfo(cafeURL);
		
		
		map.put("cafe", cafe);
		map.put("cafeURL", cafeURL);
		
		return "cafe/common/closedCafe";
	}
	
	@RequestMapping("{cafeURL}/needApply")
	public String NeedApply(@PathVariable String cafeURL, HttpServletRequest req, Map<String, Object> map) throws Exception {
		User user = (User) req.getSession().getAttribute("user");
		Cafe cafe = cafeManageService.getCafeInfo(cafeURL);
		
		Search search = new Search();
		search.setSearchCondition("0");
		search.setCafeURL(cafeURL);
		search.setCafeNo(cafe.getCafeNo());
		search.setUserNo(user.getUserNo());
		List<Board> boardList = cafeManageService.getCafeBoardList(search);
		CafeApplication cafeApplication = cafeManageService.getCafeApplicationForMember(search);
		
		map.put("cafe", cafe);
		map.put("boardList", boardList);
		map.put("cafeURL", cafeURL);
		map.put("cafeApplication", cafeApplication);
		
		return "cafe/common/needApply";
	}
	
	@RequestMapping("{cafeURL}/memberBlock")
	public String MemberBlock(@PathVariable String cafeURL, HttpServletRequest req, Map<String, Object> map) throws Exception {
		User user = (User) req.getSession().getAttribute("user");
		Cafe cafe = cafeManageService.getCafeInfo(cafeURL);
		
		Search search = new Search();
		search.setSearchCondition("0");
		search.setCafeURL(cafeURL);
		List<Board> boardList = cafeManageService.getCafeBoardList(search);
		
		search.setUserNo(user.getUserNo());
		CafeMember cafeMember = cafeMemberService.getCafeMemberByURL(search);
		
		//해당 카페 멤버의 정지 내역 출력이 필요함
		CafeMemberBlock cafeMemberBlock = cafeMemberService.getCafeMemberBlockInfo(cafeMember.getMemberNo());
		
		map.put("cafe", cafe);
		map.put("boardList", boardList);
		map.put("cafeURL", cafeURL);
		map.put("cafeMember", cafeMember);
		map.put("cafeMemberBlock", cafeMemberBlock);
		
		return "cafe/common/memberBlock";
	}
	
	@RequestMapping("{cafeURL}/accessDenied")
	public String AccessDenied(@PathVariable String cafeURL, HttpServletRequest req, @RequestParam(required = false) int boardNo, Map<String, Object> map) throws Exception {
		User user = (User) req.getSession().getAttribute("user");
		Cafe cafe = cafeManageService.getCafeInfo(cafeURL);
		
		Search search = new Search();
		search.setSearchCondition("0");
		search.setCafeURL(cafeURL);
		search.setUserNo(user.getUserNo());
		List<Board> boardList = cafeManageService.getCafeBoardList(search);
		CafeMember cafeMember = cafeMemberService.getCafeMemberByURL(search);
		
		Board targetBoard = null;
		
		for(Board board : boardList) {
			if(board.getBoardNo() == boardNo) {
				targetBoard = board;
				break;
			}
		}
		
		map.put("cafe", cafe);
		map.put("boardList", boardList);
		map.put("cafeURL", cafeURL);
		map.put("board", targetBoard);
		map.put("cafeMember", cafeMember);
		
		return "cafe/common/accessDenied";
	}
	
	@RequestMapping("{cafeURL}/deletedPost")
	public String DeletedPost(@PathVariable String cafeURL, HttpServletRequest req, @RequestParam(required = false) int boardNo, Map<String, Object> map) throws Exception {
		User user = (User) req.getSession().getAttribute("user");
		Cafe cafe = cafeManageService.getCafeInfo(cafeURL);
		
		Search search = new Search();
		search.setSearchCondition("0");
		search.setCafeURL(cafeURL);
		List<Board> boardList = cafeManageService.getCafeBoardList(search);
		CafeMember cafeMember = cafeMemberService.getCafeMemberByURL(search);
		
		Board targetBoard = null;
		
		for(Board board : boardList) {
			if(board.getBoardNo() == boardNo) {
				targetBoard = board;
				break;
			}
		}
		
		map.put("cafe", cafe);
		map.put("boardList", boardList);
		map.put("cafeURL", cafeURL);
		
		return "cafe/common/deletedPost";
	}
}
