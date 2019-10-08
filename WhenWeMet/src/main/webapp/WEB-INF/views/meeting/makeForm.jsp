<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

    <div class="signup-box">
		<div class="signup-logo">
            <b>새로운 일정 만들기</b>
        </div>
        
        <div class="signup-box-body">

            <form:form role="form" modelAttribute="meetingCommand" action="${pageContext.request.contextPath }/meeting/makeProcess" method="POST">
                
                <div>
                	<form:input type="hidden" placeholder="${loginID}" path="creator" value="${loginID}"/>
                </div>
                 
                <div class="row">
                	<div class="form-group has-feedback">
                    	<form:input type="text" class="form-control" placeholder="모임 이름" path="mname"/>
                    	<span class="glyphicon glyphicon-envelope form-control-feedback"></span>
                    	<form:errors path="mname" class="make-errors"/>
                	</div>
                	
                    <div class="col-xs-3">
                        <button type="submit" class="btn btn-style">만들기</button>
                    </div>
                </div>
            </form:form>
        </div>
    </div>