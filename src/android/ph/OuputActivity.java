package android.ph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.ph.R;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class OuputActivity extends Activity {
	Runtime runtime = Runtime.getRuntime();
    Process proc = null;
    TextView output;
    Button home,back,exit;
    String errorString;
    int device;
    
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.output);
        output = (TextView)this.findViewById(R.id.output);
        home = (Button)findViewById(R.id.home);
        back = (Button)findViewById(R.id.back);
        exit = (Button)findViewById(R.id.exit);
        device = getIntent().getIntExtra("device", -1);
        switch (device) {
	        case 1:  output.setText( "Output = "+ exec_sdfulltest());  break;
	        case 2:  output.setText( "Output = "+ exec_i2cfulltest()); break;
	        case 3:  output.setText( "Output = "+ exec_spifulltest()); break;
	        case 4:  output.setText( "Output = "+ exec_dmafulltest()); break;
	        
	        default: errorString = "Invalid device"; break;
	    }
        
        
        exit.setOnClickListener(new View.OnClickListener() {
			@Override
			//TODO: remove in each sub activity..not a good practice
			public void onClick(View v) {
				System.exit(0);
			}
		});
        
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	finish();
            }
        });
        
        home.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(OuputActivity.this, StartActivity.class));
            }
        });
	}	
	String exec_sdfulltest(){
		try {
		    // Executes the command.
		    Process process = Runtime.getRuntime().exec("/data/kernel-tests/sd_test.sh");
		    
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
		    
		    // Waits for the command to finish.
		    process.waitFor();
		    
		    return output.toString();

		} catch (IOException e) {
		    throw new RuntimeException(e);
		} catch (InterruptedException e) {
		    throw new RuntimeException(e);
		}
    }
	
	String exec_i2cfulltest(){
		try {
		    // Executes the command.
		    Process process = Runtime.getRuntime().exec("/data/kernel-tests/i2c-msm-test.sh");
		    
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
		    
		    // Waits for the command to finish.
		    process.waitFor();
		    
		    return output.toString();

		} catch (IOException e) {
		    throw new RuntimeException(e);
		} catch (InterruptedException e) {
		    throw new RuntimeException(e);
		}
    }
	
	String exec_spifulltest(){
		try {
		    // Executes the command.
		    Process process = Runtime.getRuntime().exec("/data/kernel-tests/spidevtest.sh");
		    
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
		    
		    // Waits for the command to finish.
		    process.waitFor();
		    
		    return output.toString();

		} catch (IOException e) {
		    throw new RuntimeException(e);
		} catch (InterruptedException e) {
		    throw new RuntimeException(e);
		}
    }
	
	String exec_dmafulltest(){
		try {
		    // Executes the command.
		    Process process = Runtime.getRuntime().exec("/data/kernel-tests/msm_dma.sh");
		    
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
		    
		    // Waits for the command to finish.
		    process.waitFor();
		    
		    return output.toString();

		} catch (IOException e) {
		    throw new RuntimeException(e);
		} catch (InterruptedException e) {
		    throw new RuntimeException(e);
		}
    }
	
}
