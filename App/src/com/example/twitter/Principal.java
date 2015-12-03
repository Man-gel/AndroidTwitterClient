package com.example.twitter;

import java.util.ArrayList;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class Principal extends Activity 
{
	private EditText editT;
	private final ArrayList<Tweet> lista = new ArrayList<Tweet>();
	private ListView listV;
	private MiAdapter adapter ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_principal);
		editT = (EditText)findViewById(R.id.etBusqueda);
		crearInfo();
	}
	
	private void crearInfo()
	{
		/*lista.add( new Person(R.drawable.einstein ,"Albert Einstein","778 2359776") );
		lista.add( new Person(R.drawable.hawking,"Stephen Hawking", "651 5763678") );
		lista.add( new Person(R.drawable.irons ,"Andy Irons", "555 5454267") );
		lista.add( new Person(R.drawable.kobain ,"Kurt Kobain","212 6545746") );
		lista.add( new Person(R.drawable.neruda ,"Pablo Neruda","988 7664327") );
		lista.add( new Person(R.drawable.stallman ,"Richard Stallman","877 6534522") );
		lista.add( new Person(R.drawable.torvalds ,"Linus Torvalds","561 7665430") );*/
		lista.add(new Tweet("Michael","@jordan","Hola mundo de tweets","4","03/dic/15",""+R.drawable.ic_launcher));
		lista.add(new Tweet("Michael","@jordan","Hola mundo","4","03/dic/15",""+R.drawable.ic_launcher));
		lista.add(new Tweet("Michael","@jordan","Esto es un comentario de prueba","4","03/dic/15",""+R.drawable.ic_launcher));
		lista.add(new Tweet("Michael","@jordan","Hola mundo de tweets","4","03/dic/15",""+R.drawable.ic_launcher));
		lista.add(new Tweet("Michael","@jordan","Hola mundo de tweets","4","03/dic/15",""+R.drawable.ic_launcher));
		lista.add(new Tweet("Michael","@jordan","Hola mundo de tweets","4","03/dic/15",""+R.drawable.ic_launcher));
		lista.add(new Tweet("Michael","@jordan","Hola mundo de tweets","4","03/dic/15",""+R.drawable.ic_launcher));
		lista.add(new Tweet("Michael","@jordan","Hola mundo de tweets","4","03/dic/15",""+R.drawable.ic_launcher));
		lista.add(new Tweet("Michael","@jordan","Hola mundo de tweets","4","03/dic/15",""+R.drawable.ic_launcher));
		
		adapter = new MiAdapter(Principal.this,lista);
		listV = (ListView)findViewById(R.id.lv_personajes);
		listV.setAdapter(adapter);		
	}
	
	
	public void btnBusqueda(View v)
	{
		String info = editT.getText().toString();
		boolean noPresente = true;
		mostrarToast("Aun no se ha habilitado este botón");
	}
	
	private void mostrarToast(CharSequence mensaje)
	{
		Context contexto = getApplicationContext();
		Toast toast = Toast.makeText(contexto, mensaje, Toast.LENGTH_SHORT);
		toast.setDuration(3);
		toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL, 0, 0);
		toast.show();	
	}

}
