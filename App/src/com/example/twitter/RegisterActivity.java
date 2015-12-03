package com.example.twitter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.temboo.core.*;
import com.temboo.Library.Twitter.*;

public class RegisterActivity extends Activity 
{
	private EditText et_usName, et_pass, et_passConfirm, et_nomb, et_edad;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		et_nomb = (EditText)findViewById(R.id.et_NombreRegister);
		et_edad = (EditText)findViewById(R.id.EditText01);
		et_usName = (EditText)findViewById(R.id.et_usuario);
		et_pass = (EditText)findViewById(R.id.EditText02);
		et_passConfirm = (EditText)findViewById(R.id.et_password);
	}
	
	public void registrarClick(View v)
	{
		if( !et_pass.getText().toString().trim().equals(et_passConfirm.getText().toString().trim()))
		{
			mostrarToast("Las contraseñas no coinciden");
			return;
		}
		if(et_usName.getText().toString().isEmpty() || et_nomb.getText().toString().isEmpty() || et_passConfirm.getText().toString().isEmpty() || et_edad.getText().toString().isEmpty())
		{
			mostrarToast("Se deben completar todos los campos");
			return;
		}
		Integer edad = 0;
		String usName = et_usName.getText().toString(); 
		String nombre = et_nomb.getText().toString();
		String pass = et_passConfirm.getText().toString();
		if(et_edad.getText().toString().matches("[a-zA-Z]"))
			edad = Integer.parseInt( et_edad.getText().toString() );
		else
		{
			mostrarToast("El campo 'edad' solo puede contener números");
			return;
		}
		User datosRegistro = new User(nombre, edad, usName, pass);
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
