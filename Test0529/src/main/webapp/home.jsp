<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ page import="dbTest.DbProcess" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>
<!DOCTYPE html>
<head>
    <title>와이파이 정보 구하기</title>

</head>
<body>

<%
    String lat = request.getParameter("latitudeInput");
    String lnt = request.getParameter("longitudeInput");

%>

<form action="Location">
    <h2>와이파이 정보 구하기</h2>
    <a href="home.jsp">홈</a> | <a href="history.jsp">위치 히스토리 목록</a> | <a href="wifiList.jsp">Open API 와이파이 정보 가져오기</a> |
    <a href="bookmark/showBookMark.jsp">북마크 보기</a> | <a href="bookmark/editBookMark.jsp">북마크 그룹 관리</a> <br>
    <br>
    LAT: <input type="number" id="latitudeInput" value="<%=lat%>">, LNT: <input type="number" id="longitudeInput" value="<%=lnt%>">
    <button type="button" onclick="getLocation()">내 위치 가져오기</button>
    <button type="button" onclick="getNearWifiList(latitudeInput.value, longitudeInput.value)">근처 WIFI 정보 보기</button>  <br>

    <br>
</form>

<script type="text/javascript">
    let userLatitude;
    let userLongitude;

    // 버튼 클릭 시 위치 정보를 가져오는 함수
    function getLocation() {
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(showPosition);
        } else {
            alert("Geolocation is not supported by this browser.");
        }
    }

    // 위치 정보를 받아온 후 처리하는 콜백 함수
    function showPosition(position) {
        var latitude = position.coords.latitude;
        var longitude = position.coords.longitude;

        // 위치 정보를 input에 넣기
        userLatitude = latitude;
        userLongitude = longitude;
        document.getElementById("latitudeInput").value = latitude;
        document.getElementById("longitudeInput").value = longitude;
    }


    // 입력값을 매개변수로 넘긴 홈페이지 리셋
    function getNearWifiList(latitudeInput, longitudeInput) {
        location.href = "home.jsp?latitudeInput=" + latitudeInput +"&longitudeInput=" + longitudeInput;
    }

</script>
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
            <th class="tgHead">거리(Km)</th>
            <th class="tgHead">관리번호</th>
            <th class="tgHead">자치구</th>
            <th class="tgHead">와이파이명</th>
            <th class="tgHead">도로명주소</th>
            <th class="tgHead">상세주소</th>
            <th class="tgHead">설치위치(층)</th>
            <th class="tgHead">설치유형</th>
            <th class="tgHead">설치기관</th>
            <th class="tgHead">서비스구분</th>
            <th class="tgHead">망종류</th>
            <th class="tgHead">설치년도</th>
            <th class="tgHead">실내외구분</th>
            <th class="tgHead">WIFI접속환경</th>
            <th class="tgHead">x좌표</th>
            <th class="tgHead">y좌표</th>
            <th class="tgHead">작업일자</th>
        </tr>
        </thead>
        <tbody>
<%
    // 경도와 위도가 입력값이 있을때만 실행하는 페이지
    if ( lat != null && lat.length() > 0 &&  lnt != null  && lnt.length() > 0){

        DbProcess proc = new DbProcess();
        Double dLat = Double.valueOf(lat);
        Double dLnt = Double.valueOf(lnt);
        proc.dbInsertHistory(dLat, dLnt);
        ArrayList<HashMap<String, String>> result = proc.dbSelectNearWifi(dLat, dLnt);

        int listCount = result.size();

        if ( listCount <= 0 ){
%>
        <tr>
            <td colspan=17 align="center">위치 정보를 입력한 후에 조회해 주세요</td>
        </tr>
<%
        } else {

        for (int cnt = 0 ; cnt < listCount; cnt++){
            HashMap<String, String> data = result.get(cnt);
            if (cnt % 2 == 0) {
%>
<tr class="tgBodyEven">
    <td><%=data.get("distance")%></td>
    <td><%=data.get("X_SWIFI_MGR_NO") %></td>
    <td><%=data.get("X_SWIFI_WRDOFC") %></td>
    <td><a href="detail.jsp?wifiId=<%=data.get("X_SWIFI_MGR_NO")%>&LAT=<%=lat%>&LNT=<%=lnt%>"><%=data.get("X_SWIFI_MAIN_NM") %></a></td>
    <td><%=data.get("X_SWIFI_ADRES1") %></td>
    <td><%=data.get("X_SWIFI_ADRES2") %></td>
    <td><%=data.get("X_SWIFI_INSTL_FLOOR") %></td>
    <td><%=data.get("X_SWIFI_INSTL_TY") %></td>
    <td><%=data.get("X_SWIFI_INSTL_MBY") %></td>
    <td><%=data.get("X_SWIFI_SVC_SE") %></td>
    <td><%=data.get("X_SWIFI_CMCWR") %></td>
    <td><%=data.get("X_SWIFI_CNSTC_YEAR") %></td>
    <td><%=data.get("X_SWIFI_INOUT_DOOR") %></td>
    <td><%=data.get("X_SWIFI_REMARS3") %></td>
    <td><%=data.get("LAT") %></td>
    <td><%=data.get("LNT") %></td>
    <td><%=data.get("WORK_DTTM") %></td>
</tr>
<%
            } else {
%>
<tr class="tgBodyOdd">
    <td><%=data.get("distance") %></td>
    <td><%=data.get("X_SWIFI_MGR_NO") %></td>
    <td><%=data.get("X_SWIFI_WRDOFC") %></td>
    <td><a href="detail.jsp?wifiId=<%=data.get("X_SWIFI_MGR_NO")%>&LAT=<%=lat%>&LNT=<%=lnt%>"><%=data.get("X_SWIFI_MAIN_NM") %></a></td>
    <td><%=data.get("X_SWIFI_ADRES1") %></td>
    <td><%=data.get("X_SWIFI_ADRES2") %></td>
    <td><%=data.get("X_SWIFI_INSTL_FLOOR") %></td>
    <td><%=data.get("X_SWIFI_INSTL_TY") %></td>
    <td><%=data.get("X_SWIFI_INSTL_MBY") %></td>
    <td><%=data.get("X_SWIFI_SVC_SE") %></td>
    <td><%=data.get("X_SWIFI_CMCWR") %></td>
    <td><%=data.get("X_SWIFI_CNSTC_YEAR") %></td>
    <td><%=data.get("X_SWIFI_INOUT_DOOR") %></td>
    <td><%=data.get("X_SWIFI_REMARS3") %></td>
    <td><%=data.get("LAT") %></td>
    <td><%=data.get("LNT") %></td>
    <td><%=data.get("WORK_DTTM") %></td>
</tr>
<%
            }
        }
    }
} else {
%>
    <tr>
        <td colspan=17 align="center">위치 정보를 입력한 후에 조회해 주세요</td>
    </tr>
<%
    }

%>
        </tbody>

    </table>


</body>
</html>
