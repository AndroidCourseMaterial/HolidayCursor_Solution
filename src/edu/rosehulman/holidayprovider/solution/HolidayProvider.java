package edu.rosehulman.holidayprovider.solution;

import java.util.HashMap;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

public class HolidayProvider extends ContentProvider 
{
	public static final String TAG = "HolidayProvider";
	public static HashMap<String, String> sHolidaysProjectionMap;
	static 
	{
		sHolidaysProjectionMap = new HashMap<String, String>();
		sHolidaysProjectionMap.put(HolidayProviderMetaData.HolidayTableMetaData._ID, HolidayProviderMetaData.HolidayTableMetaData._ID);
		sHolidaysProjectionMap.put(HolidayProviderMetaData.HolidayTableMetaData.HOLIDAY, HolidayProviderMetaData.HolidayTableMetaData.HOLIDAY);
		sHolidaysProjectionMap.put(HolidayProviderMetaData.HolidayTableMetaData.MONTH, HolidayProviderMetaData.HolidayTableMetaData.MONTH);
		sHolidaysProjectionMap.put(HolidayProviderMetaData.HolidayTableMetaData.DAY_IN_MONTH, HolidayProviderMetaData.HolidayTableMetaData.DAY_IN_MONTH);
		sHolidaysProjectionMap.put(HolidayProviderMetaData.HolidayTableMetaData.SAME_DAY_EVERY_YEAR, HolidayProviderMetaData.HolidayTableMetaData.SAME_DAY_EVERY_YEAR);
		sHolidaysProjectionMap.put(HolidayProviderMetaData.HolidayTableMetaData.OCCURS_ON, HolidayProviderMetaData.HolidayTableMetaData.OCCURS_ON);
		sHolidaysProjectionMap.put(HolidayProviderMetaData.HolidayTableMetaData.APPROX_ORDINAL_DATE, HolidayProviderMetaData.HolidayTableMetaData.APPROX_ORDINAL_DATE);
	}
	private static final UriMatcher sUriMatcher;
	private static final int INCOMING_URI_INDICATOR_HOLIDAYS = 1;
	private static final int INCOMING_URI_INDICATOR_SINGLE_HOLIDAY = 2;
	static {
		sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		sUriMatcher.addURI(HolidayProviderMetaData.AUTHORITY, HolidayProviderMetaData.HolidayTableMetaData.TABLE_NAME, INCOMING_URI_INDICATOR_HOLIDAYS);
		sUriMatcher.addURI(HolidayProviderMetaData.AUTHORITY, HolidayProviderMetaData.HolidayTableMetaData.TABLE_NAME+"/#", INCOMING_URI_INDICATOR_SINGLE_HOLIDAY);
	}

	private static class HolidayDbOpenHelper extends SQLiteOpenHelper {
		
		private static String DROP_STATEMENT = "DROP TABLE IF EXISTS " + HolidayProviderMetaData.HolidayTableMetaData.TABLE_NAME;
		private static String CREATE_STATEMENT;
		static {
			StringBuilder s = new StringBuilder();
			s.append("CREATE TABLE ");
			s.append(HolidayProviderMetaData.HolidayTableMetaData.TABLE_NAME);
			s.append(" (");
			s.append(HolidayProviderMetaData.HolidayTableMetaData._ID);
			s.append(" INTEGER PRIMARY KEY AUTOINCREMENT, ");
			s.append(HolidayProviderMetaData.HolidayTableMetaData.HOLIDAY);
			s.append(" TEXT, ");
			s.append(HolidayProviderMetaData.HolidayTableMetaData.MONTH);
			s.append(" TEXT, ");
			s.append(HolidayProviderMetaData.HolidayTableMetaData.DAY_IN_MONTH);
			s.append(" INTEGER, ");
			s.append(HolidayProviderMetaData.HolidayTableMetaData.SAME_DAY_EVERY_YEAR);
			s.append(" INTEGER, ");
			s.append(HolidayProviderMetaData.HolidayTableMetaData.OCCURS_ON);
			s.append(" TEXT, ");
			s.append(HolidayProviderMetaData.HolidayTableMetaData.APPROX_ORDINAL_DATE);
			s.append(" INTEGER)");
			CREATE_STATEMENT = s.toString();
		}
		
		public HolidayDbOpenHelper(Context context) {
			super(context, HolidayProviderMetaData.DATABASE_NAME, null, HolidayProviderMetaData.DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(CREATE_STATEMENT);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL(DROP_STATEMENT);
			onCreate(db);
		}
	}
	
	private HolidayDbOpenHelper mOpenHelper;
	
