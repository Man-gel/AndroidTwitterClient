package com.example.twitter;

import android.util.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.StrictMode;
import android.support.v4.app.*;
import android.view.View;
import android.widget.ProgressBar;
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
	private ProgressBar pb;
	
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
			startActivityForResult(i,1);		
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
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		if(resultCode == Activity.RESULT_OK)
		{
			String oauthVer = (String)data.getExtras().get("oauth_verifier");
			Log.e( "oauth_verifier -> ", oauthVer);
			System.out.format("\n\n* * * * Authorization: %s\n",oauthVer);
			request.saveOauthVerif(oauthVer);
			ArrayList<Tweet> tl = request.consultarTimeLine();
			Intent i = new Intent(getApplicationContext(), TimeLineActivity.class);
			i.putExtra("timeline",  tl);
			startActivity(i);			
		}
	}
		
}
