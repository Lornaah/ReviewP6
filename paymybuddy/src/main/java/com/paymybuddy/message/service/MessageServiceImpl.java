package com.paymybuddy.message.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymybuddy.message.model.Message;
import com.paymybuddy.message.repository.MessageRepository;

@Service("messageService")
public class MessageServiceImpl implements MessageService {

	@Autowired
	MessageRepository messageRepository;

	@Override
	public String addMessage(Message message) {

		messageRepository.save(message);

		return "message saved";
	}

}
