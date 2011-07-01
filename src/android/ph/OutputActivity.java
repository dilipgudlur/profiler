package android.ph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import i2c.*;
import dma.*;
import spi.*;
import sd.*;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.ph.R;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class OutputActivity extends Activity {
	Runtime runtime = Runtime.getRuntime();
    Process proc = null;
    TextView output;
    Button home,back,exit;
    String errorString;
    String device;
   // ProgressBar spinner;
    //int myProgress = 0;
    
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.output);
        output = (TextView)this.findViewById(R.id.output);
        output.setMovementMethod(new ScrollingMovementMethod());
        //spinner=(ProgressBar)findViewById(R.id.spinner);
        home = (Button)findViewById(R.id.home);
        back = (Button)findViewById(R.id.back);
        exit = (Button)findViewById(R.id.exit);
        device = getIntent().getStringExtra("device");     
        
        switch (StartActivity.Device.valueOf(device)) {
	        case SD:  
	        	SDActivity sd = new SDActivity();
	        	output.setText( "Output = "+ sd.sdScript());  
	        	break;
	        case I2C: 
	        	I2CActivity i2c = new I2CActivity(); 
	        	output.setText( "Output = "+ i2c.i2cScript()); 
	        	break;
	        /*case SPI: 
	        	SPIActivity spi = new SPIActivity();
	        	output.setText( "Output = "+ spi.spiScript(); 
	        	break;
	        case DMA: 
	        	DMAActivity dma = new DMAActivity();
	        	output.setText( "Output = "+ dma.dmaScript(); 
	        	break;*/
	        
	        default: errorString = "Invalid device"; break;
	    }
        
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
                startActivity(new Intent(OutputActivity.this, StartActivity.class));
            }
        });
	}
	
	public static String displayOnScreen(String calledScript){
		try {
		    Process process = Runtime.getRuntime().exec(calledScript);
		    
		    // Reads stdout.
		    // NOTE: You can write to stdin of the command using
		    //       process.getOutputStream().
		    BufferedReader reader = new BufferedReader(
		            new InputStreamReader(process.getInputStream()));
		    int read;
		    char[] buffer = new char[4096];
		    StringBuffer output = new StringBuffer();
		    while ((read = reader.read(buffer)) > 0) {
		        output.append(buffer, 0, read);
		    }
		    reader.close();
		    process.waitFor();
		    return output.toString();

		} catch (IOException e) {
		    throw new RuntimeException(e);
		} catch (InterruptedException e) {
		    throw new RuntimeException(e);
		}
    }
}
