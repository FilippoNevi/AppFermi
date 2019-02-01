package it.gov.fermimn.appfermi;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;

public class DialogOrario extends DialogFragment {
	
	public URL url;
	public BufferedReader reader;
	
	protected ArrayList<String> docenti;
	protected ArrayList<String> classi;
	protected FragmentManager manager;
	
	public static final String SITO = "http://www.fermimn.gov.it/orari/orario_in_corso/";
	
	public DialogOrario(FragmentManager manager){
		this.manager = manager;
	}
	
	@Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
		
		docenti = new ArrayList<String>();
		classi = new ArrayList<String>();
		
		try {
			url = new URL(SITO + "index.html");			
		} catch (Exception e) {
			Log.e("DIALOG ERROR", e.getMessage());
		}
		
	
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        
        builder.setMessage(R.string.orariMessage)
               .setPositiveButton(R.string.docenti, new DialogInterface.OnClickListener() {
            	   
                   public void onClick(DialogInterface dialog, int id) {
                	   
                	   DocentiLoader docentiLoader = new DocentiLoader();
                	   docentiLoader.execute();
                	   
                   }
                   
               })
               .setNegativeButton(R.string.classi, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                       
                	   ClassiLoader classiLoader = new ClassiLoader();
                	   classiLoader.execute();
                	   
                	   
                   }
               });
       
        return builder.create();
    }
	
	class DocentiLoader extends AsyncTask <String, NewsData, String> {
		
		@Override
		protected String doInBackground(String... params) {
			
			
			Pattern p = Pattern.compile(".*<A *HREF=\"Docenti/[a-zA-Z]*[0-9]*.*>", Pattern.CASE_INSENSITIVE | Pattern.DOTALL | Pattern.MULTILINE);
     	    try{
     		   HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
     		   reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
         	   String temp = reader.readLine();
         	   
         	   while(temp != null){
         		  
         		   Matcher m = p.matcher(temp); 
         		  
         		   
         		   if(m.matches()){
         			   
         			   docenti.add(temp);
         		   }
         		   temp = reader.readLine();
         	   	}
         	   
         	   DialogListaElementi dialogLista = new DialogListaElementi("Seleziona il docente", docenti);
         	   dialogLista.show(manager, "dialogDocente");
         	   
     	    }catch(Exception e){
     	    	Log.e("Errore",e.getMessage());
     	   	}
     	    return null;
		}
	}
	
	class ClassiLoader extends AsyncTask <String, NewsData, String> {
		
		@Override
		protected String doInBackground(String... params) {
			
			
			Pattern p = Pattern.compile(".*<A *HREF=\"Classi/[a-zA-Z]*[0-9]*.*>", Pattern.CASE_INSENSITIVE | Pattern.DOTALL | Pattern.MULTILINE);
     	    try{
     		   HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
     		   reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
         	   String temp = reader.readLine();
         	   
         	   while(temp != null){
         		  
         		   Matcher m = p.matcher(temp); 
         		  
         		   
         		   if(m.matches()){
         			   
         			   classi.add(temp);
         		   }
         		   temp = reader.readLine();
     	   		}
         	    
         	    DialogListaElementi dialogLista = new DialogListaElementi("Seleziona la classe", classi);
        	    dialogLista.show(manager,"dialogClasse");
        	   
        	    
     	    }catch(Exception e){
     	    	Log.e("Errore",e.getMessage());
     	   	}
     	    return null;
		}
	}
	

}
