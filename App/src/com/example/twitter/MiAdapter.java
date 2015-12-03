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
		private TextView itmView;
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
			viewHolder.itmView = (TextView)view.findViewById(R.id.txtV);
			viewHolder.imgView = (ImageView)view.findViewById(R.id.imgV);
			view.setTag(viewHolder);
		}
		else
		{
			viewHolder = (ViewHolder)view.getTag();
		}
		if(registro != null)
		{
			viewHolder.itmView.setText(formatoTweet(registro.screenName,registro.nombreUs,registro.contenido,registro.fecha,registro.retweetCont));
			viewHolder.imgView.setImageResource(Integer.parseInt(registro.imagen));
		}
		return view;		
	}
	
	private String formatoTweet(String screenNmb,String nmbUs, String cont, String rtwtCnt, String f)
	{
		return String.format("%s\n%s\n\n%s\nretweets:%s\t%s", screenNmb,nmbUs,cont, rtwtCnt, f);
	}
}
