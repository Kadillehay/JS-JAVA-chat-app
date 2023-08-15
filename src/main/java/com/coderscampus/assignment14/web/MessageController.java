package com.coderscampus.assignment14.web;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coderscampus.assignment14.domain.Channel;
import com.coderscampus.assignment14.domain.Message;
import com.coderscampus.assignment14.service.ChannelService;
import com.coderscampus.assignment14.service.MessageService;

@RestController
@RequestMapping("/channels")
public class MessageController {
	@Autowired
	private MessageService messageService;
	@Autowired
	private ChannelService channelService;

	@GetMapping("/{channelName}/messages")
	public ResponseEntity<List<Message>> getMessagesForChannel(@PathVariable String channelName) {
		 System.out.println("Received REQUEST for channel: " + channelName);
		List<Message> messages = messageService.getMessagesForChannel(channelName);
		 System.out.println("Messages for CHANNEL " + channelName + ": " + messages);
		return ResponseEntity.ok(messages);
	}
	   @PostMapping("/{channelName}/sendMessage")
	    public ResponseEntity<String> sendMessage(
	            @PathVariable String channelName,
	            @RequestBody Message message) {
		   System.out.println("IN  THE  POST MAPPING" + message);

	        // Set the timestamp and channel for the message
	        message.setTimestamp(LocalDateTime.now());
	        Channel channel = channelService.findByChannelName(channelName);
	        message.setChannel(channel);

	        // Save the message to the channel
	        messageService.saveMessage(message);
	       
	        System.out.println("MESSAGE  NOW  SAVED");

	        return ResponseEntity.ok().build();
	    }

}
