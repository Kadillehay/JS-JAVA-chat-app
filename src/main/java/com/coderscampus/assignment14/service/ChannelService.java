package com.coderscampus.assignment14.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderscampus.assignment14.domain.Channel;
import com.coderscampus.assignment14.repository.ChannelRepository;
import com.coderscampus.assignment14.repository.UserRepository;

import jakarta.annotation.PostConstruct;

@Service
public class ChannelService {
	@Autowired
	UserRepository userRepo;
	@Autowired
	ChannelRepository channelRepo;

	@PostConstruct
	public void initializeDefaultChannels() {
		Channel existingGeneralChannel = channelRepo.findByChannelName("General Channel");
		if (existingGeneralChannel == null) {
			createChannel("General Channel");
		}
	}

	public Channel createChannel(String channelName) {
		Channel existingChannel = channelRepo.findByChannelName(channelName);
		if (existingChannel != null) {
			return null;
		}

		Channel channel = new Channel();
		channel.setChannelName(channelName);
		return channelRepo.save(channel);
	}

	public Channel findByChannelName(String channelName) {
		return channelRepo.findByChannelName(channelName);
	}

	public List<Channel> getAllChannels() {
		return channelRepo.findAll();
	}
}
