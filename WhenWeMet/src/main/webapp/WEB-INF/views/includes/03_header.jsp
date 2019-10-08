<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="header">
	<nav>
		<ul class="nav nav-pills pull-right">
			<c:catch>
				<c:choose>
					<c:when test="${empty authInfo }">
						<ul class="nav nav-pills pull-right">
							<li role="presentation"><a
								href="/user/signin">로그인</a></li>
							<li role="presentation"><a
								href="/user/signup/step1">회원가입</a></li>
						</ul>
					</c:when>
					<c:otherwise>
						<c:choose>
							<c:when test="${authInfo.role eq 0 }">
								<ul class="nav nav-pills pull-right">
									<li role="presentation"><a href="#">관리자 ${authInfo.name }님, 안녕하세요.</a></li>
									<li role="presentation"><a href="/user/signout">로그아웃</a></li>
								</ul>
							</c:when>
							<c:otherwise>
								<ul class="nav nav-pills pull-right">
									<li role="presentation"><a href="#">${authInfo.name }님, 반갑습니다.</a></li>
									<li role="presentation"><a href="/user/signout">로그아웃</a></li>
								</ul>
							</c:otherwise>
						</c:choose>
					</c:otherwise>
				</c:choose>
			</c:catch>
		</ul>
	</nav>
	
	<h3 class="text-muted">
		<a href="/"><b>우리</b>언제<b>만날까?</b></a>
	</h3>
	
	<div>
		<c:catch>
			<c:choose>
				<c:when test="${empty authInfo }">
					<div>
						<ul class="nav nav-pills">
							<%-- 탭:모임 만들기 --%>
							<li role="presentation"><a
								href="/user/signin">새로운 모임 만들기</a>
							</li>
							<%-- 탭:모임관리 --%>
							<li role="presentation"><a
								href="/user/signin">모임 관리</a>
							</li>
							<%-- 탭:받은초대 --%>
							<li role="presentation"><a
								href="/user/signin">받은 초대</a>
							</li>
						</ul>
					</div>
				</c:when>
				<c:otherwise>
					<div>
						<ul class="nav nav-pills">
							<%-- 탭:모임 만들기 --%>
							<li role="presentation">
								<a href="/meeting/make">새로운 모임 만들기</a>
							</li>
							<%-- 탭:모임관리 --%>
							<li role="presentation">
								<a href="#" id="meetingList">모임 관리</a>
							</li>
							<%-- 탭:받은초대 --%>
							<li role="presentation">
								<a href="/invitation/index">받은 초대</a>
							</li>
						</ul>
					</div>
					<form method="post" action="/meeting/list" id="postMeetingList">
						<input type="hidden" name="userId" value="${authInfo.id }">
					</form>
				</c:otherwise>
			</c:choose>
		</c:catch>
	</div>
</div>
<script>
	$(document).ready(function(){
		$("#meetingList").on("click", function(e){
			e.preventDefault();
			$("#postMeetingList").submit();
		});
	});
</script>
