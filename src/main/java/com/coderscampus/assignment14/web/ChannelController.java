package com.coderscampus.assignment14.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.coderscampus.assignment14.service.ChannelService;

@Controller
public class ChannelController {

	@Autowired
    private ChannelService channelService;
	
	 @PostMapping("/createChannel")
	    public String createChannel(@RequestParam String channelName) {
	        channelService.createChannel(channelName);
	        return "redirect:/welcome"; // Redirect to the welcome page after creating the channel
	    }
	 
	 @GetMapping("/channels/general")
	 public String showGeneralChannel(Model model) {
	     // Logic to retrieve chat messages for the general channel and populate the model
	     return "channels"; 
	 }
	 
	 
}

