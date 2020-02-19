package citiipay.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import citiipay.implementation.LogindaoImpl;
import citiipay.messages.DBException;

@WebServlet("/CheckBalance")
public class CheckBalance extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		HttpSession ses=request.getSession();
		long senderNo=Long.parseLong(ses.getAttribute("mobileNo").toString());
		LogindaoImpl obj=new LogindaoImpl();
		try {
			int balance=obj.balanceCheck(senderNo);
			System.out.println(balance);
			RequestDispatcher dispatcher1 = request.getRequestDispatcher("Balance.jsp?balance="+balance);
			dispatcher1.forward(request, response);
		} catch (DBException e) {
			e.printStackTrace();
		}
		
		
		
	}

}
