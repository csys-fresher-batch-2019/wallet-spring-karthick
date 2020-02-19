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

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		long Mobilenumber = Long.parseLong(request.getParameter("mobilenumber"));
		int Pinnumber = Integer.parseInt(request.getParameter("pinnumber"));
		LogindaoImpl obj = new LogindaoImpl();
		String result = "";
		HttpSession ses=request.getSession();
		try {
			result = obj.login(Mobilenumber, Pinnumber);
			if (result.equals("Account logged-in")) {
				ses.setAttribute("mobileNo", Mobilenumber);
				RequestDispatcher dispatcher = request.getRequestDispatcher("UserProfile.jsp");
				dispatcher.forward(request, response);
				
				
			} else {
				request.setAttribute("message", result);
				RequestDispatcher dispatcher = request.getRequestDispatcher("signin.jsp");
				dispatcher.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
