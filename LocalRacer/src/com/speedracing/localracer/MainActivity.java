package com.speedracing.localracer;

import java.util.List;

import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Menu;
import android.widget.ProgressBar;

public class MainActivity extends Activity {

	 private static final int PROGRESS = 0x1;

     private ProgressBar mProgress;
     private int mProgressStatus = 0;

     private Handler mHandler = new Handler();
     
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*mProgress = (ProgressBar) findViewById(R.id.progress_bar);

        // Start lengthy operation in a background thread
        new Thread(new Runnable() {
            public void run() {
                while (mProgressStatus < 100) {
                	int x =1;
                    mProgressStatus = doWork(x);

                    // Update the progress bar
                    mHandler.post(new Runnable() {
                        public void run() {
                            mProgress.setProgress(mProgressStatus);
                        }
                    });
                    x++;
                    Log.e("error", "test");
                }
                Log.e("error", "pass");
                
            }

			private int doWork(int x) {
				if(x==1){
					if(isOnline())
						return 50;
				}
				else if(x==2){
					if(hasGPSDevice())
						return 50;
				}
				return 0;
			}
			public boolean isOnline() {
			    ConnectivityManager cm =
			        (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
			    NetworkInfo netInfo = cm.getActiveNetworkInfo();
			    if (netInfo != null && netInfo.isConnectedOrConnecting()) {
			        return true;
			    }
			    return false;
			}
			public boolean hasGPSDevice()
		    {
				final LocationManager mgr = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
				if ( mgr == null ) 
					return false;
				final List<String> providers = mgr.getAllProviders();
				if ( providers == null )
					return false;
		    	return providers.contains(LocationManager.GPS_PROVIDER);
		    }

        }).start();*/
        
        
    }
    


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
