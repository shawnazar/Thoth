package com.thoth;

import NavDrawerListAdapter.NavDrawerListAdapter;
import info.slidingmenu.model.NavDrawerItem;

import java.util.ArrayList;

import com.thoth.fragments.CommunityFragment;
import com.thoth.fragments.ExitFragment;
import com.thoth.fragments.FindPeopleFragment;
import com.thoth.fragments.HomeFragment;
import com.thoth.fragments.PhotosFragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends Activity 
{
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;

	// Navigation Drawer Title
	private CharSequence mDrawerTitle;

	// used to store app title
	private CharSequence mTitle;

	// slide menu items
	private String[] navMenuTitles;
	private TypedArray navMenuIcons;

	private ArrayList<NavDrawerItem> navDrawerItems;
	private NavDrawerListAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mTitle = mDrawerTitle = getTitle();

		// load slide menu items
		navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);

		// Navigation drawer icons from resources
		// Grabs the icons in the application storage.
		navMenuIcons = getResources().obtainTypedArray(R.array.nav_drawer_icons);

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout); // Drawer layout assigned; xml file.
		mDrawerList   = (ListView) findViewById(R.id.list_slidermenu);   // Drawer list assigned; xml file.

		// The array is created for the item list in the menu.
		navDrawerItems = new ArrayList<NavDrawerItem>();

		/* This section will create array segments for the sliding menu. */
		// Home - Item 0
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons.getResourceId(0, -1)));
		// CHANGE TITLE - Item 1
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons.getResourceId(1, -1)));
		// CHANGE TITLE - Item 2
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons.getResourceId(2, -1)));
		// CHANGE TITLE - Item 3
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons.getResourceId(3, -1)));
		// EXIT FOR NOW - Item 4
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[4], navMenuIcons.getResourceId(4, -1)));

		// Recycle the typed array
		navMenuIcons.recycle();

		//Event trigger for the sliding menu activation.
		mDrawerList.setOnItemClickListener(new SlideMenuClickListener());

		// setting the nav drawer list adapter
		adapter = new NavDrawerListAdapter(getApplicationContext(), navDrawerItems);
		mDrawerList.setAdapter(adapter);

		// Enabling Action Bar application icon and behaving it as toggle button.
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, // Navigation menu toggle icon.
				R.string.app_name, // Navigation drawer open - description for accessibility
				R.string.app_name // Navigation drawer close - description for accessibility
		) {
			public void onDrawerClosed(View view) 
			{
				getActionBar().setTitle(mTitle);
				// calling onPrepareOptionsMenu() to show action bar icons
				invalidateOptionsMenu();
			}

			public void onDrawerOpened(View drawerView) 
			{
				getActionBar().setTitle(mDrawerTitle);
				// calling onPrepareOptionsMenu() to hide action bar icons
				invalidateOptionsMenu();
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);

		if (savedInstanceState == null) {
			// on first time display view for first nav item
			displayView(0);
		}
	}

	/**
	 * Slide menu item click listener
	 * */
	private class SlideMenuClickListener implements
	ListView.OnItemClickListener 
	{
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) 
		{
			// display view for selected navigation drawer item
			displayView(position);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{
		// toggle navigation drawer on selecting action bar app icon/title
		if (mDrawerToggle.onOptionsItemSelected(item)) 
		{
			return true;
		}
		// Handle action bar actions click
		switch (item.getItemId()) 
		{
		case R.id.action_settings:
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	/**
	 * Called when invalidateOptionsMenu() is triggered
	 */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) 
	{
		// if nav drawer is opened, hide the action items
		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
		menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}

	/**
	 * Displaying fragment view for selected navigation drawer list items.
	 * 
	 * + Home
	 * + Snapshot
	 * + Help
	 * 
	 * */
	private void displayView(int position) 
	{
		// update the main content by replacing fragments
		Fragment fragment = null;
		
		//Menu item list - fragment creation
		switch (position) 
		{
		// + HOME
		case 0:
			fragment = new HomeFragment();
			break;
			
	    // + SNAPSHOT
		case 1:
			fragment = new FindPeopleFragment();
			break;
			
		// + IMAGE_LIB	
		case 2:
			fragment = new PhotosFragment();
			break;
			
		// + HELP	
		case 3:
			fragment = new CommunityFragment();
			break;
			
		// + EXIT	
		case 4:
			fragment = new ExitFragment();
			break;
			
		// + DEFAULTY
		default:
			break;
		}

		//Checks to see if the fragments are there on_creation.
		if (fragment != null) 
		{
			FragmentManager fragmentManager = getFragmentManager();
			
			fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit();

			// update selected item and title, then close the drawer
			mDrawerList.setItemChecked(position, true);
			mDrawerList.setSelection(position);
			setTitle(navMenuTitles[position]);
			mDrawerLayout.closeDrawer(mDrawerList);
		} 
		else
		{
			// Debug - Error in creating fragment.
			Log.e("MainActivity", "Error in creating fragment");
		}
		
	}//DISPLAYVIEW - END

	@Override
	public void setTitle(CharSequence title) 
	{
		mTitle = title;
		getActionBar().setTitle(mTitle);
	}

	/**
	 * When using the ActionBarDrawerToggle, you must call it during
	 * onPostCreate() and onConfigurationChanged()...
	 */
	@Override
	protected void onPostCreate(Bundle savedInstanceState) 
	{
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) 
	{
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggls
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

}
