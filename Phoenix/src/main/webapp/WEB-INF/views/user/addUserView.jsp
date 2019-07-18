<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>

<html lang="ko">

<head>
<meta charset="EUC-KR">

<!-- 참조 : http://getbootstrap.com/css/   참조 -->
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<!--  ///////////////////////// Bootstrap, jQuery CDN ////////////////////////// -->
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
	integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
	crossorigin="anonymous"></script>
<!-- Bootstrap CDN -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
	integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
	crossorigin="anonymous"></script>

<style>
body>div.container {
	border: 3px solid #D6CDB7;
	margin-top: 10px;	
}

</style>


<script type="text/javascript">
	$(function() {
		//==> DOM Object GET 3가지 방법 ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
		$("button.btn.btn-primary").on("click", function() {
			fncAddUser();
		});
	});

	//유효성 검사
	function fncAddUser() {

		var userId = $("input[name='userId']").val();
		var password = $("input[name='password']").val();

		if (userId == null || userId.length < 1) {
			alert("카페이름은 반드시 입력하셔야 합니다.");
			return;
		}
		if (password == null || password.length < 1) {
			alert("비밀번호는  반드시 입력하셔야 합니다.");
			return;
		}


		$("form").attr("method", "POST").attr("action","/user/addUser").submit();
	}
	/*//카페이름 중복확인 
	$(function() {

		$("input[name='userId']").on('keyup',function() {

							var inputed = $("input[name='userId']").val();
							// alert("입력  : "+inputed);

									$.ajax({
										url : "/cafe/json/checkCafeNameDuplication",
										method : "POST",
										dataType : "json",
										headers : {
											"Accept" : "application/json",
											"Content-Type" : "application/json"
										},
										data : JSON.stringify({
											cafeName : inputed,
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
																"<strong class=\"text-muted\">카페이름을 입력해주세요.</strong>");
											}
										}

									});
						});

	});

	//==>"URL중복확인" 
	$(function() {

		$("input[name='URL']")
				.on(
						'keyup',
						function() {

							var inputed = $("input[name='URL']").val();
							// alert("입력  : "+inputed);

									$.ajax({
										url : "/cafe/json/checkCafeNameDuplication",
										method : "POST",
										dataType : "json",
										headers : {
											"Accept" : "application/json",
											"Content-Type" : "application/json"
										},
										data : JSON.stringify({
											cafeName : inputed,
										}),

										success : function(JSONData) {
											//alert(JSONData); 
											//alert(typeof(JSONData));

											if (JSONData && inputed != "") {
												$("#check1").children("strong")
														.remove();
												$("#check1")
														.append(
																"<strong class=\"text-success\">사용 가능합니다.</strong>");
											} else {
												$("#check1").children("strong")
														.remove();
												$("#check1")
														.append(
																"<strong  class=\"text-danger\">사용 불가능합니다.</strong>");
											}
											if (inputed == "") {
												$("#check1").children("strong")
														.remove();
												$("#check1")
														.append(
																"<strong class=\"text-muted\">URL을 입력해주세요.</strong>");
											}
										}

									});
						});

	});*/

	//============= "만들기"  Event 연결 =============
	$(function() {
		//==> DOM Object GET 3가지 방법 ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
		$("button.btn btn-primary").on("click", function() {
			fncAddUser();
		});
	});

	$(function() {
		//==> DOM Object GET 3가지 방법 ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
		$("a[href='#' ]").on("click", function() {
			//$("form")[0].reset();
			self.location = "/cafe/main"
		});
	});
</script>

</head>
<body>

	<div class="container">

		<h4 class="bg-primary text-center">회원가입</h4>
		
			<form class="form-horizontal">

				<div class="form-group">
					<center>
					<label for="exampleFormControlInput1" class="col-sm-offset-1 col-sm-3 control-label">아이디</label>
						<div class="col-sm-4">				
							<input type="text" class="form-control"
								id="exampleFormControlInput1" placeholder="" name="userId">
							<span id="check"> <strong>아이디를 입력해주세요</strong>
							</span>
						</div>
					</center>
				</div>
				<center>
					<div class="form-group">
						<label for="exampleFormControlInput1"
							class="col-sm-offset-1 col-sm-3 control-label">비밀번호</label>
						<div class="col-sm-4">
							<input type="text" class="form-control"
								id="exampleFormControlInput1" placeholder="" name="password">
							
						</div>
					</div>
				</center>
				<center>
					<div class="form-group">
						<label for="exampleFormControlInput1"
							class="col-sm-offset-1 col-sm-3 control-label">비밀번호확인</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" 
							id="exampleFormControlTextarea1"  placeholder="" name="password1">
						</div>
					</div>
				</center>
				<center>
					<div class="form-group">
						<label for="exampleFormControlInput1"
							class="col-sm-offset-1 col-sm-3 control-label">이름</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" id="exampleFormControlInput1"
								 placeholder="" name="userName"></textarea>
						</div>
					</div>
				</center>
				<center>
					<div class="form-group">
						<label for="exampleFormControlInput1"
							class="col-sm-offset-1 col-sm-3 control-label">전화번호</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" id="exampleFormControlInput1"
								placeholder="" name="phone">
						</div>
					</div>
				</center>
				<center>
					<div class="form-group">
						<label for="exampleFormControlInput1"
							class="col-sm-offset-1 col-sm-3 control-label">닉네임</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" id="exampleFormControlInput1"
								placeholder="" name="userNickname">
						</div>
					</div>
				</center>	
				<center>
					<div class="form-group">
						<label for="exampleFormControlInput1"
							class="col-sm-offset-1 col-sm-3 control-label">이메일</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" id="exampleFormControlInput1"
								placeholder="" name="email">
						</div>
					</div>
				</center>
				<center>
					<div class="form-group">
						<label for="exampleFormControlInput1"
							class="col-sm-offset-1 col-sm-3 control-label">프로필이미지</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" id="exampleFormControlInput1"
								placeholder="" name="profileImg">
						</div>
					</div>
				</center>
				
				
				<center>
					<div class="form-group">
						<div class="col-sm-offset-4  col-sm-4 text-center">
							<button type="button" class="btn btn-primary">만들기</button>
							<a class="btn btn-success btn" href="#" role="button">취&nbsp;소</a>
						</div>
					</div>
				</center>

		</form>
		<!-- form Start /////////////////////////////////////-->
	</div>


	<!--  화면구성 div end /////////////////////////////////////-->

</body>

</html>