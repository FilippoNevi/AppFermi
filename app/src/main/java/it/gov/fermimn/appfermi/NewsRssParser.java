package it.gov.fermimn.appfermi;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

import javax.xml.parsers.*;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewsRssParser extends Fragment {
	
	private ListView notizie;
	private ProgressBar attesa;
	private List<NewsData> news = new ArrayList<NewsData>();
	private View root;
	private NewsAdapter adapter;
	
	public static final String NEWS_DATA = "NEWS_DATA";
	
	class LoadNews extends AsyncTask <String, NewsData, String> {

		@Override
		protected String doInBackground(String... params) {
			try {
				
				URL image;
				URL url = new URL("http://www.fermimn.edu.it/index.php?action=rss");
				HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
				try {
					String linea;
					InputStream in = urlConnection.getInputStream();
					
					Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(in);
					
					NodeList items = doc.getElementsByTagName("item");
					
					int i, j;
					String testo = null;
			
					NewsData newsElement;
					
					for (i=0;i<items.getLength();i++){
						
						newsElement = new NewsData();
						
						Element item = (Element) items.item(i);
						
						newsElement.setTitolo(item.getElementsByTagName("title").item(0).getTextContent());
						
						NodeList children = item.getChildNodes();
						
						for (j=0;j<children.getLength();j++){

							if (children.item(j).getNodeName().equalsIgnoreCase("description")){
								testo = children.item(j).getTextContent();
								break;
							}
							
						}
						
						Pattern p = Pattern.compile(".*<img *alt=\"([^\"]*)\" *src=\"([^\"]*)\".*", Pattern.CASE_INSENSITIVE | Pattern.DOTALL | Pattern.MULTILINE);
						Matcher m = p.matcher(testo); 
						if (m.matches()) {
							try {
								
								image = new URL(m.group(2).replace("&amp;", "&"));
								BitmapDrawable bitDraw = new BitmapDrawable(getActivity().getResources(), image.openStream());
								Bitmap immagine = bitDraw.getBitmap();
								newsElement.setImmagine(immagine);
								
							    
							} catch (Exception e) {
								Log.e("Error reading file", e.toString());
							}
						}
						
						//stringa.trim().replaceAll("<.?title>"); -> quando trova una riga che contiene title lo elimina
						//. == qualsiasi car, ? se presente o meno
						
						p = Pattern.compile(".*<pubDate>[a-z]*, ([0-9]*) ([a-z]*) ([0-9]*).*</pubDate>.*", Pattern.CASE_INSENSITIVE | Pattern.DOTALL | Pattern.MULTILINE); 
						m = p.matcher(testo); 
						if (m.matches()) {
							Dictionary<String, String> mesi = new Hashtable<String, String>();
							mesi.put("Jan", "Gen");
							mesi.put("Feb", "Feb");
							mesi.put("Mar", "Mar");
							mesi.put("Apr", "Apr");
							mesi.put("May", "Mag");
							mesi.put("Jun", "Giu");
							mesi.put("Jul", "Lug");
							mesi.put("Aug", "Ago");
							mesi.put("Sep", "Set");
							mesi.put("Oct", "Ott");
							mesi.put("Nov", "Nov");
							mesi.put("Dec", "Dic");
							
							newsElement.setData(m.group(1) + " " + mesi.get(m.group(2)) + " " + m.group(3));
						}
						testo = testo.replaceAll("<img[^>]*>", "");
						newsElement.setTesto(testo);
						news.add(newsElement);
						
						publishProgress(newsElement);
						
					}
				}
				catch (Exception e) {
					e.printStackTrace();
					return e.getMessage();
				}
				finally {
					urlConnection.disconnect();
				};
			} catch (Exception ee) {
				ee.printStackTrace();
				return ee.getMessage();
			}

			return null;
		}

		protected void onProgressUpdate(NewsData... progress) {
			NewsData news = progress[0];
			adapter.add(news);
			//adapter.notifyDataSetChanged();
		}

		protected void onPostExecute(String result) {
		
			attesa.setVisibility(View.INVISIBLE);
			startLayout();
			
		}

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LayoutInflater li = this.getActivity().getLayoutInflater();
		root = inflater.inflate(R.layout.news_list,container,false);
		adapter = new NewsAdapter(getActivity(), R.layout.news_item, news); 
		attesa = (ProgressBar)root.findViewById(R.id.newsLoading);
		notizie = (ListView)root.findViewById(R.id.listView);
		
		caricaNotizie();
		
		return root;
	}
	
	private void caricaNotizie() {
		attesa.setVisibility(View.VISIBLE);
		LoadNews task = new LoadNews();
		task.execute();
		notizie.setAdapter(adapter);
		
	}
	
	private void launchDetailActivity(NewsData novita){
		Intent intent = new Intent(this.getActivity(),NewsDetailActivity.class);
		intent.putExtra(NEWS_DATA,novita);
		this.getActivity().startActivity(intent);
	}
	
	private void startLayout(){		
		
		notizie.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			
			@Override
			public void onItemClick(AdapterView<?> adapter, View view, int pos, long id) {
				
				NewsData novita = (NewsData) adapter.getItemAtPosition(pos);
				launchDetailActivity(novita);
				
			}
		});
	}
}
