package i2c;

import java.io.IOException;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.ph.R;
import android.ph.StartActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class I2CActivity extends Activity{
	Runtime runtime = Runtime.getRuntime();
    Process proc = null;
    Button i2c1,exit,home,back;
        
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.i2c);
        i2c1 = (Button)findViewById(R.id.i2c1Button);
        home = (Button)findViewById(R.id.HomeI2C);
        back = (Button)findViewById(R.id.BackI2C);
        exit = (Button)findViewById(R.id.ExitI2C);
        
        exit.setOnClickListener(new View.OnClickListener() {
			@Override
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
                startActivity(new Intent(I2CActivity.this, StartActivity.class));
            }
        });
        
        i2c1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				exec_shell();
		       // finish();		        
			}
		});                
    }
        
    void exec_shell(){
        try {
        	
        	proc = Runtime.getRuntime().exec("/data/local/sd_test.sh");
        } catch (IOException e) {
				e.printStackTrace();
        }
    }
}
