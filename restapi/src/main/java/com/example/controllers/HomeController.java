package com.example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.StaticData;
import com.example.model.User;
import com.example.services.BirthdayWishService;

@RestController
@RequestMapping("/emailapi")
public class HomeController {

	@Autowired
	BirthdayWishService birthdayWishService;
	
	@PostMapping("/wishuser")
	public ResponseEntity<String> getUser(@RequestBody User user)
	{
		birthdayWishService.addUser(user);
		return new ResponseEntity<String>(HttpStatus.ACCEPTED);
		
		
	}
	
	@PostMapping("/modifyService")
	public void modifyService(@RequestBody String flag)
	{
		
		
		if (flag.equals("1"))
		StaticData.startService = true;
		else
			StaticData.startService = false;
		
		birthdayWishService.startWishingService();
	}
	
	
}
