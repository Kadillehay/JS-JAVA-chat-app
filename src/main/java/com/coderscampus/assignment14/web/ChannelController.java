package com.coderscampus.assignment14.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
		    // Attempt to create the channel
		   Channel newChannel = channelService.createChannel(channelName);
		    if (newChannel == null) {
		        model.addAttribute("channelCreationError", true);
		    } else {
		        model.addAttribute("newChannel", newChannel);
		    }
		    return "welcome";
	}
	 
	 @GetMapping("/channels/{channelName}")
	 public String showChannelPage(@PathVariable String channelName, Model model) {
	     List<Message> chatMessages = messageService.getMessagesForChannel(channelName);
	     List<Channel> existingChannels = channelService.getAllChannels();
	     model.addAttribute("channelName", channelName);
	     model.addAttribute("chatMessages", chatMessages);
	     model.addAttribute("existingChannels", existingChannels);
	     return "channel"; // Replace with the actual name of your channel view template
	 }
	 
	 
}

