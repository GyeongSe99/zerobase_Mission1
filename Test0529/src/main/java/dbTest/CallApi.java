package dbTest;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;

public class CallApi {

    public int getTotal(){
        int totalCount = -1;
        try {

            StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088");
            urlBuilder.append("/" + URLEncoder.encode("537a5164546a61793835545054716c","UTF-8") );
            urlBuilder.append("/" + URLEncoder.encode("json","UTF-8") );
            urlBuilder.append("/" + URLEncoder.encode("TbPublicWifiInfo","UTF-8"));
            urlBuilder.append("/" + URLEncoder.encode("1","UTF-8"));
            urlBuilder.append("/" + URLEncoder.encode("1","UTF-8"));

            String urlStr = urlBuilder.toString();

            // URL 연결 설정
            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader bufferedReader;

            // 서비스코드가 정상이면 200~300사이의 숫자가 나옵니다.
            if(connection.getResponseCode() >= 200 && connection.getResponseCode() <= 300) {
                bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            } else {
                bufferedReader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            }

            // API 응답 읽기
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            bufferedReader.close();
            connection.disconnect();

            // JSON 데이터 파싱
            Gson gson = new Gson();
            ResponseData responseData = gson.fromJson(sb.toString(), ResponseData.class);

            // 파싱 결과 출력
            TbPublicWifiInfo wifiInfo = responseData.getTbPublicWifiInfo();
            totalCount = wifiInfo.getListTotalCount();
            System.out.println("Total Count: " + totalCount);

        } catch (Exception e) {
            return -1;
        }

        return totalCount;
    }

    public void addWifiData(int total){
        int maxNum = 1000;
        int loopCount = total % maxNum != 0 ? total / maxNum + 1 : total / maxNum;
        int cnt = 0;

        while (cnt < loopCount) {
            int start = cnt * maxNum + 1;
            cnt += 1;
            int end = Math.min(cnt * maxNum, total);
            System.out.println(start);
            System.out.println(end);
            addPart(start, end);
        }
    }

    public void addPart(int start, int end){
        int total = end - start + 1;
        try {
            StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088");
            urlBuilder.append("/" + URLEncoder.encode("537a5164546a61793835545054716c","UTF-8") );
            urlBuilder.append("/" + URLEncoder.encode("json","UTF-8") );
            urlBuilder.append("/" + URLEncoder.encode("TbPublicWifiInfo","UTF-8"));
            urlBuilder.append("/" + URLEncoder.encode(Integer.toString(start),"UTF-8"));
            urlBuilder.append("/" + URLEncoder.encode(Integer.toString(end),"UTF-8"));

            String urlStr = urlBuilder.toString();

            // URL 연결 설정
            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader bufferedReader;

            // 서비스코드가 정상이면 200~300사이의 숫자가 나옵니다.
            if(connection.getResponseCode() >= 200 && connection.getResponseCode() <= 300) {
                bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            } else {
                bufferedReader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            }

            // API 응답 읽기
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            bufferedReader.close();
            connection.disconnect();


            // JSON 데이터 파싱
            Gson gson = new Gson();
            ResponseData responseData = gson.fromJson(sb.toString(), ResponseData.class);
            System.out.println("Hi");


            String sUrl = "jdbc:mariadb://127.0.0.1:3306/mission1";
            String dbUserID = "mission1user";
            String dbPassword = "mission1";
            Connection DBconnection = null;

            try {
                Class.forName("org.mariadb.jdbc.Driver");

                DBconnection = DriverManager.getConnection(sUrl, dbUserID, dbPassword);

                DbProcess dbProcess = new DbProcess();
                for (Row row : responseData.getTbPublicWifiInfo().getRows()) {
                    dbProcess.dbInsert(DBconnection, row);
                }

            } catch (Exception e) {
                System.out.println("db connection error-----" + e.getMessage());
            } finally {
                if (DBconnection != null && !DBconnection.isClosed()) {
                    DBconnection.close();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
