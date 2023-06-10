<%@ page import="dbTest.DbProcess" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="bookMark.BookMarkDB" %><%--
  Created by IntelliJ IDEA.
  User: segyeong
  Date: 2023-06-02
  Time: 오후 3:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<html>
<head>
    <title>와이파이 정보 구하기</title>
    <style type="text/css">
        .tg  {border-collapse:collapse; width: 100%}
        .tg td{border-color:#cdcdcd;border-style:solid;border-width:1px;font-family:Arial, sans-serif;font-size:14px;
            overflow:hidden;padding:6px 8px;word-break:normal;}
        .tg th{border-color:#cdcdcd;border-style:solid;border-width:1px;font-family:Arial, sans-serif;font-size:14px;
            font-weight:normal;overflow:hidden;padding:6px 8px;word-break:normal;}
        .tg .tg-key{background-color:#28be86;border-color:#cdcdcd;color:#ffffff;text-align: center; width: 20%}
        .tg .tg-valueOdd{background-color: #e6e6e6;border-color: #cdcdcd;color: rgba(19,19,19,0.96); width: 80%}
        .tg .tg-valueEven{background-color:#ffffff;border-color: #cdcdcd;color: rgba(19,19,19,0.96); text-align:left; width: 80%}
        .no-line-break {
            display: inline-block;
            vertical-align: middle;
        }
    </style>
</head>
<body>
<h2>와이파이 상세 정보</h2>
<a href="home.jsp">홈</a> | <a href="history.jsp">위치 히스토리 목록</a> | <a href="wifiList.jsp">Open API 와이파이 정보 가져오기</a> |
<a href="home.jsp">북마크 보기</a> | <a href="home.jsp">북마크 그룹 관리</a>  <br>
<br>
<%
    String WifiID = request.getParameter("wifiId");
    String lat = request.getParameter("LAT");
    String lnt = request.getParameter("LNT");

    BookMarkDB bookMarkDB = new BookMarkDB();
    ArrayList<HashMap<String, String>> resultBookmark = bookMarkDB.showBookMarkInDetail();

    DbProcess proc = new DbProcess();
    ArrayList<HashMap<String, String>> resultMainDB = proc.dbSelectDetail(WifiID, lat, lnt);
    HashMap<String, String> data1 = resultMainDB.get(0);
    String wifiName = data1.get("X_SWIFI_MAIN_NM");
    System.out.println(wifiName);

%>
<select name="bookmarkList" id="bookmarks" class="no-line-break">
    <option>북마크 그룹 이름 추가</option>
    <% if (resultBookmark.size() > 0) {
        for (HashMap<String, String> bookmark : resultBookmark) {
            String bookmarkId = bookmark.get("ID");
            String bookmarkName = bookmark.get("BOOKMARKNAME");
    %>
    <option value="<%= bookmarkId %>"><%= bookmarkName %></option>
    <% }
    } %>
</select>
<script>
    function addBookmark() {
        var selectedBookmark = document.getElementById("bookmarks").value;
        if (selectedBookmark) {
            var form = document.createElement("form");
            form.method = "POST";
            form.action = "wifiAddBookMark.jsp";

            var inputBookmarkId = document.createElement("input");
            inputBookmarkId.type = "hidden";
            inputBookmarkId.name = "bookmarkId";
            inputBookmarkId.value = selectedBookmark;

            var inputId = document.createElement("input");
            inputId.type = "hidden";
            inputId.name = "WifiID";
            inputId.value = '<%= WifiID %>';

            var inputLat = document.createElement("input");
            inputLat.type = "hidden";
            inputLat.name = "lat";
            inputLat.value = '<%= lat %>';

            var inputLnt = document.createElement("input");
            inputLnt.type = "hidden";
            inputLnt.name = "lnt";
            inputLnt.value = '<%= lnt %>';

            var inputWifiName = document.createElement("input");
            inputWifiName.type = "hidden";
            inputWifiName.name = "WIFINAME";
            inputWifiName.value = '<%= wifiName %>';

            <%-- <input name="WIFINAME" value="<%= resultMainDB.get(0).get("WIFINAME") %>"> --%>
            form.appendChild(inputBookmarkId);
            form.appendChild(inputId);
            form.appendChild(inputLat);
            form.appendChild(inputLnt);
            form.appendChild(inputWifiName);
            document.body.appendChild(form);

            form.submit();
        } else {
            alert("북마크 그룹을 선택하세요.");
        }
    }
</script>
<button type="button" onclick="addBookmark()" class="no-line-break">북마크에 추가</button>

<%
    if (resultMainDB.size() > 0) {
        for (int i = 0; i < resultMainDB.size(); i++) {
            HashMap<String, String> data = resultMainDB.get(i);
%>
<table class="tg" width="100%">
    <tbody>
    <tr>
        <th class="tg-key">거리(Km)</th>
        <th class="tg-valueEven"><%=data.get("distance")%></th>
    </tr>
    <tr>
        <td class="tg-key">관리번호</td>
        <td class="tg-valueOdd"><%=data.get("X_SWIFI_MGR_NO")%></td>
    </tr>
    <tr>
        <td class="tg-key">자치구</td>
        <td class="tg-valueEven"><%=data.get("X_SWIFI_WRDOFC")%></td>
    </tr>
    <tr>
        <td class="tg-key">와이파이명</td>
        <td class="tg-valueOdd"><%=data.get("X_SWIFI_MAIN_NM")%></td>
    </tr>
    <tr>
        <td class="tg-key">도로명주소</td>
        <td class="tg-valueEven"><%=data.get("X_SWIFI_ADRES1")%></td>
    </tr>
    <tr>
        <td class="tg-key">상세주소</td>
        <td class="tg-valueOdd"><%=data.get("X_SWIFI_ADRES2")%></td>
    </tr>
    <tr>
        <td class="tg-key">설치위치(층)</td>
        <td class="tg-valueEven"><%=data.get("X_SWIFI_INSTL_FLOOR")%></td>
    </tr>
    <tr>
        <td class="tg-key">설치유형</td>
        <td class="tg-valueOdd"><%=data.get("X_SWIFI_INSTL_TY")%></td>
    </tr>
    <tr>
        <td class="tg-key">설치기관</td>
        <td class="tg-valueEven"><%=data.get("X_SWIFI_INSTL_MBY")%></td>
    </tr>
    <tr>
        <td class="tg-key">서비스구분</td>
        <td class="tg-valueOdd"><%=data.get("X_SWIFI_SVC_SE")%></td>
    </tr>
    <tr>
        <td class="tg-key">망종류</td>
        <td class="tg-valueEven"><%=data.get("X_SWIFI_CMCWR")%></td>
    </tr>
    <tr>
        <td class="tg-key">설치년도</td>
        <td class="tg-valueOdd"><%=data.get("X_SWIFI_CNSTC_YEAR")%></td>
    </tr>
    <tr>
        <td class="tg-key">실내외구분</td>
        <td class="tg-valueEven"><%=data.get("X_SWIFI_INOUT_DOOR")%></td>
    </tr>
    <tr>
        <td class="tg-key">WIFI접속환경</td>
        <td class="tg-valueOdd"><%=data.get("X_SWIFI_REMARS3")%></td>
    </tr>
    <tr>
        <td class="tg-key">X좌표</td>
        <td class="tg-valueEven"><%=data.get("LAT")%></td>
    </tr>
    <tr>
        <td class="tg-key">Y좌표</td>
        <td class="tg-valueOdd"><%=data.get("LNT")%></td>
    </tr>
    <tr>
        <td class="tg-key">작업일자</td>
        <td class="tg-valueEven"><%=data.get("WORK_DTTM")%></td>
    </tr>
    </tbody>
</table>
<%
        }
    }
%>

</body>
</html>
