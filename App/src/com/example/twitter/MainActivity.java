package com.example.twitter;

import com.temboo.core.TembooException;
import com.temboo.core.TembooSession;
import android.app.Activity;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;


public class MainActivity extends ListFragment
{
	  @Override
	  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	  {
	        String[] tweets = getArguments().getStringArray("tweets");
	 
	        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
	                inflater.getContext(),
	                android.R.layout.simple_list_item_1,
	                tweets
	        );
	 
	        // Este método es parte de la clase ListFragment y nos permitirá indicarle cual es el adaptador de la vista.
	        setListAdapter(arrayAdapter);
	 
	        return super.onCreateView(inflater, container, savedInstanceState);
	   }
	  
	/*DatosUsuario datosUserLocal;
	private EditText et_nomReg, et_01, et_usuario;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		et_nomReg = (EditText)findViewById(R.id.et_NombreRegister);
		et_01 = (EditText)findViewById(R.id.EditText01);
		et_usuario = (EditText)findViewById(R.id.et_usuario);
		et_nomReg.setEnabled(false);
		et_01.setEnabled(false);
		et_usuario.setEnabled(false);
		datosUserLocal = new DatosUsuario(this);
		initSesionTemboo();		
	}
	
	@Override
	protected void onStart()
	{
		super.onStart();
		if(autenticado())
			mostrarDetallesUser();
		else
			startActivity(new Intent(this, LoginActivity.class));
	}
	
	private boolean autenticado()
	{
		return datosUserLocal.getEstadoLoggeado();
	}
	
	private void mostrarDetallesUser()
	{
		User us = datosUserLocal.getDetallesUsuarioLoggeado();
		et_nomReg.setText(us.nombre);
		et_01.setText(""+us.edad);
		et_usuario.setText(us.userName);
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
	*/	
}
