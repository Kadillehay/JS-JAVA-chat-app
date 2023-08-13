package com.coderscampus.assignment14.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.coderscampus.assignment14.domain.Channel;
import com.coderscampus.assignment14.service.ChannelService;
import com.coderscampus.assignment14.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private ChannelService channelService;

	@GetMapping("/welcome")
	public String getWelcome(Model model) {
		List<Channel> existingChannels = channelService.getAllChannels();
		model.addAttribute("existingChannels", existingChannels);
		
		return "/welcome";
		
	}
}
