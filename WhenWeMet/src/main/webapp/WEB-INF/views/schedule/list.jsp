<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@ include file="/WEB-INF/views/includes/00_head.jsp" %>
</head>
<body class="main-pages">
	<div class="container">
        <%@ include file="/WEB-INF/views/includes/03_header.jsp" %>
		
		<c:catch>
			<c:choose>
				<c:when test="${emtpy authInfo }">
					<p>받은 초대가 없습니다.</p>
				</c:when>
				<c:otherwise>
					<c:forEach var="meeting" items="${myMeeting}" varStatus="status">
						<a class="dropdown-item" href="${pageContext.request.contextPath }/schedule/manage?${meeting.mid}">${meeting.mid}</a>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</c:catch>
		
        <%@ include file="/WEB-INF/views/includes/09_footer.jsp" %>
    </div>
	
</body>

</html>