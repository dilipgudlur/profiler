package dma;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.ph.OuputActivity;
import android.ph.R;
import android.ph.StartActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class DMAActivity extends Activity {
/** Called when the activity is first created. */
    
    Runtime runtime = Runtime.getRuntime();
    Process proc = null;
    Button dma,home,exit,back;
        
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dma);
        dma = (Button)findViewById(R.id.dma);
        home = (Button)findViewById(R.id.home);
        back = (Button)findViewById(R.id.back);
        exit = (Button)findViewById(R.id.exit);
        
        exit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				android.os.Process.killProcess(android.os.Process.myPid());	
			}
		});
        
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	finish();
            }
        });
        
        home.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(DMAActivity.this, StartActivity.class));
            }
        });
                
        dma.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(DMAActivity.this, OuputActivity.class);
		        i.putExtra("device",4);	//4 is DMA	
				startActivity(i);	   	        
			}
		});                
    }   
}
