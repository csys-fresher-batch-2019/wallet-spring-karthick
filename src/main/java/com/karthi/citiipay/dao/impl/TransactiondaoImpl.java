package com.karthi.citiipay.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Random;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.karthi.citiipay.dao.TransactionDAO;
import com.karthi.citiipay.exception.DBException;
import com.karthi.citiipay.exception.ErrorMessages;
import com.karthi.citiipay.model.CashBack;
import com.karthi.citiipay.model.Merchant;
import com.karthi.citiipay.model.MerchantTableDetails;
import com.karthi.citiipay.model.TransactionDetails;

@Repository
public class TransactiondaoImpl implements TransactionDAO {

	private static final Logger LOGGER = LoggerFactory.getLogger(TransactiondaoImpl.class);
	
	@Autowired
	private DataSource dataSource;
	
	public CashBack walletTransaction(long senderMobileNo, long receiverMobileNo, float amount, String comments)
			throws DBException {

		try (Connection conn = dataSource.getConnection();
				CallableStatement cStmt = conn.prepareCall("{call wallet_proc(?,?,?,?,?)}");) {
			cStmt.setLong(1, senderMobileNo);
			cStmt.setLong(2, receiverMobileNo);
			cStmt.setFloat(3, amount);
			cStmt.setString(4, comments);
			cStmt.registerOutParameter(5, Types.VARCHAR);
			cStmt.executeUpdate();
			String result = cStmt.getString(5);
			CashBack object = new CashBack();
			object.setResult(result);
			if (result.equals("Transaction Successfull")) {
				// SmsSend.msgSender(senderMobileNo, receiverMobileNo, amount);
				// SmsSend.msgReceiver(senderMobileNo, receiverMobileNo, amount);
				TransactiondaoImpl obj = new TransactiondaoImpl();
				int rows = 0;
				if (amount > 100 && amount <= 1000) {
					Random r = new Random();
					int low = 10;
					int high = 50;
					int wallet = r.nextInt(high - low) + low;
					rows = obj.addingCashback(senderMobileNo, wallet);
					if (rows == 1) {
						object.setCashbackAmount(wallet);
						obj.cashbackTableEntry(senderMobileNo, wallet);
					}
				} else if (amount > 1000 && amount <= 5000) {
					Random r = new Random();
					int low = 50;
					int high = 250;
					int wallet = r.nextInt(high - low) + low;
					rows = obj.addingCashback(senderMobileNo, wallet);
					if (rows == 1) {
						object.setCashbackAmount(wallet);
						obj.cashbackTableEntry(senderMobileNo, wallet);
					}
				} else if (amount > 5000) {
					Random r = new Random();
					int low = 250;
					int high = 500;
					int wallet = r.nextInt(high - low) + low;
					rows = obj.addingCashback(senderMobileNo, wallet);
					if (rows == 1) {
						object.setCashbackAmount(wallet);
						obj.cashbackTableEntry(senderMobileNo, wallet);
					}
				}
			}
			return object;
		} catch (Exception e) {
			throw new DBException(ErrorMessages.CONNECTION_FAILURE);
		}
	}

	@Override
	public String accountToWallet(long mobileNo, long accountNo, float amount, String comments) throws DBException {

		try (Connection conn = dataSource.getConnection();
				CallableStatement cStmt = conn.prepareCall("{call account_to_wallet(?,?,?,?,?)}");) {
			cStmt.setLong(1, accountNo);
			cStmt.setLong(2, mobileNo);
			cStmt.setFloat(3, amount);
			cStmt.setString(4, comments);
			cStmt.registerOutParameter(5, Types.VARCHAR);
			cStmt.executeUpdate();
			String result = cStmt.getString(5);
			if (result.equals("Transaction Successfull")) {
				SmsSend.msgReceiver(accountNo, mobileNo, amount);
			}
			return result;
		} catch (Exception e) {
			throw new DBException(ErrorMessages.CONNECTION_FAILURE);
		}
	}

	public int addingCashback(long mobileNo, float amount) throws DBException {

		String sql = "update kyc set kyc_wallet=kyc_wallet+" + amount + " where mobile_no=" + mobileNo;
		try (Connection conn = dataSource.getConnection(); Statement stmt = conn.createStatement();) {
			int rows = stmt.executeUpdate(sql);
			return rows;

		} catch (Exception e) {
			throw new DBException(ErrorMessages.CONNECTION_FAILURE);
		}
	}

	public Merchant payToMerchant(String merchantId, long mobileNo, float amount) throws DBException {

		try (Connection conn = dataSource.getConnection();
				CallableStatement cStmt = conn.prepareCall("{call pay_to_merchant(?,?,?,?,?)}");) {
			cStmt.setString(1, merchantId);
			cStmt.setLong(2, mobileNo);
			cStmt.setFloat(3, amount);
			cStmt.registerOutParameter(4, Types.VARCHAR);
			cStmt.registerOutParameter(5, Types.INTEGER);
			cStmt.executeUpdate();
			Merchant obj = new Merchant();
			String errorMessage = cStmt.getString(4);
			Integer id = cStmt.getInt(5);
			if (errorMessage == null) {
				obj.setStatus("SUCCESS");
				obj.setTransactionId(id);
				obj.setErrorMessage(null);
			} else {
				obj.setStatus("FAILURE");
				obj.setTransactionId(null);
				obj.setErrorMessage(errorMessage);
			}
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DBException(ErrorMessages.CONNECTION_FAILURE);
		}

	}

