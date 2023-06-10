<%@ page import="bookMark.BookMarkDB" %><%--
  Created by IntelliJ IDEA.
  User: segyeong
  Date: 2023-06-09
  Time: 오후 1:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%
    request.setCharacterEncoding("utf-8");
    String bookmarkID = request.getParameter("bookmarkId");
    System.out.println("bookmarkID = " + bookmarkID);
    String wifiID = request.getParameter("WifiID");
    String LAT = request.getParameter("lat");
    String LNT = request.getParameter("lnt");
    String WIFINAME = request.getParameter("WIFINAME");

    BookMarkDB bookMarkDB = new BookMarkDB();
    bookMarkDB.updateWifiInBookMark(bookmarkID, wifiID, WIFINAME, LAT, LNT);
%>
<html>
<head>
    <title>와이파이 정보 구하기</title>
</head>
<body>
<%
    if (bookmarkID == null || bookmarkID.equals("") ||bookmarkID.equals("북마크 그룹 이름 추가")) {
        %>
<script>
    alert("북마크 그룹 정보 추가에 실패하였습니다!!")
    window.location.href = "bookmark/showBookMark.jsp";
</script>
<%
    } else {
%>
<script>
    alert("북마크 그룹 정보를 추가하였습니다.")
    window.location.href = "bookmark/showBookMark.jsp";
</script>
<%
    }
%>
</body>
</html>
