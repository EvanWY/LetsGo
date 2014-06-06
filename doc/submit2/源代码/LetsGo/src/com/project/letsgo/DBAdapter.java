package com.project.letsgo;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBAdapter extends SQLiteOpenHelper{
	public static final String DB_NAME = "record.db";
	public static final int DB_VERSION = 1;
	public static final String TABLE_NAME = "plan";
	public static final String KEY_ID = "_id";
	public static final String KEY_STATE = "state";
	public static final String KEY_DATE = "date";
	public static final String KEY_START_TIME = "startTime";
	public static final String KEY_END_TIME = "endTime";
    public static final String KEY_DISTANCE = "distance";
	

	public DBAdapter(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String SQL_CREATE_TABLE = "create table " + TABLE_NAME
									+ " ("+KEY_ID+" integer primary key autoincrement,"
									+ " "+KEY_STATE+" integer,"
									+ " "+KEY_DATE+" TEXT,"
									+ " "+KEY_START_TIME+" TEXT,"
									+ " "+KEY_END_TIME+" TEXT,"
									+ " "+KEY_DISTANCE+" integer);";
		db.execSQL(SQL_CREATE_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}
	
	private ContentValues recordToValues(ContentValues values, Record record)
	{
		values.put(KEY_STATE, record.getState());
		values.put(KEY_DATE, record.getDate());
		values.put(KEY_START_TIME, record.getStartTime());
		values.put(KEY_END_TIME, record.getEndTime());
        values.put(KEY_DISTANCE, record.getDistance());
		return values;
	}
	
	private Record valuesToRecord(Record record, Cursor cursor)
	{
		record.setID(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
		record.setState(cursor.getInt(cursor.getColumnIndex(KEY_STATE)));
		record.setDate(cursor.getString(cursor.getColumnIndex(KEY_DATE)));
		record.setStartTime(cursor.getString(cursor.getColumnIndex(KEY_START_TIME)));
		record.setEndTime(cursor.getString(cursor.getColumnIndex(KEY_END_TIME)));
        record.setDistance(cursor.getInt(cursor.getColumnIndex(KEY_DISTANCE)));
		return record;
	}
	
	public long insert(Record record)
	{
		SQLiteDatabase db = getWritableDatabase();
		return db.insert(
							TABLE_NAME, 
							null, 
							recordToValues(new ContentValues(), record)
						);
	}
	
	public int deleteOneData(long id)
	{
		SQLiteDatabase db = getWritableDatabase();
		return db.delete(
							TABLE_NAME, 
							KEY_ID+"="+id, 
							null
						);
	}
	
	public int updateOneData(long id, Record record)
	{
		SQLiteDatabase db = getWritableDatabase();
		return db.update(
							TABLE_NAME, 
							recordToValues(new ContentValues(), record), 
							KEY_ID+"="+id, 
							null
						);
	}
	
	public ArrayList<Record> convertToRecord(Cursor cursor)
	{
        ArrayList<Record> records = new ArrayList<Record>();
		int resultCounts = cursor.getCount();
		if (resultCounts == 0 || !cursor.moveToFirst())
		{
			return records;
		}
		for (int i = 0; i < resultCounts; i++)
		{
			records.add( 0, valuesToRecord(new Record(), cursor) );
			cursor.moveToNext();
		}
		return records;
	}
	
	public Cursor getCursor()
	{
		return getReadableDatabase().query(TABLE_NAME, null, null, null, null, null, null);
	}
}
