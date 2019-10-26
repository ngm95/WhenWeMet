<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@ page import="com.spring.project.security.CustomUserDetails" %>
<%@ page import="org.springframework.security.core.Authentication"%>
<%
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	Object principal = auth.getPrincipal();
	String uid = auth.getName();
%>
<div class="header">
	<sec:authorize access="isAnonymous()">
		<ul class="nav nav-pills pull-right">
			<li role="presentation"><a href="/user/login/loginPage">로그인</a></li>
			<li role="presentation"><a href="/user/signup/step1">회원가입</a></li>
		</ul>
	</sec:authorize>
	<sec:authorize access="isAuthenticated()">
		<ul class="nav nav-pills pull-right">
			<li><button class="btn btn-info" data-toggle="modal" data-target="#getInviteModal" id="getInvitation">받은 초대</button></li>
			<li role="presentation"><a href="#"><%=uid%>님, 반갑습니다.</a></li>
			<li role="presentation"><a href="/user/logout">로그아웃</a></li>
		</ul>
	</sec:authorize>

	<div>
		<ul class="nav nav-pills">
			<li role="presentation"><h3 class="text-muted"><a href="/"><b>우리</b>언제<b>만날까?</b></a></h3></li>
			<%-- 탭:모임 만들기 --%>
			<li role="presentation"><a href="/meeting/make">새로운 모임 만들기</a></li>
			<%-- 탭:모임관리 --%>
			<li role="presentation"><a href="#" id=meetingList>모임 관리</a></li>
		</ul>
	</div>

	<form method="post" action="/meeting/list" id="postMeetingList">
		<input type="hidden" name="userId" value="<%=uid%>"> 
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	</form>
	
	<div class="modal fade" id="getInviteModal" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">나에게 온 초대</h4>
				</div>
				<div class="modal-body">
					<ul id="senderList">
					</ul>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
	<sec:authorize access="isAuthenticated()">
		<input type="hidden" id="uid" value="<%=uid%>"> 
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	</sec:authorize>
</div>
<script src="/resources/js/invitation.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#meetingList").on("click", function(e) {
			e.preventDefault();
			$("#postMeetingList").submit();
		});
	});

	<sec:authorize access="isAuthenticated()">
		var userId = $("#uid").val();
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		var csrf = {
				token:token,
				header:header
		};
		
		function getMeetingName(mid) {
			var mid = mid;
			var mname = null;
			$.ajax({
				url : "/meeting/name/"+mid,
				type : "get",
				success : function(data) {
					mname = data;
				},
				async: false
			});
			return mname;
		}
		function showList(mlist) {
			var mlist = mlist;
			var str = "";
			var len = mlist.length;
			for(var i = 0; i < len; i++) {
				var data = mlist[i];
				var meetingName = getMeetingName(data.mid);
				str += "<li id='"+ data.sender +"'>" + data.sender + " 님이 "+ meetingName + " 모임으로 초대하였습니다.";
				str += "<a class='accept' href='"+data.mid+"'> 수락</a> <a class='deny' href='"+data.mid+"'>거절</a>"
				str += "</li>";
			}
			$("#senderList").html(str);
		}
		$(document).ready(function(){
			var getInvitation = $("#getInvitation");
			
			getInvitation.on("click", function(){
				ajaxManager.get(userId, showList);
			});
		});
		
		$(document).on("click", ".accept", function(e){
			e.preventDefault();
			var sender = $(this).parent().attr('id');
			var mid = $(this).attr("href");
			var obj = {userId:userId, sender:sender, mid:mid, csrf:csrf};
			ajaxManager.accept(obj, function(){
				ajaxManager.get(userId, showList);
			});
		});
		
		$(document).on("click", ".deny", function(e){
			e.preventDefault();
			var sender = $(this).parent().attr('id');
			var mid = $(this).attr("href");
			var obj = {userId:userId, sender:sender, mid:mid, csrf:csrf};
			ajaxManager.deny(obj, function(){
				ajaxManager.get(userId, showList);
			});
		});
	</sec:authorize>
</script>