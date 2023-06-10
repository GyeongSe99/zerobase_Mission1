package bookMark;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class BookMarkDB {
    String url = "jdbc:mariadb://127.0.0.1:3306/mission1";
    String dbUserID = "mission1user";
    String dbPassword = "mission1";

    public void bookMarkNameAdd(String bookMarkName, String num) {

        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection DBconnection = null;
        PreparedStatement preparedStatement = null;

        try {
            DBconnection = DriverManager.getConnection(url, dbUserID, dbPassword);

            // 4. 쿼리 실행
            String sql = "INSERT INTO bookmark (bookmarkname, num, registerdate) values (?, ?, now());";

            preparedStatement = DBconnection.prepareStatement(sql);
            preparedStatement.setString(1, bookMarkName);
            preparedStatement.setString(2, num);

            int affectedrows = preparedStatement.executeUpdate();

            if (affectedrows > 0) {
                System.out.println("저장 성공");

            } else {
                System.out.println("저장 실패");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
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

    // edit북마크페이지에서 북마크 리스트 보여주기
    public ArrayList<HashMap<String, String>> showBookMarkList() {
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

            String sql ="SELECT * FROM bookMark ORDER BY NUM;";

            preparedStatement = DBconnection.prepareStatement(sql);

            resultSet = preparedStatement.executeQuery();

            HashMap<String, String> map = null;

            while (resultSet.next()) {
                map = new HashMap<String, String>();
                String ID = resultSet.getString("ID");
                String NUM = resultSet.getString("NUM");
                String BOOKMARKNAME = resultSet.getString("BOOKMARKNAME");
                String WIFINAME = resultSet.getString("WIFINAME");
                String REGISTERDATE = resultSet.getString("REGISTERDATE");
                String CHANGEDATE = resultSet.getString("CHANGEDATE");
                String wifi_ID = resultSet.getString("wifi_ID");

                map.put("ID", ID);
                map.put("NUM", NUM);
                map.put("BOOKMARKNAME", BOOKMARKNAME);
                map.put("WIFINAME", WIFINAME);
                map.put("REGISTERDATE", REGISTERDATE);
                map.put("CHANGEDATE", CHANGEDATE);
                map.put("wifi_ID", wifi_ID);

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

    // wifi이름이 설정된 북마크리스트 보여주기
    public ArrayList<HashMap<String, String>> showWifiBookMarkList() {
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

            String sql ="SELECT * FROM bookMark WHERE WIFINAME IS NOT NULL AND WIFINAME <> '' ORDER BY NUM;";

            preparedStatement = DBconnection.prepareStatement(sql);

            resultSet = preparedStatement.executeQuery();

            HashMap<String, String> map = null;

            while (resultSet.next()) {
                map = new HashMap<String, String>();
                String ID = resultSet.getString("ID");
                String NUM = resultSet.getString("NUM");
                String BOOKMARKNAME = resultSet.getString("BOOKMARKNAME");
                String WIFINAME = resultSet.getString("WIFINAME");
                String REGISTERDATE = resultSet.getString("REGISTERDATE");
                String CHANGEDATE = resultSet.getString("CHANGEDATE");
                String wifi_ID = resultSet.getString("wifi_ID");
                String wifi_register_date = resultSet.getString("wifi_register_date");
                String mylat = resultSet.getString("mylat");
                String mylnt = resultSet.getString("mylnt");

                map.put("ID", ID);
                map.put("NUM", NUM);
                map.put("BOOKMARKNAME", BOOKMARKNAME);
                map.put("WIFINAME", WIFINAME);
                map.put("REGISTERDATE", REGISTERDATE);
                map.put("CHANGEDATE", CHANGEDATE);
                map.put("wifi_ID", wifi_ID);
                map.put("wifi_register_date", wifi_register_date);
                map.put("mylat", mylat);
                map.put("mylnt", mylnt);

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

    // detail페이지에서 북마크리스트 옵션에 띄우는 용도
    public ArrayList<HashMap<String, String>> showBookMarkInDetail() {
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

            // wifiname이 빈칸인 경우만
            String sql ="SELECT * FROM bookMark WHERE WIFINAME IS NULL OR WIFINAME = '' ORDER BY NUM;";

            preparedStatement = DBconnection.prepareStatement(sql);

            resultSet = preparedStatement.executeQuery();

            HashMap<String, String> map = null;

            while (resultSet.next()) {
                map = new HashMap<String, String>();
                String ID = resultSet.getString("ID");
                String NUM = resultSet.getString("NUM");
                String BOOKMARKNAME = resultSet.getString("BOOKMARKNAME");
                String WIFINAME = resultSet.getString("WIFINAME");
                String REGISTERDATE = resultSet.getString("REGISTERDATE");
                String CHANGEDATE = resultSet.getString("CHANGEDATE");
                String wifi_ID = resultSet.getString("wifi_ID");
                String wifi_register_date = resultSet.getString("wifi_register_date");

                map.put("ID", ID);
                map.put("NUM", NUM);
                map.put("BOOKMARKNAME", BOOKMARKNAME);
                map.put("WIFINAME", WIFINAME);
                map.put("REGISTERDATE", REGISTERDATE);
                map.put("CHANGEDATE", CHANGEDATE);
                map.put("wifi_ID", wifi_ID);
                map.put("wifi_register_date", wifi_register_date);

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
    public ArrayList<HashMap<String, String>> showDeletePage(String bookmarkID) {
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

            // wifiname이 빈칸인 경우만
            String sql ="SELECT * FROM bookMark WHERE ID = ?;";

            preparedStatement = DBconnection.prepareStatement(sql);
            preparedStatement.setString(1, bookmarkID);

            resultSet = preparedStatement.executeQuery();

            HashMap<String, String> map = null;

            while (resultSet.next()) {
                map = new HashMap<String, String>();
                String ID = resultSet.getString("ID");
                String NUM = resultSet.getString("NUM");
                String BOOKMARKNAME = resultSet.getString("BOOKMARKNAME");
                String WIFINAME = resultSet.getString("WIFINAME");
                String REGISTERDATE = resultSet.getString("REGISTERDATE");
                String CHANGEDATE = resultSet.getString("CHANGEDATE");
                String wifi_ID = resultSet.getString("wifi_ID");
                String wifi_register_date = resultSet.getString("wifi_register_date");

                map.put("ID", ID);
                map.put("NUM", NUM);
                map.put("BOOKMARKNAME", BOOKMARKNAME);
                map.put("WIFINAME", WIFINAME);
                map.put("REGISTERDATE", REGISTERDATE);
                map.put("CHANGEDATE", CHANGEDATE);
                map.put("wifi_ID", wifi_ID);
                map.put("wifi_register_date", wifi_register_date);

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

    public void updateBookMark(String bookmarkName, String num, String ID) {
        int affectedRows = -100;
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DriverManager.getConnection(url, dbUserID, dbPassword);

            // update 쿼리문
            String sql = "update bookmark set " + "BOOKMARKNAME = ?, NUM = ?, CHANGEDATE = now() " +
                    "where ID = ?";

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, bookmarkName);
            preparedStatement.setString(2, num);
            preparedStatement.setString(3, ID);

            affectedRows = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

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

        if (affectedRows > 0) {
            System.out.println("업데이트 성공");
        } else {
            System.out.println("업데이트 실패");
        }
    }

    public void updateWifiInBookMark(String bookmarkID, String wifiID, String WIFINAME, String LAT, String LNT) {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DriverManager.getConnection(url, dbUserID, dbPassword);

            String sql = "update bookmark set " + "WIFINAME = ?, wifi_ID = ?, wifi_register_date = now(), mylat = ?, mylnt = ? " +
                    "where ID = ?";

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, WIFINAME);
            preparedStatement.setString(2, wifiID);
            preparedStatement.setString(3, LAT);
            preparedStatement.setString(4, LNT);
            preparedStatement.setString(5, bookmarkID);

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("와이파이정보 저장 성공");
            } else {
                System.out.println("와이파이정보 저장 실패");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

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

    public void deleteBookMark(String ID) {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DriverManager.getConnection(url, dbUserID, dbPassword);

            // update 쿼리문
            String sql = "delete from bookmark where ID = ?";

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, ID);

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("삭제 성공");
            } else {
                System.out.println("삭제 실패");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

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

}
