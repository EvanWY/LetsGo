package com.project.letsgo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import android.os.Bundle;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends Activity {
    
    TabHost tabHost;
	
    ListView tab1_listView1;
    
    TextView tab2_textView1;
    TextView tab2_textView2;
    TextView tab2_textView3;	
    Button tab2_button1;
    Button tab2_button2;
    Button tab2_button3;
    Button tab2_button4;    
    
    Chronometer tab3_chronometer1;
    TextView tab3_textView1;
    Button tab3_button1;
    
    Context context = this;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabHost=(TabHost)findViewById(R.id.tabHost);
        tab1_listView1 = (ListView)findViewById(R.id.tab1_listView1);
        tab2_textView1 = (TextView)findViewById(R.id.tab2_textView1);
        tab2_textView2 = (TextView)findViewById(R.id.tab2_textView2);
        tab2_textView3 = (TextView)findViewById(R.id.tab2_textView3);
        tab2_button1 = (Button)findViewById(R.id.tab2_button1);
        tab2_button2 = (Button)findViewById(R.id.tab2_button2);
        tab2_button3 = (Button)findViewById(R.id.tab2_button3);
        tab2_button4 = (Button)findViewById(R.id.tab2_button4);
        tab3_chronometer1 = (Chronometer)findViewById(R.id.tab3_chronometer1);
        tab3_textView1 = (TextView)findViewById(R.id.tab3_textView1);
        tab3_button1 = (Button)findViewById(R.id.tab3_button1);
        
        // Setting up Tabs UI.	
        tabHost.setup();
      
        TabSpec spec1=tabHost.newTabSpec("tab1");
        spec1.setContent(R.id.tab1);
        spec1.setIndicator("查看记录");
        tabHost.addTab(spec1);
        
        TabSpec spec2=tabHost.newTabSpec("tab2");
        spec2.setContent(R.id.tab2);
        spec2.setIndicator("添加计划");
        tabHost.addTab(spec2);
      
        TabSpec spec3=tabHost.newTabSpec("tab3");
        spec3.setContent(R.id.tab3);
        spec3.setIndicator("开始记录");
        tabHost.addTab(spec3);
        // End of Tabs UI setting up.
        
        
        // list View 1 process
        //String[] str = new String[] { "Android Introduction","Android Setup/Installation","Android Hello World","Intents in Android","Intents in Android","Intents in Android","Intents in Android","Intents in Android","Intents in Android","Intents in Android","Intents in Android","Intents in Android","Intents in Android","Intents in Android","Android Layouts/Viewgroups","Android Activity & Lifecycle","Intents in Android"};
        refresh_tab1_listView1();
        tab1_listView1.setOnItemLongClickListener(new OnItemLongClickListener(){
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                RecordListManager.getInstance().removeRecord(arg2);
                refresh_tab1_listView1();
                Toast.makeText(
                        getApplicationContext(),
                        "记录已删除", 
                        Toast.LENGTH_LONG
                        ).show();
                return false;
            }
        });
        // ListView 1 finish.
        
        
        // setting up tab2
        final Calendar myCalendar = Calendar.getInstance();

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                String myFormat = "yyyy/MM/dd";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.CHINA);
                tab2_textView1.setText(sdf.format(myCalendar.getTime()));
                System.out.print(myCalendar.getTime());
                }
        };
        
        tab2_button1.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                new DatePickerDialog(MainActivity.this,
                        date,
                        myCalendar.get(Calendar.YEAR),
                        myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)
                        ).show();
            }
        });
        
        tab2_button2.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        tab2_textView2.setText( selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true).show();
                
            }
        });
        
        tab2_button3.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        tab2_textView3.setText( selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true).show();

            }
        });
        
        tab2_button4.setOnClickListener(new OnClickListener(){
            public void onClick(View v) {
                RecordListManager.getInstance().addRecord(
			            tab2_textView1.getText().toString(),
				        tab2_textView2.getText().toString(),
					    tab2_textView3.getText().toString());
			    refresh_tab1_listView1();
			    Toast.makeText(
                        getApplicationContext(),
                        "记录已添加", 
                        Toast.LENGTH_LONG
                        ).show();
			    tabHost.setCurrentTab(0);
            }
        });
        // tab2 finish

        
	}
	private void refresh_tab1_listView1() {
	    String[] str = RecordListManager.getInstance().getStringArray();
	    ArrayAdapter<String> lab1_listView1_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, str);
            
	    tab1_listView1.setAdapter(lab1_listView1_adapter);
	}
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
	    // Inflate the menu; this adds items to the action bar if it is present.
	    getMenuInflater().inflate(R.menu.main, menu);
	    return true;
	}

}
