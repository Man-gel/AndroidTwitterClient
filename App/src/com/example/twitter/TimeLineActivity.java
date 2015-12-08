package com.example.twitter;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

public class TimeLineActivity extends FragmentActivity 
{
	DatosUsuario datosUserLocal;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
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

}
