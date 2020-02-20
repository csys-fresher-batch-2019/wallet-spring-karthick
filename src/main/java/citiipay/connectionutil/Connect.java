package citiipay.connectionutil;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connect {

	public static Connection connect() throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String server = "13.235.147.120";// "localhost";
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@" + server + ":1521:XE", "system",
				"oracle");
		return connection;
	}
}
