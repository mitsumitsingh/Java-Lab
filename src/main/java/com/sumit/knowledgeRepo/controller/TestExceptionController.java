package com.sumit.knowledgeRepo.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowCredentials = "true", exposedHeaders = "true", allowedHeaders = "*")
@RequestMapping("/rest")
public class TestExceptionController {

	@GetMapping("/getArithmeticException")
	public Integer arithmetic(){
		return 10/0;
	}
	
}
