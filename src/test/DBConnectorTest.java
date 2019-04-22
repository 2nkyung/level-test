package test;

import static org.junit.Assert.assertNotEquals;

import java.sql.Connection;

import org.junit.Test;

import com.DBConnector;

public class DBConnectorTest {

	@Test
	public void getConnectionTest() {
		Connection con = DBConnector.getCon();
		assertNotEquals(null, con);
	}//Connection이 잘 리턴 되는가.
}