	@Override
	public Merchant refundToCustomer(String merchantId, int transactionId, float amount) throws DBException {

		try (Connection conn = dataSource.getConnection();
				CallableStatement cStmt = conn.prepareCall("{call refund_to_customer(?,?,?,?)}");) {
			cStmt.setInt(1, transactionId);
			cStmt.setString(2, merchantId);
			cStmt.setFloat(3, amount);
			cStmt.registerOutParameter(4, Types.VARCHAR);
			cStmt.executeUpdate();
			Merchant obj = new Merchant();
			String errorMessage = cStmt.getString(4);
			if (errorMessage == null) {
				obj.setStatus("SUCCESS");
				obj.setErrorMessage(null);
			} else {
				obj.setStatus("FAILURE");
				obj.setErrorMessage(errorMessage);
			}
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DBException(ErrorMessages.CONNECTION_FAILURE);
		}
	}

	public ArrayList<TransactionDetails> transactionDebited(long mobileNumber) throws DBException {

		String transactionHistory = "select * from transaction_table where categories='Debited' and mobile_no=? order by transaction_time desc";
		try (Connection conn = dataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(transactionHistory);) {
			stmt.setLong(1, mobileNumber);
			ArrayList<TransactionDetails> list = new ArrayList<>();
			try (ResultSet rs = stmt.executeQuery();) {
				while (rs.next()) {
					TransactionDetails user = new TransactionDetails();
					user.setSenderNumber(rs.getLong("mobile_no"));
					user.setReceiverNumber(rs.getLong("rec_mob_no"));
					user.setCategory(rs.getString("categories"));
					user.setTransactionDate(rs.getTimestamp("transaction_time"));
					user.setTransactionAmount(rs.getFloat("transaction_amount"));
					user.setTransactionStatus(rs.getString("status"));
					user.setComments(rs.getString("comments"));
					list.add(user);
				}
			} catch (Exception e) {
				throw new DBException(ErrorMessages.INVALID_COLUMN_INDEX);
			}
			return list;
		} catch (Exception e) {
			throw new DBException(ErrorMessages.CONNECTION_FAILURE);
		}
	}

	public ArrayList<TransactionDetails> transactionCredited(long mobileNumber) throws DBException {

		String transactionHistory = "select * from transaction_table where categories='Credited' and mobile_no=? order by transaction_time desc";
		try (Connection conn = dataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(transactionHistory);) {
			stmt.setLong(1, mobileNumber);
			ArrayList<TransactionDetails> list = new ArrayList<>();
			try (ResultSet rs = stmt.executeQuery();) {
				while (rs.next()) {
					TransactionDetails user = new TransactionDetails();
					user.setSenderNumber(rs.getLong("mobile_no"));
					user.setReceiverNumber(rs.getLong("rec_mob_no"));
					user.setCategory(rs.getString("categories"));
					user.setTransactionDate(rs.getTimestamp("transaction_time"));
					user.setTransactionAmount(rs.getFloat("transaction_amount"));
					user.setTransactionStatus(rs.getString("status"));
					user.setComments(rs.getString("comments"));
					list.add(user);
				}
			} catch (Exception e) {
				throw new DBException(ErrorMessages.INVALID_COLUMN_INDEX);
			}
			return list;
		} catch (Exception e) {
			throw new DBException(ErrorMessages.CONNECTION_FAILURE);
		}
	}

	public ArrayList<MerchantTableDetails> transactionMerchant(long mobileNumber) throws DBException {

		String transactionHistory = "select * from merchant_details where mobile_no=? order by transaction_time desc";
		try (Connection conn = dataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(transactionHistory);) {
			stmt.setLong(1, mobileNumber);
			ArrayList<MerchantTableDetails> list = new ArrayList<>();
			try (ResultSet rs = stmt.executeQuery();) {
				while (rs.next()) {
					MerchantTableDetails user = new MerchantTableDetails();
					user.setTransactionId(rs.getInt("transaction_id"));
					user.setMerchantId(rs.getString("merchant_id"));
					user.setMobileNo(rs.getLong("mobile_no"));
					user.setTransactionDate(rs.getTimestamp("transaction_time"));
					user.setAmount(rs.getFloat("amount"));
					user.setComments(rs.getString("comments"));
					list.add(user);
				}
			} catch (Exception e) {
				throw new DBException(ErrorMessages.INVALID_COLUMN_INDEX);
			}
			return list;
		} catch (Exception e) {
			throw new DBException(ErrorMessages.CONNECTION_FAILURE);
		}
	}

	public void cashbackTableEntry(long mobileNumber, int cashabck) throws DBException {
		String sql = "insert into transaction_table(mobile_no,rec_mob_no,categories,transaction_amount,status,comments) values(?,8608872041,'Credited',?,'Success','Cashback')";
		try (Connection conn = dataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setLong(1, mobileNumber);
			stmt.setLong(2, cashabck);
			stmt.executeUpdate();
		} catch (Exception e) {
			throw new DBException(ErrorMessages.CONNECTION_FAILURE);
		}
	}

}
