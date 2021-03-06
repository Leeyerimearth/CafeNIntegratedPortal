<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>

<html lang="ko">

<head>
<meta charset="EUC-KR">

<!-- 참조 : http://getbootstrap.com/css/   참조 -->
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous"></script>
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

<header>
	<title>카페가입양식관리화면</title>
</header>

<script type="text/javascript">
	//============= "가입"  Event 연결 =============
	$(function() {
		//==> DOM Object GET 3가지 방법 ==> 1. $(tagName) : 2.(#id) : 3.$(.className)

		$("button.btn.btn-success")
				.on(
						"click",
						function() {
							alert("저장");
							$("form")
									.attr("method", "POST")
									.attr("action",
											"/cafe/${cafe.cafeURL}/manage/updateCafeApplicationForm")
									.submit();
						});
	});
</script>


</head>
<jsp:include page="../common/cafeManageTollbar.jsp" />
<body>



	<div class="container">
		<div class="row">
			<div class="col-2">
				<c:import url="/WEB-INF/views/common/cafeManageMenubar.jsp"></c:import>
			</div>

			<div class="col-10">
			<br>
			<br>
				<h1 class="text-center">가입양식관리</h1>
			</br>
			</br>
			<hr>
				
				<form class="form-horizontal">
					<center>
						<input type="hidden" name="cafeNo" value="${cafe.cafeNo }" />
						<div class="form-group">
							<label for="exampleFormControlSelect1"
								class="col-sm-offset-1 col-sm-3 control-label">자동가입승인</label>
							<div class="col-sm-4">
								<select class="form-control" id="exampleFormControlSelect1"
									name="autoApplicationAcceptFlag">
									<option value="true">사용</option>
									<option value="false">미사용</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="exampleFormControlSelect1"
								class="col-sm-offset-1 col-sm-3 control-label">별명사용여부</label>
							<div class="col-sm-4">
								<select class="form-control" id="exampleFormControlSelect1"
									name="memberNicknameFlag">
									<option value="true">사용</option>
									<option value="false">미사용</option>
								</select>
							</div>
						</div>

						<div class="form-group">

							<label for="exampleFormControlInput1"
								class="col-sm-offset-1 col-sm-3 control-label">질문 1</label>
							<div class="col-sm-4">
								<input type="text" class="form-control"
									id="exampleFormControlInput1" placeholder=""
									name="applicationQuestion1">
							</div>
						</div>
						<div class="form-group">
							<label for="exampleFormControlInput1"
								class="col-sm-offset-1 col-sm-3 control-label">질문 2</label>
							<div class="col-sm-4">
								<input type="text" class="form-control"
									id="exampleFormControlInput1" placeholder=""
									name="applicationQuestion2">
							</div>
						</div>
						<div class="form-group">
							<label for="exampleFormControlInput1"
								class="col-sm-offset-1 col-sm-3 control-label">질문 3</label>
							<div class="col-sm-4">
								<input type="text" class="form-control"
									id="exampleFormControlInput1" placeholder=""
									name="applicationQuestion3">
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-4  col-sm-4 text-center">
								<button type="button" class="btn btn-success">저장</button>
							</div>
						</div>
					</center>
				</form>
				<!-- form Start /////////////////////////////////////-->
			</div>
		</div>
	</div>
	<!--  화면구성 div end /////////////////////////////////////-->

</body>

</html>
