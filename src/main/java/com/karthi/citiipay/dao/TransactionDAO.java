package com.karthi.citiipay.dao;

import java.util.ArrayList;

import com.karthi.citiipay.exception.DBException;
import com.karthi.citiipay.model.CashBack;
import com.karthi.citiipay.model.Merchant;
import com.karthi.citiipay.model.MerchantTableDetails;
import com.karthi.citiipay.model.TransactionDetails;

public interface TransactionDAO {

	public CashBack walletTransaction(long senderMobileNo, long receiverMobileNo, float transferAmount, String comments)
			throws DBException;

	public String accountToWallet(long mobileNo, long accountNo, float amount, String comments) throws DBException;

	public Merchant payToMerchant(String merchantId, long mobileNo, float amount) throws DBException;

	public Merchant refundToCustomer(String merchantId, int transactionId, float amount) throws DBException;
	
	public ArrayList<TransactionDetails> transactionDebited(long mobileNumber)
			throws DBException;
	
	public ArrayList<TransactionDetails> transactionCredited(long mobileNumber)
			throws DBException;
	
	public ArrayList<MerchantTableDetails> transactionMerchant(long mobileNumber)
			throws DBException;
	
	public void cashbackTableEntry(long mobileNumber,int cashabck) throws DBException;
}
