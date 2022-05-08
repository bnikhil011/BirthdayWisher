package com.example.model;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/*
 * StaticData is used to save User Data
 * users is a map which takes Date(as key) and Users(as Value)
 * It saves all the users according to the date.
 * Example: if there are  5 users have birthDate 1/1/1998 -> all of them will be saved 
 * in the list associated to that date
 * 
 * startService flag is used to start the service , after starting the service code checks whether service
 * is running or is it in dead state and accordingly it will take action .
 */
public class StaticData {
	
	public static Map <String,List<User>>users = new HashMap<String,List<User>>();
	public static boolean startService = false; 

}
