package citiipay.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import citiipay.implementation.TransactiondaoImpl;
import citiipay.messages.DBException;
import citiipay.models.CashBack;

@WebServlet("/WalletToWallet")
public class WalletToWallet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		long receiverNo =Long.parseLong(request.getParameter("mobileNo"));
		int transferAmount =Integer.parseInt(request.getParameter("amount"));
		HttpSession ses=request.getSession();
		long senderNo=Long.parseLong(ses.getAttribute("mobileNo").toString());
		System.out.println(senderNo);
		String comments=request.getParameter("comments");
		TransactiondaoImpl obj1=new TransactiondaoImpl();
		String result="";
		try {
			CashBack object=new CashBack();
			object=obj1.walletTransaction(senderNo, receiverNo, transferAmount, comments);			
			request.setAttribute("result",object.getResult());
			request.setAttribute("cashback",object.getCashbackAmount());
			RequestDispatcher dispatcher = request.getRequestDispatcher("success.jsp");
			dispatcher.forward(request, response);
			}
		catch (DBException e) {
			e.printStackTrace();
		}
		
	}
}
