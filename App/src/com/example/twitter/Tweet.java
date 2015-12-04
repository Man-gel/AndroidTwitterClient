package com.example.twitter;

public class Tweet 
{
	String nombre,screenName,fecha,contenido,retweetCont,imagen;
	public Tweet(String nmb, String screenNmb, String f, String cont, String rtwtCnt, String img )
	{
		nombre = nmb;
		screenName = screenNmb;		
		contenido = cont;
		fecha = f;
		retweetCont = rtwtCnt;
		imagen = img;
	}
	
	public Tweet()
	{
		
	}

}
