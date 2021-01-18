package com.sumit.knowledgeRepo.service.impl;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.sumit.knowledgeRepo.service.CachingExampleService;

@Service
public class CachingExampleServiceImpl implements CachingExampleService {

	@Override
	@Cacheable("example")
	public String checkCachingIsWorkingOrNot() {
		return getResource();
	}

    private String getResource() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Resources";
    }
}
