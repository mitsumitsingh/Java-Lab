package com.sumit.knowledgeRepo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.sumit.knowledgeRepo.config.FileStorageProperties;

@EnableTransactionManagement
@SpringBootApplication
@EnableConfigurationProperties({
    FileStorageProperties.class
})
public class KnowledgeRepoApplication {

	public static void main(String[] args) {
		SpringApplication.run(KnowledgeRepoApplication.class, args);
	}

}
