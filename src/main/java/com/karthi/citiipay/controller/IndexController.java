package com.karthi.citiipay.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller

public class IndexController {
	


private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);	

    @RequestMapping("/")    
    public String index() {        
    	LOGGER.info("this is a info message");
    	LOGGER.warn("this is a warn message");
    	LOGGER.error("this is a error message");
    	LOGGER.debug("this is a debug message");
        return "index.jsp";
    }
}