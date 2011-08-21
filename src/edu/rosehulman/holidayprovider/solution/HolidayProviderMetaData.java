package edu.rosehulman.holidayprovider.solution;

import android.net.Uri;
import android.provider.BaseColumns;

public class HolidayProviderMetaData {
	public static final String AUTHORITY = "edu.rosehulman.provider.Holidays";
	public static final String DATABASE_NAME = "holidays.db";
	public static final int DATABASE_VERSION = 1;
	
	// Private constructor to avoid instantiation
	private HolidayProviderMetaData() {}
	
	public static final class HolidayTableMetaData implements BaseColumns
	{
		// Private constructor to avoid instantiation
		private HolidayTableMetaData() {}
		public static final String TABLE_NAME = "us_national_holidays";

		public static final String DEFAULT_SORT_ORDER = _ID + " DESC";

		//uri and mime type definitions
		public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + TABLE_NAME);
		public static final Uri CONTENT_ID_URI_BASE = Uri.parse("content://" + AUTHORITY + "/" + TABLE_NAME +"/");
		public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.rosehulman.holiday";
		public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.rosehulman.holiday";
		
		// Columns
		public static final String HOLIDAY = "holiday";
		public static final String MONTH = "month";
		public static final String DAY_IN_MONTH = "day_in_month";
		public static final String SAME_DAY_EVERY_YEAR = "same_day_every_year";
		public static final String OCCURS_ON = "occurs_on";	
	}
}
