package com.example.twitter;

import android.content.Context;
import android.content.SharedPreferences;

public class DatosUsuario
{
	
	
	SharedPreferences dbLocalUser;
	
	public DatosUsuario(Context contexto)
	{
		dbLocalUser = contexto.getSharedPreferences(AppSettings.SHRD_PREF_NAME, 0);
	}
	
	public void saveDetallesUsuario(User user)
	{
		SharedPreferences.Editor spEditor = dbLocalUser.edit();
		spEditor.putString("user_secret", user.user_secret);
		spEditor.putString("user_token", user.user_token);
		spEditor.commit();
	}
	
	public User getDetallesUsuarioLoggeado()
	{
		String user_tkn = dbLocalUser.getString("user_token", "");
		String user_sec = dbLocalUser.getString("user_secret", "");
		return new User(user_tkn,user_sec);
	}
	
	public void setUsuarioLoggeado(boolean loggeado)
	{
		SharedPreferences.Editor spEditor = dbLocalUser.edit();
		spEditor.putBoolean("loggedIn", loggeado);
		spEditor.commit();
	}
	
	public void borrarDatosUser()
	{
		SharedPreferences.Editor spEditor = dbLocalUser.edit();
		spEditor.clear();
		spEditor.commit();
	}
	
	public boolean getEstadoLoggeado()
	{
		if(dbLocalUser.getBoolean("loggedIn", false) == true)
			return true;
		else
			return false;
	}
	
}
