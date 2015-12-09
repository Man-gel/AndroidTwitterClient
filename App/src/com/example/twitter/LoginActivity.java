package com.example.twitter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class LoginActivity extends Activity
{

	private WebView webV;
	private WebSettings webS;
	private Intent intent;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{	
		super.onCreate(savedInstanceState);
		if (android.os.Build.VERSION.SDK_INT > 15) {
	        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
	                .permitAll().build();
	        StrictMode.setThreadPolicy(policy);
	    }
		setContentView(R.layout.activity_login);
		intent = getIntent();
		String url = (String)intent.getExtras().get("URL");
		webV = (WebView)findViewById(R.id.webView1);
		webS = webV.getSettings();
		webV.setWebViewClient(new WebViewClient()
		{
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url)
			{
				if(url.contains(AppSettings.CALLBACK_URL))
				{
					Uri uri = Uri.parse(url);
					String oauthVer = uri.getQueryParameter("oauth_verifier");
					intent.putExtra("oauth_verifier",oauthVer);
					setResult(RESULT_OK,intent);
					finish();
					return true;
				}
				return false;
			}
		});
		webS.setJavaScriptEnabled(true);
		webS.setSavePassword(false);
		webV.loadUrl(url);		
		//getAccessToken();
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
	
	@SuppressLint("ShowToast")
	private void mostrarToast(CharSequence mensaje)
	{
		Context contexto = getApplicationContext();
		Toast toast = Toast.makeText(contexto, mensaje, 3);
		toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL, 0, 0);
		toast.show();	
	}
	
}
