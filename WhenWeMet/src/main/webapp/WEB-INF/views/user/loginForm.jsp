<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@ include file="/WEB-INF/views/includes/00_head.jsp" %>
    <title>로그인 | WhenWeMet</title>
</head>
<body class="signup-pages">

    <div class="signup-box">
        <div class="signup-logo">
            <b>When</b>We<b>Met</b>
        </div>

        <div class="signup-box-body">
            <p class="box-msg">로그인 양식</p>

            <form:form role="form" modelAttribute="loginCommand" action="${pageContext.request.contextPath }/user/signin" method="post">
                <div class="form-group has-feedback">
                    <form:input type="text" class="form-control" placeholder="ID" path="id"/>
                    <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
                    <form:errors path="id" class="signup-errors"/>
                </div>
                <div class="form-group has-feedback">
                    <form:input type="password" class="form-control" placeholder="PASSWORD" path="password"/>
                    <span class="glyphicon glyphicon-lock form-control-feedback"></span>
                    <form:errors path="password" class="signup-errors"/>
                </div>
                <div class="row">
                    <div class="col-xs-8">
                        <label class="btn">
                            <form:checkbox path="rememberId"/> 아이디 기억하기
                        </label>
                    </div>
                    <div class="col-xs-3">
                        <button type="submit" class="btn btn-style">sign in</button>
                    </div>
                </div>
            </form:form>
            
        </div>
    </div>

</body>
</html>