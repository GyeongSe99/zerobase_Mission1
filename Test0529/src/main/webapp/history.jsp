<%@ page import="dbTest.DbProcess" %>
<%@ page import="java.util.*" %><%--
  Created by IntelliJ IDEA.
  User: segyeong
  Date: 2023-05-29
  Time: 오후 9:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<html>
<head>
    <title>와이파이 정보 구하기</title>
</head>
<body>
    <h2>와이파이 정보 구하기</h2>
    <a href="home.jsp">홈</a> | <a href="history.jsp">위치 히스토리 목록</a> | <a href="wifiList.jsp">Open API 와이파이 정보 가져오기</a> |
    <a href="home.jsp">북마크 보기</a> | <a href="home.jsp">북마크 그룹 관리</a>  <br>

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
    <table class="tg" width="100%">
    <thead>
    <tr>
        <th class="tgHead">ID</th>
        <th class="tgHead">x좌표</th>
        <th class="tgHead">y좌표</th>
        <th class="tgHead">조회일자</th>
        <th class="tgHead">비고</th>
    </tr>
    </thead>
<tbody>
<%
    DbProcess proc = new DbProcess();
    ArrayList<HashMap<String, String>> result = proc.dbSelectHistory();

    if (result.size() > 0) {
        for (int i = result.size() - 1; i >= 0; i--) {
            Map data = result.get(i);
            if (i % 2 == 0) {
%>
    <tr class="tgBodyEven">
        <td><%=data.get("ID") %></td>
        <td><%=data.get("LAT") %></td>
        <td><%=data.get("LNT") %></td>
        <td><%=data.get("LOOKDATE") %></td>
        <td style="text-align: center">
            <form method="post" action="deleteHistory.jsp">
            <input type="hidden" name="id" value="<%=data.get("ID") %>">
            <input type="submit" value="삭제">
        </form>
        </td>
    </tr>
<%
            } else {
%>
<tr class="tgBodyOdd">
    <td><%=data.get("ID") %></td>
    <td><%=data.get("LAT") %></td>
    <td><%=data.get("LNT") %></td>
    <td><%=data.get("LOOKDATE") %></td>
    <td style="text-align: center">
        <form method="post" action="deleteHistory.jsp">
            <input type="hidden" name="id" value="<%=data.get("ID") %>">
            <input type="submit" value="삭제">
        </form>
    </td>
</tr>
<%
            }
        }
    } else {
        %>
<tr>
    <td colapan=5 align="center">위치를 조회했던 히스토리가 없습니다.</td>
</tr>
<%
            }
%>
</tbody>
    </table>
</body>
</html>
