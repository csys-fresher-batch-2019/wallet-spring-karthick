package com.karthi.citiipay.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.karthi.citiipay.dao.impl.LogindaoImpl;
import com.karthi.citiipay.dao.impl.TransactiondaoImpl;
import com.karthi.citiipay.dto.MessageDTO;
import com.karthi.citiipay.dto.RefundDTO;
import com.karthi.citiipay.exception.DBException;
import com.karthi.citiipay.model.Merchant;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api")
public class ProductController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

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

			} else {
				message.setErrorMessage(result);
			}
		} else {
			message.setErrorMessage("Invalid PIN");
		}

		return message;
	}

	@GetMapping("/walletPayment")
	public Merchant walletpayment(@RequestParam("mobilenumber") long MobileNo,
			@RequestParam("merchantId") String merchant, @RequestParam("amount") float amount) throws DBException {
		TransactiondaoImpl obj = new TransactiondaoImpl();
		Merchant obj1 = obj.payToMerchant(merchant, MobileNo, amount);
		System.out.println(obj1);
		return obj1;
	}

	@PostMapping("/refundPayment")
	public Merchant refundpayment(@RequestBody RefundDTO dto) throws DBException {
		TransactiondaoImpl obj = new TransactiondaoImpl();
		String merchantId = dto.getMerchantId();
		int transactionId = dto.getTransactionId();
		float amount = dto.getAmount();
		Merchant obj1 = obj.refundToCustomer(merchantId, transactionId, amount);
		return obj1;
	}

	// @RequestParam("transactionId") int transactonid, @RequestParam("merchantId")
	// String merchant,
	// @RequestParam("amount") float amount

}
