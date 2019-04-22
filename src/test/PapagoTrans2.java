package test;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

public class PapagoTrans2 {

   public static void main(String[] args) {
      String clientId = "bW29mxN17qCeRXGSallE";
      String clientSecret = "3Qdwv7AfGY";
      try {
         Scanner scan = new Scanner(System.in);
         String srcLangCode = sourceLangCode();
         //이건 안해도되자나 ㅇㅅㅇ
         
         System.out.println("번역할 문장을 입력해 주세요 : ");
         String transText = scan.nextLine();
         
         String tarLangCode = targetLangCode();

         String text = URLEncoder.encode(transText, "UTF-8");
         String apiURL = "https://openapi.naver.com/v1/papago/n2mt";
         URL url = new URL(apiURL);
         HttpURLConnection con = (HttpURLConnection) url.openConnection();
         con.setRequestMethod("POST");
         con.setRequestProperty("X-Naver-Client-Id", clientId);
         con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
         // post request

         String postParams = "source=" + srcLangCode + "&target=" + tarLangCode + "&text=" + text;
         con.setDoOutput(true);
         DataOutputStream wr = new DataOutputStream(con.getOutputStream());
         wr.writeBytes(postParams);
         wr.flush();
         wr.close();
         int responseCode = con.getResponseCode();
         BufferedReader br;
         if (responseCode == 200) { // 정상 호출
            br = new BufferedReader(new InputStreamReader(con.getInputStream()));
         } else { // 에러 발생
            br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
         }
         String inputLine;
         StringBuffer response = new StringBuffer();
         while ((inputLine = br.readLine()) != null) {
            response.append(inputLine);
         }
         br.close();
         System.out.println(response.toString());
      } catch (Exception e) {
         System.out.println(e);
      }
   }
   
   public static String sourceLangCode() {
      Scanner scan = new Scanner(System.in);
      System.out.println("번역 전 언어의 국가코드를 입력해주세요 : ");
      System.out.println("1.ko 2.en 3.zh-CN 4.zh-TW, 5.es 6.fr 7.vi 8.th 9.id");
      String sourceCode = scan.nextLine();
      if("1".equals(sourceCode)) {
         sourceCode = "ko";
      }else if("2".equals(sourceCode)) {
         sourceCode = "en";
      }else if("3".equals(sourceCode)) {
         sourceCode = "zh-CN";
      }else if("4".equals(sourceCode)) {
         sourceCode = "zh-TW";
      }else if("5".equals(sourceCode)) {
         sourceCode = "es";
      }else if("6".equals(sourceCode)) {
         sourceCode = "fr";
      }else if("7".equals(sourceCode)) {
         sourceCode = "vi";
      }else if("8".equals(sourceCode)) {
         sourceCode = "th";
      }else if("9".equals(sourceCode)) {
         sourceCode = "id";
      }else {
         System.out.println("없거나 지원하지 않는 국가 코드입니다.");
         sourceCode = sourceLangCode();
      }
      return sourceCode;
      
   }
   
   public static String targetLangCode() {
      Scanner scan = new Scanner(System.in);
      System.out.println("번역 후 언어의 국가코드를 입력해주세요 : ");
      System.out.println("1.ko 2.en 3.zh-CN 4.zh-TW, 5.es 6.fr 7.vi 8.th 9.id");
      String targetCode = scan.nextLine();
      if("1".equals(targetCode)) {
         targetCode = "ko";
      }else if("2".equals(targetCode)) {
         targetCode = "en";
      }else if("3".equals(targetCode)) {
         targetCode = "zh-CN";
      }else if("4".equals(targetCode)) {
         targetCode = "zh-TW";
      }else if("5".equals(targetCode)) {
         targetCode = "es";
      }else if("6".equals(targetCode)) {
         targetCode = "fr";
      }else if("7".equals(targetCode)) {
         targetCode = "vi";
      }else if("8".equals(targetCode)) {
         targetCode = "th";
      }else if("9".equals(targetCode)) {
         targetCode = "id";
      }else {
         System.out.println("없거나 지원하지 않는 국가 코드입니다.");
         targetCode = targetLangCode();
      }
      return targetCode;
      
   }
}