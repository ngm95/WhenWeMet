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
		
			<div class="input-group-btn">
				<button class="btn btn-danger" style="height: 35px;" id="btn">손으로 설정해주세요</button>
			</div>
	</div>

	<%@ include file="/WEB-INF/views/includes/09_footer.jsp"%>
</body>
</html>