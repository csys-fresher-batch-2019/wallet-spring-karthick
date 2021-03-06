package com.karthi.citiipay.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.karthi.citiipay.dao.impl.TransactiondaoImpl;
import com.karthi.citiipay.exception.DBException;
import com.karthi.citiipay.model.MerchantTableDetails;

@WebServlet("/TransactionMerchant")
public class TransactionMerchant extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		HttpSession ses=request.getSession();
		long senderNo=Long.parseLong(ses.getAttribute("mobileNo").toString());
		TransactiondaoImpl obj=new TransactiondaoImpl();
		ArrayList<MerchantTableDetails> list = new ArrayList<>();
		try {
			list=obj.transactionMerchant(senderNo);
			request.setAttribute("list",list);
			RequestDispatcher dispatcher = request.getRequestDispatcher("transactionMerchant.jsp");
			dispatcher.forward(request, response);
		} catch (DBException e) {
			e.printStackTrace();
		}
	}


}
