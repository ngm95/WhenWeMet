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

		<center>
			<h1>${meeting.mname }</h1>
			<h2>by ${meeting.creator }</h2>

			<button class="btn btn-success">일정 등록</button>
			<br> <br>
			<button class="btn btn-info" data-toggle="modal" data-target="#inviteModal">초대 하기</button>
		</center>
		<br>
		<hr>
		<div class="container">
			<h3>참여자 목록</h3>
			<div class="panel panel-default" id="usrList"></div>
		</div>
		<div class="container">
			<h3>가능한 시간 목록</h3>
			<div class="panel panel-default" id="timeList"></div>
		</div>

		<%@ include file="/WEB-INF/views/includes/09_footer.jsp"%>
	</div>
	<div class="modal fade" id="inviteModal" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">초대하기</h4>
				</div>
				<div class="modal-body">
					아이디: <input type="text" id="receiver"> <button id="invite">초대</button>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
	<input type="hidden" id="mid" value="${meeting.mid }">
	<input type="hidden" id="userId" value="${authInfo.id }">
</body>
<script src="/resources/js/invitation.js"></script>
<script>
	function printUserList(list) {
		var list = list;
		var str = "";
		var len = list.length;
		for (var i = 0; i < len; i++) {
			var user = list[i];
			str += "<div class='panel-body'>" + user + "</div>";
		}
		$("#usrList").html(str);
	}
	
	function successInvite() {
		alert("초대가 완료 되었습니다.");
	}
	
	function errorInvite() {
		alert("사용자를 찾을 수 없거나 이미 초대 되었습니다.");
	}
	$(document).ready(function() {
		var mid = $("#mid").val();
		$.getJSON('/meeting/' + mid, printUserList);
		$("#invite").on("click", function() {
			var sender = $("#userId").val();
			var receiver = $("#receiver").val();
			var obj = {
					mid:mid,
					sender:sender,
					receiver:receiver
			};
			ajaxManager.invite(obj, successInvite, errorInvite);
		});
	});
</script>
</html>