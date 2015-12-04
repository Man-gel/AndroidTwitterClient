package com.example.twitter;

import twitter4j.AsyncTwitter;
import twitter4j.AsyncTwitterFactory;
import twitter4j.TwitterListener;
import twitter4j.conf.ConfigurationBuilder;

public class ServerRequest 
{
	private static ServerRequest instancia;
	private ServerRequest()
	{
		
	}
	
	public ConfigurationBuilder iniciarOauth()
	{
		ConfigurationBuilder c = new ConfigurationBuilder();
	    c.setDebugEnabled(true)
	         .setOAuthConsumerKey(AppSettings.TWTR_CONSUMER_KEY)
	         .setOAuthConsumerSecret(AppSettings.TWTR_CONSUMER_SECRET)
	         .setOAuthAccessToken(AppSettings.TWTR_ACCESS_TOKEN)
	         .setOAuthAccessTokenSecret(AppSettings.TWTR_TOKEN_SECRET);	 
	    return c;		
	}
	
	public void getTimelineAsync(TwitterListener listener) 
	{
	    AsyncTwitterFactory aTfactory = new AsyncTwitterFactory((iniciarOauth().build()));
	    AsyncTwitter asyncTwitter = aTfactory.getInstance();
	    asyncTwitter.addListener(listener);
	    asyncTwitter.getHomeTimeline();
	}
}
