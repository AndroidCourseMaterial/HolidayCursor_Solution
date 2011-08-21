package edu.rosehulman.holidayprovider.solution;

import android.app.ListActivity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SimpleCursorAdapter;

public class HolidayListViewActivity extends ListActivity {

	/**
	 * TAG for debug log messages
	 */
	public static final String TAG = "Holidays";
	
	private int mCounter = 0;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.holiday_list_view_activity);

		UsNationalHolidays.populateUsNationalHolidays(this);
		
		ContentResolver cr = this.getContentResolver();
		Uri uri = HolidayProviderMetaData.HolidayTableMetaData.CONTENT_URI;

		Cursor holidayCursor = this.managedQuery(uri, 
				null, //projection, 
				null, //selection, 
				null, //selectionArgs, 
				null); //sortOrder
		
		int viewResourceId = R.layout.row_with_2_items;

		String[] fromColumns = new String[] { 
				HolidayProviderMetaData.HolidayTableMetaData.HOLIDAY, 
				//HolidayProviderMetaData.HolidayTableMetaData.MONTH, 
				HolidayProviderMetaData.HolidayTableMetaData.OCCURS_ON};
		int[] toTextViews = new int[] { R.id.textView1, R.id.textView2};
		SimpleCursorAdapter holidayAdapter = new SimpleCursorAdapter(this, viewResourceId, 
				holidayCursor, fromColumns, toTextViews);
		setListAdapter(holidayAdapter);  // Magic function for a ListActivity to set the adapter
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.holiday_list_view_activity_options_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);
		mCounter++;
		switch (item.getItemId()) {
		case R.id.menu_add:
			Uri uri = HolidayProviderMetaData.HolidayTableMetaData.CONTENT_URI;
			ContentResolver cr = this.getContentResolver();
			ContentValues cv = new ContentValues();
			cv.put(HolidayProviderMetaData.HolidayTableMetaData.HOLIDAY, "Holiday " + mCounter);
			cv.put(HolidayProviderMetaData.HolidayTableMetaData.MONTH, "January");
			cv.put(HolidayProviderMetaData.HolidayTableMetaData.DAY_IN_MONTH, mCounter);
			cv.put(HolidayProviderMetaData.HolidayTableMetaData.SAME_DAY_EVERY_YEAR, 1);
			cv.put(HolidayProviderMetaData.HolidayTableMetaData.OCCURS_ON, "January " + mCounter);
			cv.put(HolidayProviderMetaData.HolidayTableMetaData.APPROX_ORDINAL_DATE, mCounter);
			cr.insert(uri, cv);
			return true;
		case R.id.menu_restore_defaults:
			UsNationalHolidays.resetDefaultHolidayTable();
			return true;
		}
		return false;
	}
}