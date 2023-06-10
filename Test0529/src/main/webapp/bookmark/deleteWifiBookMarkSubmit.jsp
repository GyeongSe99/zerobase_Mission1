<%@ page import="bookMark.BookMarkDB" %><%--
  Created by IntelliJ IDEA.
  User: segyeong
  Date: 2023-06-09
  Time: 오후 7:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    request.setCharacterEncoding("utf-8");

    String ID = request.getParameter("bookmarkID");

    BookMarkDB bookMarkDB = new BookMarkDB();
    if (ID != null) {
        bookMarkDB.deleteBookMark(ID);
    } else {
        %>
<script>
    alert("북마크 정보 삭제에 실패하였습니다.")
    window.location.href = "showBookMark.jsp"
</script>
<%
    }
%>
<html>
<head>
    <title>와이파이 정보 구하기</title>
</head>
<body>
<script>
    alert("북마크 정보를 삭제하였습니다.")
    window.location.href = "showBookMark.jsp"
</script>
</body>
</html>
