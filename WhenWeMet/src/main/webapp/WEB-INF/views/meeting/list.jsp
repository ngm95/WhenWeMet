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
		<div class="container">
			<h2>모임 리스트</h2>
			<div class="panel-group">
				<c:forEach var="meeting" items="${meetingList }">
					<div class="panel panel-default panel-info meeting" id="${meeting.mid }">
						<div class="panel-heading meeting">${meeting.mname }</div>
						<div class="panel-body">created by <b>${meeting.creator }</b></div>
					</div>
					<hr>
				</c:forEach>
			</div>
		</div>
		<form method="post" action="/meeting/meetingInfo" id="meetingInfo">
			<input type="hidden" name="mid" id="mid">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		</form>
		<%@ include file="/WEB-INF/views/includes/09_footer.jsp"%>
	</div>
</body>
<script type="text/javascript">
	$(document).ready(function(){
		$(document).on("click",'.meeting',function(){
			var mid = $(this).attr('id');
			$("#mid").val(mid);
			$("#meetingInfo").submit();
		});
		
		$(document).on("mouseover",".meeting",function(){
			$(this).css('cursor','pointer');
		});
	});
</script>
</html>