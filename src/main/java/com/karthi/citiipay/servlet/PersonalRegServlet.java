package com.karthi.citiipay.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.karthi.citiipay.dao.impl.LogindaoImpl;


@WebServlet("/PersonalRegServlet")
public class PersonalRegServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		long Mobilenumber =Long.parseLong(request.getParameter("mobilenumber"));
		int Pinnumber=Integer.parseInt(request.getParameter("pinnumber"));
		int Confirmationpin=Integer.parseInt(request.getParameter("confirmation"));
		LogindaoImpl obj=new LogindaoImpl();
		String result="";
		if(Pinnumber==Confirmationpin) {
			try {
				result=obj.personalRegister(Mobilenumber,Pinnumber);
					request.setAttribute("message",result);
					RequestDispatcher dispatcher=request.getRequestDispatcher("signin.jsp");
					dispatcher.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else {
			String result1="Password mismatch - Account not created";
			request.setAttribute("errorMessage",result1);
			RequestDispatcher dispatcher=request.getRequestDispatcher("persSignUp1.jsp");
			dispatcher.forward(request, response);
		}
	}


}
