package com.sumit.knowledgeRepo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sumit.knowledgeRepo.model.UploadFile;

public interface UploadFileRepository extends JpaRepository<UploadFile, Integer>{

}
