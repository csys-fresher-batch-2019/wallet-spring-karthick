package com.karthi.citiipay.util;

import java.sql.Connection;
import java.sql.DriverManager;

import org.springframework.stereotype.Component;
@Component
public class Connect {

	/* public static Connection connect() throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String server = "13.235.147.120";// "localhost";System.getenv("host");
		TimeZone timeZone = TimeZone.getTimeZone("Asia/Kolkata");
			TimeZone.setDefault(timeZone);
		String username = "karthick";// System.getenv("db_username");
		String password = "karthick";//System.getenv("db_password");
		
		System.out.println(server + "- " + username + "-" + password);
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@" + server + ":1521:XE", username,
				password);
		return connection;
	}
	*/
	
	public static Connection connect() throws Exception
	{
		Class.forName("oracle.jdbc.driver.OracleDriver");

		String server = "CSLH2018";
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@"+server+":1521:XE", "system", "oracle");
		return connection;
	}
	
}
