package it.gov.fermimn.appfermi;

import org.apache.http.util.EncodingUtils;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class CalendarRegisterHolder  extends Fragment{
	public static final String ARG_SECTION_NUMBER = "section_number";
	public static final String REGISTRO_URL = "https://fermi-mn-sito.registroelettronico.com/login/";
	public static final String CALENDAR_URL = "https://www.google.com/calendar/embed?showTz=0&mode=AGENDA&wkst=1&bgcolor=%23ffffcc&src=isfermimantova%40gmail.com&color=%2329527A&ctz=Europe%2FRome";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	
        View rootView = inflater.inflate(R.layout.fragment_section_dummy, container, false);
        Bundle args = getArguments();
        
        switch((Integer)args.get(ARG_SECTION_NUMBER)){
        	case 1: registroWebView(rootView);
        		break;
        	case 2: calendarWebView(rootView);
        		break;
        }
        
        return rootView;
    }
    
    public void registroWebView(View rootView){
    	
    	WebView registro = (WebView) rootView.findViewById(R.id.registroElettronico);
        registro.loadUrl(REGISTRO_URL);
        WebSettings webSettings = registro.getSettings();
        
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
	
    }
    public void calendarWebView(View rootView){
    	WebView registro = (WebView) rootView.findViewById(R.id.registroElettronico);
        registro.loadUrl(CALENDAR_URL);
        WebSettings webSettings = registro.getSettings();
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
    }
    
   
}
