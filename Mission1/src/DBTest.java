import java.sql.*;

public class DBTest {
    public static void main(String[] args) {
        String url = "jdbc:mariadb://127.0.0.1:3306/mission1";
        String dbUserID = "mission1user";
        String dbPassword = "mission1";

        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(url, dbUserID, dbPassword);

            // 4. 쿼리 실행
            String sql = "select * " +
                    " from WIFI_INFO ";

            preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setString(1, memberTypeValue);    // 쿼리의 ?에 memberTypeValue가 들어감

            resultSet = preparedStatement.executeQuery();

            // 5. 결과 수행
            while (resultSet.next()) {
                String X_SWIFI_MGR_NO = resultSet.getString("X_SWIFI_MGR_NO");
                String X_SWIFI_WRDOFC = resultSet.getString("X_SWIFI_WRDOFC");
                String X_SWIFI_MAIN_NM = resultSet.getString("X_SWIFI_MAIN_NM");
                String X_SWIFI_ADRES1 = resultSet.getString("X_SWIFI_ADRES1");

                System.out.println(X_SWIFI_MGR_NO + ", " + X_SWIFI_WRDOFC + ", " + X_SWIFI_MAIN_NM + ", " + X_SWIFI_ADRES1 + ", ");
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
}
