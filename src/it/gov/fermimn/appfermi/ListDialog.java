package it.gov.fermimn.appfermi;

import java.util.List;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class ListDialog extends DialogFragment{
	
	private String title;
	private List<String> elements;
	
	public ListDialog(String title, List<String>elements){
		this.title = title;
		this.elements = elements;
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
	    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
	    builder.setTitle(title)
	           .setItems((String[])elements.toArray(), new DialogInterface.OnClickListener() {
	               public void onClick(DialogInterface dialog, int which) {
	               
	           }
	    });
	    return builder.create();
	}
}
