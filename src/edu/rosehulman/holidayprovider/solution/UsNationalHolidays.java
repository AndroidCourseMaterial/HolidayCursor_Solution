package edu.rosehulman.holidayprovider.solution;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

public class UsNationalHolidays {
	
	private static Context mContext;
	private static final String TAG = "UsNationalHolidays";

	public static void populateUsNationalHolidays(Context context) {
		// Check if the content provider has holidays already
		mContext = context;
		ContentResolver cr = mContext.getContentResolver();
		Uri uri = HolidayProviderMetaData.HolidayTableMetaData.CONTENT_URI;

		Cursor cursor = cr.query(uri,
                null,
                null,
                null,
                null);
		
		if (cursor.getCount() > 0) {
			// Values already present in the table
			Toast.makeText(context, "Holidays present", Toast.LENGTH_SHORT).show();
			return;
		} else {
			Toast.makeText(context, "Loading default holidays", Toast.LENGTH_SHORT).show();
			loadHolidayTableWithDefaults();
		}
		cursor.close();
	}
	
	public static void resetDefaultHolidayTable() {
		Uri uri = HolidayProviderMetaData.HolidayTableMetaData.CONTENT_URI;
		ContentResolver cr = mContext.getContentResolver();
		int numItemsDeleted = cr.delete(uri, null, null);
		Log.d(TAG, numItemsDeleted + " items deleted.");
		loadHolidayTableWithDefaults();
	}

	private static final int CONSTANT_DATE = 1;
	private static final int VARIABLE_DATE = 0;
	
