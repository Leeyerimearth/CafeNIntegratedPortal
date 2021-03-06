package com.phoenix.mvc.service.cafe.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.phoenix.mvc.common.Search;
import com.phoenix.mvc.service.cafe.CafeManageDao;
import com.phoenix.mvc.service.cafe.CafeManageService;
import com.phoenix.mvc.service.cafe.CafeMemberDao;
import com.phoenix.mvc.service.cafe.CafeMemberService;
import com.phoenix.mvc.service.cafe.CafePostDao;
import com.phoenix.mvc.service.cafe.CafePostService;
import com.phoenix.mvc.service.cafe.CafeTabDao;
import com.phoenix.mvc.service.cafe.CafeTabService;
import com.phoenix.mvc.service.domain.Board;
import com.phoenix.mvc.service.domain.Cafe;
import com.phoenix.mvc.service.domain.CafeApplication;
import com.phoenix.mvc.service.domain.CafeGrade;
import com.phoenix.mvc.service.domain.CafeMember;
import com.phoenix.mvc.service.domain.Post;
import com.phoenix.mvc.service.domain.User;

@Service("cafeTabServiceImpl")
@Transactional
public class CafeTabServiceImpl implements CafeTabService {

	@Autowired
	@Qualifier("cafeTabDaoImpl")
	private CafeTabDao cafeTabDao;

	@Autowired
	@Qualifier("cafeManageDaoImpl")
	private CafeManageDao cafeManageDao;

	@Autowired
	@Qualifier("cafeMemberDaoImpl")
	private CafeMemberDao cafeMemberDao;

	@Autowired
	@Qualifier("cafePostDaoImpl")
	private CafePostDao cafePostDao;

	@Autowired
	@Qualifier("cafePostServiceImpl")
	private CafePostService cafePostServiceImpl;

	@Autowired
	@Qualifier("cafeManageServiceImpl")
	private CafeManageService cafeManageService;

	public void setCafeDao(CafeTabDao cafeDao) {
		this.cafeTabDao = cafeDao;
	}

	public void setCafeManageDao(CafeManageDao cafeManageDao) {
		this.cafeManageDao = cafeManageDao;
	}

	public void setCafeMemberDao(CafeMemberDao cafeMemberDao) {
		this.cafeMemberDao = cafeMemberDao;
	}

	public void setCafePostDao(CafePostDao cafePostDao) {
		this.cafePostDao = cafePostDao;
	}

	public CafeTabServiceImpl() {
		System.out.println(getClass().getName() + "default Constuctor");
	}

	/////////////////////////////// 준호시작///////////////////////////////////////
	@Override
	public void addCafe(Cafe cafe) throws Exception {

		cafeTabDao.addCafe(cafe);// 카페생성

		CafeGrade cafeGrade = new CafeGrade();// 카페 생성시 멤버등급 레코드 추가
		cafeGrade.setCafeNo(cafe.getCafeNo());
		cafeTabDao.addMemberGrade(cafeGrade);
		
		CafeMember cafeMember = new CafeMember();// 카페 생성시 멤버추가(매니저)
		cafeMember.setCafeNo(cafe.getCafeNo());
		int i = cafeTabDao.getChangeGrade(cafeMember.getCafeNo());
		cafeMember.setCafeMemberGradeNo(i);
		cafeMember.setUserNo(cafe.getManageUserNo());
		cafeMember.setMemberNickname("매니저");
		cafeMemberDao.addCafeMember(cafeMember);
		
		Board board = new Board();// 카페 생성시 게시판 추가
		int j = cafeTabDao.getChangeBoard(cafeMember.getCafeNo());
		board.setAccessGrade(String.valueOf(j));
		board.setCafeNo(cafe.getCafeNo());
		board.setCafeURL(cafe.getCafeURL());
		cafeTabDao.addBoard(board);
	}

	public boolean checkCafeNameDuplication(String cafeName) throws Exception {

		boolean result = true;

		Cafe cafe = cafeManageDao.getCafeName(cafeName);

		if (cafe != null) {
			result = false;
		}
		return result;
	}

	public boolean checkCafeURLDuplication(String CafeURL) throws Exception {

		boolean result = true;

		Cafe cafe = cafeManageDao.getCafeURL(CafeURL);

		if (cafe != null) {
			result = false;
		}
		return result;
	}

