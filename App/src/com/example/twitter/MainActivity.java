package com.example.twitter;

import com.temboo.core.TembooException;
import com.temboo.core.TembooSession;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initSesionTemboo();
	}
	
	public void onClickBtn(View v) 
	{		
		startActivity(new Intent(this, LoginActivity.class));		
	}
	
	private void initSesionTemboo()
	{
		if(AppSettings.sesion != null)
			try
			{
				AppSettings.sesion = new TembooSession(AppSettings.TMB_ACCOUNT_NAME,AppSettings.TMB_APP_KEY_NAME,AppSettings.TMB_APP_KEY_VALUE);
			}
			catch (TembooException e) 
			{
				e.printStackTrace();
			}
	}	
}
