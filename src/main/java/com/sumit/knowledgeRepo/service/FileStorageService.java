package com.sumit.knowledgeRepo.service;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sumit.knowledgeRepo.pojo.UploadFilePojo;

@Service
public interface FileStorageService {

	Resource loadFileAsResource(String fileName);

	UploadFilePojo uploadFile(MultipartFile file);

}
