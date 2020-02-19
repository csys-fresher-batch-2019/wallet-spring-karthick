package citiipay.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import citiipay.implementation.LogindaoImpl;
import citiipay.implementation.TransactiondaoImpl;
import citiipay.models.Merchant;




@WebServlet("/PaymentServlet")
public class PaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.getWriter().append("Served at: <br/>").append(request.getContextPath());
		
		String MerchantId =request.getParameter("merchantid");
		long Mobilenumber =Long.parseLong(request.getParameter("mobileno"));
		int Amount=Integer.parseInt(request.getParameter("amount"));
		int Pin=Integer.parseInt(request.getParameter("pinnumber"));
		LogindaoImpl obj=new LogindaoImpl();
		TransactiondaoImpl obj1=new TransactiondaoImpl();
		Merchant obj2=new Merchant();
		try {
			String result=obj.login(Mobilenumber, Pin);
			if(result.equals("Account logged-in")) {
				obj2=obj1.payToMerchant(MerchantId, Mobilenumber, Amount);
				String url=request.getParameter("redirect_url");
				System.out.println(url);
				String param = "?status="+obj2.getStatus()+"&id="+obj2.getTransactionId();
				response.sendRedirect(url+param);
			}
			else {
				String url=request.getParameter("redirect_url");
				System.out.println(url);
				String param = "?message="+result;
				response.sendRedirect(url+param);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}


}
