package org.umit.icm.mobile.gui.dialogs;


import java.util.regex.Pattern;

import org.umit.icm.mobile.R;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ServiceSuggestionDialog extends Dialog{
	
	String selection;
	Context contextControl;
    private ReadyListener readyListener;
    EditText ServiceName;
    EditText ServiceHost;
    EditText ServiceIP;
    EditText ServicePort;
    
    
    private final Pattern IP_PATTERN = Pattern.compile(
    		"(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.)" +
    		"{3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)"); 
    
    private boolean checkIP(String ip) {
        return IP_PATTERN.matcher(ip).matches();
    }
    
    private boolean checkPort(String port){
    	try{
    		Integer.parseInt(port);
    	}catch(Exception e){
    		return false;
    	}
    	return true;
    }
	
	 public ServiceSuggestionDialog(Context context, String selection, 
	            ReadyListener readyListener) {
	        super(context);
	        this.selection = selection;
	        this.readyListener = readyListener;
	        this.contextControl = context;
	    }
	 
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.suggestservicedialog);
	        Button buttonOK = (Button) findViewById(R.id.SendServiceSuggestion);
	        buttonOK.setOnClickListener(new sendListener());
	        ServiceName = (EditText) findViewById(R.id.ServiceName);
	        ServiceHost = (EditText) findViewById(R.id.ServiceHost);
	        ServiceIP = (EditText) findViewById(R.id.ServiceIP);
	        ServicePort = (EditText) findViewById(R.id.ServicePort);
	    }
	 
	 
	 public interface ReadyListener {
	        public void ready(String selection);
	    }
	 
	 private class sendListener implements android.view.View.OnClickListener {

			@Override
			public void onClick(View arg0) {	
				Context context = ServiceSuggestionDialog.this.getContext();
				if((!ServiceHost.getText().toString().equals("")) && (!ServiceIP.getText().toString().equals("")) && (!ServicePort.getText().toString().equals("")) ){
    				if(!checkIP(ServiceIP.getText().toString())){
    	        		
    	        		CharSequence text = context.getString(R.string.toast_ip);
    	        		int duration = Toast.LENGTH_SHORT;

    	        		Toast toast = Toast.makeText(context, text, duration);
    	        		toast.show();
    	        	}else if(!checkPort(ServicePort.getText().toString())){
    	        		CharSequence text = context.getString(R.string.toast_port);
    	        		int duration = Toast.LENGTH_SHORT;

    	        		Toast toast = Toast.makeText(context, text, duration);
    	        		toast.show();
    	        	}
    				else {
    	    			readyListener.ready("Service"
    	    	    			+ "&" + ServiceName.getText().toString() 
    	    	    			+ "&" + ServiceHost.getText().toString() 
    	    					+ "&" + ServiceIP.getText().toString()
    	    					+ "&" + ServicePort.getText().toString());
    	                ServiceSuggestionDialog.this.dismiss();
    	        	}
    			} else {
    				CharSequence text = context.getString(R.string.invalid_host_ip);
            		int duration = Toast.LENGTH_SHORT;

            		Toast toast = Toast.makeText(context, text, duration);
            		toast.show();
    			}
    		}

	   }
}