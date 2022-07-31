<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>memberOutput.jsp</title>
</head>
<body>
<!--memberProc에서 member 값을 가져올거임 -->
<c:set var="vo" value="${member }"></c:set>
<p><c:out value="${vo.name }"></c:out>님 가입완료</p>

</body>
</html>