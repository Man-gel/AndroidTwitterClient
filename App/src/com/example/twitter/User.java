package com.example.twitter;

public class User
{
	String user_token = "";
	String user_secret = "";
	
	public User(String usTkn, String usScrt)
	{
		user_token = usTkn;
		user_secret = usScrt;
	}
	
	
	public User(User u)
	{
		this.user_secret = u.user_secret;
		this.user_token = u.user_token;
	}
}
