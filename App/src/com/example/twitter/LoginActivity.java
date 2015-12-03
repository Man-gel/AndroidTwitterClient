package com.example.twitter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.os.AsyncTask;
import org.json.*;
import com.temboo.core.*;
import com.temboo.core.Choreography.ResultSet;
import com.temboo.Library.Twitter.Users.*;
import com.temboo.Library.Twitter.Lists.*;
import com.temboo.Library.Twitter.Users.VerifyCredentials.*;
import com.temboo.Library.Twitter.Tweets.*;


public class LoginActivity extends Activity implements View.OnClickListener
{

	private GetAccountSettings set;
	private EditText usuario;
	private EditText contraseña;
	private TextView tvLink;
	private DatosUsuario datosUserLocal;
	private VerifyCredentials credenciales;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		usuario = (EditText)findViewById(R.id.et_usuario);
		contraseña = (EditText)findViewById(R.id.et_password);
		datosUserLocal = new DatosUsuario(this);
		tvLink = (TextView)findViewById(R.id.tv_linkReg);
		tvLink.setOnClickListener(this);
		InputMethodManager input = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
		input.toggleSoftInput(InputMethodManager.SHOW_FORCED,InputMethodManager.HIDE_IMPLICIT_ONLY);		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) 
		{
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void validarUsuario(View v)
	{	
		String user = usuario.getText().toString();
		String pass = contraseña.getText().toString();		
		if(user.isEmpty() || pass.isEmpty())
		{
			mostrarToast("Debe ingresar un nombre de usuario y una contraseña");
			contraseña.setText("");
			usuario.setText("");
			return;
		}
		
		try 
		{			
			set = new GetAccountSettings(AppSettings.sesion);
			credenciales = new VerifyCredentials(AppSettings.sesion);
			VerifyCredentialsInputSet verificarInput = credenciales.newInputSet();
			verificarInput.set_AccessToken(AppSettings.TWTR_ACCESS_TOKEN);
			verificarInput.set_AccessTokenSecret(AppSettings.TWTR_TOKEN_SECRET);
			VerifyCredentialsResultSet resulSet = credenciales.execute(verificarInput);
			
		}
		catch (TembooException e) 
		{
			e.printStackTrace();
		}

		if( !( user.equals("root") && pass.equals("admin")) )
		{
			mostrarToast("El nombre de usuario y/o la contraseña no son correctos");
			contraseña.setText("");
			usuario.setText("");
		}
		else
		{
			contraseña.setText("");
			usuario.setText("");
			mostrarToast("Bienvenido "+user+"!");
			User datosAcceso = null;
			datosUserLocal.saveDetallesUsuario(datosAcceso);
			datosUserLocal.setUsuarioLoggeado(true);
			InputMethodManager input = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
			input.hideSoftInputFromWindow(usuario.getWindowToken(),0);
			startActivity(new Intent(this,Principal.class));
		}
	}
	
	@SuppressLint("ShowToast")
	private void mostrarToast(CharSequence mensaje)
	{
		Context contexto = getApplicationContext();
		Toast toast = Toast.makeText(contexto, mensaje, 3);
		toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL, 0, 0);
		toast.show();	
	}

	@Override
	public void onClick(View v) 
	{
		switch(v.getId())
		{		
			case R.id.tv_linkReg:
				startActivity(new Intent(this, RegisterActivity.class));
				break;
		}
		
	}
	
	
	
	protected void onPostExecute(String result)
	{
		
	}
}
