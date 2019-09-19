<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

        <div class="header">
            <nav>
               <ul class="nav nav-pills pull-right">
                  <c:catch>
                     <c:choose>
                         <c:when test="${empty authInfo }">
                            <ul class="nav nav-pills pull-right">
                               <li role="presentation"><a href="${pageContext.request.contextPath }/user/signin">Sign in</a></li>
                               <li role="presentation"><a href="${pageContext.request.contextPath }/user/signup/step1">Sign up</a></li>
                            </ul>
                         </c:when>
                         <c:otherwise>
                            <c:choose>
                               <c:when test="${authInfo.role eq 0 }">
                                  <ul class="nav nav-pills pull-right">
                                     <li role="presentation"><a href="#">관리자 ${authInfo.name }님, 안녕하세요.</a></li>
                                     <li role="presentation"><a href="${pageContext.request.contextPath }/user/signout">Sign out</a></li>
                                  </ul>                            
                               </c:when>
                               <c:otherwise>
                                  <ul class="nav nav-pills pull-right">
                                     <li role="presentation"><a href="#">${authInfo.name }님, 반갑습니다.</a></li>
                                     <li role="presentation"><a href="${pageContext.request.contextPath }/user/signout">Sign out</a></li>
                                  </ul>          
                               </c:otherwise>
                            </c:choose>
                         </c:otherwise>
                     </c:choose>
                  </c:catch>
               </ul>
            </nav>
            

            <h3 class="text-muted"><b>When</b>We</b>Met</h3>
        </div>