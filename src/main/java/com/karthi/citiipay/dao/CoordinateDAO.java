package com.karthi.citiipay.dao;

import com.karthi.citiipay.exception.DBException;
import com.karthi.citiipay.model.Coordinates;

public interface CoordinateDAO {
	
	public Coordinates checkStatus(int spped, float lattitude, float longitude) throws DBException;

}
