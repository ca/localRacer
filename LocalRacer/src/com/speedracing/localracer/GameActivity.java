package com.speedracing.localracer;

import java.util.ArrayList;
import java.util.List;

import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.Geofence.Builder;
import com.google.android.gms.location.LocationClient;

import android.os.Bundle;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.view.Menu;

public class GameActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);	
		Builder geo1 = new Geofence.Builder();
		Builder geo2 = new Geofence.Builder();
		geo1.setCircularRegion(40.485340, -74.436923, 10);
		geo2.setCircularRegion(40.483659, -74.438678, 10);
		geo1.setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER);
		geo2.setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER);
		geo1.setExpirationDuration(Geofence.NEVER_EXPIRE);
		geo2.setExpirationDuration(Geofence.NEVER_EXPIRE);
		LocationClient loc = new LocationClient(null, null, null);
		ArrayList<Geofence> gList = new ArrayList<Geofence>();
		gList.add(geo1.build());
		gList.add(geo2.build());
		loc.addGeofences(gList, null, null);
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game, menu);
		return true;
	}

}
