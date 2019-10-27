<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ include file="/WEB-INF/views/includes/00_head.jsp"%>

<body class="main-pages">
	<div class="container" style="height: 100%">
		<%@ include file="/WEB-INF/views/includes/03_header.jsp"%>
		<div class="jumbotron">
			<div class="signup-box">
				<div class="signup-logo">
					<b>새로운 모임 만들기</b>
				</div>
				<br><br>
				<sec:authentication property="principal" var="userid" />
				<div>
					<form:form role="form" modelAttribute="meetingCommand"
						action="${pageContext.request.contextPath }/meeting/makeProcess"
						method="POST">

						<div>
							<form:input type="hidden" path="creator" value="${authInfo.id}" />
						</div>

						<div class="row">
							<div class="form-group has-feedback">
								<form:input type="text" class="form-control" placeholder="모임 이름"
									path="mname" />
								<span class="glyphicon glyphicon-envelope form-control-feedback"></span>
								<form:errors path="mname" class="make-errors" />
							</div>

							<div>
								<button type="submit" class="btn btn-info">만들기</button>
							</div>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/views/includes/09_footer.jsp"%>
</body>

