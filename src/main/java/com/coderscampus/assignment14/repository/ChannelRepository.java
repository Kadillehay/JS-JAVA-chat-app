package com.coderscampus.assignment14.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.coderscampus.assignment14.domain.Channel;


public interface ChannelRepository extends JpaRepository <Channel, String>{

	Channel findByChannelName(String channelName);

}
