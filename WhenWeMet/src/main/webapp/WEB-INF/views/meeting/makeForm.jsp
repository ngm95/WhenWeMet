<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@ include file="/WEB-INF/views/includes/00_head.jsp" %>
    <title>모임 만들기 | WhenWeMet</title>
</head>
<body class="signup-pages">

    <div class="signup-box">
        <div class="signup-logo">
            <a href="#"><b>When</b>We<b>Met</b></a>
        </div>

        <div class="signup-box-body">
            <p class="box-msg">모임 만들기 양식</p>

            <form:form role="form" modelAttribute="meetingCommand" action="${pageContext.request.contextPath }/meeting/make" method="POST">
                <div class="form-group has-feedback">
                    <form:input type="text" class="form-control" placeholder="모임 이름" path="mname"/>
                    <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
                    <form:errors path="mname" class="make-errors"/>
                </div>
                <div>
                	<form:input type="hidden" placeholder="${authInfo.id}" path="creator" value="${authInfo.id}"/>
                </div>
                 
                <div class="row">
                    <div class="col-xs-3">
                        <button type="submit" class="btn btn-style">만들기</button>
                    </div>
                </div>
            </form:form>
        </div>
    </div>
</body>
</html>