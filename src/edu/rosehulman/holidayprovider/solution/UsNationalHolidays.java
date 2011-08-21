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
	}

	private static final int CONSTANT_DATE = 1;
	private static final int VARIABLE_DATE = 0;
	
	private static void loadHolidayTableWithDefaults() {
		Uri uri = HolidayProviderMetaData.HolidayTableMetaData.CONTENT_URI;
		ContentResolver cr = mContext.getContentResolver();
		Uri insertedUri;
		
		ContentValues cv = new ContentValues();
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.HOLIDAY, "Christmas");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.MONTH, "December");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.DAY_IN_MONTH, 25);
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.SAME_DAY_EVERY_YEAR, CONSTANT_DATE);
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.OCCURS_ON, "December 25th");
		cr.insert(uri, cv);
		
		cv = new ContentValues();
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.HOLIDAY, "Thanksgiving");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.MONTH, "November");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.SAME_DAY_EVERY_YEAR, VARIABLE_DATE);
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.OCCURS_ON, "Fourth Thursday in November");
		cr.insert(uri, cv);
		
		cv = new ContentValues();
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.HOLIDAY, "Independence Day");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.MONTH, "July");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.DAY_IN_MONTH, 4);
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.SAME_DAY_EVERY_YEAR, CONSTANT_DATE);
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.OCCURS_ON, "The 4th of July");
		cr.insert(uri, cv);
		
		cv = new ContentValues();
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.HOLIDAY, "Easter");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.MONTH, "March,April");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.SAME_DAY_EVERY_YEAR, VARIABLE_DATE);
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.OCCURS_ON, "First Sunday after the first full moon in Spring");
		cr.insert(uri, cv);
		/*
		cv = new ContentValues();
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.HOLIDAY, "New Year's Day");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.MONTH, "");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.DAY_IN_MONTH, );
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.SAME_DAY_EVERY_YEAR, );
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.OCCURS_ON, "");
		cr.insert(uri, cv);
		
		cv = new ContentValues();
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.HOLIDAY, "Mother's Day");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.MONTH, "");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.DAY_IN_MONTH, );
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.SAME_DAY_EVERY_YEAR, );
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.OCCURS_ON, "");
		cr.insert(uri, cv);
		
		cv = new ContentValues();
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.HOLIDAY, "Halloween");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.MONTH, "");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.DAY_IN_MONTH, );
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.SAME_DAY_EVERY_YEAR, );
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.OCCURS_ON, "");
		cr.insert(uri, cv);
		
		cv = new ContentValues();
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.HOLIDAY, "Labor Day");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.MONTH, "");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.DAY_IN_MONTH, );
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.SAME_DAY_EVERY_YEAR, );
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.OCCURS_ON, "");
		cr.insert(uri, cv);
		
		cv = new ContentValues();
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.HOLIDAY, "Memorial Day");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.MONTH, "");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.DAY_IN_MONTH, );
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.SAME_DAY_EVERY_YEAR, );
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.OCCURS_ON, "");
		cr.insert(uri, cv);
		
		cv = new ContentValues();
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.HOLIDAY, "Super Bowl Sunday");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.MONTH, "");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.DAY_IN_MONTH, );
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.SAME_DAY_EVERY_YEAR, );
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.OCCURS_ON, "");
		cr.insert(uri, cv);
		
		cv = new ContentValues();
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.HOLIDAY, "Saint Patrick's Day");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.MONTH, "");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.DAY_IN_MONTH, );
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.SAME_DAY_EVERY_YEAR, );
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.OCCURS_ON, "");
		cr.insert(uri, cv);
		
		cv = new ContentValues();
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.HOLIDAY, "David Fisher's Birthday");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.MONTH, "");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.DAY_IN_MONTH, );
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.SAME_DAY_EVERY_YEAR, );
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.OCCURS_ON, "");
		cr.insert(uri, cv);
		
		cv = new ContentValues();
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.HOLIDAY, "Father's Day");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.MONTH, "");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.DAY_IN_MONTH, );
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.SAME_DAY_EVERY_YEAR, );
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.OCCURS_ON, "");
		cr.insert(uri, cv);
		
		cv = new ContentValues();
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.HOLIDAY, "Martin Luther King Jr.");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.MONTH, "");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.DAY_IN_MONTH, );
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.SAME_DAY_EVERY_YEAR, );
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.OCCURS_ON, "");
		cr.insert(uri, cv);
		
		cv = new ContentValues();
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.HOLIDAY, "Good Friday");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.MONTH, "");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.DAY_IN_MONTH, );
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.SAME_DAY_EVERY_YEAR, );
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.OCCURS_ON, "");
		cr.insert(uri, cv);
		
		cv = new ContentValues();
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.HOLIDAY, "Cinco de Mayo");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.MONTH, "");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.DAY_IN_MONTH, );
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.SAME_DAY_EVERY_YEAR, );
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.OCCURS_ON, "");
		cr.insert(uri, cv);
		
		cv = new ContentValues();
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.HOLIDAY, "April Fools' Day");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.MONTH, "");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.DAY_IN_MONTH, );
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.SAME_DAY_EVERY_YEAR, );
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.OCCURS_ON, "");
		cr.insert(uri, cv);
		
		cv = new ContentValues();
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.HOLIDAY, "Valentine's Day");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.MONTH, "");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.DAY_IN_MONTH, );
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.SAME_DAY_EVERY_YEAR, );
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.OCCURS_ON, "");
		cr.insert(uri, cv);
		
		cv = new ContentValues();
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.HOLIDAY, "Groundhog Day");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.MONTH, "");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.DAY_IN_MONTH, );
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.SAME_DAY_EVERY_YEAR, );
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.OCCURS_ON, "");
		cr.insert(uri, cv);
		
		cv = new ContentValues();
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.HOLIDAY, "Leap Day");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.MONTH, "");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.DAY_IN_MONTH, );
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.SAME_DAY_EVERY_YEAR, );
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.OCCURS_ON, "");
		cr.insert(uri, cv);
		
		cv = new ContentValues();
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.HOLIDAY, "Veterans Day");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.MONTH, "");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.DAY_IN_MONTH, );
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.SAME_DAY_EVERY_YEAR, );
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.OCCURS_ON, "");
		cr.insert(uri, cv);
		
		cv = new ContentValues();
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.HOLIDAY, "Columbus Day");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.MONTH, "");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.DAY_IN_MONTH, );
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.SAME_DAY_EVERY_YEAR, );
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.OCCURS_ON, "");
		cr.insert(uri, cv);
		
		cv = new ContentValues();
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.HOLIDAY, "Flag Day");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.MONTH, "");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.DAY_IN_MONTH, );
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.SAME_DAY_EVERY_YEAR, );
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.OCCURS_ON, "");
		cr.insert(uri, cv);
		
		cv = new ContentValues();
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.HOLIDAY, "Inauguration Day");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.MONTH, "");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.DAY_IN_MONTH, );
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.SAME_DAY_EVERY_YEAR, );
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.OCCURS_ON, "");
		cr.insert(uri, cv);
		
		cv = new ContentValues();
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.HOLIDAY, "First day of Spring");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.MONTH, "");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.DAY_IN_MONTH, );
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.SAME_DAY_EVERY_YEAR, );
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.OCCURS_ON, "");
		cr.insert(uri, cv);
		
		cv = new ContentValues();
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.HOLIDAY, "First day of Summer");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.MONTH, "");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.DAY_IN_MONTH, );
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.SAME_DAY_EVERY_YEAR, );
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.OCCURS_ON, "");
		cr.insert(uri, cv);
		
		cv = new ContentValues();
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.HOLIDAY, "");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.MONTH, "");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.DAY_IN_MONTH, );
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.SAME_DAY_EVERY_YEAR, );
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.OCCURS_ON, "");
		cr.insert(uri, cv);
		
		cv = new ContentValues();
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.HOLIDAY, "");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.MONTH, "");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.DAY_IN_MONTH, );
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.SAME_DAY_EVERY_YEAR, );
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.OCCURS_ON, "");
		cr.insert(uri, cv);
		
		cv = new ContentValues();
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.HOLIDAY, "");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.MONTH, "");
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.DAY_IN_MONTH, );
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.SAME_DAY_EVERY_YEAR, );
		cv.put(HolidayProviderMetaData.HolidayTableMetaData.OCCURS_ON, "");
		cr.insert(uri, cv);
		*/
	}
}
