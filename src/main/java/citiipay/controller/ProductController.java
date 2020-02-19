package citiipay.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import citiipay.implementation.LogindaoImpl;
import citiipay.messages.DBException;

@RestController
@RequestMapping("api")
public class ProductController {
	
	@GetMapping("/personalRegister")
	public void personalRegister(@RequestParam("mobilenumber") long MobileNo,@RequestParam("pinnumber") int Pinnumber,@RequestParam("confirmation") int Confirmationpin) throws DBException {
		LogindaoImpl obj=new LogindaoImpl();
		String result="";
		if(Pinnumber==Confirmationpin) {
			result=obj.personalRegister(MobileNo,Pinnumber);
		}
	}
}
