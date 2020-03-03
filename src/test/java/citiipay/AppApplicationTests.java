package citiipay;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.karthi.citiipay.dto.MessageDTO;

@SpringBootTest
class AppApplicationTests {

	@Test
	void testWallet() {
		payWallet(8940267920l, "IRCTC", 100f);
		
		
	}
	
	public void payWallet(long mobileNo, String merchantId, float amount) {
		
		
		
		  String url = "https://apiwalletappin.cfapps.io";
		  String apiUrl = url + "/api/walletPayment?mobilenum="+mobileNo+"mercahntid="+merchantId+"amount="+amount;
		  System.out.println(apiUrl);
//		  Map<String, Object> params = new HashMap();
//		  params.put("mobilenum", mobileNo);
//		  params.put("merchantid", merchantId);
//		  params.put("amount", amount);
//		  System.out.println(params);
//		  Gson gson = new Gson();
//		  String json = gson.toJson(params);

//		  HttpEntity<String> request = new HttpEntity<>(json, getHeaders());
		//  HttpEntity<String> request = new HttpEntity<>(getHeaders());

		        RestTemplate restTemplate = new RestTemplate(); 
		  //restTemplate.postForEntity(apiUrl, request, Void.class);
		        ResponseEntity<MessageDTO> responseEntity = restTemplate.getForEntity(apiUrl, MessageDTO.class);
		        MessageDTO body = responseEntity.getBody();
		        System.out.println(body);
		        


		 }
	
	public HttpHeaders getHeaders() {
		HttpHeaders headers=new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return headers;
	}
}
