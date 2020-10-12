package com.sumit.knowledgeRepo.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface AmazonBucketService {
	
	public String uploadFile(MultipartFile multipartFile, String imageUrl);
	
}
