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

@WebServlet("/ChangePin")
public class ChangePin extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession ses=request.getSession();
		long senderNo=Long.parseLong(ses.getAttribute("mobileNo").toString());
		int Pinnumber=Integer.parseInt(request.getParameter("pinnumber"));
		int Confirmationpin=Integer.parseInt(request.getParameter("confirmation"));
		LogindaoImpl obj=new LogindaoImpl();
		String result="";
		if(Pinnumber==Confirmationpin) {
			try {
				result=obj.pinUpdate(senderNo, Pinnumber);
				request.setAttribute("message",result);
				RequestDispatcher dispatcher=request.getRequestDispatcher("changePin.jsp");
				dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	else {
		result="Password mismatch - Account not created";
		request.setAttribute("message",result);
		RequestDispatcher dispatcher=request.getRequestDispatcher("changePin.jsp");
		dispatcher.forward(request, response);
	}
	}


}
