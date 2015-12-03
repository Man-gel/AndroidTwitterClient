package com.example.twitter;

import java.util.ArrayList;

import com.example.contactos.Contacto;
import com.example.contactos.MainActivity;
import com.example.contactos.MiAdapter;
import com.example.contactos.Person;
import com.example.contactos.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class Principal extends Activity 
{
	private EditText editT;
	private final ArrayList<Person> lista = new ArrayList<Person>();
	private ListView listV;
	private MiAdapter adapter ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_principal);
		editT = (EditText)findViewById(R.id.etBusqueda);
		crearInfo();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	private void crearInfo()
	{
		lista.add( new Person(R.drawable.einstein ,"Albert Einstein","778 2359776") );
		lista.add( new Person(R.drawable.hawking,"Stephen Hawking", "651 5763678") );
		lista.add( new Person(R.drawable.irons ,"Andy Irons", "555 5454267") );
		lista.add( new Person(R.drawable.kobain ,"Kurt Kobain","212 6545746") );
		lista.add( new Person(R.drawable.neruda ,"Pablo Neruda","988 7664327") );
		lista.add( new Person(R.drawable.stallman ,"Richard Stallman","877 6534522") );
		lista.add( new Person(R.drawable.torvalds ,"Linus Torvalds","561 7665430") );
		adapter = new MiAdapter(MainActivity.this,lista);
		listV = (ListView)findViewById(R.id.lv_personajes);
		listV.setAdapter(adapter);
		listV.setOnItemClickListener(new AdapterView.OnItemClickListener()
		{
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
            	            	System.out.format("** MAINACTIVITY L62: position: %d\n",position);
            	Intent intent = new Intent(MainActivity.this, Contacto.class);
            	intent.putExtra("nombre",String.valueOf(lista.get(position).txtView));
            	intent.putExtra("idImagen", Integer.valueOf(lista.get(position).imgView));
            	intent.putExtra("phone", String.valueOf(lista.get(position).phone) );
				startActivity(intent);
            }
        });
	}
	
	
	public void btnBusqueda(View v)
	{
		String info = editT.getText().toString();
		boolean noPresente = true;
		for(Person p : lista)
		{
			if(p.txtView.contains(info))
			{
				Intent intent = new Intent(MainActivity.this, Contacto.class);
				intent.putExtra("nombre",String.valueOf(p.txtView));
            	intent.putExtra("idImagen", Integer.valueOf(p.imgView));
            	intent.putExtra("phone", String.valueOf(p.phone) );
            	editT.setText("");
            	noPresente = false;
				startActivity(intent);
			}				
		}
		if(noPresente)
			mostrarToast("No se encontraron coincidencias");
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
