package com.example.twitter;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class MainActivity extends FragmentActivity
{
	DatosUsuario datosUserLocal;	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		System.out.format("*\n");
		System.out.format("* * *\n");
		System.out.format("* * * * * * TWITTER CUTONALA INICIADO * * * * * * \n");
		datosUserLocal = new DatosUsuario(this);
		datosUserLocal.setUsuarioLoggeado(true);
		if(autenticado())
			setContentView(R.layout.activity_main);
		else
			startActivity(new Intent(this, LoginActivity.class));		
	}
	
	@Override
	protected void onStart()
	{
		super.onStart();
	}
	
	private boolean autenticado()
	{
		return datosUserLocal.getEstadoLoggeado();
	}	
		
}
