package com.karthi.citiipay.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.karthi.citiipay.dao.impl.TransactiondaoImpl;
import com.karthi.citiipay.exception.DBException;

@WebServlet("/TopupServlet")
public class TopupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		long Accountnumber = Long.parseLong(request.getParameter("accountnumber"));
		HttpSession ses=request.getSession();
		long senderNo=Long.parseLong(ses.getAttribute("mobileNo").toString());
		int TransferAmount = Integer.parseInt(request.getParameter("amount"));
		String comments=request.getParameter("comments");
		TransactiondaoImpl obj1=new TransactiondaoImpl();
		try {
			String result=obj1.accountToWallet(senderNo, Accountnumber, TransferAmount, comments);
			request.setAttribute("result",result);
			RequestDispatcher dispatcher = request.getRequestDispatcher("TopupWallet.jsp");
			dispatcher.forward(request, response);
		} catch (DBException e) {
			e.printStackTrace();
		}
	}

}
