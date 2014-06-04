package com.project.letsgo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class StartRecord extends Activity 
{
    Button act2_button1;
    TextView act2_textView1;
    int position;
    Context context = this;
    
    protected void onCreate(Bundle savedInstanceState) {
        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startrecord);
        
        act2_button1 = (Button)findViewById(R.id.act2_button1);
        act2_textView1 = (TextView)findViewById(R.id.act2_textView1);
        
        position = getIntent().getExtras().getInt("position");
        
        act2_textView1.setText("正在处理第"+position+"条记录");
        
        act2_button1.setOnClickListener(new OnClickListener(){
            public void onClick(View v) {
                RecordListManager.getInstance(context).successRecord(position);
                
                finish();
            }
        });
    }

}
