package android.ph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import i2c.*;
import sd.*;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.ph.R;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
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
	        	output.setText( "Output: \n\n"+ sd.sdScript());  
	        	break;
	        case I2C: 
	        	I2CActivity i2c = new I2CActivity(); 
	        	output.setTextSize(20);
	        	output.setText( "Output:\n\n"+ i2c.i2cScript());
	        	
	        	break;

	        case I2CHELP: 
	        	I2CActivity i2chelp = new I2CActivity(); 
	        	output.setTextSize(20);
	        	output.setText( "Help File for I2C Test\n\n"+ i2chelp.i2chelpScript());
	        	
	        	break;
	        	
	        case I2CLOG: 
	        	I2CActivity i2clog = new I2CActivity(); 
	        	output.setTextSize(20);
	        	output.setText( "Output Written to file:" + i2clog.i2clogScript());
	        	
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
		    // process.getOutputStream().
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
	
	public static void logtoFile(File fp, String calledScript){
		try {
		    Process process = Runtime.getRuntime().exec(calledScript);
		    
		    // Reads stdout.
		    // NOTE: You can write to stdin of the command using
		    // process.getOutputStream().
		    BufferedWriter writer = null;
		    BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		    int read;
		    char[] buffer = new char[4096];
		    StringBuilder output = new StringBuilder();
		    while ((read = reader.read(buffer)) > 0) {
		    	output.append(buffer, 0, read);
		    }
		    try{
		    writer = new BufferedWriter(new FileWriter(fp));
		    writer.write(output.toString());
		    }
		    catch(Exception e)
		    { e.printStackTrace();}
		    finally {
	            //Close the BufferedWriter
	            try {
	                if (writer != null) {
	                    writer.flush();
	                    writer.close();
	                }
	            } catch (IOException ex) {
	                ex.printStackTrace();
	            }
	        }
		    
		    
		    
		    //OutputStreamWriter;
		    
		    //BufferedReader reader = new BufferedReader(
		      //      new InputStreamReader(process.getInputStream()));
		    //int read;
		    //char[] buffer = new char[4096];
		    //StringBuffer output = new StringBuffer();
		    //while ((read = reader.read(buffer)) > 0) {
		      //  output.append(buffer, 0, read);
		    //}
		    reader.close();
		    //writer.close();
		    process.waitFor();
		    //return output.toString();

		} catch (IOException e) {
		    throw new RuntimeException(e);
		} catch (InterruptedException e) {
		    throw new RuntimeException(e);
		}
    }
	
}
