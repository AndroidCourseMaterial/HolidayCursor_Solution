package edu.rosehulman.holidayprovider.solution;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Content provider meta data to define columns and other constants for the HolidayProvider
 * 
 * @author Dave Fisher
 *
 */
public class HolidayProviderMetaData {
	public static final String AUTHORITY = "edu.rosehulman.provider.Holidays";
	public static final String DATABASE_NAME = "holidays.db";
	public static final int DATABASE_VERSION = 1;

	private HolidayProviderMetaData() {}  // Private constructor to avoid instantiation
	
	/**
	 * Meta data for the us_national_holidays table in the holidays database
	 * Note: Super class BaseColumns includes two constants 
	 *   public static final String _ID = "_id";
	 *   public static final String _COUNT = "_count";
	 */
	public static final class HolidayTableMetaData implements BaseColumns
	{
		private HolidayTableMetaData() {} // Private constructor to avoid instantiation
		
		/**
		 * Arbitrary name for this table in the database based on expected usage
		 */
		public static final String TABLE_NAME = "us_national_holidays";

		/**
		 * To access the HolidayProvider content provider use this uri (for many holidays)
		 * content://edu.rosehulman.provider.Holidays/us_national_holidays
		 */
		public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + TABLE_NAME);
		
		/**
		 * To access the HolidayProvider content provider use this uri (for a single holiday)
		 * content://edu.rosehulman.provider.Holidays/us_national_holidays/#
		 */
		public static final Uri CONTENT_ID_URI_BASE = Uri.parse("content://" + AUTHORITY + "/" + TABLE_NAME +"/");
		
		/** Descriptive mime type name for a directory of holidays */
		public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.rosehulman.holiday";
		/** Descriptive mime type name for a single holiday */
		public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.rosehulman.holiday";
		
		// Columns: _id (via BaseColumns) HOLIDAY  MONTH  DAY_IN_MONTH  SAME_DAY_EVERY_YEAR  OCCURS_ON  APPROX_ORDINAL_DATE
		
		/**
		 * Description: Name of the holiday
		 * Type:        String - Required
		 * Example:     Christmas 
		 */
		public static final String HOLIDAY = "holiday";
		
		/**
		 * Description: Month(s) the holiday occurs in
		 * Type:        String - Optional
		 * Example:     December (for Christmas) 
		 */
		public static final String MONTH = "month";
		
		/**
		 * Description: Day in the month for the holiday (if always the same day)
		 * Type:        Integer - Optional
		 * Example:     25 (for Christmas)
		 */
		public static final String DAY_IN_MONTH = "day_in_month";
		
		/**
		 * Description: Does this holiday remain the same date every year
		 * Type:        Integer (0 for false, 1 for true)  - Required
		 * Example:     1 (for Christmas)   0 (for Easter)
		 */
		public static final String SAME_DATE_EVERY_YEAR = "same_date_every_year";
		
		/**
		 * Description: Detailed explanation of when the holiday occurs
		 * Type:        String - Required
		 * Example:     Second Sunday in May  (for Mother's Day)
		 */
		public static final String OCCURS_ON = "occurs_on";	

		/**
		 * Description: Numeric value from 1 to 365 of when the holiday typically occurs  
		 * Type:    	Integer - Required
		 * Example:     1 (for New Year's Day)   365 (for New Year's Eve)
		 */
		public static final String APPROX_ORDINAL_DATE = "approx_ordinal_date";	

		/**
		 *  If no sorting order is defined, the default will be based on the ordinal date
		 */
		public static final String DEFAULT_SORT_ORDER = APPROX_ORDINAL_DATE + " ASC";
	}
}
