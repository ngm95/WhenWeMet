
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/views/includes/00_head.jsp"%>
</head>
<body class="main-pages" style="height: 100%">
	<div class="container">
		<%@ include file="/WEB-INF/views/includes/03_header.jsp"%>

		<center>
			<h1>${meeting.mname }</h1>
			<h2>by ${meeting.creator }</h2>
			<form method="post" action="/schedule/index">
				<input type="hidden" name="mid" value="${meeting.mid }">
				 <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				<button type="submit" class="btn btn-success">일정 등록</button>
			</form>
			<br>
			<button class="btn btn-info" data-toggle="modal"
				data-target="#inviteModal">초대 하기</button>
		</center>
		<br>
		<hr>
		<div class="container">
			<h3>참여자 목록 </h3>
			<div class="panel panel-default" id="usrList"></div>
		</div>
		<div class="container">
			<h3>가능한 시간 목록</h3>
			<form method="post" action="/schedule/table">
				<input type="hidden" name="mid" value="${meeting.mid }">
				<input type="hidden" name="allSchedule" value="${allSchedule}">
				<button type="submit" class="btn btn-success pull-right">표로 확인하기</button>
			</form>
			
			<div class="panel panel-default" id="timeList">
				
			</div>
		</div>
	</div>
	
	
	<div class="modal fade" id="inviteModal" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">초대하기</h4>
				</div>
				<div class="modal-body">
					아이디: <input type="text" id="receiver">
					<button id="invite">초대</button>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="timeModal" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">등록한 시간</h4>
				</div>
				<div class="modal-body" id="myTime">
					
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
	<input type="hidden" id="mid" value="${meeting.mid }">
	<input type="hidden" id="userId" value="${authInfo.id }">
	<input type="hidden" id="csrfId" name="${_csrf.parameterName}" value="${_csrf.token}" />
</body>
<%@ include file="/WEB-INF/views/includes/09_footer.jsp"%>
<script src="/resources/js/invitation.js"></script>
<script src="/resources/js/proto.js"></script>
<script>
	var tList = null;
	var userList = null;
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	var csrf = {
			token:token,
			header:header
	};
	function printUserList(list) {
		var userList = list;
		var str = "";	
		var len = userList.length;
		for (var i = 0; i < len; i++) {
			var user = userList[i];
			str += "<div class='panel-body userInfo' id='"+user+"' data-toggle='modal' data-target='#timeModal'>" + user + "</div>";		
		}
		$("#usrList").html(str);
	}
	function printSchedule(list) {
		var list = list;
		var len = list.length;
		var str = "";
		if(len == 0)
			$("#myTime").html("<p>등록한 시간이 없습니다.</p>");
		else {
			for(var i = 0; i < len;	i++) {
				var schedule = list[i];
				var sid = schedule.sid;
				var startTime = new Date(schedule.start_time).format("yyyy년 MM월 dd일 a/p hh시 mm분");
				var endTime = new Date(schedule.end_time).format("yyyy년 MM월 dd일 a/p hh시 mm분");
				str += "<div class='panel panel-default'><div class='panel-body'>"+startTime+"  ~~  "+endTime+"</div></div>";
			}
			$("#myTime").html(str);
		}
	}
	
	function printAvailableTime(list) {
		var mid = $("#mid").val();
		var obj = {
				"mid" : mid,
				"userList" : list
		};
		$.ajax({
			url : "/schedule/availableList",
			type : "get",
			data : obj,
			contentType : "application/json; charset=utf-8",
			success : function(list) {
				var list = list;
				var len = list.length;
				var print = "";
				for(var i = 0; i < len; i++) {
					var date = list[i];
					var start = new Date(date.start_time).format("yyyy년 MM월 dd일 a/p hh시 mm분");
					var end = new Date(date.end_time).format("yyyy년 MM월 dd일 a/p hh시 mm분");
					print += "<div class='panel panel-default'><div class='panel-body'>시작 시간: "+start+" <br>종료 시간: "+end+"</div></div>";
				}
				$("#timeList").html(print);
			},
			async : false
		});
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
		$.getJSON('/meeting/' + mid, printAvailableTime);
		$("#invite").on("click", function() {
			var sender = $("#userId").val();
			var receiver = $("#receiver").val();
			if (receiver == "")
				e.preventDefault();
			var obj = {
				mid : mid,
				sender : sender,
				receiver : receiver,
				csrf : csrf
			};
			ajaxManager.invite(obj, successInvite, errorInvite);
		});
	});
	
	$(document).on("mouseover",".userInfo",function(){
		$(this).css('cursor','pointer');
	});
	
	$(document).on("click",".userInfo",function(){
		var userId = $(this).attr('id');
		var mid = $("#mid").val();
		$.ajax({
			url : "/schedule/list/"+userId+"/"+mid,
			type : "get",
			success : function(result){
				tList = result;
			},	
			async : false 
		});
		printSchedule(tList);
	});
</script>

</html>