	@Override
	public boolean onCreate() {
		mOpenHelper = new HolidayDbOpenHelper(getContext());
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {

		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

		switch (sUriMatcher.match(uri)) {
		case INCOMING_URI_INDICATOR_HOLIDAYS:
			qb.setTables(HolidayProviderMetaData.HolidayTableMetaData.TABLE_NAME);
			qb.setProjectionMap(sHolidaysProjectionMap);
			break;
		case INCOMING_URI_INDICATOR_SINGLE_HOLIDAY:
			qb.setTables(HolidayProviderMetaData.HolidayTableMetaData.TABLE_NAME);
			qb.setProjectionMap(sHolidaysProjectionMap);
			qb.appendWhere(HolidayProviderMetaData.HolidayTableMetaData._ID + "=" 
					+ uri.getPathSegments().get(1));
			break;

		default:
			throw new IllegalArgumentException("Unknown URI " + uri);
		}

		// If no sort order is specified use the default
		String orderBy;
		if (TextUtils.isEmpty(sortOrder)) {
			orderBy = HolidayProviderMetaData.HolidayTableMetaData.DEFAULT_SORT_ORDER;
		} else {
			orderBy = sortOrder;
		}

		// Get the database and run the query
		SQLiteDatabase db = mOpenHelper.getReadableDatabase();
		//SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		Cursor c = qb.query(db, projection, selection, selectionArgs, null, null, orderBy);

		// Tell the cursor what uri to watch, 
		// so it knows WHEN_DESCRIPTION its source data changes
		c.setNotificationUri(getContext().getContentResolver(), uri);
		return c;
	}

	@Override
	public String getType(Uri uri) {
		switch (sUriMatcher.match(uri)) {
		case INCOMING_URI_INDICATOR_HOLIDAYS:
			return HolidayProviderMetaData.HolidayTableMetaData.CONTENT_TYPE;
		case INCOMING_URI_INDICATOR_SINGLE_HOLIDAY:
			return HolidayProviderMetaData.HolidayTableMetaData.CONTENT_ITEM_TYPE;
		default:
			throw new IllegalArgumentException("Unknown URI " + uri);
		}
	}

	private static final String DEFAULT_MONTH = "January";
	private static final String DEFAULT_DAY_IN_MONTH = "0";
		
	@Override
	public Uri insert(Uri uri, ContentValues initialValues) {
		// Validate the requested uri
		if (sUriMatcher.match(uri) != INCOMING_URI_INDICATOR_HOLIDAYS) {
			throw new IllegalArgumentException("Unknown URI " + uri);
		}

		ContentValues values;
		if (initialValues != null) {
			values = new ContentValues(initialValues);
		} else {
			values = new ContentValues();
		}

		// Make sure that the fields are all set
		if (values.containsKey(HolidayProviderMetaData.HolidayTableMetaData.HOLIDAY) == false) {
			throw new SQLException("Failed to insert row because HOLIDAY is needed " + uri);
		}
		if (values.containsKey(HolidayProviderMetaData.HolidayTableMetaData.MONTH) == false) {
			values.put(HolidayProviderMetaData.HolidayTableMetaData.MONTH, DEFAULT_MONTH);
		}
		if (values.containsKey(HolidayProviderMetaData.HolidayTableMetaData.DAY_IN_MONTH) == false) {
			values.put(HolidayProviderMetaData.HolidayTableMetaData.DAY_IN_MONTH, DEFAULT_DAY_IN_MONTH);
		}
		if (values.containsKey(HolidayProviderMetaData.HolidayTableMetaData.SAME_DAY_EVERY_YEAR) == false) {
			throw new SQLException("Failed to insert row because SAME_DAY_EVERY_YEAR is needed " + uri);
		}
		if (values.containsKey(HolidayProviderMetaData.HolidayTableMetaData.OCCURS_ON) == false) {
			throw new SQLException("Failed to insert row because OCCURS_ON is needed " + uri);
		}
		if (values.containsKey(HolidayProviderMetaData.HolidayTableMetaData.APPROX_ORDINAL_DATE) == false) {
			throw new SQLException("Failed to insert row because APPROX_ORDINAL_DATE is needed " + uri);
		}

		SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		long rowId = db.insert(HolidayProviderMetaData.HolidayTableMetaData.TABLE_NAME, null, values);
		if (rowId > 0) {
			Uri insertedHolidayUri = ContentUris.withAppendedId(HolidayProviderMetaData.HolidayTableMetaData.CONTENT_URI, rowId);
			getContext().getContentResolver().notifyChange(insertedHolidayUri, null);
			return insertedHolidayUri;
		}
		throw new SQLException("Failed to insert row into " + uri);
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		int count;
		switch (sUriMatcher.match(uri)) {
		case INCOMING_URI_INDICATOR_HOLIDAYS:
			count = db.delete(HolidayProviderMetaData.HolidayTableMetaData.TABLE_NAME, selection, selectionArgs);
			break;
		case INCOMING_URI_INDICATOR_SINGLE_HOLIDAY:
			String rowId = uri.getPathSegments().get(1);
			count = db.delete(HolidayProviderMetaData.HolidayTableMetaData.TABLE_NAME, 
					HolidayProviderMetaData.HolidayTableMetaData._ID + "=" + rowId
					+ (!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : ""), 
					selectionArgs);
			break;
		default:
			throw new IllegalArgumentException("Unknown URI " + uri);
		}

		getContext().getContentResolver().notifyChange(uri, null);
		return count;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		int count;
		switch (sUriMatcher.match(uri)) {
		case INCOMING_URI_INDICATOR_HOLIDAYS:
			count = db.update(HolidayProviderMetaData.HolidayTableMetaData.TABLE_NAME, 
					values, selection, selectionArgs);
			break;
		case INCOMING_URI_INDICATOR_SINGLE_HOLIDAY:
			String rowId = uri.getPathSegments().get(1);
			count = db.update(HolidayProviderMetaData.HolidayTableMetaData.TABLE_NAME, 
					values, HolidayProviderMetaData.HolidayTableMetaData._ID + "=" + rowId
					+ (!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : ""), 
					selectionArgs);
			break;
		default:
			throw new IllegalArgumentException("Unknown URI " + uri);
		}
		getContext().getContentResolver().notifyChange(uri, null);
		return count;
	}
}
