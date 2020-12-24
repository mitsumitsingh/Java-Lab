package com.sumit.knowledgeRepo.service;

import javax.jms.Message;
import javax.jms.Session;

import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public interface ActiveMQConsumerService {

	public void receiveMessage(@Payload String payload,
            @Headers MessageHeaders headers,
            Message message, Session session);
	
}
