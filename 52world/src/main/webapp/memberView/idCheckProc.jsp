<%@page import="com.dev.dao.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>idCheckProc.jsp</title>
</head>
<body>
<div style="text-align:center;">
<h3>아이디 중복체크 결과</h3>


<%
	MemberDAO dao = new MemberDAO();
	String id= request.getParameter("id").trim();
	int cnt=dao.checkId(id);
	out.println("입력 ID : <strong>"+id+"</strong>");
	if(cnt==0){
		out.println("<p>사용 가능한 아이디 입니다</p>");
		out.println("<a href='javascript:apply(\""+id+"\")'>[적용]</a>");
	
	}else{
		out.println("<p style='color:red'>해당 아이디는 사용할 수 없습니다.</p>");
	}
%>	
	<hr>
	<a href="javascript:history.back()">[다시검색]</a>
	&nbsp;&nbsp;
	<a href="javascript:window.close()">[창닫기]</a>
	
	
	</div>
</body>
</html>