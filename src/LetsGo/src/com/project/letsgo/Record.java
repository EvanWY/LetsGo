package com.project.letsgo;

public class Record {
	
	private int state; // 0 for not complete; 1 for success; 2 for fail
	private String date;
	private String start_time;
	private String end_time;
	
	public Record(String d, String s, String e)
	{
		state = 0;
		date = d;
		start_time = s;
		end_time = e;
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
	
	public void setState(int i){
		state = i;
	}
	
	// debug use
	public void printInfo(){
		System.out.print("" + state + " " + date + " " + start_time + " " + end_time);
	}
}
