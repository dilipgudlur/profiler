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
    static String testOptions=" ";
    
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
				if(!editDevice.getText().toString().equals(""))
					testOptions = testOptions.concat("-D dev/i2c-" + editDevice.getText().toString());
				if(!editBlock.getText().toString().equals(""))
					testOptions = testOptions.concat(" -b" + editBlock.getText().toString());
				if(!editNumber.getText().toString().equals(""))
					testOptions = testOptions.concat(" -n" + editNumber.getText().toString());
				if(!editOffset.getText().toString().equals(""))
					testOptions = testOptions.concat(" -o" + editOffset.getText().toString());
				if(!editIterations.getText().toString().equals(""))
					testOptions = testOptions.concat(" -i" + editIterations.getText().toString());
				if(checkVerbose.isChecked())
					testOptions = testOptions.concat(" -v");
								
				Intent i = new Intent(I2CActivity.this, OutputActivity.class);
		        i.putExtra("device", "I2C");	//2 is I2C	
				startActivity(i);
			}
		});     
        
    }
    
    public String i2cScript()
    {
    	String i2cStr = "/data/kernel-tests/i2c-msm-changed.sh";
    	i2cStr = i2cStr.concat(testOptions);
    	return testOptions + OutputActivity.displayOnScreen(i2cStr);
    }    
}  
