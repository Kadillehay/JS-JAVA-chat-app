package com.coderscampus.assignment14.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderscampus.assignment14.domain.Channel;
import com.coderscampus.assignment14.domain.Message;
import com.coderscampus.assignment14.repository.MessageRepository;

@Service
public class MessageService {
	
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private ChannelService channelService;

    // Implement methods to interact with the repository
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    public void saveMessage(Message message) {
        messageRepository.save(message);
    }

	public List<Message> getMessagesForChannel(String channelName) {
		 Channel channel = channelService.findByChannelName(channelName);
		return messageRepository.findByChannel(channel);
	}

}
