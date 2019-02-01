package it.gov.fermimn.appfermi;

import java.util.Set;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class LoginDialog extends DialogFragment{
	
	public static final String SET_USERNAME = "username"; 
	public static final String SET_PASSWORD = "password"; 
	public static final String MY_PREFERENCES = "myPreferences";
	protected View view;
	
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        
		LayoutInflater inflater = getActivity().getLayoutInflater();
		
		view = inflater.inflate(R.layout.user_dialog,null);
        
        builder.setView(view);

        
        builder.setMessage(R.string.userMessage)
               .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
            	   
                   public void onClick(DialogInterface dialog, int id) {
                       
                	   EditText username = (EditText) view.findViewById(R.id.username);
                	   EditText password = (EditText) view.findViewById(R.id.password);
                	   
                	   if (((username.getText().toString()!=null))&&(password.getText().toString()!=null)) {
	                	   SharedPreferences preferences = getActivity().getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);
						
	                	   SharedPreferences.Editor options = preferences.edit();
	                	   
						   options.putString(SET_USERNAME, username.getText().toString());
	                	   options.putString(SET_PASSWORD, username.getText().toString());
                	   }
                	   
                   }
                   
               })
               .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                       // User cancelled the dialog
                   }
               });
        // Create the AlertDialog object and return it
        return builder.create();
    }


}
