package i2c;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Calendar;
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
    Button i2c,exit,home,back,help,log;
    TextView textDevice,textBlock,textNumber,textOffset,textIterations,textVerbose,textProbe;
    EditText editDevice,editBlock,editNumber,editOffset,editIterations;
    CheckBox checkVerbose,checkProbe;
    static String testOptions=" ";
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.i2c);
        
        textDevice = (TextView)findViewById(R.id.textViewDevice);
        textBlock = (TextView)findViewById(R.id.textViewBlock);
        textNumber = (TextView)findViewById(R.id.textViewNumberBlock);
        textOffset = (TextView)findViewById(R.id.textViewOffset);
        textIterations = (TextView)findViewById(R.id.textViewIterations);
        textVerbose = (TextView)findViewById(R.id.textViewVerbose);
        textProbe = (TextView)findViewById(R.id.textViewProbe);
        editDevice = (EditText)findViewById(R.id.editTextDevice);
        editBlock = (EditText)findViewById(R.id.editTextBlock);
        editNumber = (EditText)findViewById(R.id.editTextNumberBlock);
        editOffset = (EditText)findViewById(R.id.editTextOffset);
        editIterations = (EditText)findViewById(R.id.editTextIterations);
        checkVerbose = (CheckBox)findViewById(R.id.checkBoxVerbose);
        checkProbe = (CheckBox)findViewById(R.id.checkBoxProbe);
        
        home = (Button)findViewById(R.id.home);
        back = (Button)findViewById(R.id.back);
        exit = (Button)findViewById(R.id.exit);
        help = (Button)findViewById(R.id.help);
        i2c = (Button)findViewById(R.id.i2c);
        log = (Button)findViewById(R.id.log);
        
            
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
        
        help.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(I2CActivity.this, OutputActivity.class);
		        i.putExtra("device", "I2CHELP");	//2 is I2C	
				startActivity(i);
			}
		});
        
        log.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				generateTestOptions();
				Intent i = new Intent(I2CActivity.this, OutputActivity.class);
		        i.putExtra("device", "I2CLOG");	//2 is I2C	
				startActivity(i);
			}
		});

   
        i2c.setOnClickListener(new OnClickListener() {
        	@Override
			public void onClick(View v) {
				generateTestOptions();
				Intent i = new Intent(I2CActivity.this, OutputActivity.class);
		        i.putExtra("device", "I2C");	//2 is I2C	
				startActivity(i);
			}
		});     
        
    }
    
    public String i2cScript()
    {
    	String i2cStr = "/data/kernel-tests/i2c-msm-changed.sh";
    	String tempOptions = getTestOptions();
    	setTestOptions();
    	i2cStr = i2cStr.concat(tempOptions);
    	return OutputActivity.displayOnScreen(i2cStr);
    }

   
    public String i2chelpScript()
    {
    	String i2cStr = "/data/kernel-tests/i2c-msm-changed.sh";
    	return OutputActivity.displayOnScreen(i2cStr);
    }
    
    public String i2clogScript()
    {
    	File fp = new File("/data/local/i2c-"+getDateTime()+".txt");
    	if(!fp.exists()){
    		try {
				fp.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	String i2cStr = "/data/kernel-tests/i2c-msm-changed.sh";
    	String tempOptions = getTestOptions();
    	setTestOptions();
    	i2cStr = i2cStr.concat(tempOptions);
    	OutputActivity.logtoFile(fp,i2cStr);
    	return fp.getName();
    }
    
    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        return dateFormat.format(cal.getTime());
    }
    
    public void generateTestOptions()
    {
    	if(!editDevice.getText().toString().equals(""))
			testOptions = testOptions.concat(" -D dev/i2c-" + editDevice.getText().toString());
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
		if(checkProbe.isChecked())
			testOptions = testOptions.concat(" -p");
	}
    
    public String getTestOptions()
    {	
    	return testOptions;
    }
    
    public void setTestOptions()
    {
    	testOptions = " ";
    }
}  
