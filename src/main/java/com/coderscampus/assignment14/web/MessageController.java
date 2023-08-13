package com.coderscampus.assignment14.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coderscampus.assignment14.domain.Message;
import com.coderscampus.assignment14.service.MessageService;

@RestController
@RequestMapping("/channels")
public class MessageController {
	@Autowired
	private MessageService messageService;

	@GetMapping("/{channelName}/messages")
	public ResponseEntity<List<Message>> getMessagesForChannel(@PathVariable String channelName) {
		List<Message> messages = messageService.getMessagesForChannel(channelName);
		return ResponseEntity.ok(messages);
	}

}
