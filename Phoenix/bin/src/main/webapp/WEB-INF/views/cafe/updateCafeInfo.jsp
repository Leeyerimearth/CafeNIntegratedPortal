<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html lang="ko">
	
<head>

	
	<!-- 참조 : http://getbootstrap.com/css/   참조 -->
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<!-- Bootstrap CDN -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

	
	<header>
	
	
		<title>카페가입양식관리화면</title>
	</header>
	
		<script type="text/javascript">

		
		//============= "가입"  Event 연결 =============
		 $(function() {

				//트루 펄스 값 한글로 나오게 하기
			 $(".autoAcceptOption").each(function(){
				    if($(this).val()=="${cafe.autoApplicationAcceptFlag}"){
				      $(this).attr("selected","selected");
				    }
			 });
			
			 $(".check").each(function(){
				    if($(this).val()=="${cafe.memberNicknameFlag}"){
				      $(this).attr("selected","selected");
				    }
			 });
				    
			//==> DOM Object GET 3가지 방법 ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
			var cafeURL = "22";
			$( "button.btn.btn-success" ).on("click" , function() {
				alert("확인");
				$("form").attr("method" , "POST").attr("action" , "/cafe/${cafe.cafeURL}/manage/updateCafeApplicationForm").submit();
			});
		});	

			
			
		</script>
<body>


<jsp:include page="../common/cafeManageTollbar.jsp" />
		<div class="container">
		
		<div class="row">
			<div class="col-2">
				<c:import url="/WEB-INF/views/common/cafeManageMenubar.jsp"></c:import>
			</div>
			
			<div class="col-10">
		
		
			<br>
				<h2 class="text-center">카페정보수정</h2>		
			
			</br>
			<hr>	
<form class="form-horizontal">


		
		
		<input type="hidden" name="cafeNo" value="${cafe.cafeNo }"/>
				<center>
					<div class="form-group">
	
					<label for="exampleFormControlInput1" class="col-sm-offset-1 col-sm-3 control-label">카 페 이 름</label>
						<div class="col-sm-4">				
							<input type="text" class="form-control"
								id="exampleFormControlInput1" placeholder="" name="cafeName"  value="${cafe.cafeName}">							
						</div>
					</div>		
					
					<div class="form-group">
	
					<label for="exampleFormControlInput1" class="col-sm-offset-1 col-sm-3 control-label">카 페 설 명</label>
						<div class="col-sm-4">				
							<input type="text" class="form-control"
								id="exampleFormControlInput1" placeholder="" name="cafeDetail"  value="${cafe.cafeDetail}">							
						</div>
					</div>
		
					<div class="form-group">
	
					<label for="exampleFormControlInput1" class="col-sm-offset-1 col-sm-3 control-label">배너이미지</label>
						<div class="col-sm-4">				
							<div><img src="/images/uploadFiles/bannerImg/${cafe.bannerImg}" width="300"; height="200px";/>	</div></div>
					</div>
					
					<div class="form-group">
	
					<label for="exampleFormControlInput1" class="col-sm-offset-1 col-sm-3 control-label">메인이미지</label>
						<div class="col-sm-4">				
							<div><img src="/images/uploadFiles/mainImg/${cafe.mainImg}" width="300"; height="200px";/>	</div></div>
					</div>
					
					<div class="form-group">
	
					<label for="exampleFormControlInput1" class="col-sm-offset-1 col-sm-3 control-label">카페아이콘</label>
						<div class="col-sm-4">				
							<div><img src="/images/uploadFiles/cafeIcon/${cafe.cafeIcon}" width="300"; height="200px";/>	</div></div>
					</div>
			
	
		  
				  
		
		  
		  <div class="form-group">
		    <div class="col-sm-offset-4  col-sm-4 text-center">
		      <button type="button" class="btn btn-success"  >확인</button>
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
					