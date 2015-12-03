package com.example.twitter;

public class User
{
	String nombre = "";
	String password;
	String userName;
	Integer edad = 0;
	public User(String nom, Integer e, String usName, String psw )
	{
		nombre = nom;
		edad = e;
		usName = userName;
		password = psw;
	}
	
	public User(String usName, String psw)
	{
		password = psw;
		userName = usName;
	}
	

}
