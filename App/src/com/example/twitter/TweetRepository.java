package com.example.twitter;

import twitter4j.AsyncTwitter;
import twitter4j.AsyncTwitterFactory;
import twitter4j.TwitterListener;
import twitter4j.conf.ConfigurationBuilder;

public class TweetRepository 
{
	private static TweetRepository instance;
	 
    private TweetRepository() {
 
    }
 
    public static TweetRepository getInstance() {
        if (instance == null) {
            instance = new TweetRepository();
        }
        return instance;
    }
 
    private ConfigurationBuilder getConfiguration() {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(AppSettings.TWTR_CONSUMER_KEY)
                .setOAuthConsumerSecret(AppSettings.TWTR_CONSUMER_SECRET)
                .setOAuthAccessToken(AppSettings.TWTR_ACCESS_TOKEN)
                .setOAuthAccessTokenSecret(AppSettings.TWTR_TOKEN_SECRET); 
        return cb;
    }
 
    public void getTimelineAsync(TwitterListener listener) {
        AsyncTwitterFactory factory = new AsyncTwitterFactory((getConfiguration().build()));
        AsyncTwitter asyncTwitter = factory.getInstance();
        asyncTwitter.addListener(listener);
        asyncTwitter.getHomeTimeline();
    }

}