	private static void loadHolidayTableWithDefaults() {
		Uri uri = HolidayProviderMetaData.HolidayTableMetaData.CONTENT_URI;
		ContentResolver cr = mContext.getContentResolver();
		Log.d(TAG, "Add default holiday data");
		
		ContentValues cv = new ContentValues();
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.HOLIDAY, "Christmas");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.MONTH, "December");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.DAY_IN_MONTH, 25);
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.SAME_DAY_EVERY_YEAR, CONSTANT_DATE);
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.OCCURS_ON, "December 25th");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.APPROX_ORDINAL_DATE,359);
		cr.insert(uri, cv);
		
		cv = new ContentValues();
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.HOLIDAY, "Thanksgiving");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.MONTH, "November");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.SAME_DAY_EVERY_YEAR, VARIABLE_DATE);
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.OCCURS_ON, "Fourth Thursday in November");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.APPROX_ORDINAL_DATE, 329);
		cr.insert(uri, cv);
		
		cv = new ContentValues();
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.HOLIDAY, "Independence Day");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.MONTH, "July");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.DAY_IN_MONTH, 4);
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.SAME_DAY_EVERY_YEAR, CONSTANT_DATE);
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.OCCURS_ON, "The 4th of July");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.APPROX_ORDINAL_DATE, 185);
		cr.insert(uri, cv);
		
		cv = new ContentValues();
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.HOLIDAY, "Easter");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.MONTH, "March,April");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.SAME_DAY_EVERY_YEAR, VARIABLE_DATE);
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.OCCURS_ON, "First Sunday after the first full moon in Spring");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.APPROX_ORDINAL_DATE, 98);
		cr.insert(uri, cv);
		
		cv = new ContentValues();
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.HOLIDAY, "New Year's Day");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.MONTH, "January");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.DAY_IN_MONTH, 1);
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.SAME_DAY_EVERY_YEAR, CONSTANT_DATE);
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.OCCURS_ON, "January 1st");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.APPROX_ORDINAL_DATE, 1);
		cr.insert(uri, cv);
		
		cv = new ContentValues();
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.HOLIDAY, "Mother's Day");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.MONTH, "May");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.SAME_DAY_EVERY_YEAR, VARIABLE_DATE);
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.OCCURS_ON, "Second Sunday in May");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.APPROX_ORDINAL_DATE, 131);
		cr.insert(uri, cv);
		
		cv = new ContentValues();
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.HOLIDAY, "Halloween");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.MONTH, "October");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.DAY_IN_MONTH, 31);
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.SAME_DAY_EVERY_YEAR, CONSTANT_DATE);
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.OCCURS_ON, "October 31st");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.APPROX_ORDINAL_DATE, 304);
		cr.insert(uri, cv);
		
		cv = new ContentValues();
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.HOLIDAY, "Labor Day");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.MONTH, "September");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.SAME_DAY_EVERY_YEAR, VARIABLE_DATE);
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.OCCURS_ON, "First Monday in September");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.APPROX_ORDINAL_DATE, 247);
		cr.insert(uri, cv);
		
		cv = new ContentValues();
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.HOLIDAY, "Memorial Day");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.MONTH, "May");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.SAME_DAY_EVERY_YEAR, VARIABLE_DATE);
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.OCCURS_ON, "Last Monday in May");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.APPROX_ORDINAL_DATE, 148);
		cr.insert(uri, cv);
		
		cv = new ContentValues();
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.HOLIDAY, "Super Bowl Sunday");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.MONTH, "February");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.SAME_DAY_EVERY_YEAR, VARIABLE_DATE);
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.OCCURS_ON, "First Sunday in February");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.APPROX_ORDINAL_DATE, 35);
		cr.insert(uri, cv);
		
		cv = new ContentValues();
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.HOLIDAY, "Saint Patrick's Day");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.MONTH, "March");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.DAY_IN_MONTH, 17);
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.SAME_DAY_EVERY_YEAR, CONSTANT_DATE);
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.OCCURS_ON, "March 17th");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.APPROX_ORDINAL_DATE, 76);
		cr.insert(uri, cv);
		
		cv = new ContentValues();
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.HOLIDAY, "David Fisher's Birthday");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.MONTH, "August");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.DAY_IN_MONTH, 2);
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.SAME_DAY_EVERY_YEAR, CONSTANT_DATE);
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.OCCURS_ON, "August 2nd");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.APPROX_ORDINAL_DATE, 214);
		cr.insert(uri, cv);
		
		cv = new ContentValues();
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.HOLIDAY, "Father's Day");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.MONTH, "June");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.SAME_DAY_EVERY_YEAR, VARIABLE_DATE);
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.OCCURS_ON, "Third Sunday in June");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.APPROX_ORDINAL_DATE, 169);
		cr.insert(uri, cv);
		
		cv = new ContentValues();
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.HOLIDAY, "Martin Luther King Jr.");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.MONTH, "January");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.SAME_DAY_EVERY_YEAR, VARIABLE_DATE);
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.OCCURS_ON, "Third Monday in January");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.APPROX_ORDINAL_DATE, 18);
		cr.insert(uri, cv);
		
		cv = new ContentValues();
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.HOLIDAY, "Good Friday");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.MONTH, "March,April");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.SAME_DAY_EVERY_YEAR, VARIABLE_DATE);
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.OCCURS_ON, "Friday before Easter");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.APPROX_ORDINAL_DATE, 96);
		cr.insert(uri, cv);
		
		cv = new ContentValues();
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.HOLIDAY, "Cinco de Mayo");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.MONTH, "May");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.DAY_IN_MONTH, 5);
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.SAME_DAY_EVERY_YEAR, CONSTANT_DATE);
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.OCCURS_ON, "May 5th");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.APPROX_ORDINAL_DATE, 125);
		cr.insert(uri, cv);
		
		cv = new ContentValues();
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.HOLIDAY, "April Fools' Day");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.MONTH, "April");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.DAY_IN_MONTH, 1);
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.SAME_DAY_EVERY_YEAR, CONSTANT_DATE);
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.OCCURS_ON, "April 1st");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.APPROX_ORDINAL_DATE, 91);
		cr.insert(uri, cv);
		
		cv = new ContentValues();
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.HOLIDAY, "Valentine's Day");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.MONTH, "February");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.DAY_IN_MONTH, 14);
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.SAME_DAY_EVERY_YEAR, CONSTANT_DATE);
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.OCCURS_ON, "February 14th");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.APPROX_ORDINAL_DATE, 45);
		cr.insert(uri, cv);
		
		cv = new ContentValues();
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.HOLIDAY, "Groundhog Day");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.MONTH, "February");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.DAY_IN_MONTH, 2);
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.SAME_DAY_EVERY_YEAR, CONSTANT_DATE);
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.OCCURS_ON, "February 2nd");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.APPROX_ORDINAL_DATE, 33);
		cr.insert(uri, cv);
		
		cv = new ContentValues();
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.HOLIDAY, "Leap Day");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.MONTH, "February");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.DAY_IN_MONTH, 29);
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.SAME_DAY_EVERY_YEAR, VARIABLE_DATE);
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.OCCURS_ON, "February 29th on leap years");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.APPROX_ORDINAL_DATE, 60);
		cr.insert(uri, cv);
		
		cv = new ContentValues();
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.HOLIDAY, "Veterans Day");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.MONTH, "November");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.DAY_IN_MONTH, 11);
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.SAME_DAY_EVERY_YEAR, CONSTANT_DATE);
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.OCCURS_ON, "November 11th");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.APPROX_ORDINAL_DATE, 315);
		cr.insert(uri, cv);
		
		cv = new ContentValues();
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.HOLIDAY, "Columbus Day");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.MONTH, "October");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.SAME_DAY_EVERY_YEAR, VARIABLE_DATE);
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.OCCURS_ON, "Second Monday in October");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.APPROX_ORDINAL_DATE, 284);
		cr.insert(uri, cv);
		
		cv = new ContentValues();
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.HOLIDAY, "Flag Day");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.MONTH, "June");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.DAY_IN_MONTH, 14);
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.SAME_DAY_EVERY_YEAR, CONSTANT_DATE);
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.OCCURS_ON, "June 14th");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.APPROX_ORDINAL_DATE, 165);
		cr.insert(uri, cv);
		
		cv = new ContentValues();
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.HOLIDAY, "Inauguration Day");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.MONTH, "January");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.DAY_IN_MONTH, 20);
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.SAME_DAY_EVERY_YEAR, VARIABLE_DATE);
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.OCCURS_ON, "January 20th following Presidential election");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.APPROX_ORDINAL_DATE, 20);
		cr.insert(uri, cv);
		
		cv = new ContentValues();
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.HOLIDAY, "First day of Spring");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.MONTH, "March");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.DAY_IN_MONTH, 20);
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.SAME_DAY_EVERY_YEAR, VARIABLE_DATE);
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.OCCURS_ON, "March 20th sometimes the 21st");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.APPROX_ORDINAL_DATE, 79);
		cr.insert(uri, cv);
		
		cv = new ContentValues();
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.HOLIDAY, "First day of Summer");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.MONTH, "June");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.DAY_IN_MONTH, 21);
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.SAME_DAY_EVERY_YEAR, VARIABLE_DATE);
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.OCCURS_ON, "June 20th or 21st");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.APPROX_ORDINAL_DATE, 172);
		cr.insert(uri, cv);
		
		cv = new ContentValues();
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.HOLIDAY, "First day of Fall");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.MONTH, "September");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.DAY_IN_MONTH, 22);
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.SAME_DAY_EVERY_YEAR, VARIABLE_DATE);
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.OCCURS_ON, "September 22nd or 23rd");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.APPROX_ORDINAL_DATE, 265);
		cr.insert(uri, cv);
		
		cv = new ContentValues();
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.HOLIDAY, "First day of Winter");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.MONTH, "December");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.DAY_IN_MONTH, 21);
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.SAME_DAY_EVERY_YEAR, VARIABLE_DATE);
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.OCCURS_ON, "December 21st or 22nd");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.APPROX_ORDINAL_DATE, 355);
		cr.insert(uri, cv);
		
		cv = new ContentValues();
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.HOLIDAY, "Christmas Eve");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.MONTH, "December");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.DAY_IN_MONTH, 24);
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.SAME_DAY_EVERY_YEAR, CONSTANT_DATE);
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.OCCURS_ON, "December 24th evening");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.APPROX_ORDINAL_DATE, 358);
		cr.insert(uri, cv);
		
		cv = new ContentValues();
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.HOLIDAY, "New Year's Eve");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.MONTH, "December");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.DAY_IN_MONTH, 31);
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.SAME_DAY_EVERY_YEAR, CONSTANT_DATE);
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.OCCURS_ON, "December 31st evening");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.APPROX_ORDINAL_DATE, 365);
		cr.insert(uri, cv);
		
	}
}
