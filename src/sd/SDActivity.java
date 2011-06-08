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
    Button sd1,home,exit,back;
        
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sd);
        sd1 = (Button)findViewById(R.id.sd1Button);
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
        
        sd1.setOnClickListener(new OnClickListener() {
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
		// TODO Auto-generated catch block
		e.printStackTrace();
        }
    }
}