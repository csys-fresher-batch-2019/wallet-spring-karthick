package citiipay.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import citiipay.dto.MessageDTO;
import citiipay.implementation.LogindaoImpl;
import citiipay.implementation.TransactiondaoImpl;
import citiipay.messages.DBException;
import citiipay.models.Merchant;

@CrossOrigin
@RestController
@RequestMapping("api")
public class ProductController {

	@GetMapping("/personalRegister")
	public MessageDTO personalRegister(@RequestParam("mobilenumber") long MobileNo,
			@RequestParam("pinnumber") int Pinnumber, @RequestParam("confirmation") int Confirmationpin)
			throws DBException {
		LogindaoImpl obj = new LogindaoImpl();
		String result = "";
		MessageDTO message = new MessageDTO();
		if (Pinnumber == Confirmationpin) {
			result = obj.personalRegister(MobileNo, Pinnumber);
			if (result.equals("Account created")) {
				
			}
			else {
				message.setErrorMessage(result);
			}
		} else {
			message.setErrorMessage("Invalid PIN");
		}

		return message;
	}

	@GetMapping("/walletPayment")
	public Merchant walletpayment(@RequestParam("mobilenumber") long MobileNo, @RequestParam("merchantId") String merchant,
			@RequestParam("amount") float amount) throws DBException {
		TransactiondaoImpl obj = new TransactiondaoImpl();
		Merchant obj1 = new Merchant();
		obj1=obj.payToMerchant(merchant, MobileNo, amount);
		return obj1;
	}
	
	@GetMapping("/refundPayment")
	public String refundpayment(@RequestParam("transactionId") int transactonid, @RequestParam("merchantId") String merchant,
			@RequestParam("amount") float amount) throws DBException {
		TransactiondaoImpl obj = new TransactiondaoImpl();
		String result=obj.refundToCustomer(merchant, transactonid, amount);
		return result;
	}
	
	

}
