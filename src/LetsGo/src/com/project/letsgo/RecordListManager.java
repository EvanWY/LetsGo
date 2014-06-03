package com.project.letsgo;

import java.util.ArrayList;

import android.content.Context;

public class RecordListManager {
	
	private static RecordListManager instance = null;
	private DBAdapter myHelper;
	private Context context;
	
	public static RecordListManager getInstance(Context c) {
		if (instance == null)
			instance = new RecordListManager(c);
		return instance;
	}
	
	private ArrayList<Record> recordList;
	
	private RecordListManager(Context c) {
		//load from database...
		context = c;
		myHelper = new DBAdapter(context);
		recordList = myHelper.convertToRecord(myHelper.getCursor());
	}
	
	public String[] getStringArray() {
		if (recordList == null)
			return null;
		String[] strList = new String[recordList.size()];
		for (int i=0; i!=recordList.size(); i++) {
			strList[i] = recordList.get(i).getDate() + " (" + recordList.get(i).getStartTime() + " - " + recordList.get(i).getEndTime() + ")"; 
		}
		return strList;
	}
	
	public void addRecord(String date, String stime, String etime) {
		recordList.add(0, new Record(date, stime, etime));
		// update data base!
	}
	
	public Record removeRecord(int index) {
		return recordList.remove(index);
		// update data base too!
	}
	
	public void updateDatabase() {
		// todo...
		// just as the function name say!
		// store recordList into database
	}
}
