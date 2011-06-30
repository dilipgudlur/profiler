package sd;

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
    
    public static String exec_sdfulltest(){
		try {
		    // Executes the command.
			@SuppressWarnings("unused")
			//String[] str = {"export TEST_ENV_SETUP=/data/kernel-tests/test_env_setup.sh","export PATH=$PATH:/data/busybox"};//"/system/bin/chmod 755 /data/kernel-tests/sd_test.sh"};
			//Process p1 = Runtime.getRuntime().exec("/system/bin/chmod 755 /data/local/newentry.sh");
			Process p = Runtime.getRuntime().exec("data/busybox/su");
			Process process = Runtime.getRuntime().exec("/data/busybox/chmod 777 dev/i2c-3");
		    
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