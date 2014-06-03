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
	
	private ArrayList<Record> recordList = new ArrayList<Record>();
	
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
		Record newRecord = new Record(date, stime, etime);
		long thisId = myHelper.insert(newRecord);
		newRecord.setID(thisId);
		if (thisId != -1)
			recordList.add(0, newRecord);
		// update data base!
	}
	
	public Record removeRecord(int index) {
		long thisId = recordList.get(index).getID();
		myHelper.deleteOneData(thisId);
		return recordList.remove(index);
		// update data base too!
	}
	
	public void updateDatabase() {
		// todo...
		// just as the function name say!
		// store recordList into database
	}
}
