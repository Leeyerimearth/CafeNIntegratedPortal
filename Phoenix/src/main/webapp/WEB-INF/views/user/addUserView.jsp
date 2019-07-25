<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>

<html lang="ko">

<head>
		<!-- ToolBar Start /////////////////////////////////////-->
		<jsp:include page="../common/toolbar.jsp" />
		<!-- ToolBar End /////////////////////////////////////-->

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width,initial-scale=1">
<title>회원가입</title>


<link href="/plugins/sweetalert/css/sweetalert.css" rel="stylesheet">
<link href="/css/style.css" rel="stylesheet">
<link rel="stylesheet" href="/css/custom/scroll-top.css">
<link rel="stylesheet" href="/plugins/sweetalert/css/sweetalert.css">


</head>




<body>

<!--*******************
        Preloader start
    ********************-->
	<div id="preloader" style="display: none;">
		<div class="loader">
			<svg class="circular" viewBox="25 25 50 50">
                <circle class="path" cx="50" cy="50" r="20" fill="none"
					stroke-width="3" stroke-miterlimit="10"></circle>
            </svg>
		</div>
	</div>
	<!--*******************
        Preloader end
    ********************-->


<div id="main-wrapper">



<!--**********************************
            Sidebar start
        ***********************************-->
		
		<!--**********************************
            Sidebar end
        ***********************************-->

		<!--**********************************
            Content body start
        ***********************************-->
