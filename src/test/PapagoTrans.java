package test;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

public class PapagoTrans {

	public static String targetLangCode() {
		Scanner scan = new Scanner(System.in);
		System.out.println("번역 될 국가 언어의 번호(숫자)를 입력해주세요 : ");
		System.out.println("1.한국어 2.영어 3.중국어 간체 4.중국어 번체 5.스페인어 6.프랑스어 7.러시아어 8.베트남어 9.태국어 10.인도네시아어");
		String targetCode = scan.nextLine();
		if ("1".equals(targetCode)) {
			targetCode = "ko";
		} else if ("2".equals(targetCode)) {
			targetCode = "en";
		} else if ("3".equals(targetCode)) {
			targetCode = "zh-CN";
		} else if ("4".equals(targetCode)) {
			targetCode = "zh-TW";
		} else if ("5".equals(targetCode)) {
			targetCode = "es";
		} else if ("6".equals(targetCode)) {
			targetCode = "fr";
		} else if ("7".equals(targetCode)) {
			targetCode = "ru";
		} else if ("8".equals(targetCode)) {
			targetCode = "vi";
		} else if ("9".equals(targetCode)) {
			targetCode = "th";
		} else if ("10".equals(targetCode)) {
			targetCode = "id";
		} else {
			System.out.println("없거나 지원하지 않는 국가 코드입니다.");
			targetCode = targetLangCode();
		}
		return targetCode;
	}

	public static void main(String[] args) {
		String clientId = "RKMtE3jrPVvv5gSRHZiv";
		String clientSecret = "c8Y2Z65ugb";
		try {
			Scanner scan = new Scanner(System.in);
			System.out.println("번역할 내용을 영어로 입력해주세요 : ");
			String trans = scan.nextLine();
			String targetCode = targetLangCode();

			String text = URLEncoder.encode(trans, "UTF-8");
			String apiURL = "https://openapi.naver.com/v1/papago/n2mt";
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("X-Naver-Client-Id", clientId);
			con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
			// post request
			String postParams = "source=en&target=" + targetCode + "&text=" + text;
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(postParams);
			wr.flush();
			wr.close();
			int responseCode = con.getResponseCode();
			BufferedReader br;
			if (responseCode == 200) {
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else {
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
}
