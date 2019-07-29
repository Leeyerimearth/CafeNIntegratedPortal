package com.phoenix.mvc.web.chatting;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.phoenix.mvc.common.Search;
import com.phoenix.mvc.service.chatting.ChattingService;
import com.phoenix.mvc.service.domain.ChatRoomForMongo;
import com.phoenix.mvc.service.domain.ChatRoomInfo;
import com.phoenix.mvc.service.domain.User;

@Controller
@RequestMapping("/chat/*")
public class ChattingContoller {
	
	@Autowired
	@Qualifier("chattingServiceImpl")
	private ChattingService chattingService;
	
	public ChattingContoller() {
		System.out.println(getClass().getName() + "default Constuctor");
	}
	
	@RequestMapping("main")
	public String getChatMain() throws Exception {
		int a = 10006;
		int b = 10002;
		int c = 10004;
		Search search = new Search();
		List<Integer> list= new ArrayList<Integer>();
		list.add(a);
		list.add(b);
		list.add(c);
		search.setUserNo(10000);
		search.setTargetUserNos(list);
		Map map = chattingService.getFriendsListForInvite(search);
		List listing = (List)map.get("FriendsForInvite");
		System.out.println(listing);
		return "chat/chatMain";
	}
	
	@RequestMapping("chatFriendsList")
	public String getChatFriendsList(@SessionAttribute("user") User user, Model model) throws Exception {
		Search search = new Search();
		search.setUserNo(user.getUserNo());
		search.setStatus(0);
		Map map = chattingService.getMyFriendsList(search);
		List friendsList = (List) map.get("friendsList");
		model.addAttribute("friendsList", friendsList);
		model.addAttribute("search", search);
		return "chat/listChatFriends";
	}

	@PostMapping("wannaBeFriend")
	public String getWannaBeFriendList(@SessionAttribute("user") User user, Model model) throws Exception {
		Search search = new Search();
		search.setUserNo(user.getUserNo());
		search.setPositionIndex(1);
		Map map=chattingService.getWannaBeFriendList(search);
		List wannaBeFreindList = (List) map.get("wannaBeFreindList");
		model.addAttribute("wannaBeFreindList", wannaBeFreindList);
		model.addAttribute("search", search);
		return "chat/listChatFriends";
	}
	
	@GetMapping("searchChatFriend")
	public String searchChatFriendView(Model model) throws Exception {
		System.out.println("/chat/searchChatFriend 겟방식임");
		Search search = new Search();
		search.setPositionIndex(2);
		model.addAttribute("search", search);
		return "chat/listChatFriends";
	}
	
	@PostMapping("searchChatFriend")
	public String searchChatFriend(@SessionAttribute("user") User user, @ModelAttribute Search search, Model model) throws Exception {
		search.setUserNo(user.getUserNo());
		search.setPositionIndex(2);
		Map map=chattingService.getFriendSearchList(search);
		List searchList = (List) map.get("searchList");
		model.addAttribute("searchList", searchList);
		model.addAttribute("search", search);
		return "chat/listChatFriends";
	}
	
	@RequestMapping("chatRoomList")
	public String getChatRoomList(@SessionAttribute("user") User user, Model model, HttpSession session) throws Exception {
		
		Search search = new Search();
		search.setUserNo(user.getUserNo());
		Map map = chattingService.getMyChatRoomList(search);
		List<ChatRoomInfo> chatRoomList = (List) map.get("chatRoomList");
		List<String> roomNoList = new ArrayList<String>();
		String roomNos = "";
		for (int i=0; i<chatRoomList.size(); i++) {
			String a = chatRoomList.get(i).getChatRoomId();
			roomNoList.add(a);
			roomNos += a+",";
		}
		System.out.println("방목록 스트링 찍어주라 : "+roomNos);
		//session.setAttribute("roomNos",roomNos);
		//session.setAttribute("roomNoList",roomNoList);
		model.addAttribute("chatRoomList",chatRoomList);
		model.addAttribute("roomNos",roomNos);
		return "chat/listChatRoom";
	}
	
	@RequestMapping("enterChatRoom")
	public String getChatRoom(@SessionAttribute("user") User user, @ModelAttribute Search search, Model model) throws Exception {
		search.setUserNo(user.getUserNo());
		Map map = chattingService.getChatRoom(search);
		List chatList = (List) map.get("chatList");
		List userList = (List) map.get("userList");
		List inviteList = (List) map.get("inviteList");
		ChatRoomInfo chatRoomInfo= (ChatRoomInfo)map.get("chatRoomInfo");
		//System.out.println(chatRoom.getChatRoomNo());
		//Search search = new Search();
		//search.setUserNo(user.getUserNo());
		//Map map = chattingService.getMyChatRoomList(search);
		//List chatRoomList = (List) map.get("chatRoomList");
		model.addAttribute("chatList", chatList);
		model.addAttribute("userList", userList);
		model.addAttribute("inviteList", inviteList);
		model.addAttribute("chatRoomInfo", chatRoomInfo);
		return "chat/chatRoom";
	}

	@RequestMapping("addChatRoom")
	public String addChatRoom(@SessionAttribute("user") User user, Model model) throws Exception {
		System.out.println("/chat/addChatRoom 입니다.");
		
		ChatRoomForMongo chatRoomForMongo = new ChatRoomForMongo();
		chatRoomForMongo.setOpenUserNo(user.getUserNo());
		chatRoomForMongo.setRegDate(new Date());
		Map map = new HashMap();
		map.put("chatRoomForMongo",chatRoomForMongo);
		map.put("user",user);
		Map returnMap = chattingService.addChatRoom(map);
		List chatList = (List) returnMap.get("chatList");
		List userList = (List) returnMap.get("userList");
		List inviteList = (List) returnMap.get("inviteList");
		
		ChatRoomInfo chatRoomInfo= (ChatRoomInfo)returnMap.get("chatRoomInfo");
			
		model.addAttribute("chatList", chatList);
		model.addAttribute("userList", userList);
		model.addAttribute("inviteList", inviteList);
		model.addAttribute("chatRoomInfo", chatRoomInfo);
		return "chat/chatRoom";
	}
	


	
	
	
	
	
	
	
	
	@RequestMapping("leaveChatRoom")
	public String deleteChatRoom(@ModelAttribute ChatRoomInfo chatRoomInfo) throws Exception {
		System.out.println(chatRoomInfo);
		chattingService.deleteMyChatRoom(chatRoomInfo);
		return "redirect:/chat/chatRoomList";
	}
	
	@RequestMapping("config")
	public String getChatConfig() {
		
		return "chat/chatConfig";
	}
	
	/////////////////////////jsp 테스트용 페이지들/////////////////////////
	@RequestMapping("test")
	public String testpage() {
		return "chat/test";
	}
	
	@RequestMapping("test1")
	public String testpage1() {
		return "chat/test1";
	}
	
	@RequestMapping("test2")
	public String testpage2() {
		return "chat/test2";
	}
	
	@RequestMapping("test3")
	public String testpage3() {
		return "chat/test3";
	}
	/////////////////////////jsp 테스트용 페이지들/////////////////////////
	
}
