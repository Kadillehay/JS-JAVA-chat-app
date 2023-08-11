package com.coderscampus.assignment14.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderscampus.assignment14.domain.Channel;
import com.coderscampus.assignment14.repository.ChannelRepository;
import com.coderscampus.assignment14.repository.UserRepository;

@Service
public class ChannelService {
	@Autowired
	UserRepository userRepo;
	@Autowired
	ChannelRepository channelRepo;
	
	 public Channel createChannel(String channelName) {
	        Channel channel = new Channel();
	        channel.setChannelName(channelName);
	        // You might generate a unique channelId here if needed
	        return channelRepo.save(channel);
	    }
	}