<div class="content-body" style="min-height: 743px;">



		<div class="container-fluid">
			<div class="row">
				<div class="col-lg-12">
					<div class="card">
						<div class="card-body">			
							<h2 class="text-center">회원가입</h2>
							<hr style="border: solid 1px gray;">		
								<div class="basic-form">			
								<br>
									<form>

								<input type="hidden" name="userNo" value="${user.userNo }"/>

                                        <div class="form-group row">
                                            <label class="col-lg-4 col-form-label" for="userId1"><h5>아이디 <span class="text-danger">*</span></h5>
                                            </label>
                                            <div class="col-lg-6">
                                            	<input type="text" class="form-control" id="userid" name="userId">
                                                <span id="check"> <strong>아이디를 입력해주세요</strong>
													</span>
                                            </div>
                                       	</div>
                                       	<hr>
                                        
                                        <div class="form-group row">
                                            <label class="col-lg-4 col-form-label" for="userName1"><h5>이름 <span class="text-danger">*</span></h5>
                                            </label>
                                            <div class="col-lg-6">
                                             <input type="text" class="form-control" id="userName1" name="userName">
                                            </div>
                                       	   </div><hr>
                                        
                                        
										<div class="form-group row">
                                            <label class="col-lg-4 col-form-label" for="password1"><h5>비밀번호 <span class="text-danger">*</span></h5>
                                             </label>
                                            <div class="col-lg-6">
                                             <input type="password" class="form-control" id="passWord" name="password">
                                            </div>
                                       	   </div><hr>

                                     
										<div class="form-group row">
                                            <label class="col-lg-4 col-form-label" for="password21"><h5>비밀번호확인<span class="text-danger">*</span></h5>
                                             </label>
                                            <div class="col-lg-6">
                                             <input type="password" class="form-control" id="passwordCheck" name="passwordCheck">
                                            </div>
                                       	   </div><hr>

                                        
                                        
                                        <div class="form-group row">
                                            <label class="col-lg-4 col-form-label" for="userNickname1"><h5>닉네임<span class="text-danger">*</span></h5>
                                             </label>
                                            <div class="col-lg-6">
                                             <input type="text" class="form-control" id="userNickname1" name="userNickname">
                                            </div>
                                       	   </div><hr>
      	
      								<div class="form-group row">
                                            <label class="col-lg-4 col-form-label" for="phone1"><h5>전화번호 <span class="text-danger">*</span></h5>
                                            </label>
                                            <div class="col-lg-6">
                                            	<input type="text" class="form-control" id="phone1" name="phone" placeholder="-없이 숫자만 입력하세요">                                               
                                            	</div>
                                       		   </div><hr>
                                       		   
                                       <div class="form-group row">
                                            <label class="col-lg-4 col-form-label" for="email1"><h5>이메일<span class="text-danger">*</span></h5>
                                             </label>
                                            <div class="col-lg-6">
                                             <input type="text" class="form-control" id="email1" name="email">
                                            </div>
                                       	   </div><hr>
                                       	   
                                       	   <div class="form-group row">
                                            <label class="col-lg-4 col-form-label" for="profileImg1"><h5>프로필이미지</h5>
                                             </label>
                                            <div class="col-lg-6">
                                             <input type="text" class="form-control" id="profileImg1" name="profileImg">
                                            </div>
                                       	   </div><hr>		   
                                       		   
				  				<div class="form-group row">
                                	<div class="col-lg-8 ml-auto">
                                  		<button type="button" class="btn btn-primary" id="add">가입</button>
                                  		 <a class="btn btn-success btn" href="#" role="button" id="cancle">취&nbsp;소</a>
                                    </div>
                                   </div>
						  
	 					</form>
	 						
		 			 </div>
		  			</div>
		  		   </div>
		 		  </div>
		 		 </div>
		 	    </div>
		 	   </div>
			  </div>
		  
		  
	
		<!-- form Start /////////////////////////////////////-->



	<!--  화면구성 div end /////////////////////////////////////-->
	<script src="/plugins/common/common.min.js"></script>
	<script src="/js/custom.min.js"></script>
	<script src="/js/settings.js"></script>
	<script src="/js/gleek.js"></script>
	<script src="/js/styleSwitcher.js"></script>
	<!-- 메뉴바 이용을 위한 스크립트 -->
	<script src="/js/custom/scroll-top.js"></script>
	<script src="/plugins/sweetalert/js/sweetalert.min.js"></script>
	<script type="text/javascript">

	
	$(function() {
		//==> DOM Object GET 3가지 방법 ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
		$("#add").on("click", function() {
			fncAddUser();
		});
	});


	$(function() {
		//==> DOM Object GET 3가지 방법 ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
		$("#cancle").on("click", function() {
			//$("form")[0].reset();
			
			
			self.location = "/";
			
		});
	});
	
	//유효성 검사
	function fncAddUser() {

		var userName = $("input[name='userName']").val();
		var userId = $("input[id='userid']").val();
		var password = $("input[id='passWord']").val();
		var pw_confirm = $("input[id='passwordCheck']").val();
		var name = $("input[name='userName']").val();
		var email = $("input[name='email']").val();
		var phone = $("input[name='phone']").val();

		if (userId == null || userId.length < 1) {
			sweetAlert("아이디를 입력하세요","","error");
			return;
		}
		if(name == null || name.length <1){
			sweetAlert("이름을 입력하세요.","","error");
			return;
		}		
		if (password == null || password.length < 1) {
			sweetAlert("비밀번호를 입력하세요","","error");
			return;
		}
		if (password.length < 1 || password.length > 12) {
			sweetAlert("비밀번호를 12자리 까지만 입력하세요","","error");
            return false;
        }
		if(pw_confirm == null || pw_confirm.length <1){			
			sweetAlert("비밀번호 확인은 입력하셔야 합니다.","","error");
			return;
		}
		if( password != pw_confirm ) {			
			sweetAlert("비밀번호 확인이 일치하지 않습니다.","","error");
			$("input:text[name='password2']").focus();
			return;
		}
		if (phone == null || phone.length < 1) {
			sweetAlert("전화번호를 입력하세요.","","error");
			return;
		}
		if (email == null || email.length < 1) {
			sweetAlert("이메일을 입력하세요.","","error");
			return;
		}
		
	   
		sweetAlert(userName+"님 환영합니다~","");
		
		//alert(userName+"님 환영합니다~");
		$("form").attr("method", "POST").attr("action","/user/addUser").submit();
		
	}

	//한글 입력못하게
	$(function() {
		$("input[name='phone']").on('keyup',function() {

			
	 $(this).val($(this).val().replace(/[^0-9]/g,""));
	 //alert("숫자만 입력하셔야 합니다.");
	 
		});
	   });


	   
	//카페이름 중복확인 
	$(function() {

		$("input[id='userid']").on('keyup',function() {
					
							inputed = $("input[id='userid']").val();
							//alert("입력  : "+inputed);

									$.ajax({
										url : "/user/json/checkUserIdDuplication",
										method : "POST",
										dataType : "json",
										headers : {
											"Accept" : "application/json",
											"Content-Type" : "application/json"
										},
										data : JSON.stringify({
											userId : inputed,
										}),

										success : function(JSONData) {
											//alert(JSONData); 
											//alert(typeof(JSONData));

											if (JSONData && inputed != "") {
												$("#check").children("strong")
														.remove();
												$("#check")
														.append(
																"<strong class=\"text-success\">사용 가능합니다.</strong>");
											} else {
												$("#check").children("strong")
														.remove();
												$("#check")
														.append(
																"<strong  class=\"text-danger\">사용 불가능합니다.</strong>");
											}
											if (inputed == "") {
												$("#check").children("strong")
														.remove();
												$("#check")
														.append(
																"<strong class=\"text-muted\">아이디를 입력해주세요.</strong>");
											}
										}

									});
		  						  });
								});

	

	
</script>
	<script src="/js/custom/cafeCommon.js"></script>

</body>

</html>
