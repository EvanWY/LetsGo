package com.project.letsgo;

import java.util.ArrayList;
import java.util.List;

public class RecordListManager {
	
	private static RecordListManager instance = null;
	
	public static RecordListManager getInstance() {
		if (instance == null)
			instance = new RecordListManager();
		return instance;
	}
	
	private List<Record> recordList = new ArrayList<Record>();
	
	private RecordListManager() {
		// load from Database ...
		recordList.add(new Record("2014/05/02", "22:01", "22:05"));
		recordList.add(new Record("2014/05/03", "11:04", "12:08"));
	}
	
	public String[] getStringArray() {
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