	/////////////////////////////// 준호끝///////////////////////////////////////

//////////////////////////////////////예림시작/////////////////////////////////////
	@Override
	public Map getCafeMain(User user, String cafeURL) throws Exception { // 예림예림

		CafeMember cafeMember = null;
		Map map = new HashMap();
		Search search = new Search();

		int cafeNo = cafeManageDao.getCafeNo(cafeURL);

		// 로그인 안되있을 경우
		if (user != null) {
			cafeMember = cafeMemberDao.getCafeMember(cafeNo, user.getUserNo());// 카페번호랑회원번호로
		}

		map.put("cafeMember", cafeMember);
		map.put("cafeURL", cafeURL);
// 공통
// 1.카페의 공지게시글 가져오기 ->승규 dao (카페URL , 보드코드)
		search.setCafeURL(cafeURL);
		List<Post> noticePostList = cafePostDao.getPostListByNotice(search);
		map.put("noticePostList", noticePostList);
// 2.cafe정보가져오기 ->준호

// 3.카페 게시판 list ->내가한거네
		List boardList = cafeManageDao.getCafeBoard(cafeNo);
		map.put("boardList", boardList);

	/////// 카페정보
		Cafe cafe = cafeManageDao.getCafeInfo(cafeNo);
		map.put("cafe", cafe);

		return map;
	}

/////////////////////////////////////////////// 예림 끝/////////////////////////////////////

////////////////////////////// 기황시작//////////////////////////////

	@Override
	public Map getCafeHome(Search search) throws Exception {
		Map map = new HashMap();
		List myCafelist = new ArrayList();
		int totalCount;
		if (search.getStatus() == 2) {
			totalCount = cafeTabDao.ownCafeTotalCount(search);
			myCafelist = cafeTabDao.getMyOwnCafeList(search);
		} else {
			totalCount = cafeTabDao.myCafeListTotalCount(search);
			myCafelist = cafeTabDao.getMyCafeList(search);
		}
		List categorizedCafeList = cafeTabDao.getCategorizedCafeList(search);
		map.put("totalCount", totalCount);
		map.put("myCafelist", myCafelist);
		map.put("categorizedCafeList", categorizedCafeList);
		return map;
	}

	@Override
	public Map getCafeApplicationListForUser(Search search) throws Exception {
// TODO Auto-generated method stub
		Map map = new HashMap();

		List applicationList = cafeTabDao.getCafeApplicationListForUser(search.getUserNo());
		int totalCount = cafeTabDao.countCafeApplicationListForUser(search.getUserNo());

		map.put("applicationList", applicationList);
		map.put("totalCount", new Integer(totalCount));

		return map;
	}

	@Override
	public Map searchCafe(Search search) throws Exception {
		int totalCount;
		Map map = new HashMap();
		List cafeList = new ArrayList();
		List postList = new ArrayList();
		if (search.getSearchCondition().equals("0")) {
			cafeList = cafeTabDao.searchCafe(search);
			search.setSearchCondition("2");
			Map postMap = cafePostServiceImpl.getPostListBySearch(search);
			search.setSearchCondition("0");
			postList = (List) postMap.get("postList");
// totalCount = (int) postMap.get("postTotalCount");
// postList = cafeTabDao.seachPost(search);
			map.put("cafeList", cafeList);
			map.put("postList", postList);
			map.put("totalCount", new Integer(10));
		}
		if (search.getSearchCondition().equals("1")) {
			cafeList = cafeTabDao.searchCafe(search);
			totalCount = cafeTabDao.cafeTotalCount(search);
			map.put("cafeList", cafeList);
			map.put("totalCount", new Integer(totalCount));
		}
		if (search.getSearchCondition().equals("2")) {
			Map postMap = cafePostServiceImpl.getPostListBySearch(search);
			postList = (List) postMap.get("postList");
			totalCount = (int) postMap.get("postTotalCount");
			map.put("postList", postList);
			map.put("totalCount", new Integer(totalCount));
		}

		return map;
	}

	@Override
	public Map getCategorizedCafeList(Search search) throws Exception {
		Map map = new HashMap();
		int totalCount = cafeTabDao.countCategorizedCafe(search);
		List cafeList = cafeTabDao.getCategorizedCafeList(search);
		map.put("cafeList", cafeList);
		map.put("totalCount", new Integer(totalCount));
		return map;
	}

	@Override
	public Map getNewsFeed(int userNo) throws Exception {
		Map map = new HashMap();

		List newsFeed = cafePostDao.getMyPostList(userNo);
		map.put("newsFeed", newsFeed);
		
		return map;
	}
	
	@Override
	public int updateFavorite(CafeMember cafeMember) throws Exception {
		return cafeMemberDao.updateFavorite(cafeMember);
	}
	
////////////////////////////// 기황끝//////////////////////////////
	
}
