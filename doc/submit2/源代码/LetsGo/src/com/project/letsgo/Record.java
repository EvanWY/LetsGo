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
	private int distance;
	
	public Record()
	{
		id = -1;
		state = Record.NOT_COMPLETE;
		date = "";
		start_time = "";
		end_time = "";
		distance = 0;
	}
	
	public Record(String d, String s, String e, int dist)
	{
		id = -1;
		state = Record.NOT_COMPLETE;
		date = d;
		start_time = s;
		end_time = e;
		distance = dist;
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
	
	public int getDistance() {
	    return distance;
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
	
	public void setDistance(int dist) {
	    distance = dist;
	}
	
	public static int getTimeMapping(String date, String time){
	    int ans = 0;
	    
	    int year = Integer.parseInt(date.substring(0, 4));
	    int month = Integer.parseInt(date.substring(5, 7));
	    int day = Integer.parseInt(date.substring(8, 10));
	    
	    int colonPos = 0;
	    while (time.charAt(colonPos)!=':')
	        colonPos++;
	    
	    int hour = Integer.parseInt(time.substring(0, colonPos));
	    int minute = Integer.parseInt(time.substring(colonPos + 1, time.length()));
	    
	    ans += year;
	    ans *= 12;
	    ans += month;
	    ans *= 31;
	    ans += day;
	    ans *= 24;
	    ans += hour;
	    ans *= 60;
	    ans += minute;
	    
	    System.out.println("time!!!" + year + " " + month + " " + day + " " + hour + " " + minute + " " + ans);
	    
	    return ans;
	}
	
	// debug use
	public void printInfo(){
		System.out.print("" + state + " " + date + " " + start_time + " " + end_time);
	}
}
