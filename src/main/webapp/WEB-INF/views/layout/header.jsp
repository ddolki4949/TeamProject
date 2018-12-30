<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<div class="w3-top top-bar">
 <div class="w3-bar w3-theme-d2 w3-left-align w3-large">
 <%-- 각 페이지 연결할 아이콘 --%>
 <div id="bar_icon">
  <a class="w3-bar-item w3-button w3-hide-medium w3-hide-large w3-right w3-padding-large w3-hover-white w3-large w3-theme-d2" href="javascript:void(0);" onclick="openNav()"><i class="fa fa-bars"></i></a>
  <a href="#" class="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white" title="News"><i class="fa fa-globe" style="color:#B0F8EC"></i></a>
  <a href="#" class="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white" onclick="window.open('multiChat', 'multiChat', 'width=370, height=450')" title="Chat"><i class="fa fa-user" style="color:#A0EEEE"></i></a>
  <a href="messageList" class="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white" title="Send Message"><i class="fa fa-envelope" style="color:#A4D5FE"></i></a>
	<%-- 새 글 알림 --%>
	<div class="w3-dropdown-hover w3-hide-small">
    	<button class="w3-button w3-padding-large" title="Notifications">
    		<i class="fa fa-bell" style="color:#A3B4FD"></i>
    		<span class="w3-badge w3-right w3-small w3-green">3</span>
    	</button>  
   			<%-- 새 글 알림 드롭다운 --%>   
    		<div class="w3-dropdown-content w3-card-4 w3-bar-block" style="width:300px">
      			<a href="#" class="w3-bar-item w3-button">One new friend request</a>
     			 <a href="#" class="w3-bar-item w3-button">John Doe posted on your wall</a>
     			 <a href="#" class="w3-bar-item w3-button">Jane likes your post</a>
   			 </div>
  	</div>
  	<%-- 새 글 알림 --%>
</div> 
<%-- 각 페이지 연결할 아이콘 --%> 
  <%-- 로고 --%>
  <div class="babylogo">
  	<a href="${pageContext.request.contextPath}/main" class="w3-bar-item w3-button w3-padding-large w3-theme-d4">
  		<img src="${pageContext.request.contextPath}/resources/img/logo2.png" height="50px">
  	</a>
  </div> 
  <%-- 로그인 확인, 로그아웃 및 회원 정보 관리 --%>
  <h6 class="w3-bar-item w3-button w3-hide-small w3-right w3-padding-large w3-hover-white" >
  	<c:if test="${!empty member.ID}">
		<a href="${pageContext.request.contextPath}/logout">${member.ID}(${member.NAME})</a>
	</c:if>
	<c:if test="${empty member.ID}">
		<a href="${pageContext.request.contextPath}/login">로그인</a>
	</c:if>
  </h6>
 </div>
</div>

<!-- Navbar on small screens -->
<div id="navDemo" class="w3-bar-block w3-theme-d2 w3-hide w3-hide-large w3-hide-medium w3-large">
  <a href="#" class="w3-bar-item w3-button w3-padding-large">Link 1</a>
  <a href="#" class="w3-bar-item w3-button w3-padding-large">Link 2</a>
  <a href="#" class="w3-bar-item w3-button w3-padding-large">Link 3</a>
  <a href="#" class="w3-bar-item w3-button w3-padding-large">My Profile</a>
</div>