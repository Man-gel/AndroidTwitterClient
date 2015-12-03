package com.example.twitter;

import android.content.Context;
import android.content.SharedPreferences;

public class DatosUsuario
{
	public static final String SP_NAME = "userDetails";
	
	SharedPreferences dbLocalUser;
	
	public DatosUsuario(Context contexto)
	{
		dbLocalUser = contexto.getSharedPreferences(SP_NAME, 0);
	}
	
	public void saveDetallesUsuario(User user)
	{
		SharedPreferences.Editor spEditor = dbLocalUser.edit();
		spEditor.putString("nombre", user.nombre);
		spEditor.putString("password", user.password);
		spEditor.putString("userName", user.userName);
		spEditor.putInt("edad", user.edad);
		spEditor.commit();
	}
	
	public User getDetallesUsuarioLoggeado()
	{
		String nombre = dbLocalUser.getString("nombre", "");
		String password = dbLocalUser.getString("password", "");
		String userName = dbLocalUser.getString("userName", "");
		Integer edad = dbLocalUser.getInt("edad", 0);
		return new User(nombre,edad,userName,password);
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
