<%@ page import="dbTest.DbProcess" %><%--
  Created by IntelliJ IDEA.
  User: segyeong
  Date: 2023-06-02
  Time: 오후 9:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<html>
<head>
</head>
<body>
<%
  String id = request.getParameter("id");

  // 데이터 삭제를 위한 쿼리 실행
  DbProcess proc = new DbProcess();
  proc.dbDeleteHistoryData(id);

  // 삭제 후에는 다시 원래 페이지로 리다이렉트
  response.sendRedirect("history.jsp");
%>
</body>
</html>
