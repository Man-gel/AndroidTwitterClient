package com.example.twitter;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Date;

import android.content.Context;
import android.util.Log;
import twitter4j.AsyncTwitter;
import twitter4j.AsyncTwitterFactory;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.TwitterListener;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

public class ServerRequest 
{
	private Twitter t;
	private RequestToken rqstTkn;
	DatosUsuario dataUser;
	
	public ServerRequest(Context context)
	{
		System.out.format("\n\n* * * * INICIANDO requestToken * * * *\n");
		dataUser = new DatosUsuario(context);
		t = new TwitterFactory().getInstance();
		rqstTkn = null;
		t.setOAuthConsumer(AppSettings.TWTR_CONSUMER_KEY, AppSettings.TWTR_CONSUMER_SECRET);
		try
		{
			rqstTkn = t.getOAuthRequestToken(AppSettings.CALLBACK_URL);					
		}
		catch(TwitterException e)
		{
			Log.e("\n* * *TwitterException", e.getMessage());
		}	
	}
	
	public void saveOauthVerif(String oauth)
	{
		AccessToken acsTkn = null;
		try
		{
			acsTkn = t.getOAuthAccessToken(rqstTkn,oauth);
			dataUser.saveDetallesUsuario(new User(acsTkn.getToken(),acsTkn.getTokenSecret())); 
		}
		catch(TwitterException e)
		{
			Log.e("\n* * *TwitterException", e.getMessage());
		}
	}
	
	public String getauthURL()
	{
		return rqstTkn.getAuthenticationURL();
	}
	
	public void salirTWTR()
	{
		dataUser.borrarDatosUser();
		dataUser.setUsuarioLoggeado(false);
	}
	
	public boolean verificarLogin()
	{
		if(dataUser.getEstadoLoggeado())
		{
			Log.e("UsuarioLoggeado", "Datos de token existe ya");
			return true;
		}
		else
			return false;
	}
	
	public boolean enviarTweet(String tweet)
	{
		User u = dataUser.getDetallesUsuarioLoggeado();
		if(u.user_token != null && u.user_secret != null)
		{
			Configuration conf = new ConfigurationBuilder()
					.setOAuthConsumerKey(AppSettings.TWTR_CONSUMER_KEY)
					.setOAuthConsumerSecret(AppSettings.TWTR_CONSUMER_SECRET)
					.setOAuthAccessToken(u.user_token)
					.setOAuthAccessTokenSecret(u.user_secret).build();
			t = new TwitterFactory(conf).getInstance();
			try
			{
				t.updateStatus(tweet);
			}
			catch(TwitterException e)
			{
				Log.e("\n* * *TwitterException", e.getMessage());
				return false;
			}
			return true;
		}
		else
			return false;
	}
	
	public ArrayList<Tweet> consultarTimeLine()
	{
		ArrayList<Tweet> tLine = new ArrayList<Tweet>();
		try 
		{
			@SuppressWarnings("rawtypes")
			ResponseList<Status> lista = t.getHomeTimeline();
			for(Status s : lista)
			{
				Tweet twt = new Tweet(s.getUser().getName(),
						              s.getUser().getScreenName(),
						              ""+s.getCreatedAt().compareTo(new Date()),
						              s.getText(),
						              ""+s.getRetweetCount(),
						              s.getUser().getProfileImageURL().toString());
				System.out.format("\n%s\n",twt.toString());
				tLine.add(twt);
				
			}
		}
		catch(ConcurrentModificationException e)
		{
			Log.e("\n* * *ConcurrentModificationException en responseList", e.getMessage());
		}
		catch (TwitterException e)
		{
			Log.e("\n* * *TwitterException en responseList", e.getMessage());
		}
		return tLine;
	}
		
}






























