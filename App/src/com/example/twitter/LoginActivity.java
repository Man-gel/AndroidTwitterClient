package com.example.twitter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.RequestToken;

public class LoginActivity extends Activity
{

	private EditText usuario;
	private EditText contraseña;
	private DatosUsuario datosUserLocal;
	private Twitter t;
	private RequestToken rqstTkn;
	private String authURL  = "";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		usuario = (EditText)findViewById(R.id.et_usuario);
		contraseña = (EditText)findViewById(R.id.et_password);
		datosUserLocal = new DatosUsuario(this);
		initSesionOauth();
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
			User datosAcceso = new User(user,pass);
			datosUserLocal.saveDetallesUsuario(datosAcceso);
			datosUserLocal.setUsuarioLoggeado(true);
			InputMethodManager input = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
			input.hideSoftInputFromWindow(usuario.getWindowToken(),0);
			startActivity(new Intent(this,MainActivity.class));
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
	protected void onNewIntent(Intent intent)
	{
		super.onNewIntent(intent);
		Uri tURI = intent.getData();
		if(tURI != null && tURI.toString().startsWith(AppSettings.ACCESS_TOKEN_URL))
		{
			String oauthVerif = tURI.getQueryParameter("oauth_verifier");
		}
	}
	
	private void initSesionOauth()
	{
		System.out.format("\n\n* * * * INICIANDO requestToken * * * *\n");
		t = new TwitterFactory().getInstance(); 
		t.setOAuthConsumer(AppSettings.TWTR_CONSUMER_KEY,AppSettings.TWTR_CONSUMER_SECRET);
		try
		{
			rqstTkn = t.getOAuthRequestToken(AppSettings.ACCESS_TOKEN_URL);
		}
		catch(TwitterException e)
		{
			Log.e("TwitterException", e.getMessage()+"\n");
		}
		authURL = rqstTkn.getAuthenticationURL();
		startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse(authURL)));
	}
	
	
	
	protected void onPostExecute(String result)
	{
		
	}
}
