<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">
	function formCheck(){
		
	}
	send();
	function send() {
		var msg = $("#messageContents").val();
		if (msg != "") {
			me
		}
		
	}
</script>
<body>
<c:if test="${member.NAME!= null}">
	<div id="sendForm" title="메세지 전송">
	  <form action="messageSendOk" method="post" onsubmit="return formCheck();">
	    <table>
		    <tr>
		   	 	<td>작성자</td>
		   	 	<td>${member.NAME}</td>
		    </tr>
		    <tr>
		   	 	<td>받는 사람</td>
		   	 	<td><input type="text" name="receiver"></td>
		    </tr>
	      	<tr>
	      		<td>전송할 내용을 입력하세요</td>
	      		<td><textarea id = "messageArea" rows="15" cols="48" name="messageContents" id="messageContents"></textarea></td>
	      	</tr>
	      	<tr>
	      		<td><input type="submit" value="보내기" id="sendBtn"></td>
	      		<td><input type="button" value="창닫기" onclick="window.close()"></td>
	      	</tr>	      
	    </table>
	  </form>
	</div>
</c:if>
</body>
</html>