package com.example.twitter;

import java.util.ArrayList;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

public class MiAdapter extends ArrayAdapter<Tweet> implements Filterable
{
	
	private static class ViewHolder
	{
		private TextView tv_nom;
		private TextView tv_usnom;
		private TextView tv_cont;
		private TextView tv_rtF;
		private ImageView imgView;
	}

	public MiAdapter(Activity context, ArrayList<Tweet> objects) 
	{
		super(context, 0,objects);
	}	

	@Override
	public View getView(int position, View view, ViewGroup parent)
	{
		ViewHolder viewHolder;
		Tweet registro = (Tweet)getItem(position);
		if(view == null)
		{
			view = LayoutInflater.from(getContext()).inflate(R.layout.row_list, parent,false);
			viewHolder = new ViewHolder();
			viewHolder.tv_nom = (TextView)view.findViewById(R.id.txtV);
			viewHolder.tv_usnom = (TextView)view.findViewById(R.id.TextView01);
			viewHolder.tv_cont = (TextView)view.findViewById(R.id.TextView03);
			viewHolder.tv_rtF = (TextView)view.findViewById(R.id.TextView02);
			viewHolder.imgView = (ImageView)view.findViewById(R.id.imgV);
			view.setTag(viewHolder);
		}
		else
		{
			viewHolder = (ViewHolder)view.getTag();
		}
		if(registro != null)
		{
			viewHolder.tv_nom.setText(registro.nombre);
			viewHolder.tv_usnom.setText(registro.screenName);
			viewHolder.tv_cont.setText(registro.contenido);
			viewHolder.tv_rtF.setText(registro.retweetCont+" "+registro.fecha);
			viewHolder.imgView.setImageResource(R.drawable.egg);
		}
		return view;		
	}	
	
}
