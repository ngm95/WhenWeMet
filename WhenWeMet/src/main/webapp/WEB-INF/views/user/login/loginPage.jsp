<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/views/includes/00_head.jsp"%>
<title>로그인 | WhenWeMet</title>
</head>
<body class="signup-pages">

	<div class="signup-box">
		<div class="signup-logo">
			<b>When</b>We<b>Met</b>
		</div>

		<div class="signup-box-body">
			<p class="box-msg">로그인 양식</p>

			<form class="px-4 py-3" action='<c:url value="/user/login/loginProcess"/>' method="post">
				<div class="form-group">
					<label for="exampleDropdownFormEmail1">아이디</label> 
					<input type="text" class="form-control" name="loginID" placeholder="ID" value="${loginId }">
				</div>
				<div class="form-group">
					<label for="exampleDropdownFormPassword1">비밀번호</label> 
					<input type="password" class="form-control" name="loginPW" placeholder="Password" value="${loginPw }">
				</div>
				<c:if test="${not empty ERRORMSG}">
					<font color="red">
						<p>
							${ERRORMSG }
						</p>
					</font>
				</c:if>
				<div class="form-check">
					<label class="form-check-label"> 
						<input type="checkbox" class="form-check-input"> 아이디 기억하기
					</label>
				</div>
				<input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}" />
				<button type="submit" class="btn btn-primary">로그인</button>
			</form>
			<div class="dropdown-divider"></div>
			<a class="dropdown-item" href="/user/signup/step1">회원가입하기</a> <a
				class="dropdown-item" href="/user/findPW">비밀번호 찾기</a>
		</div>

	</div>

</body>
</html>