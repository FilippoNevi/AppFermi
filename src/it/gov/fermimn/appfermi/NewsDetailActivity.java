package it.gov.fermimn.appfermi;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.text.Html;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
public class NewsDetailActivity extends Activity{
	
	private NewsData news;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_details);
        
        ActionBar actionBar = getActionBar();
        //actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.show();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.rgb(51, 181, 229)));
        
        TextView titolo = (TextView) findViewById(R.id.textTitolo);
        TextView data = (TextView) findViewById(R.id.textData);
        ImageView img = (ImageView) findViewById(R.id.imageImmagine);
        TextView testo = (TextView) findViewById(R.id.textTesto);
        
        news = (NewsData) this.getIntent().getSerializableExtra(NewsRssParser.NEWS_DATA);
       
        titolo.setText(news.getTitolo());
        data.setText(news.getData());
        
        if (news.getImmagine() != null){
        	img.setImageBitmap(news.getImmagine());
        }
        testo.setText(Html.fromHtml(news.getTesto()));
	}
	
}
