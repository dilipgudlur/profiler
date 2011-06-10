package i2c;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.ph.R;
import android.ph.OuputActivity;
import android.ph.StartActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class I2CActivity extends Activity{
	Runtime runtime = Runtime.getRuntime();
    Process proc = null;
    Button i2c,exit,home,back;
        
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.i2c);
        i2c = (Button)findViewById(R.id.i2c);
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
				Intent i = new Intent(I2CActivity.this, OuputActivity.class);
		        i.putExtra("device",2);	//2 is I2C	
				startActivity(i);	        
			}
		});                
    }
}  
