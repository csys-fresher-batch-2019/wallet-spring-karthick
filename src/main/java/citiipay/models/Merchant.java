package citiipay.models;

public class Merchant {

	private Integer transactionId;
	private String status;
	private String errorMessage;

	public String getErrorMessage() {
		return errorMessage;
	}	

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Integer getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Merchant [transactionId=" + transactionId + ", status=" + status + ", errorMessage=" + errorMessage
				+ "]";
	}

}
