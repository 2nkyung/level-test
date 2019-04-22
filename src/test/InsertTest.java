package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.DBConnector;

public class InsertTest {

	private static String insertSql = "insert into test(name,age) values(?,?)";

	private static int insertTest(List<Map<String, Object>> dataList) {
		int cnt = 0;
		try (Connection con = DBConnector.getCon(); PreparedStatement ps = con.prepareStatement(insertSql);) {
			for (int i = 0; i < dataList.size(); i++) {
				Map<String, Object> user = dataList.get(i);
				System.out.println(user);
				ps.setObject(1, user.get("name"));
				ps.setObject(2, user.get("age"));
				ps.addBatch();
			}
			int[] resultCnts = ps.executeBatch();
			for (int resultCnt : resultCnts) {
				if (resultCnt == -2) {
					cnt++;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	public static void main(String[] args) {

		int cnt = 0;
		int totalCnt = 0;
		Long sTime = System.currentTimeMillis();
		List<Map<String, Object>> dataList = new ArrayList<>();
		for (int i = 1; i <= 100000; i++) {
			Map<String, Object> hm = new HashMap<>();
			hm.put("name", "이름" + i);
			int age = (int) (Math.random() * 100) + 1;
			hm.put("age", age);
			dataList.add(hm);
			if (dataList.size() == 10000) {
				Long subTime = System.currentTimeMillis();
				int insertResult = insertTest(dataList);
				cnt++;
				totalCnt += dataList.size();
				dataList.clear();
				System.out.println(cnt + "번째 반영된 건수:" + insertResult);
				System.out.println(cnt + "번째 완료 시간:" + (System.currentTimeMillis() - subTime));
				System.out.println("총 반영 건수: " + totalCnt);
			}
		}
		if (dataList.size() > 0) {
			Long subTime  = System.currentTimeMillis();
			int insertResult = insertTest(dataList);
			cnt++;
			totalCnt += dataList.size();
			dataList.clear();
			System.out.println(cnt + "번째 반영된 건수:" + insertResult);
			System.out.println(cnt + "번째 완료 시간:" + (System.currentTimeMillis() - subTime));
			System.out.println("총 반영 건수: " + totalCnt);
		}
		System.out.println("전체 수행 시간 :" + (System.currentTimeMillis() - sTime));
	}

}
