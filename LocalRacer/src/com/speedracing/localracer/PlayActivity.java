package com.speedracing.localracer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.location.Geofence.Builder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class PlayActivity extends FragmentActivity implements
		ActionBar.TabListener {

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;

	public void myClickMethod(View v){
		
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_play);

		// Set up the action bar.
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

		// When swiping between different sections, select the corresponding
		// tab. We can also use ActionBar.Tab#select() to do this if we have
		// a reference to the Tab.
		mViewPager
				.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
						actionBar.setSelectedNavigationItem(position);
					}
				});

		// For each of the sections in the app, add a tab to the action bar.
		for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
			// Create a tab with text corresponding to the page title defined by
			// the adapter. Also specify this Activity object, which implements
			// the TabListener interface, as the callback (listener) for when
			// this tab is selected.
			actionBar.addTab(actionBar.newTab()
					.setText(mSectionsPagerAdapter.getPageTitle(i))
					.setTabListener(this));
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.play, menu);
		return true;
	}

	@Override
	public void onTabSelected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
		// When the given tab is selected, switch to the corresponding page in
		// the ViewPager.
		mViewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
		
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}
		public void startTime(View view){
			getItem(view.getId());
		}
		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a DummySectionFragment (defined as a static inner class
			// below) with the page number as its lone argument.
			Fragment fragment = new DummySectionFragment();
			Bundle args = new Bundle();
			args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, position + 1);
			fragment.setArguments(args);
			//fragment.onCreateView(getLayoutInflater(), mViewPager, args);
			return fragment;
		}

		@Override
		public int getCount() {
			// Show 3 total pages.
			return 3;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return getString(R.string.title_section1).toUpperCase(l);
			case 1:
				return getString(R.string.title_section2).toUpperCase(l);
			case 2:
				return getString(R.string.title_section3).toUpperCase(l);
			}
			return null;
		}
		
	}

	/**
	 * A dummy fragment representing a section of the app, but that simply
	 * displays dummy text.
	 */
	public static class DummySectionFragment extends Fragment implements OnClickListener, GooglePlayServicesClient{
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		public static final String ARG_SECTION_NUMBER = "section_number";
		
		public void onClick(View view){
			startTime(view);
			
		}
		public DummySectionFragment() {
		}

		public void startTime(View view){
			
			startTime = System.nanoTime();
			Log.e("Start time", String.valueOf(startTime));
			/*Builder geo1 = new Geofence.Builder();
			Builder geo2 = new Geofence.Builder();
			geo1.setCircularRegion(40.485340, -74.436923, 10);
			geo2.setCircularRegion(40.483659, -74.438678, 10);
			geo1.setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER);
			geo2.setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER);
			geo1.setExpirationDuration(Geofence.NEVER_EXPIRE);
			geo2.setExpirationDuration(Geofence.NEVER_EXPIRE);
			//LocationClient loc = new LocationClient(this.getActivity().getApplicationContext(), null, null);
			ArrayList<Geofence> gList = new ArrayList<Geofence>();
			gList.add(geo1.build());
			gList.add(geo2.build());
			//loc.addGeofences(gList, null, null);*/
			Log.e("got here", String.valueOf(System.nanoTime()));
			
		}
		
		public static long startTime;
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView= null;
			if(getArguments().getInt(ARG_SECTION_NUMBER)==1){
				rootView = inflater.inflate(R.layout.fragment_play_dummy_play,container, false);
				Button b = (Button) rootView.findViewById(R.id.button_start);
				b.setOnClickListener(this);
				//SimpleGeofence geof1 = new SimpleGeofence("start", 40.485340, -74.436923, 15, Long.MAX_VALUE, Geofence.GEOFENCE_TRANSITION_ENTER);
				
				//WebView webview = new WebView(getActivity());
				//webview.loadUrl("http://hackru.alexvallorosi.com/races/");
				
				TextView dummyTextView = (TextView) rootView
					.findViewById(R.id.section_label);
				
				/*Race r = new Race();
				r.email = "blah";
				r.time = "24903";
				
				Gson g = new Gson();
				String jsonStr = g.toJson(r);
				System.err.println(jsonStr);*/
			}
			else if(getArguments().getInt(ARG_SECTION_NUMBER)==2){
				rootView = inflater.inflate(R.layout.fragment_play_dummy_leaderboard,container, false);
				
				ArrayAdapter<String> adapter;
				Gson xx = new Gson();
				ArrayList<Race> races = new ArrayList<Race>();
				final StringBuilder total = new StringBuilder();
				
				Runnable runnable = new Runnable() {
					
					@Override
					public void run() {
						try {
							HttpGet get = new HttpGet("http://hackru.alexvallorosi.com/races");
							HttpClient client = new DefaultHttpClient();
							HttpResponse response = client.execute(get);
							HttpEntity entity = response.getEntity();
					        BufferedReader in = new BufferedReader(
					        new InputStreamReader(entity.getContent()));
			
					        String inputLine;
					        while ((inputLine = in.readLine()) != null)
					            total.append(inputLine +"\n");
					        in.close();
						}
						catch (Exception e) {
							e.printStackTrace();
						}
					}
				};
				Thread bg = new Thread(runnable);
				bg.start();
				try {
					bg.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				Type listType = new TypeToken<List<Race>>(){}.getType();
				ArrayList<Race> listItems= xx.fromJson(total.toString(), listType);
				for (Race r : listItems) {
					if(r.time=="d" || r.time=="")
						r.time="0";
				}
				Collections.sort(listItems);
				ArrayList<String> stringList = new ArrayList<String>();
				
				for (Race r : listItems) {
					stringList.add(r.email +  " "  +r.time);
				}
				System.out.print(Arrays.toString(listItems.toArray()));
				adapter=new ArrayAdapter<String>(
						getActivity(), android.R.layout.simple_list_item_1,
		            	stringList);
				final ListView test = (ListView) rootView;
				test.setClickable(true);
				test.setOnItemClickListener(new AdapterView.OnItemClickListener() {

				  @Override
				  public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

					  Log.e("Click", "item");
				    //Object o = test.getItemAtPosition(position);
				    /* write you handling code like...
				    String st = "sdcard/";
				    File f = new File(st+o.toString());
				    // do whatever u want to do with 'f' File object
				    */  
				  }
				});
				test.setAdapter(adapter);
			}
			else{
				rootView = inflater.inflate(R.layout.fragment_play_dummy, container, false);
				TextView dummyTextView = (TextView) rootView
					.findViewById(R.id.section_label);
				dummyTextView.setText(Integer.toString(getArguments().getInt(
						ARG_SECTION_NUMBER)));
				
			}
			
			return rootView;
		}
		@Override
		public void connect() {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void disconnect() {
			// TODO Auto-generated method stub
			
		}
		@Override
		public boolean isConnected() {
			// TODO Auto-generated method stub
			return false;
		}
		@Override
		public boolean isConnecting() {
			// TODO Auto-generated method stub
			return false;
		}
		@Override
		public boolean isConnectionCallbacksRegistered(ConnectionCallbacks arg0) {
			// TODO Auto-generated method stub
			return false;
		}
		@Override
		public boolean isConnectionFailedListenerRegistered(
				OnConnectionFailedListener arg0) {
			// TODO Auto-generated method stub
			return false;
		}
		@Override
		public void registerConnectionCallbacks(ConnectionCallbacks arg0) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void registerConnectionFailedListener(
				OnConnectionFailedListener arg0) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void unregisterConnectionCallbacks(ConnectionCallbacks arg0) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void unregisterConnectionFailedListener(
				OnConnectionFailedListener arg0) {
			// TODO Auto-generated method stub
			
		}
	}
	public static class Race implements Comparable<Race>{
		public String email = "";
		public String vehicle = "";
		public String map = "";
		public String time = "";
		public String _id = "";
	
		
		public int compareTo(Race x) {
			int numT = 0;
			int numX = 0;
			if(time!= "" && x.time!="" && time!= "d" && x.time!="d"){
				numT= Integer.parseInt(time);
				numX = Integer.parseInt(x.time);
			}
			if(numT < numX)
				return -1;
			else
				return 1;
		}


	}

}
