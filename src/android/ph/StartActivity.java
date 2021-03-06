package android.ph;

import dma.DMAActivity;
import i2c.I2CActivity;
import sd.SDActivity;
import spi.SPIActivity;
import android.app.Activity;
import android.os.Bundle;
import android.ph.R;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.content.Intent;

public class StartActivity extends Activity {
	Button sd,i2c,spi,dma,back,exit;
	public enum Device
	{
	    SD,I2C,I2CHELP,I2CLOG,SPI,DMA; 
	}
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        sd = (Button)findViewById(R.id.sdButton);
        i2c = (Button)findViewById(R.id.i2cButton);
        spi = (Button)findViewById(R.id.spiButton);
        dma = (Button)findViewById(R.id.dmaButton);
        back = (Button)findViewById(R.id.btnBack);
        exit = (Button)findViewById(R.id.btnExit);
        
        //exec_entry(); //call entry script for basic busybox installation and other 
        				//steps
        
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
        
        sd.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(StartActivity.this, SDActivity.class);
		        startActivity(i);
		        //finish();
		       		        
			}
		}); 
        i2c.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(StartActivity.this, I2CActivity.class);
		        startActivity(i);
		        //finish();
		       		        
			}
		}); 
        spi.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(StartActivity.this, SPIActivity.class);
		        startActivity(i);
		        //finish();
		       		        
			}
		});
        
        dma.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(StartActivity.this, DMAActivity.class);
		        startActivity(i);
		        //finish();
		       		        
			}
		});       
    }

	@SuppressWarnings("unused")
	private void exec_entry() {
		/*try {
        	//Process p1 = Runtime.getRuntime().exec("/system/bin/chmod 755 /data/local/entry.sh");
			//Process p2 = Runtime.getRuntime().exec("/data/local/entry.sh");
        } catch (IOException e) {
	
		e.printStackTrace();
        }*/	
	}	
}