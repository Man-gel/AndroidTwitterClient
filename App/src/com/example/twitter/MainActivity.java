package com.example.twitter;

import android.util.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.*;
import android.view.View;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterAdapter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.TwitterListener;
import twitter4j.TwitterMethod;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

public class MainActivity extends FragmentActivity
{
	public static Twitter t;
	public static  RequestToken rqstTkn;
	//private String authURL  = "";
	private ServerRequest request;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
{
		super.onCreate(savedInstanceState);
		System.out.format("*\n");
		System.out.format("* * *\n");
		System.out.format("* * * * * * TWITTER CUTONALA INICIADO * * * * * * \n");
		if (android.os.Build.VERSION.SDK_INT > 15) {
	        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
	                .permitAll().build();
	        StrictMode.setThreadPolicy(policy);
	    }
		request = new ServerRequest(getApplicationContext());
		if(!autenticado())
		{	
			Intent i = new Intent(MainActivity.this, LoginActivity.class);
			i.putExtra("URL", request.getauthURL());
			startActivity(i);		
		}
		setContentView(R.layout.activity_main);					
	}
	
	@Override
	protected void onStart()
	{
		super.onStart();
	}
	
	private boolean autenticado()
	{
		return request.verificarLogin();
	}	
	
	/*	
	private void initSesionOauth() throws TwitterException, IOException
	{	
		t = new TwitterFactory().getInstance(); 
		t.setOAuthConsumer(AppSettings.TWTR_CONSUMER_KEY,AppSettings.TWTR_CONSUMER_SECRET);
		rqstTkn = t.getOAuthRequestToken(AppSettings.CALLBACK_URL);
		authURL = rqstTkn.getAuthorizationURL();
		
		AccessToken acsTkn = null;
		BufferedReader bfR = new BufferedReader(new InputStreamReader(System.in));
		Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
		Bundle b = new Bundle();
		b.putString("authorizationURL", authURL);
		intent.putExtras(b);
		startActivityForResult(intent,1);//new Intent(Intent.ACTION_VIEW,Uri.parse(authURL)));
	}*/
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		if(resultCode == Activity.RESULT_OK)
		{
			String oauthVer = (String)data.getExtras().get("oauth_verifier");
			Log.e( "oauth_verifier -> ", oauthVer);
			System.out.format("\n\n* * * * Authorization URL: %s\n",oauthVer);
			startActivity(new Intent(this, TimeLineActivity.class));
			request.saveOauthVerif(oauthVer);
		}
	}
		
}
