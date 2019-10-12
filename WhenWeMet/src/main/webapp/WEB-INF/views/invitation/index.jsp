<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/views/includes/00_head.jsp"%>
</head>
<body class="main-pages">
	<div class="container">
		<%@ include file="/WEB-INF/views/includes/03_header.jsp"%>
		<div>
			<a data-toggle="collapse" href="#collapse1" id="getInvitation">초대 요청 <span class="badge" id="listSize"></span></a> 
		</div>
		<div id="collapse1" class="collapse">
			<ul id="senderList">
			</ul>
		</div>
		<div>
			<a data-toggle="collapse" href="#collapse2" id="group">그룹 목록 <span class="badge" id="groupSize"></span></a> 
		</div>
		<div id="collapse2" class="collapse">
			<ul id="groupList">
			</ul>
		</div>
		<input type="hidden" value="${authInfo.id }" id="userId">
		 <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		<%@ include file="/WEB-INF/views/includes/09_footer.jsp"%>
	</div>
</body>
<script src="/resources/js/invitation.js"></script>
<script type="text/javascript">
	var userId = $("#userId").val();
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
	function showList(list) {
		var list = list;
		var str = "";
		var len = list.length;
		for(var i = 0; i < len; i++) {
			var data = list[i];
			var meetingName = getMeetingName(data.mid);
			str += "<li id='"+ data.sender +"'>" + data.sender + " 님이 "+ meetingName + " 모임으로 초대하였습니다.";
			str += "<a class='accept' href='"+data.mid+"'> 수락</a> <a class='deny' href='"+data.mid+"'>거절</a>"
			str += "</li>";
		}
		$("#senderList").html(str);
		$("#listSize").html(len);
	}
	function showGroupList(list) {
		var list = list;
		var str = "";
		var len = list.length;
		for(var i = 0; i < len; i++) {
			var data = list[i];
			str += "<li> 그룹 이름: " + data.mname + ", 만든 사람: " + data.creator
			str += "</li>"
		}
		$("#groupList").html(str);
		$("#groupSize").html(len);
	}
	$(document).ready(function(){
		var getInvitation = $("#getInvitation");
		var group= $("#group");
		ajaxManager.get(userId, showList);
		ajaxManager.getGroup(userId, showGroupList);
		getInvitation.on("click", function(){
			ajaxManager.get(userId, showList);
		});
		group.on("click", function(){
			ajaxManager.getGroup(userId, showGroupList);
		});
	});
	
	$(document).on("click", ".accept", function(e){
		e.preventDefault();
		var sender = $(this).parent().attr('id');
		var mid = $(this).attr("href");
		var obj = {userId:userId, sender:sender, mid:mid, csrf:csrf};
		ajaxManager.accept(obj, function(){
			ajaxManager.get(userId, showList);
			ajaxManager.getGroup(userId, showGroupList);
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
	
</script>
</html>