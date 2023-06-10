<%@ page import="dbTest.CallApi" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="dbTest.DbProcess" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<html>
<head>
    <title>와이파이 정보 구하기</title>
</head>
<body>
    <%
        String sUrl = "jdbc:mariadb://127.0.0.1:3306/mission1";
        String dbUserID = "mission1user";
        String dbPassword = "mission1";
        Connection DBconnection = null;
        try {
            Class.forName("org.mariadb.jdbc.Driver");

            DBconnection = DriverManager.getConnection(sUrl, dbUserID, dbPassword);
            DbProcess dbProcess = new DbProcess();
            // DB안의 내용을 새로 받아오기 위해선 한번 싹 지운 후 다시 받아오기
            dbProcess.dbDeleteAllData(DBconnection);

        } catch (Exception e) {
            System.out.println("db connection error-----" + e.getMessage());
        } finally {
            if (DBconnection != null && !DBconnection.isClosed()) {
                DBconnection.close();
            }
        }
    %>
    <%
        int total_count = -1;
        try {
            CallApi api = new CallApi();

            total_count = api.getTotal();
            System.out.println(total_count);

            api.addWifiData(total_count);


        } catch (Exception e) {
            e.printStackTrace();
        }
    %>
<div style="text-align: center"><h1><%=total_count%>개의 WIFI정보를 정상적으로 저장하였습니다.</h1></div>
    <div style="text-align: center"><a href="home.jsp">홈으로 가기</a></div>

</body>
</html>
