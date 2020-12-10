package com.sumit.knowledgeRepo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sumit.knowledgeRepo.service.AmazonBucketService;

@RestController
public class AmazonBucketController extends RestCtrl{
	
	@Autowired
	private AmazonBucketService amazonBucketService;
	
    @PostMapping(value = "/uploadImage", consumes = "multipart/form-data")
    public String uploadFile(@RequestPart(value = "file") MultipartFile file, @RequestParam(value = "imageUrl") String imageUrl) throws Exception{
    	return  this.amazonBucketService.uploadFile(file, imageUrl);
    }
    
}
