package android.ph;

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
import android.widget.TextView;

public class OutputActivity extends Activity {
	Runtime runtime = Runtime.getRuntime();
    Process proc = null;
    TextView output;
    Button home,back,exit;
    String errorString;
    String device;
    
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.output);
        output = (TextView)this.findViewById(R.id.output);
        output.setMovementMethod(new ScrollingMovementMethod());
        home = (Button)findViewById(R.id.home);
        back = (Button)findViewById(R.id.back);
        exit = (Button)findViewById(R.id.exit);
        device = getIntent().getStringExtra("device");     
        
        switch (StartActivity.Device.valueOf(device)) {
	        case SD:  output.setText( "Output = "+ SDActivity.exec_sdfulltest());  break;
	        case I2C: output.setText( "Output = "+ I2CActivity.exec_i2cfulltest()); break;
	        case SPI: output.setText( "Output = "+ SPIActivity.exec_spifulltest()); break;
	        case DMA: output.setText( "Output = "+ DMAActivity.exec_dmafulltest()); break;
	        
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
}
