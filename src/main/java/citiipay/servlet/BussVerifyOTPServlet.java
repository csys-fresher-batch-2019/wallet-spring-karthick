package citiipay.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/BussVerifyOTPServlet")
public class BussVerifyOTPServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
		long mobileNo =Long.parseLong(request.getParameter("mobileNo"));
		int otpTyped =Integer.parseInt(request.getParameter("otp"));
		int otpSent =Integer.parseInt(request.getParameter("sentotp"));
		if(otpTyped==otpSent) {
			request.setAttribute("mobileNo", mobileNo);		
			RequestDispatcher dispatcher = request.getRequestDispatcher("bussPinGenerate3.jsp");
			dispatcher.forward(request, response);
			}
		else {
			String result="Verification OTP is incorrect";
			request.setAttribute("errorMessage", result);	
			RequestDispatcher dispatcher = request.getRequestDispatcher("bussVerifyOTP2.jsp");
			dispatcher.forward(request, response);
			
		}
	}

}
