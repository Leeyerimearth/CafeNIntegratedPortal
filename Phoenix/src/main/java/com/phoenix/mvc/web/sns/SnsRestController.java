package com.phoenix.mvc.web.sns;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phoenix.mvc.common.Page;
import com.phoenix.mvc.common.Search;
import com.phoenix.mvc.service.domain.TimeLine;
import com.phoenix.mvc.service.sns.impl.SnsServiceImpl;

@RestController
@RequestMapping("/sns/*")
public class SnsRestController {

	@Autowired
	@Qualifier("snsServiceImpl")
	private SnsServiceImpl snsService;

	public SnsRestController() {
		System.out.println(this.getClass().getName() + "생성자 시작");
	}

	@RequestMapping(value = "/json/getTimeLine")
	public Map getTimeLine(@RequestBody Search search) throws Exception {

		System.out.println("/json/getTimeLine 시작!");

		if (search.getFace() == 100) {

			search.setFbId("wlsgml1416@naver.com");
			search.setFbPw("011!wlslgogo");

		} 
		
		if (search.getInsta() == 200) {

			search.setIgId("rlawlsgml1416");
			search.setIgPw("011!wlslgogo");
			
			//search.setIgId("andaralamira");
			//search.setIgPw("011wlslgogo");
			
		}

		System.out.println("@@search@@ " + search);

		Map returnMap = new HashMap();

		Map<String, Object> fbMap = snsService.getFaceBookTimeLineList(search);
		Map<String, Object> igMap = snsService.getInstaTimeLineList(search);
		System.out.println("fbMap이욤 : " + fbMap);
		System.out.println("igMap이욤 : " + igMap);

		returnMap.put("faceTimeLine", fbMap.get("timeLine"));
		returnMap.put("faceSearch", fbMap.get("search"));
		returnMap.put("instaTimeLine", igMap.get("timeLine"));
		returnMap.put("instaSearch", igMap.get("search"));
		
		System.out.println("returnmap이욤 "+returnMap);

		return returnMap;

	}

	@RequestMapping(value = "/json/writeFB")
	public TimeLine writeFB(@RequestBody Search search) {

		System.out.println("/json/writeFB 시작!");

		TimeLine timeLine = snsService.writeFb(search);

		System.out.println("값 확인 " + timeLine);

		return timeLine;

	}

}
