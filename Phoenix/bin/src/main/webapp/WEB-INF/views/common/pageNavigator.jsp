<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div class="container d-flex justify-content-center">
		 <nav>
		  <!-- 크기조절 :  pagination-lg pagination-sm-->
		  <ul class="pagination" style="align-content:  center;">
		    
		    <!--  <<== 좌측 nav -->
		  	<c:if test="${ page.currentPage <= page.pageUnit }">
		 		<li class="page-item disabled">
		 			<span class="page-link">&laquo;</span>
		 		</li>
			</c:if>
			<c:if test="${ page.currentPage > page.pageUnit }">
				<li class="page-item">
					<a class="page-link" href="javascript:fncGet${param.subject }List('${ page.beginUnitPage-1}')">
						<span style='color:#f5a142;'>&laquo;</span>
					</a>
				</li>
			</c:if>
		    
		    <!--  중앙  -->
			<c:forEach var="i"  begin="${page.beginUnitPage}" end="${page.endUnitPage}" step="1">
				
				<!--  현재 page 가르킬경우 : active -->
				<c:if test="${ page.currentPage == i }">
				    <li class="page-item active " aria-current="page" 
				    >
				    	<span class="page-link" style='background-color:#f5a142; border: 1px solid #f5a142'>
				    		${ i }
				    		<span class="sr-only">(current)</span>
				    	</span>
				    </li>
				</c:if>	
				
				<!-- 현재 page가 아닐 경우 -->				
				<c:if test="${ page.currentPage != i}">	
					<li class="page-item">
						<a class="page-link" href="javascript:fncGet${param.subject }List('${ i }');" style='color:#f5a142;'>${ i }</a>
					</li>
				</c:if>
			</c:forEach>
		    
		     <!--  우측 nav==>> -->
		     <c:if test="${ page.endUnitPage >= page.maxPage }">
		  		<li class="page-item disabled">
		  			<span class="page-link">
		  				<span>&raquo;</span>
		  			</span>
		  		</li>
			</c:if>
			<c:if test="${ page.endUnitPage < page.maxPage }">
				<li class="page-item">
					<a class="page-link"  href="javascript:fncGet${param.subject }List('${page.endUnitPage+1}')">
			        	<span style='color:#f5a142;'>&raquo;</span>
			      	</a>
			    </li>
			</c:if>
		  </ul>
		</nav>
</div>
