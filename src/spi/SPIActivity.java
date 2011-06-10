package spi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.ph.OuputActivity;
import android.ph.R;
import android.ph.StartActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SPIActivity extends Activity{
	Runtime runtime = Runtime.getRuntime();
    Process proc = null;
    Button spi,home,exit,back;
        
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spi);
        spi = (Button)findViewById(R.id.spi);
        home = (Button)findViewById(R.id.home);
        back = (Button)findViewById(R.id.back);
        exit = (Button)findViewById(R.id.exit);
        
        exit.setOnClickListener(new View.OnClickListener() {
			@Override
			//TODO: remove in each sub activity..not a good practice
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
                startActivity(new Intent(SPIActivity.this, StartActivity.class));
            }
        });
        
        spi.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(SPIActivity.this, OuputActivity.class);
		        i.putExtra("device",3);	//3 is SPI	
				startActivity(i);	   	        
			}
		});                
    }
}
