<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ page import="bookMark.BookMarkDB" %>

<html>
<head>
    <title>와이파이 정보 구하기</title>
</head>
<body>
<%
    request.setCharacterEncoding("utf-8");
    String bookmarkName = request.getParameter("bookmarkName");
    String num = request.getParameter("number");
    String ID = request.getParameter("ID");

    System.out.println(bookmarkName.length());

    BookMarkDB bookMarkDB = new BookMarkDB();
    bookMarkDB.updateBookMark(bookmarkName, num, ID);
    %>
<script>
    alert("북마크 그룹 정보를 수정하였습니다.")
    window.location.href = "editBookMark.jsp"
</script>
</body>
</html>
