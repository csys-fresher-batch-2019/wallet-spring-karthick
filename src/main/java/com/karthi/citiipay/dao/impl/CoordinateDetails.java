package com.karthi.citiipay.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.karthi.citiipay.dao.CoordinateDAO;
import com.karthi.citiipay.exception.DBException;
import com.karthi.citiipay.exception.ErrorMessages;
import com.karthi.citiipay.model.Coordinates;

@Repository
public class CoordinateDetails implements CoordinateDAO{
	
	@Autowired
	private DataSource dataSource;
	
	public Coordinates checkStatus(int speed, float lattitude, float longitude) throws DBException {
		
		try (Connection conn = dataSource.getConnection();
				CallableStatement Stmt = conn.prepareCall("{call geo_location(?,?,?,?)}");) {
			Stmt.setFloat(1, lattitude);
			Stmt.setFloat(2, longitude);
			Stmt.setInt(3, speed);
			Stmt.registerOutParameter(4, Types.VARCHAR);
			Stmt.executeUpdate();
			Coordinates object=new Coordinates();
			String status = Stmt.getString(4);
			object.setStatus(status);
			return object;
		
	} catch (SQLException e) {
		e.printStackTrace();
		throw new DBException(ErrorMessages.CONNECTION_FAILURE);
	}

 }
	
}
