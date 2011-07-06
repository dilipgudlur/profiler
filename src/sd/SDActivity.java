package sd;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.ph.OutputActivity;
import android.ph.R;
import android.ph.StartActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SDActivity extends Activity {
    /** Called when the activity is first created. */
    
    //Runtime runtime = Runtime.getRuntime();
    //Process proc = null;
    Button full,home,exit,back;
    int device;
        
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sd);
        /*mount = (Button)findViewById(R.id.mount);
        unmount = (Button)findViewById(R.id.unmount);
        remount = (Button)findViewById(R.id.remount);
        fileops = (Button)findViewById(R.id.fileops);*/
        full = (Button)findViewById(R.id.full);
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
                startActivity(new Intent(SDActivity.this, StartActivity.class));
            }
        });
        
       /* mount.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
						        
			}
		});
        
        unmount.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				     
			}
		});
       remount.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
					        
			}
		});
        fileops.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				     
			}
		});*/
        full.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(SDActivity.this, OutputActivity.class);
		        i.putExtra("device", "SD");			
				startActivity(i);
			}
		});
    }
    
    public String sdScript()
    {
    	String sdStr = "/data/kernel-tests/i2c-msm-changed.sh";
    	sdStr = sdStr.concat("");
    	return OutputActivity.displayOnScreen("");
    }
    
}