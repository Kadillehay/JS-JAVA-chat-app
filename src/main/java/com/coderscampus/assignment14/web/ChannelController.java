package com.coderscampus.assignment14.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.coderscampus.assignment14.domain.Channel;
import com.coderscampus.assignment14.domain.Message;
import com.coderscampus.assignment14.service.ChannelService;
import com.coderscampus.assignment14.service.MessageService;

@Controller
public class ChannelController {

	@Autowired
    private ChannelService channelService;
	@Autowired
	private MessageService messageService;
	
	
	
	 @PostMapping("/createChannel")
	 public String createChannel(@RequestParam String channelName, Model model) {
	     Channel newChannel = channelService.createChannel(channelName);
	     model.addAttribute("newChannel", newChannel); // Add the new channel to the model
	     return "redirect:/welcome";
	 }
	 
	 @GetMapping("/channels/general")
	 public String showGeneralChannel(Model model) {
		 List<Message> chatMessages = messageService.getAllMessages();  // Replace with the appropriate method to get messages
		    model.addAttribute("chatMessages", chatMessages);
	     return "channel"; 
	 }
	 
	 
}

