package com.project.letsgo;

public class Record {
	public static final int NOT_COMPLETE = 0;
	public static final int SUCCESS = 1;
	public static final int FAIL = 2;
	
	private long id;
	private int state; // 0 for not complete; 1 for success; 2 for fail
	private String date;
	private String start_time;
	private String end_time;
	
	public Record()
	{
		id = -1;
		state = Record.NOT_COMPLETE;
		date = "";
		start_time = "";
		end_time = "";
	}
	
	public Record(String d, String s, String e)
	{
		id = -1;
		state = Record.NOT_COMPLETE;
		date = d;
		start_time = s;
		end_time = e;
	}
	
	public long getID(){
		return id;
	}
	
	public int getState(){
		return state;
	}
	
	public String getDate(){
		return date;
	}
	
	public String getStartTime(){
		return start_time;
	}
	
	public String getEndTime(){
		return end_time;
	}
	
	public void setID(long i){
		id = i;
	}
	
	public void setState(int i){
		state = i;
	}
	
	public void setDate(String s){
		date = s;
	}
	
	public void setStartTime(String s){
		start_time = s;
	}
	
	public void setEndTime(String s){
		end_time = s;
	}
	// debug use
	public void printInfo(){
		System.out.print("" + state + " " + date + " " + start_time + " " + end_time);
	}
}
