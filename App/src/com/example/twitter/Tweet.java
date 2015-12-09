package com.example.twitter;

import java.io.Serializable;

public class Tweet implements Serializable
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
	
	@Override
	public String toString()
	{
		return String.format("%s\n\n%s\n%s\t\t%s\n%s\n\t\t%s",imagen,nombre,screenName,fecha,contenido,retweetCont );
	}

}
