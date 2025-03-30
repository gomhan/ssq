package lottery.download;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SSQCrawler {
    public static String sendGetRequest(String targetUrl) throws Exception {
        StringBuilder response = new StringBuilder();
        HttpURLConnection connection = null;
        try {
            URL url = new URL(targetUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream(), "UTF-8"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
            }
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return response.toString();
    }
    public static void parseHtmlContent(String htmlContent) {
        Document doc = Jsoup.parse(htmlContent);
        Element cpdata = doc.getElementById("cpdata");
        Elements rows = cpdata.select("tr");
        for (int i = 0; i < rows.size(); i++) {
        	Element row = rows.get(i);
        	int j = 0;
        	String issue = row.child(j++).text();
        	Elements tds = row.children();
        	List<String> ball = new ArrayList<>();
        	for (j = 1; j < tds.size(); j++) {
        		Element td = tds.get(j);
        		if (td.className().startsWith("chartball")) {
        			ball.add(td.text());
        		}
        		if (ball.size() == 7) {
        			break;
        		}
        	}
        	String blue = ball.remove(6);
        	System.out.println(issue+"ÆÚ | "+ String.join(" ", ball)+" | "+blue);
        }
    }
    public static void main(String[] args) {
        String targetUrl = "https://view.lottery.sina.com.cn/lotto/pc_zst/index?lottoType=ssq&actionType=chzs&sqi=2003001&eqi=2003089&dpc=1";
        try {
            String htmlContent = sendGetRequest(targetUrl);
            parseHtmlContent(htmlContent);
        } catch (Exception e) {
            System.err.println("Failed: " + e.getMessage());
        }
    }
}
