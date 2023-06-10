
package dbTest;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class DbProcess {
    String url = "jdbc:mariadb://127.0.0.1:3306/mission1";
    String dbUserID = "mission1user";
    String dbPassword = "mission1";

    public void dbSelect() {

        // 5개
        // 1. ip(domain)
        // 2. port
        // 3. 계정
        // 4. 패스워드
        // 5. 인스턴스

        String url = "jdbc:mariadb://127.0.0.1:3306/mission1";
        String dbUserID = "mission1user";
        String dbPassword = "mission1";

        // 1. 드라이버로드 -> 패키지안에 드라이버 있음
        // 2. 커넥션 객체 생성
        // 3. 스테이트먼트 객체 생성
        // 4. 쿼리 실행
        // 5. 결과 수행
        // 6. 객체 연결 해제(close)

        // 1. 드라이버로드 -> 패키지안에 드라이버 있음
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // 2. 커넥션 객체 생성
        Connection connection = null;
        // 3. 스테이트먼트 객체 생성
        PreparedStatement preparedStatement = null;
        // 4. 퀴리 실행 용 resultset만들기
        ResultSet resultSet = null;

        String memberTypeValue = "email";


        try {
            connection = DriverManager.getConnection(url, dbUserID, dbPassword);

            // 4. 쿼리 실행
            String sql = "select member_type, user_id, password, name " +
                    " from member " +
                    " where member_type = ?";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, memberTypeValue);    // 쿼리의 ?에 memberTypeValue가 들어감

            resultSet = preparedStatement.executeQuery();

            // 5. 결과 수행
            while (resultSet.next()) {
                String memberType = resultSet.getString("member_type");
                String userId = resultSet.getString("user_id");
                String password = resultSet.getString("password");
                String name = resultSet.getString("name");

                System.out.println(memberType + ", " + userId + ", " + password + ", " + name + ", ");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            // 6. 객체 연결 해제 (제일 최근에 쓴 순서대로)
            try {
                if (!resultSet.isClosed()) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (!preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (!connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<HashMap<String, String>> dbSelectNearWifi(double latitude, double longitude) {
        String url = "jdbc:mariadb://127.0.0.1:3306/mission1";
        String dbUserID = "mission1user";
        String dbPassword = "mission1";
        Connection DBconnection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        ArrayList<HashMap<String, String>> resultList = new ArrayList<HashMap<String, String>>();

        try {
            DBconnection = DriverManager.getConnection(url, dbUserID, dbPassword);

            String sql ="SELECT *, round(SQRT(POWER((lat - ?), 2) + POWER((lNT - ?), 2)), 4) AS distance  FROM wifi_info  ORDER BY distance LIMIT 20;";

            preparedStatement = DBconnection.prepareStatement(sql);
            preparedStatement.setDouble(1, latitude);
            preparedStatement.setDouble(2, longitude);

            resultSet = preparedStatement.executeQuery();

            HashMap<String, String> map = null;


            while (resultSet.next()) {
                map = new HashMap<String, String>();
                String distance = resultSet.getString("distance");
                String X_SWIFI_MGR_NO = resultSet.getString("X_SWIFI_MGR_NO");
                String X_SWIFI_WRDOFC = resultSet.getString("X_SWIFI_WRDOFC");
                String X_SWIFI_MAIN_NM = resultSet.getString("X_SWIFI_MAIN_NM");
                String X_SWIFI_ADRES1 = resultSet.getString("X_SWIFI_ADRES1");
                String X_SWIFI_ADRES2 = resultSet.getString("X_SWIFI_ADRES2");
                String X_SWIFI_INSTL_FLOOR = resultSet.getString("X_SWIFI_INSTL_FLOOR");
                String X_SWIFI_INSTL_TY = resultSet.getString("X_SWIFI_INSTL_TY");
                String X_SWIFI_INSTL_MBY = resultSet.getString("X_SWIFI_INSTL_MBY");
                String X_SWIFI_SVC_SE = resultSet.getString("X_SWIFI_SVC_SE");
                String X_SWIFI_CMCWR = resultSet.getString("X_SWIFI_CMCWR");
                String X_SWIFI_CNSTC_YEAR = resultSet.getString("X_SWIFI_CNSTC_YEAR");
                String X_SWIFI_INOUT_DOOR = resultSet.getString("X_SWIFI_INOUT_DOOR");
                String X_SWIFI_REMARS3 = resultSet.getString("X_SWIFI_REMARS3");
                String LAT = resultSet.getString("LAT");
                String LNT = resultSet.getString("LNT");
                String WORK_DTTM = resultSet.getString("WORK_DTTM");


                map.put("X_SWIFI_MGR_NO", X_SWIFI_MGR_NO);
                map.put("X_SWIFI_WRDOFC", X_SWIFI_WRDOFC);
                map.put("X_SWIFI_MAIN_NM", X_SWIFI_MAIN_NM);
                map.put("X_SWIFI_ADRES1", X_SWIFI_ADRES1);
                map.put("distance", distance);
                map.put("X_SWIFI_ADRES2", X_SWIFI_ADRES2);
                map.put("X_SWIFI_INSTL_FLOOR", X_SWIFI_INSTL_FLOOR);
                map.put("X_SWIFI_INSTL_TY", X_SWIFI_INSTL_TY);
                map.put("X_SWIFI_INSTL_MBY", X_SWIFI_INSTL_MBY);
                map.put("X_SWIFI_SVC_SE", X_SWIFI_SVC_SE);
                map.put("X_SWIFI_CMCWR", X_SWIFI_CMCWR);
                map.put("X_SWIFI_CNSTC_YEAR", X_SWIFI_CNSTC_YEAR);
                map.put("X_SWIFI_INOUT_DOOR", X_SWIFI_INOUT_DOOR);
                map.put("X_SWIFI_REMARS3", X_SWIFI_REMARS3);
                map.put("LAT", LAT);
                map.put("LNT", LNT);
                map.put("WORK_DTTM", WORK_DTTM);

                resultList.add(map);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (resultSet != null && !resultSet.isClosed()) {
                    resultSet.close();
                }
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
                if (DBconnection != null && !DBconnection.isClosed()) {
                    DBconnection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return resultList;

    }

    public void dbInsert(Connection DBconnection, Row row) {
        PreparedStatement preparedStatement = null;

        try {
            // insert쿼리문 만들기. 원래 입력값은 변수로 받아야 계속 받을 수 있음.
            String sql = "insert into wifi_info (X_SWIFI_MGR_NO, X_SWIFI_WRDOFC, X_SWIFI_MAIN_NM, X_SWIFI_ADRES1, " +
                    "X_SWIFI_ADRES2, X_SWIFI_INSTL_FLOOR, X_SWIFI_INSTL_TY, X_SWIFI_INSTL_MBY, X_SWIFI_SVC_SE, " +
                    "X_SWIFI_CMCWR, X_SWIFI_CNSTC_YEAR, X_SWIFI_INOUT_DOOR, X_SWIFI_REMARS3, LAT, LNT, WORK_DTTM) " +
                    "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?); ";

            preparedStatement = DBconnection.prepareStatement(sql);
            preparedStatement.setString(1, row.getManagerNo());    // 쿼리의 ?에 memberTypeValue가 들어감
            preparedStatement.setString(2, row.getWardOffice());
            preparedStatement.setString(3, row.getMainName());
            preparedStatement.setString(4, row.getAddress1());
            preparedStatement.setString(5, row.getAddress2());
            preparedStatement.setString(6, row.getInstallationFloor());
            preparedStatement.setString(7, row.getInstallationType());
            preparedStatement.setString(8, row.getInstallationMby());
            preparedStatement.setString(9, row.getServiceSe());
            preparedStatement.setString(10, row.getCmcwr());
            preparedStatement.setString(11, row.getConstructionYear());
            preparedStatement.setString(12, row.getInOutDoor());
            preparedStatement.setString(13, row.getRemarks3());
            preparedStatement.setString(14, row.getLongitude());
            preparedStatement.setString(15, row.getLatitude());
            preparedStatement.setString(16, row.getWorkDateTime());

            int affectedrows = preparedStatement.executeUpdate();

            if (affectedrows > 0) {
                System.out.println("저장 성공");

            } else {
                System.out.println("저장 실패");
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            // 6. 객체 연결 해제 (제일 최근에 쓴 순서대로)
            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }

    public void dbInsertHistory(double latitude, double longitude) {
        Connection DBconnection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            DBconnection = DriverManager.getConnection(url, dbUserID, dbPassword);

            // insert쿼리문
            String sql = "insert into history (LAT, LNT, LOOKDATE) " +
                    "values (?, ?, now()); ";

            preparedStatement = DBconnection.prepareStatement(sql);
            preparedStatement.setDouble(1, latitude);
            preparedStatement.setDouble(2, longitude);

            int affectedrows = preparedStatement.executeUpdate();

            if (affectedrows > 0) {
                System.out.println("저장 성공");

            } else {
                System.out.println("저장 실패");
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            // 6. 객체 연결 해제 (제일 최근에 쓴 순서대로)
            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                if (DBconnection != null && !DBconnection.isClosed()) {
                    DBconnection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }

    public ArrayList<HashMap<String, String>> dbSelectHistory() {
        Connection DBconnection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        ArrayList<HashMap<String, String>> resultList = new ArrayList<>();

        try {
            DBconnection = DriverManager.getConnection(url, dbUserID, dbPassword);

            String sql ="SELECT * FROM history;";

            preparedStatement = DBconnection.prepareStatement(sql);

            resultSet = preparedStatement.executeQuery();

            HashMap<String, String> map = null;


            while (resultSet.next()) {
                map = new HashMap<String, String>();
                String ID = resultSet.getString("ID");
                String LAT = resultSet.getString("LAT");
                String LNT = resultSet.getString("LNT");
                String LOOKDATE = resultSet.getString("LOOKDATE");

                map.put("ID", ID);
                map.put("LAT", LAT);
                map.put("LNT", LNT);
                map.put("LOOKDATE", LOOKDATE);

                resultList.add(map);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (resultSet != null && !resultSet.isClosed()) {
                    resultSet.close();
                }
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
                if (DBconnection != null && !DBconnection.isClosed()) {
                    DBconnection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return resultList;

    }

    public ArrayList<HashMap<String, String>> dbSelectDetail(String id, String lat, String lnt) {
        Connection DBconnection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        ArrayList<HashMap<String, String>> resultList = new ArrayList<>();

        try {
            DBconnection = DriverManager.getConnection(url, dbUserID, dbPassword);

            String sql ="SELECT *, round(SQRT(POWER((LAT - ?), 2) + POWER((LNT - ?), 2)), 4) AS distance  FROM wifi_info WHERE X_SWIFI_MGR_NO = ?";

            preparedStatement = DBconnection.prepareStatement(sql);
            preparedStatement.setString(1, lat);
            preparedStatement.setString(2, lnt);
            preparedStatement.setString(3, id);

            resultSet = preparedStatement.executeQuery();

            HashMap<String, String> map = null;

            while (resultSet.next()) {
                map = new HashMap<String, String>();
                String distance = resultSet.getString("distance");
                String X_SWIFI_MGR_NO = resultSet.getString("X_SWIFI_MGR_NO");
                String X_SWIFI_WRDOFC = resultSet.getString("X_SWIFI_WRDOFC");
                String X_SWIFI_MAIN_NM = resultSet.getString("X_SWIFI_MAIN_NM");
                String X_SWIFI_ADRES1 = resultSet.getString("X_SWIFI_ADRES1");
                String X_SWIFI_ADRES2 = resultSet.getString("X_SWIFI_ADRES2");
                String X_SWIFI_INSTL_FLOOR = resultSet.getString("X_SWIFI_INSTL_FLOOR");
                String X_SWIFI_INSTL_TY = resultSet.getString("X_SWIFI_INSTL_TY");
                String X_SWIFI_INSTL_MBY = resultSet.getString("X_SWIFI_INSTL_MBY");
                String X_SWIFI_SVC_SE = resultSet.getString("X_SWIFI_SVC_SE");
                String X_SWIFI_CMCWR = resultSet.getString("X_SWIFI_CMCWR");
                String X_SWIFI_CNSTC_YEAR = resultSet.getString("X_SWIFI_CNSTC_YEAR");
                String X_SWIFI_INOUT_DOOR = resultSet.getString("X_SWIFI_INOUT_DOOR");
                String X_SWIFI_REMARS3 = resultSet.getString("X_SWIFI_REMARS3");
                String LAT = resultSet.getString("LAT");
                String LNT = resultSet.getString("LNT");
                String WORK_DTTM = resultSet.getString("WORK_DTTM");

                map.put("distance", distance);
                map.put("X_SWIFI_MGR_NO", X_SWIFI_MGR_NO);
                map.put("X_SWIFI_WRDOFC", X_SWIFI_WRDOFC);
                map.put("X_SWIFI_MAIN_NM", X_SWIFI_MAIN_NM);
                map.put("X_SWIFI_ADRES1", X_SWIFI_ADRES1);
                map.put("X_SWIFI_ADRES2", X_SWIFI_ADRES2);
                map.put("X_SWIFI_INSTL_FLOOR", X_SWIFI_INSTL_FLOOR);
                map.put("X_SWIFI_INSTL_TY", X_SWIFI_INSTL_TY);
                map.put("X_SWIFI_INSTL_MBY", X_SWIFI_INSTL_MBY);
                map.put("X_SWIFI_SVC_SE", X_SWIFI_SVC_SE);
                map.put("X_SWIFI_CMCWR", X_SWIFI_CMCWR);
                map.put("X_SWIFI_CNSTC_YEAR", X_SWIFI_CNSTC_YEAR);
                map.put("X_SWIFI_INOUT_DOOR", X_SWIFI_INOUT_DOOR);
                map.put("X_SWIFI_REMARS3", X_SWIFI_REMARS3);
                map.put("LAT", LAT);
                map.put("LNT", LNT);
                map.put("WORK_DTTM", WORK_DTTM);

                resultList.add(map);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (resultSet != null && !resultSet.isClosed()) {
                    resultSet.close();
                }
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
                if (DBconnection != null && !DBconnection.isClosed()) {
                    DBconnection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return resultList;
    }

    public void dbUpdate() {
        String url = "jdbc:mariadb://127.0.0.1:3306/testdb1";
        String dbUserID = "testuser1";
        String dbPassword = "sgcheon";

        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String memberTypeValue = "email";
        String userIdValue = "zerobase@naver.com";
        String passwordValue = "9999";
        String nameValue = "제로베이스";


        try {
            connection = DriverManager.getConnection(url, dbUserID, dbPassword);

            // update 쿼리문
            String sql = "update member set" + "   password = ? " +
                    "where member_type = ? and user_id = ? ";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, passwordValue);    // 쿼리의 ?에 memberTypeValue가 들어감
            preparedStatement.setString(2, memberTypeValue);
            preparedStatement.setString(3, userIdValue);


            int affectedrows = preparedStatement.executeUpdate();

            if (affectedrows > 0) {
                System.out.println("저장 성공");
            } else {
                System.out.println("저장 실패");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            // 6. 객체 연결 해제 (제일 최근에 쓴 순서대로)
            try {
                if (!resultSet.isClosed()) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (!preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (!connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void dbDelete() {
        String url = "jdbc:mariadb://127.0.0.1:3306/testdb1";
        String dbUserID = "testuser1";
        String dbPassword = "sgcheon";

        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String memberTypeValue = "email";
        String userIdValue = "zerobase@naver.com";
        String passwordValue = "9999";
        String nameValue = "제로베이스";


        try {
            connection = DriverManager.getConnection(url, dbUserID, dbPassword);

            // update 쿼리문
            String sql = "delete from member " +
                    "where member_type = ? and user_id = ? ";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, memberTypeValue);    // 쿼리의 ?에 memberTypeValue가 들어감
            preparedStatement.setString(2, userIdValue);

            int affectedrows = preparedStatement.executeUpdate();

            if (affectedrows > 0) {
                System.out.println("삭제 성공");
            } else {
                System.out.println("삭제 실패");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            // 6. 객체 연결 해제 (제일 최근에 쓴 순서대로)
            try {
                if (!resultSet.isClosed()) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (!preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (!connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void dbDeleteAllData(Connection DBconnection) {
        String sql = "delete from wifi_info;";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = DBconnection.prepareStatement(sql);

            int affectedrows = preparedStatement.executeUpdate();

            if (affectedrows > 0) {
                System.out.println("삭제 성공");

            } else {
                System.out.println("삭제 실패");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            // 6. 객체 연결 해제 (제일 최근에 쓴 순서대로)
            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void dbDeleteHistoryData(String id) {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DriverManager.getConnection(url, dbUserID, dbPassword);

            String sql = "delete from history " +
                    "where ID = ? ";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);


            int affectedrows = preparedStatement.executeUpdate();

            if (affectedrows > 0) {
                System.out.println("삭제 성공");
            } else {
                System.out.println("삭제 실패");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            // 6. 객체 연결 해제 (제일 최근에 쓴 순서대로)

            try {
                if (!preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (!connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public void dbConnect() {
        String url = "jdbc:mariadb://127.0.0.1:3306/mission1";
        String dbUserID = "mission1user";
        String dbPassword = "mission1";
        Connection DBconnection = null;

        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            DBconnection = DriverManager.getConnection(url, dbUserID, dbPassword);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
