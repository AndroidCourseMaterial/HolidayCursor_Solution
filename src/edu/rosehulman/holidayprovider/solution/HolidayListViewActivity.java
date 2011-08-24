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

/**
 * ListActivity that will display the Holidays in the HolidayProvider
 * 
 * @author Dave Fisher
 *
 */
public class HolidayListViewActivity extends ListActivity {

	/**
	 * Filter string for log messages
	 */
	public static final String TAG = "Holidays";
	
	// Counter for adding fake new holidays
	private int mCounter = 0;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.holiday_list_view_activity);

		UsNationalHolidays.populateUsNationalHolidays(this);  // Load default holiday data
		Uri uri = HolidayProviderMetaData.HolidayTableMetaData.CONTENT_URI;

		Cursor holidayCursor = this.managedQuery(uri, 
				null, //projection, 
				null, //selection, 
				null, //selectionArgs, 
				null); //sortOrder
		
		int viewResourceId = R.layout.row_with_7_items;
		String[] fromColumns = new String[] {
				HolidayProviderMetaData.HolidayTableMetaData._ID, 
				HolidayProviderMetaData.HolidayTableMetaData.HOLIDAY, 
				HolidayProviderMetaData.HolidayTableMetaData.MONTH, 
				HolidayProviderMetaData.HolidayTableMetaData.DAY_IN_MONTH, 
				HolidayProviderMetaData.HolidayTableMetaData.SAME_DATE_EVERY_YEAR,
				HolidayProviderMetaData.HolidayTableMetaData.OCCURS_ON, 
				HolidayProviderMetaData.HolidayTableMetaData.APPROX_ORDINAL_DATE
				};
		int[] toTextViews = new int[] { 
				R.id.textView1, 
				R.id.textView2, 
				R.id.textView3, 
				R.id.textView4, 
				R.id.textView5, 
				R.id.textView6, 
				R.id.textView7
				};
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
			cv.put(HolidayProviderMetaData.HolidayTableMetaData.SAME_DATE_EVERY_YEAR, 1);
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