package com.karthi.citiipay.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class MessageDTO {

	private String errorMessage;
	
	private String infoMessage;
	
}
