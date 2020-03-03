package com.karthi.citiipay.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.karthi.citiipay.dao.impl.LogindaoImpl;
import com.karthi.citiipay.dao.impl.SmsSend;
import com.karthi.citiipay.exception.DBException;

@WebServlet("/BussOtpVerification")
public class BussOtpVerification extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		long Mobilenumber = Long.parseLong(request.getParameter("mobilenumber"));		
		LogindaoImpl obj=new LogindaoImpl();
		int results;
		try {
			results = obj.mobBussinessVerification(Mobilenumber);
			
			if(results==1) {
				int otpSent=SmsSend.msgForegetPassword(Mobilenumber);
				request.setAttribute("mobileNo", Mobilenumber);
				request.setAttribute("otp", otpSent);
				RequestDispatcher dispatcher = request.getRequestDispatcher("bussVerifyOTP2.jsp");
				dispatcher.forward(request, response);
				}
				else {
					String result="Account already registered";
					request.setAttribute("errorMessage", result);
					RequestDispatcher dispatcher1 = request.getRequestDispatcher("bussSignUp1.jsp");
					dispatcher1.forward(request, response);
				}
			} 
		catch (DBException e) {
			e.printStackTrace();
		}
		
		
	}
}
