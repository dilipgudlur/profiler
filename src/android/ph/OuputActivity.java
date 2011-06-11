package android.ph;

import i2c.*;
import dma.*;
import spi.*;
import sd.*;
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
	        case 1:  output.setText( "Output = "+ SDActivity.exec_sdfulltest());  break;
	        case 2:  output.setText( "Output = "+ I2CActivity.exec_i2cfulltest()); break;
	        case 3:  output.setText( "Output = "+ SPIActivity.exec_spifulltest()); break;
	        case 4:  output.setText( "Output = "+ DMAActivity.exec_dmafulltest()); break;
	        
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
                startActivity(new Intent(OuputActivity.this, StartActivity.class));
            }
        });
	}	
}
