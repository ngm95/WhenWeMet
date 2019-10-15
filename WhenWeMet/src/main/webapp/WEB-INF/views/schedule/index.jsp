<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/views/includes/00_head.jsp"%>
<style>
.ui-datepicker-trigger { 
	position:relative;
	height:30px; 
}
</style>
</head>
<body class="main-pages">
	<div class="container" style="height: 100%">
		<%@ include file="/WEB-INF/views/includes/03_header.jsp"%>
		<div class="container">
			<h2>${meeting.mname } 모임에 참석 가능한 자신의 시간을 알려주세요.</h2>
			<p>
				현재 등록한 일정
				<button class="btn btn-sm btn-info pull-right" data-toggle="modal" data-target="#scheduleModal">일정 추가하기</button>
			</p>
			<br>
			<div class="panel-group scheduleList">
				
			</div>
		</div>
		<!-- Modal -->
		<div id="scheduleModal" class="modal fade" role="dialog">
			<div class="modal-dialog modal-lg">
				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">모임에 참석 가능한 시간을 추가하세요.</h4>
					</div>
					<div class="modal-body">
						<input type="text" class="Calendar" id="start" size="12px" >
						
						<select id="startHour">
							<c:set var="n" value="0" />
								<c:forEach begin="0" end="23">
									<option value="${n }" >${n }</option>
									<c:set var="n" value="${n+1}" />
								</c:forEach>
						</select><span>시</span>
						
						<select id="startMinute">
							<c:set var="n" value="0" />
								<c:forEach begin="0" end="59">
									<option value="${n }" >${n }</option>
									<c:set var="n" value="${n+1}" />
								</c:forEach>
						</select><span>분</span>
						
						 ~ <input type="text" class="Calendar" id="end" size="12px" >
						 
						<select id="endHour">
							<c:set var="n" value="0" />
								<c:forEach begin="0" end="23">
									<option value="${n }" >${n }</option>
									<c:set var="n" value="${n+1}" />
								</c:forEach>
						</select><span>시</span>
						
						<select id="endMinute">
							<c:set var="n" value="0" />
								<c:forEach begin="0" end="59">
									<option value="${n }" >${n }</option>
									<c:set var="n" value="${n+1}" />
								</c:forEach>	
						</select><span>분</span>
						<button class="btn btn-sm btn-primary pull-right" id="addSchedule">추가하기</button>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<input type="hidden" id="mid" value="${meeting.mid }">
	<input type="hidden" id="userId" value="${authInfo.id }">
	 <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</body>
<%@ include file="/WEB-INF/views/includes/09_footer.jsp"%>
<script src="/resources/js/schedule.js"></script>
<script src="/resources/js/proto.js"></script>
<script>
	var userId = $("#userId").val();
	var mid = $("#mid").val();
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	var csrf = {
			token:token,
			header:header
	};
	var obj = {
			userId:userId,
			mid:mid,
			csrf:csrf
	};
	function printSchedule(list) {
		var list = list;
		var len = list.length;
		var str = "";
		if(len == 0)
			$(".scheduleList").html(str);
		for(var i = 0; i < len;	i++) {
			var schedule = list[i];
			var sid = schedule.sid;
			var startTime = new Date(schedule.start_time).format("yyyy년 MM월 dd일 a/p hh시 mm분");
			var endTime = new Date(schedule.end_time).format("yyyy년 MM월 dd일 a/p hh시 mm분");
			str += "<div class='panel panel-default'><div class='panel-body'>"+startTime+"  ~~  "+endTime
			+"<button class='btn btn-sm btn-danger pull-right deleteSchedule' id='"+sid+"'>삭제</button></div></div>";
		}
		$(".scheduleList").html(str);
	}
	function addSuccess() {
		alert("일정이 등록되었습니다.");
		$('input').val("");
		$('select').find('option:first').attr('selected', 'selected');
		ajaxManager.getSchedule(obj, printSchedule);
	}
	
	function deleteSuccess() {
		alert("일정이 삭제되었습니다.");
	}
	$(document).ready(function() {
		ajaxManager.getSchedule(obj, printSchedule);
		$(".Calendar").datepicker({
			showOn: "both",
			buttonImage: "/resources/Calendar_Icon_preview.png",
			buttonImageOnly: true,
			dateFormat: "yymmdd",
			nextText: "다음 달",
			prevText: "이번 달",
			changeMonth : true,
			changeYear : true,
			dayNames : ['일','월','화','수','목','금','토'],
			dayNamesMin : ['일','월','화','수','목','금','토'],
			monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
			monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
			minDate: 0
		});
		
		$("#addSchedule").on("click", function(){
			var start = $("#start").val();
			var startYY = start.substring(0, 4);
			var startMM = start.substring(4, 6);
			var startDD = start.substring(6, 8);
			var startHour = $("#startHour option:selected").val();
			var startMinute = $("#startMinute option:selected").val();
			var startDate = new Date(startYY, startMM-1, startDD, startHour, startMinute, 0);
			
			var end = $("#end").val();
			var endYY = end.substring(0, 4);
			var endMM = end.substring(4, 6);
			var endDD = end.substring(6, 8);
			var endHour = $("#endHour option:selected").val();
			var endMinute = $("#endMinute option:selected").val();
			var endDate = new Date(endYY, endMM-1, endDD, endHour, endMinute, 0);
			
			var scheduleDTO = {
					mid:mid,
					start_time:startDate,
					end_time:endDate,
					userid:userId,
					csrf:csrf
			};
			ajaxManager.addSchedule(scheduleDTO, addSuccess);
		});
	});
	
	$(document).on("click",".deleteSchedule", function(){
		var sid = $(this).attr('id');
		var data = {
				sid:sid,
				csrf:csrf
		};
		ajaxManager.deleteSchedule(data, deleteSuccess);
		ajaxManager.getSchedule(obj, printSchedule);
	});
</script>

</html>