package sd;

import java.io.IOException;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.ph.R;
import android.ph.StartActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SDActivity extends Activity {
    /** Called when the activity is first created. */
    
    Runtime runtime = Runtime.getRuntime();
    Process proc = null;
    Button mount,unmount,remount,fileops,full,home,exit,back;
        
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sd);
        mount = (Button)findViewById(R.id.mount);
        unmount = (Button)findViewById(R.id.unmount);
        remount = (Button)findViewById(R.id.remount);
        fileops = (Button)findViewById(R.id.fileops);
        full = (Button)findViewById(R.id.full);
        home = (Button)findViewById(R.id.HomeSD);
        back = (Button)findViewById(R.id.BackSD);
        exit = (Button)findViewById(R.id.ExitSD);
        
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
                startActivity(new Intent(SDActivity.this, StartActivity.class));
            }
        });
        
        mount.setOnClickListener(new OnClickListener() {
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
		});
        full.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				exec_sdfulltest();		        
			}
		});
    }
        
    void exec_sdfulltest(){
        try {
        	
        	proc = Runtime.getRuntime().exec("/data/kernel-tests/sd_test.sh");
        } catch (IOException e) {
	
		e.printStackTrace();
        }
    }
}