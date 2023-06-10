<%@ page import="bookMark.BookMarkDB" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<html>
<head>
    <title>Title</title>
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
<%
    String bookmarkID = request.getParameter("ID");
    System.out.println(bookmarkID);
    BookMarkDB bookMarkDB = new BookMarkDB();
    ArrayList<HashMap<String, String>> result = bookMarkDB.showDeletePage(bookmarkID);
    if (result.size() > 0) {
        HashMap<String, String> data = result.get(0);
%>
<script>
    function deleteBookmark(bookmarkID) {
        var form = document.createElement("form");
        form.method = "POST";
        form.action = "deleteBookmarkSubmit.jsp";

        var inputBookmarkID = document.createElement("input");
        inputBookmarkID.type = "hidden";
        inputBookmarkID.name = "bookmarkID";
        inputBookmarkID.value = bookmarkID;

        form.appendChild(inputBookmarkID);
        document.body.appendChild(form);

        form.submit();
    }
</script>
<body>
<h2>와이파이 정보 구하기</h2>
<a href="../home.jsp">홈</a> | <a href="../history.jsp">위치 히스토리 목록</a> | <a href="../wifiList.jsp">Open API 와이파이 정보 가져오기</a> |
<a href="showBookMark.jsp">북마크 보기</a> | <a href="editBookMark.jsp">북마크 그룹 관리</a> <br>
<br>
북마크를 그룹을 삭제하시겠습니까?

<table class="tg" width="100%">
    <tr>
        <td class="tgHead" width="20%">북마크 이름</td>
        <td class="tgBodyEven"><input id="bookmarkName" type="text" value="<%= data.get("BOOKMARKNAME") %>"></td>    </tr>
    <tr>
        <td class="tgHead" width="20%">순서</td>
        <td class="tgBodyOdd"><input id="number" type="number" value="<%= data.get("NUM") %>"></td>
    </tr>
    <tr>
        <td colspan="2" align="center"><a href="editBookMark.jsp">돌아가기</a> | <button onclick="deleteBookmark('<%= bookmarkID %>')">삭제</button>
    </tr>



<%
    } else {
    System.out.println("해당 ID와 일치하는 북마크가 없습니다.");
    }
%>

</body>
</html>
