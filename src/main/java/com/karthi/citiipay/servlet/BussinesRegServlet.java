package com.karthi.citiipay.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.karthi.citiipay.dao.impl.LogindaoImpl;

@WebServlet("/BussinesRegServlet")
public class BussinesRegServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.getWriter().append("Served at: ").append(request.getContextPath());
		long Mobilenumber =Long.parseLong(request.getParameter("mobilenumber"));
		String Merchantid=(String)request.getParameter("merchantid");
		long Accountno=Long.parseLong(request.getParameter("accountno"));
		LogindaoImpl obj=new LogindaoImpl();
		String result="";
		try {
			result=obj.bussinessRegister(Mobilenumber, Merchantid, Accountno);
			request.setAttribute("message",result);
			RequestDispatcher dispatcher=request.getRequestDispatcher("signin.jsp");
			dispatcher.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

}
