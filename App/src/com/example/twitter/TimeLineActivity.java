package com.example.twitter;

import java.security.SecureRandom;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.RequestToken;

public class TimeLineActivity extends FragmentActivity 
{
	DatosUsuario datosUserLocal;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		datosUserLocal = new DatosUsuario(getApplicationContext());
		setContentView(R.layout.activity_time_line);		
	}
	
	
	public void clickBtnSalir(View v) 
	{
		AppSettings.TWTR_ACCESS_TOKEN = "";
		AppSettings.TWTR_TOKEN_SECRET = "";
		datosUserLocal.borrarDatosUser();
		datosUserLocal.setUsuarioLoggeado(false);
		this.finish();
	}
	
	public void clickBtnSearch(View v)
	{
		

	}
	
	public void ClickBtnTwitear(View v)
	{
		
	}	
	
	@Override
	protected void onNewIntent(Intent intent)
	{
		super.onNewIntent(intent);
		Uri tURI = intent.getData();
		if(tURI != null && tURI.toString().startsWith(AppSettings.ACCESS_TOKEN_URL))
		{
			String oauthVerif = tURI.getQueryParameter("oauth_verifier");
		}
	}
	
	
	
	class OAuthRequest extends AsyncTask<Void,Void,Void>
	{
		@Override
		protected Void doInBackground(Void... params)
		{
			
			return null;
		}
		
	}

}
