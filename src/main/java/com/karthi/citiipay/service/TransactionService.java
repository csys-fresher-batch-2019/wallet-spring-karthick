package com.karthi.citiipay.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.karthi.citiipay.dao.impl.TransactiondaoImpl;
import com.karthi.citiipay.exception.DBException;
import com.karthi.citiipay.model.CashBack;
import com.karthi.citiipay.model.Merchant;
import com.karthi.citiipay.model.MerchantTableDetails;
import com.karthi.citiipay.model.TransactionDetails;

@Service
public class TransactionService {

	private TransactiondaoImpl transaction = new TransactiondaoImpl();

	public CashBack walletTransaction(long senderMobileNo, long receiverMobileNo, float transferAmount, String comments)
			throws DBException {
		return transaction.walletTransaction(senderMobileNo, receiverMobileNo, transferAmount, comments);
	}

	public String accountToWallet(long mobileNo, long accountNo, float amount, String comments) throws DBException {
		return transaction.accountToWallet(mobileNo, accountNo, amount, comments);
	}

	public Merchant payToMerchant(String merchantId, long mobileNo, float amount) throws DBException {
		return transaction.payToMerchant(merchantId, mobileNo, amount);
	}

	public Merchant refundToCustomer(String merchantId, int transactionId, float amount) throws DBException {
		return transaction.refundToCustomer(merchantId, transactionId, amount);
	}

	public ArrayList<TransactionDetails> transactionDebited(long mobileNumber) throws DBException {
		return transaction.transactionDebited(mobileNumber);
	}

	public ArrayList<TransactionDetails> transactionCredited(long mobileNumber) throws DBException {
		return transaction.transactionCredited(mobileNumber);
	}

	public ArrayList<MerchantTableDetails> transactionMerchant(long mobileNumber) throws DBException {
		return transaction.transactionMerchant(mobileNumber);
	}

	public void cashbackTableEntry(long mobileNumber, int cashabck) throws DBException {
		return;
	}

}
