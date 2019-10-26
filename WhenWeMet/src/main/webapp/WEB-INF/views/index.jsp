<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/views/includes/00_head.jsp"%>
</head>
<body class="main-pages">
	<div class="container" style="height: 100%">
		<%@ include file="/WEB-INF/views/includes/03_header.jsp"%>
		<div class="jumbotron">
			<h1><b>우리</b> 언제 <b>만날까?</b></h1>
			<h2>일정 관리 사이트</h2>
			<p class="lead">일정을 생성하고 팀원들과 맞는 시간을 찾아보세요!</p>
		</div>
		<div class="jumbotron" style="text-align: left;">
			<h2>사용 방법</h2>
			<p class="lead">
			1. 회원가입 후 로그인하세요.<br>
			2. "새로운 모임 만들기" 탭에서 모임을 만드세요.<br>
			3. "모임 관리" 탭에서 일정 등록과 회원 초대가 가능합니다.<br>
			4. 1개 이상의 일정이 등록되면 자동으로 가장 합리적인 모임 시간을 찾아줍니다.<br>
			5. 지금 시작하세요!</p>
		</div>
	</div>
	<%@ include file="/WEB-INF/views/includes/09_footer.jsp"%>
</body>
</html>