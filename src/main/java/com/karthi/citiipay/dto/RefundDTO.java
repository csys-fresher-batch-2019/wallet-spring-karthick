package com.karthi.citiipay.dto;

import lombok.Data;

@Data
public class RefundDTO {
	
	private int transactionId;
	private String merchantId;
	private float amount;
	
}
