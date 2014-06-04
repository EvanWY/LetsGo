package com.project.letsgo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

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

        //recordList = myHelper.convertToRecord(myHelper.getCursor());
	    
		if (recordList == null)
			return null;
		String[] strList = new String[recordList.size()];
		for (int i=0; i!=recordList.size(); i++) {
			strList[i] = recordList.get(i).getDate() 
			        + " (" + recordList.get(i).getStartTime() 
			        + " - " + recordList.get(i).getEndTime() + ")"
			        + " " + recordList.get(i).getDistance() + "m" + " ";
			if (recordList.get(i).getState() == Record.SUCCESS)
			    strList[i] += "Win!";
			else if (recordList.get(i).getState() == Record.FAIL)
			    strList[i] += "Lose!";
			else
			    strList[i] += " -- ";
		}
		return strList;
	}
	
	public void addRecord(String date, String stime, String etime, int dist) {
		Record newRecord = new Record(date, stime, etime, dist);
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
	
	public Record getRecord(int index) {
        return recordList.get(index);
    }
	
	public void failRecord(int index) {
	    long thisId = recordList.get(index).getID();
        Record rec = recordList.get(index);
        rec.setState(Record.FAIL);
        recordList.set(index, rec);
        myHelper.updateOneData(thisId, rec);
	}
	
	public void successRecord(int index) {
        long thisId = recordList.get(index).getID();
        Record rec = recordList.get(index);
        rec.setState(Record.SUCCESS);
        recordList.set(index, rec);
        myHelper.updateOneData(thisId, rec);
    }
	
	public void updateAllState() {
	    
	    String myFormat = "yyyy/MM/dd";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.CHINA);
        String currentDate = sdf.format(Calendar.getInstance().getTime());
        
        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        int minute = Calendar.getInstance().get(Calendar.MINUTE);
        
        String currentTime = "" + hour + ":" + minute;
        
        System.out.println("gogogo++ " + currentDate + " " + currentTime);
	    
	    for (int i=0; i!=recordList.size(); i++) {
	        
	        System.out.println("pp0");
	        
	        Record rec = recordList.get(i);
	        if (rec.getState() == Record.NOT_COMPLETE){
	            if (Record.getTimeMapping(rec.getDate(), rec.getEndTime())
	                    < Record.getTimeMapping(currentDate, currentTime)){
	                failRecord(i);
	            }
	        }
	        

            System.out.println("pp1");
	    }
	}
	
	public void updateDatabase() {
		// todo...
		// just as the function name say!
		// store recordList into database
	}
}
