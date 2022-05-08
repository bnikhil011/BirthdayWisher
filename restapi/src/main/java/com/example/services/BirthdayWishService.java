package com.example.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.example.model.StaticData;
import com.example.model.User;

/*
 * 	BirthdayWishService is used to do two things
 * 1: To add user to our User List with will used by our Wishing service to wish User
 * 2: To check is Wishing service is active if not it will start the service
 * 
 */
@Service
@Scope("singleton")
public class BirthdayWishService {

	
	@Autowired
	WishingService wishingService;
	
	
	private boolean isServiceRunning = false;
	
	

	public boolean isServiceRunning() {
		return isServiceRunning;
	}


	public void setServiceRunning(boolean isServiceRunning) {
		this.isServiceRunning = isServiceRunning;
	}


	public boolean addUser(User user)
	{
		System.out.println("added User");
		if(StaticData.users.get(user.getBirthDate())!=null)
		{
			StaticData.users.get(user.getBirthDate()).add(user);			
		}
		else
		{
			List <User> userslist =  new ArrayList<User>();
			userslist.add(user);
			StaticData.users.put(user.getBirthDate(),userslist);
		}
		return true;
	}
	
	
	public void startWishingService()
	{
		if(isServiceRunning)
			return;
		
		Thread service = new Thread(wishingService);
		service.start();
		isServiceRunning =  true;
		
	}
	
	
	
	
	
	
	
	

}
