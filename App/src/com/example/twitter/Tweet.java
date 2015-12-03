package com.example.twitter;

public class Tweet 
{
	String screenName,nombreUs,fecha,contenido,retweetCont,imagen;
	public Tweet(String screenNmb,String nmbUs, String f, String rtwtCnt, String img )
	{
		screenName = screenNmb;
		nombreUs = nmbUs;
		fecha = f;
		retweetCont = rtwtCnt;
		imagen = img;
	}
	
	public Tweet()
	{
		
	}

}
