			$(function(){
	    		$("[name=InnerSearch]").attr("method", "GET").attr("action", "/cafe/" + cafeURL + "/search");
	    		
	    		$("[name=menubarSubmit]").on("click", function(){
	    			location.href = "/cafe/" + cafeURL + "/search?searchKeyword=" + $("#menubarSearch").val(); 
	    		});	
	
	    		$("[name=toManage]").on("click", function(){
	    			location.href = "/cafe/" + cafeURL + "/manage/getCafeStatistics";
	    		});	    		
	    	});
	
	    	$(function(){//카페탈퇴
	    		document.querySelector(".sweet-withdraw").onclick =
		    		function(){
		    			swal({
		    				title:"카페에서 탈퇴하시겠습니까?",
		    				text:"카페 탈퇴시 작성된 게시글은 자동으로 삭제되지 않습니다.",
		    				type:"warning",
		    				showCancelButton:!0,
		    				confirmButtonColor:"#DD6B55",
		    				confirmButtonText:"예",
		    				cancelButtonText:"아니오",
		    				closeOnConfirm:!1
		    				},
		    				function(isConfirm){
			    				if(isConfirm){
				    				$.ajax({
					    				type : "POST",
					    				url : "/cafe/json/" +cafeURL + "/updateCafeMember",
					    				contentType: 'application/json',
					    				data : JSON.stringify({
											memberNo : memberNo,
											cafeNo : cafeNo,
											userNo : userNo
										}),
										success : function(JSONData,status){
											if (JSONData.result == true){
												swal({
													title: "카페 탈퇴 완료",
													text: "탈퇴 되었습니다.",
													type: "success",
													confirmButtonClass: "btn-success",
													confirmButtonText:"확인",
													},
													function(isConfirm){
														if(isConfirm){
															location.href = "/cafe/main";
														}//if
													}//function
												);
											}
										},
										error : function(){
											
											swal("카페 탈퇴 실패","탈퇴 못함","error");
										}
					    			});//ajax 끝
			    				}
			    			
		    				});
	    				}
		    		});
	    	
	    	
	    	$(function(){//프로필수정
	    		
	    		$(".updateProfile").on("click",function(){
// 	    			alert("여기")
	    			var x =  (document.body.offsetWidth / 2) - (500 / 2);
	    			var y =  (document.body.offsetHeight / 2) - (400);
	    			window.open("/cafe/" + cafeURL + "/updateCafeMemberProfileView?memberNo="+memberNo,"_blank","width=500,height=600, left="+ x + ", top="+ y,"location=no");
	    			});
	    		
	    		});
	
	    	$(function(){//카페가입
	    		$(".addMember").on("click", function(){
	    			 location.href = "/cafe/" + cafeURL + "/addCafeApplicationView";
	    		});
	    		
	    	});
	    	
	    	$(function(){ //카페설정이동
	    		$(".text-dark.mr-4.manage.cursor").on("click",function(){
	    			location.href = "/cafe/" + cafeURL + "/manage/getCafeStatistics";
	    		});
	    	});
	    	
	    	$(function(){ //카페등급확인
//	    		$(".getCafeGrade").on("click",function(){
//	    			//alert("등급확인이여!");
//	    			window.open("/cafe/" + cafeURL + "/getCafeGrade", 'width=100,height=100' ,"location=no");
//	    		});
	    		
	    	});
	    	
	    	$(function(){ //내글
	    		$(".myPost").on("click",function(){
	    			location.href = "/cafe/" + cafeURL + "/getMyPostList";
	    		});
	    	});
	    	
	    	$(function(){ //내글
	    		$(".myReply").on("click",function(){
	    			location.href = "/cafe/" + cafeURL + "/getMyPostList";
	    		});
	    	});
