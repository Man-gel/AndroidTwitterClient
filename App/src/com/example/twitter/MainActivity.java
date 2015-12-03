package com.example.twitter;

import com.temboo.core.TembooException;
import com.temboo.core.TembooSession;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends Activity
{
	private DatosUsuario datosUserLocal;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		datosUserLocal = new DatosUsuario(this);
		initSesionTemboo();
		
	}
	
	public void onClickBtn(View v) 
	{
		datosUserLocal.borrarDatosUser();
		datosUserLocal.setUsuarioLoggeado(false);
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
