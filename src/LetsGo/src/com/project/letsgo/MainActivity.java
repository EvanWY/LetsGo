package com.project.letsgo;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class MainActivity extends Activity {
	TabHost tabHost;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// Setting up Tabs UI.	
        tabHost=(TabHost)findViewById(R.id.tabHost);
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
        
        String[] codeLearnChapters = new String[] { "Android Introduction","Android Setup/Installation","Android Hello World","Intents in Android","Intents in Android","Intents in Android","Intents in Android","Intents in Android","Intents in Android","Intents in Android","Intents in Android","Intents in Android","Intents in Android","Intents in Android","Android Layouts/Viewgroups","Android Activity & Lifecycle","Intents in Android"};
        ArrayAdapter<String> codeLearnArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, codeLearnChapters);
        
        ListView tab1_listView1 = (ListView)findViewById(R.id.tab1_listView1);
        tab1_listView1.setAdapter(codeLearnArrayAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
