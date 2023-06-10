<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>

<%--
  Created by IntelliJ IDEA.
  User: segyeong
  Date: 2023-06-08
  Time: 오후 8:25
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
<table class="tg" width="100%">
    <tr>
        <td class="tgHead" width="20%">북마크 이름</td>
        <td class="tgBodyEven"><input id="bookmarkName" type="text" value="<%= request.getParameter("bookmarkName") %>"></td>
    </tr>
    <tr>
        <td class="tgHead" width="20%">순서</td>
        <td class="tgBodyOdd"><input id="number" type="number" value="<%= request.getParameter("number") %>"></td>
    </tr>
    <tr>
        <td colspan="2" align="center"><a href="editBookMark.jsp">돌아가기</a> | <button onclick="submitPage()">수정</button></td>
        <form id="submitPage" action="renameBookMarkSubmit.jsp" method="post">
            <input type="hidden" name="bookmarkName" id="hiddenBookmarkName">
            <input type="hidden" name="number" id="hiddenNumber">
            <input type="hidden" name="ID" value="<%= request.getParameter("ID") %>">
        </form>
    </tr>

    <script>
        function submitPage() {
            var bookmarkName = document.getElementById("bookmarkName").value;
            var number = document.getElementById("number").value;

            document.getElementById("hiddenBookmarkName").value = bookmarkName;
            document.getElementById("hiddenNumber").value = number;

            document.getElementById("submitPage").submit();
        }
    </script>
</table>
</body>
</html>

