package com.karthi.citiipay.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.karthi.citiipay.dao.impl.LogindaoImpl;
import com.karthi.citiipay.exception.DBException;

@WebServlet("/ReceiverMobVerification")
public class ReceiverMobVerification extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		long Mobilenumber = Long.parseLong(request.getParameter("mobilenumber"));
		LogindaoImpl obj=new LogindaoImpl();
		int results;
		HttpSession ses=request.getSession();
		long senderNo=Long.parseLong(ses.getAttribute("mobileNo").toString());
		if(Mobilenumber!=senderNo) {
			try {
				results = obj.mobPersonalVerification(Mobilenumber);
				if(results==0) {
					RequestDispatcher dispatcher = request.getRequestDispatcher("NewPayment1.jsp?ReceivermobileNo="+Mobilenumber);
					dispatcher.forward(request, response);
					}
					else {
						String result="Mobile number doesnot exist";
						request.setAttribute("errorMessage", result);
						RequestDispatcher dispatcher1 = request.getRequestDispatcher("Newpayment.jsp");
						dispatcher1.forward(request, response);
					}
				} 
			catch (DBException e) {
				e.printStackTrace();
			}
		}else {
			String result="Sender and Receiver cannot be the same";
			request.setAttribute("errorMessage", result);
			RequestDispatcher dispatcher1 = request.getRequestDispatcher("Newpayment.jsp");
			dispatcher1.forward(request, response);
		}
		
	}


}
