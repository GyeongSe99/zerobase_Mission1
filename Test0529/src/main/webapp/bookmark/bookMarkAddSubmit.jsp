<%@ page import="bookMark.BookMarkDB" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>와이파이 정보 구하기</title>
</head>
<body>
<%
    request.setCharacterEncoding("utf-8");
    String bookmarkName = request.getParameter("bookmarkName");
    String num = request.getParameter("number");

    BookMarkDB bookMarkDB = new BookMarkDB();
    bookMarkDB.bookMarkNameAdd(bookmarkName, num);
%>
<script>
    alert("북마크 그룹 정보를 추가하였습니다.")
    window.location.href = "editBookMark.jsp";
</script>
</body>
</html>
