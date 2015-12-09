package com.example.twitter;

import java.security.SecureRandom;
import java.util.ArrayList;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.RequestToken;

public class TimeLineActivity extends FragmentActivity 
{
	DatosUsuario datosUserLocal;
	private MiAdapter adapter;
	private ListView listV;
	ArrayList<Tweet> timeline;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		datosUserLocal = new DatosUsuario(getApplicationContext());
		setContentView(R.layout.activity_time_line);
		Bundle recibido = getIntent().getExtras();
		listV = (ListView)findViewById(R.id.lv_personajes);
		timeline = (ArrayList<Tweet>) recibido.get("timeline");
		adapter = new MiAdapter(this,timeline);
		listV.setAdapter(adapter);
	}
	
	
	public void clickBtnSalir(View v) 
	{
		AppSettings.TWTR_ACCESS_TOKEN = "";
		AppSettings.TWTR_TOKEN_SECRET = "";
		datosUserLocal.borrarDatosUser();
		datosUserLocal.setUsuarioLoggeado(false);
		startActivity(new Intent(getApplicationContext(),MainActivity.class));
	}
	
	public void clickBtnSearch(View v)
	{
		

	}
	
	public void ClickBtnTwitear(View v)
	{
		
	}	
}
