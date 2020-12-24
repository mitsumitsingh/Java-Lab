package com.sumit.knowledgeRepo.service.impl;

import javax.jms.Message;
import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.sumit.knowledgeRepo.config.ActiveMQConfig;
import com.sumit.knowledgeRepo.service.ActiveMQConsumerService;

@Service
public class ActiveMQConsumerServiceImpl implements ActiveMQConsumerService{

	private static Logger log = LoggerFactory.getLogger(ActiveMQConsumerServiceImpl.class);

	@JmsListener(destination = ActiveMQConfig.SENT_EMAIL_QUEUE)
	public void receiveMessage(@Payload String payload,
	                           @Headers MessageHeaders headers,
	                           Message message, Session session) {
		
	    log.info("received Payload : " + payload);
	}
	
}
