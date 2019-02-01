package it.gov.fermimn.appfermi;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class DialogListaElementi extends DialogFragment{
	
	private String titolo;
	private List<String> link;
	private String[] labels;
	
	public DialogListaElementi(String titolo, List<String>link){
		this.titolo = titolo;
		this.link = link;
		
		labels = new String[link.size()];
		
		build();
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
	    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
	    builder.setTitle(titolo)
	           .setItems(labels, new DialogInterface.OnClickListener() {
	               public void onClick(DialogInterface dialog, int which) {
	               
	            	   
	            	   /**
	            	    * fare metodo che salva nelle preferenze il link
	            	    */
	           }
	    });
	    return builder.create();
	}
	
	private void build(){
		for (int i=0;i<link.size();i++){
			
			String split[] = link.get(i).split(">");
			String item = split[1].split("<")[0];
			labels[i] = item;
			
			split = link.get(i).split("\"");
			link.set(i, split[1]);
		}
		
	}
}
