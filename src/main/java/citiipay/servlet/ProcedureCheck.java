package citiipay.servlet;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Types;

public class ProcedureCheck{
	
		public static Connection getConection() throws Exception
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "oracle");
	//System.out.println(connection);
	return connection;
			
		}
		
		
		public String login(long mobileNumber,int pinNumber) {
		Connection connection;
		String result = null;
		try {
			connection=getConection();
		
    CallableStatement cStmt = connection.prepareCall("{call login_procedure(?,?,?)}");
   
    cStmt.setLong(1,mobileNumber);
    cStmt.setInt(2,pinNumber);
    cStmt.registerOutParameter(3, Types.VARCHAR);
    cStmt.executeUpdate();
    result = cStmt.getString(3);
	  } 
		
		catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		return result;
	}
	
}
