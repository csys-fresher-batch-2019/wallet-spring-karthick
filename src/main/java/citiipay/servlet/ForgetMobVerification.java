package citiipay.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import citiipay.implementation.LogindaoImpl;
import citiipay.implementation.SmsSend;
import citiipay.messages.DBException;

@WebServlet("/ForgetMobVerification")
public class ForgetMobVerification extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		long Mobilenumber = Long.parseLong(request.getParameter("mobilenumber"));		
		LogindaoImpl obj=new LogindaoImpl();
		int results;
		try {
			results = obj.mobPersonalVerification(Mobilenumber);
			if(results==0) {
				int otpSent=SmsSend.msgForegetPassword(Mobilenumber);
				request.setAttribute("mobileNo", Mobilenumber);
				request.setAttribute("otp", otpSent);
				RequestDispatcher dispatcher = request.getRequestDispatcher("forgetPassword1.jsp");
				dispatcher.forward(request, response);
				}
				else {
					String result="Account not registered";
					request.setAttribute("errorMessage", result);
					RequestDispatcher dispatcher1 = request.getRequestDispatcher("forgetPassword.jsp");
					dispatcher1.forward(request, response);
				}
			} 
		catch (DBException e) {
			e.printStackTrace();
		}
		
		}
	}
