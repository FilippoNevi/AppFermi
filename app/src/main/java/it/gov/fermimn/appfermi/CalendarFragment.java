package it.gov.fermimn.appfermi;

import java.net.HttpURLConnection;
import java.net.URL;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ListView;
import android.widget.ProgressBar;

public class CalendarFragment extends Fragment{
	
	private static final String CALENDAR_LINK = "CALENDAR_LINK";
	
	private View root;
	private WebView webView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LayoutInflater li = this.getActivity().getLayoutInflater();
		root = inflater.inflate(R.layout.fragment_section_dummy,container,false);
		webView = (WebView) root.findViewById(R.id.registroElettronico);
		
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this.getActivity());
		if(preferences.contains(CALENDAR_LINK)){
			 
	        webView.loadUrl(preferences.getString(CALENDAR_LINK, ""));
	        
	        WebSettings webSettings = webView.getSettings();
	        //Screen adapting
	        webSettings.setUseWideViewPort(true);
	        webSettings.setLoadWithOverviewMode(true);
	        webSettings.setJavaScriptEnabled(true);
	        webSettings.setSavePassword(true);
	        webSettings.setBlockNetworkImage(false);
			webSettings.setBlockNetworkLoads(false);
			webSettings.setBuiltInZoomControls(true);
			webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
			webSettings.setLoadsImagesAutomatically(true);
			webSettings.setSupportZoom(true);
		
		}else{
			try{
				
				DialogFragment dialog = new DialogOrario(getActivity().getSupportFragmentManager());
			    dialog.show(this.getActivity().getSupportFragmentManager(), "dialogOrario");
				
				
				
			}catch(Exception e){
				Log.e("Errore","Errore connessione");
			}
		}
		
		return root;
	}

}
