package it.gov.fermimn.appfermi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.SectionIndexer;
import android.widget.TextView;

import java.util.List;

public class NewsAdapter extends ArrayAdapter<NewsData>{
	
	private List<NewsData> news;
	
	public NewsAdapter(Context context, int textViewResourceId, List<NewsData> news){
		super(context,textViewResourceId);
		this.news = news;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		
		LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		convertView = inflater.inflate(R.layout.news_item,null);
		
		NewsData novita = news.get(position);
		
		ImageView img = (ImageView) convertView.findViewById(R.id.itemEntryImageNewsBackground);
		img.setImageBitmap(novita.getImmagine());
		
		TextView titolo = (TextView) convertView.findViewById(R.id.itemEntryTextNewsTitle);
		titolo.setText(novita.getTitolo());
		
		return convertView;
	}
	
}
