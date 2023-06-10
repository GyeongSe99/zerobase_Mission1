<%@ page import="bookMark.BookMarkDB" %>
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
  alert("북마크 그룹 삭제에 실패하였습니다.")
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
  alert("북마크 그룹을 삭제하였습니다.")
  window.location.href = "showBookMark.jsp"
</script>
</body>
</html>
