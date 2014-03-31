package com.infinityappsolutions.android.webterms;

import java.util.Locale;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.infinityappsolutions.android.webterms.fragments.TermFragment;
import com.infinityappsolutions.lib.beans.DataBean;
import com.infinityappsolutions.lib.webterms.bean.Term;

public class HomeActivity extends Activity implements
		ListView.OnItemClickListener {
	private DrawerLayout mDrawerLayout;
	private String[] mDrawerTitles;
	private ActionBarDrawerToggle mDrawerToggle;
	private ListView mDrawerList;
	private CharSequence mDrawerTitle;
	private CharSequence mTitle = "Home";
	private DataBean<Term> db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		mDrawerTitle = getApplicationContext().getString(R.string.drawer_title);
		mDrawerTitles = getResources().getStringArray(
				R.array.home_drawer_strings);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.home_layout);
		mDrawerList = (ListView) findViewById(R.id.drawer_listview);
		// Set the adapter for the list view
		mDrawerList.setAdapter(new ArrayAdapter<String>(this,
				R.layout.drawer_list_item, mDrawerTitles));
		mDrawerList.setOnItemClickListener(this);
		mDrawerToggle = new ActionBarDrawerToggle(this, /* host Activity */
		mDrawerLayout, /* DrawerLayout object */
		R.drawable.ic_drawer, /* nav drawer icon to replace 'Up' caret */
		R.string.drawer_open, /* "open drawer" description */
		R.string.drawer_closed /* "close drawer" description */
		) {

			/** Called when a drawer has settled in a completely closed state. */
			public void onDrawerClosed(View view) {
				super.onDrawerClosed(view);
				getActionBar().setTitle(mTitle);
			}

			/** Called when a drawer has settled in a completely open state. */
			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);
				getActionBar().setTitle(mDrawerTitle);
			}

			@Override
			public boolean onOptionsItemSelected(MenuItem item) {
				// Pass the event to ActionBarDrawerToggle, if it returns
				// true, then it has handled the app icon touch event
				// Handle your other action bar items...

				return super.onOptionsItemSelected(item);
			}
		};

		// Set the drawer toggle as the DrawerListener
		mDrawerLayout.setDrawerListener(mDrawerToggle);

		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);
		mDrawerLayout.setDrawerListener(mDrawerToggle);

		if (savedInstanceState == null) {
			selectItem(0);
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		db = (DataBean<Term>) getIntent().getExtras().get("DataBean");
		if (db != null) {
			System.out.println(db.getUserBean().getFirstname());
		}
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Pass the event to ActionBarDrawerToggle, if it returns
		// true, then it has handled the app icon touch event
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		// Handle your other action bar items...

		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onItemClick(AdapterView parent, View view, int position, long id) {
		selectItem(position);
	}

	private void selectItem(int position) {
		Fragment fragment = null;
		Bundle args = new Bundle();
		switch (position) {
		case 0:// Home
			fragment = new PlanetFragment();// update the main content by
											// replacing fragments
			args.putInt(PlanetFragment.ARG_TERM_NUMBER, position);
			break;
		case 1:// My Terms
			fragment = new TermFragment();// update the main content by
											// replacing fragments
			args.putInt(TermFragment.ARG_TERM_NUMBER, position);
			break;
		default:
			break;
		}
		// update the main content by replacing fragments
		fragment.setArguments(args);

		FragmentManager fragmentManager = getFragmentManager();
		fragmentManager.beginTransaction()
				.replace(R.id.content_frame, fragment).commit();

		// update selected item and title, then close the drawer
		mDrawerList.setItemChecked(position, true);
		setTitle(mDrawerTitles[position]);
		mDrawerLayout.closeDrawer(mDrawerList);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}

	/**
	 * Fragment that appears in the "content_frame", shows a planet
	 */
	public static class PlanetFragment extends Fragment {
		public static final String ARG_TERM_NUMBER = "planet_number";

		public PlanetFragment() {
			// Empty constructor required for fragment subclasses
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_terms,
					container, false);
			int i = getArguments().getInt(ARG_TERM_NUMBER);
			String planet = getResources().getStringArray(
					R.array.home_drawer_strings)[i];
			int imageId = getResources().getIdentifier(
					planet.toLowerCase(Locale.getDefault()), "drawable",
					getActivity().getPackageName());
			getActivity().setTitle(planet);
			return rootView;
		}
	}

}
