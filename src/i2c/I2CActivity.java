package i2c;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.ph.OutputActivity;
import android.ph.R;
import android.ph.StartActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class I2CActivity extends Activity{
	Runtime runtime = Runtime.getRuntime();
    Process proc = null;
    Button i2c,exit,home,back;
    TextView textDevice,textBlock,textNumber,textOffset,textIterations,textVerbose;
    EditText editDevice,editBlock,editNumber,editOffset,editIterations;
    CheckBox checkVerbose;
    
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.i2c);
        i2c = (Button)findViewById(R.id.i2c);
        textDevice = (TextView)findViewById(R.id.textViewDevice);
        textBlock = (TextView)findViewById(R.id.textViewBlock);
        textNumber = (TextView)findViewById(R.id.textViewNumberBlock);
        textOffset = (TextView)findViewById(R.id.textViewOffset);
        textIterations = (TextView)findViewById(R.id.textViewIterations);
        textVerbose = (TextView)findViewById(R.id.textViewVerbose);
        editDevice = (EditText)findViewById(R.id.editTextDevice);
        editBlock = (EditText)findViewById(R.id.editTextBlock);
        editNumber = (EditText)findViewById(R.id.editTextNumberBlock);
        editOffset = (EditText)findViewById(R.id.editTextOffset);
        editIterations = (EditText)findViewById(R.id.editTextIterations);
        checkVerbose = (CheckBox)findViewById(R.id.checkBoxVerbose);
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
                startActivity(new Intent(I2CActivity.this, StartActivity.class));
            }
        });
        
        i2c.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(I2CActivity.this, OutputActivity.class);
		        i.putExtra("device", "I2C");	//2 is I2C	
				startActivity(i);	        
			}
		});                
    }
    public static String exec_i2cfulltest(){
		try {
		    // Executes the command.
			@SuppressWarnings("unused")
			//Process p1 = Runtime.getRuntime().exec("/system/bin/chmod 755 /data/kernel-tests/i2c-msm-test.sh");
			String str = "/data/kernel-tests/i2c-msm-changed.sh";
			String str1=" -i3 -v -n30";
							
			Process process = Runtime.getRuntime().exec(str.concat(str1));
		    
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
