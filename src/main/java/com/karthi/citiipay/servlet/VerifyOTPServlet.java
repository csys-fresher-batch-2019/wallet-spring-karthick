package com.karthi.citiipay.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/VerifyOTPServlet")
public class VerifyOTPServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	response.getWriter().append("Served at: ").append(request.getContextPath());
	long mobileNo =Long.parseLong(request.getParameter("mobileNo"));
	int otpTyped =Integer.parseInt(request.getParameter("otp"));
	int otpSent =Integer.parseInt(request.getParameter("sentotp"));
	if(otpTyped==otpSent) {
		request.setAttribute("mobileNo", mobileNo);		
		RequestDispatcher dispatcher = request.getRequestDispatcher("persPinGenerate3.jsp");
		dispatcher.forward(request, response);
		}
	else {
		String result="Verification OTP is incorrect";
		request.setAttribute("errorMessage", result);	
		RequestDispatcher dispatcher = request.getRequestDispatcher("persVerifyOTP2.jsp");
		dispatcher.forward(request, response);
		
	}
	
	}
}


