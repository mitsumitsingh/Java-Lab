package com.sumit.knowledgeRepo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sumit.knowledgeRepo.service.CachingExampleService;

@RestController
public class CachingExampleController extends RestCtrl{

	@Autowired
    private CachingExampleService cachingExampleService;

    @GetMapping("/checkCaching")
    public String checkCachingIsWorkingOrNot() {
        return cachingExampleService.checkCachingIsWorkingOrNot();
    }
    
}
