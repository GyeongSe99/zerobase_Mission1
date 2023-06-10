<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>

<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="bookMark.BookMarkDB" %>
<%@ page import="java.util.Map" %><%--
  Created by IntelliJ IDEA.
  User: segyeong
  Date: 2023-06-03
  Time: 오전 12:23
  To change this template use File | Settings | File Templates.
--%>

<html>
<head>
    <title>와이파이 정보 구하기</title>
    <style type="text/css">
        .tg  {border-collapse:collapse;border-spacing:0;}
        .tg td{border-color:#cdcdcd;border-style:solid;border-width:1px;font-family:Arial, sans-serif;font-size:14px;
            overflow:hidden;padding:10px 5px;word-break:normal;}
        .tg th{border-color:#cdcdcd;border-style:solid;border-width:1px;font-family:Arial, sans-serif;font-size:14px;
            font-weight:normal;overflow:hidden;padding:10px 5px;word-break:normal;}
        .tg .tgHead{background-color:#28be86;border-color:#cdcdcd;color:#ffffff;text-align:center;vertical-align:top}
        .tg .tgBodyOdd{background-color: #e6e6e6;border-color: #cdcdcd;color: rgba(19,19,19,0.96); font-family:Arial, sans-serif;font-size:14px}
        .tg .tgBodyEven{background-color:#ffffff;border-color: #cdcdcd;color: rgba(19,19,19,0.96); font-family:Arial, sans-serif;font-size:14px}
    </style>
</head>
<body>
<h2>와이파이 정보 구하기</h2>
<a href="../home.jsp">홈</a> | <a href="../history.jsp">위치 히스토리 목록</a> | <a href="../wifiList.jsp">Open API 와이파이 정보 가져오기</a> |
<a href="showBookMark.jsp">북마크 보기</a> | <a href="editBookMark.jsp">북마크 그룹 관리</a> <br>
<br>
<button onclick="redirectToWifiList()">북마크 그룹 이름 추가</button>
<script>
    function redirectToWifiList() {
        window.location.href = "bookMarkAdd.jsp";
    }
</script>

<table class="tg" width="100%">
    <tr>
        <td class="tgHead">ID</td>
        <td class="tgHead">북마크 이름</td>
        <td class="tgHead">순서</td>
        <td class="tgHead">등록일자</td>
        <td class="tgHead">수정일자</td>
        <td class="tgHead">비고</td>
    </tr>
    <%
        BookMarkDB bookMarkDB = new BookMarkDB();
        ArrayList<HashMap<String, String>> result = bookMarkDB.showBookMarkList();

        if (result.size() > 0) {
            for (int i = 0; i < result.size(); i++) {
                Map data = result.get(i);
    %>
    <tr>
        <td><%=data.get("ID") %></td>
        <td><%=data.get("BOOKMARKNAME") %></td>
        <td><%=data.get("NUM") %></td>
        <td><%=data.get("REGISTERDATE") %></td>
        <td><%= (data.get("CHANGEDATE") == null || data.get("CHANGEDATE").equals("")) ? "" : data.get("CHANGEDATE") %></td>
        <td align="center">
            <a href="renameBookMark.jsp?bookmarkName=<%= data.get("BOOKMARKNAME") %>&number=<%= data.get("NUM") %>&ID=<%= data.get("ID")%>">수정</a>
            <a href="deleteBookMark.jsp?ID=<%=data.get("ID")%>">삭제</a></td>
    </tr>
    <%
            }
        } else {
    %>
    <tr>
        <td colspan="6" align="center">정보가 존재하지 않습니다.</td>
    </tr>
    <%
        }
    %>

</table>
</body>
</html>
