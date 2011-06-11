package dma;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
    
    public static String exec_dmafulltest(){
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
