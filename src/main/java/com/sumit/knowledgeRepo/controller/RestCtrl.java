package com.sumit.knowledgeRepo.controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.web.bind.annotation.RestController
@CrossOrigin(origins="*", allowedHeaders="*", allowCredentials = "true", exposedHeaders = "true")
@RequestMapping("/rest")
public abstract class RestCtrl {}