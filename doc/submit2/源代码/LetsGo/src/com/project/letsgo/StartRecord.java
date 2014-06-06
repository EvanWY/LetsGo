package com.project.letsgo;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class StartRecord extends Activity 
{
    Button act2_button1;
    TextView act2_textView1;
    TextView act2_textView2;
    TextView act2_textView3;
    TextView act2_textView4;
    int position;
    Context context = this;
    private LocationManager locationManager;
    private static long currentTime;
    private static double currentDistance;
    private static long target;
    
    @Override
    protected void onDestroy() {
        locationManager.removeUpdates(locationListener);
        super.onDestroy();
    }
    
    protected void onCreate(Bundle savedInstanceState) {
        
        System.out.println("kk1");
        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startrecord);
        

        System.out.println("kk1.1");
        
        act2_button1 = (Button)findViewById(R.id.act2_button1);
        System.out.println("kk1.2");
        act2_textView1 = (TextView)findViewById(R.id.act2_textView1);
        System.out.println("kk1.3");
        act2_textView2 = (TextView)findViewById(R.id.act2_textView2);
        System.out.println("kk1.4");
        act2_textView3 = (TextView)findViewById(R.id.act2_textView3);
        System.out.println("kk1.5");
        act2_textView4 = (TextView)findViewById(R.id.act2_textView4);
        

        System.out.println("kk2");
        
        position = getIntent().getExtras().getInt("position");
        
        act2_textView1.setText("正在处理第"+position+"条记录");
        
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);  
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, locationListener);
        
        currentDistance = 0;
        currentTime = System.currentTimeMillis();
        target = RecordListManager.getInstance(null).getRecord(position).getDistance();
        

        System.out.println("kk3");
        
        act2_button1.setOnClickListener(new OnClickListener(){
            public void onClick(View v) {
                if (currentDistance > target){
                    RecordListManager.getInstance(context).successRecord(position);
                }
                else{
                    RecordListManager.getInstance(context).failRecord(position);
                }
                
                finish();
            }
        });
    }
    
    private final LocationListener locationListener = new LocationListener() {

        @Override
        public void onLocationChanged(Location location) {
            
            double speed = location.getSpeed();
            
            long tempTime = System.currentTimeMillis();
            currentDistance += ((tempTime - currentTime) * (speed / 1000));
            currentTime = tempTime;
            
            act2_textView2.setText("Target Distance: " + target + " meters");
            act2_textView3.setText("Current Speed: " + speed + " m/s");
            act2_textView4.setText("Current Distance: " + currentDistance + " meters");
            
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void onProviderEnabled(String provider) {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void onProviderDisabled(String provider) {
            // TODO Auto-generated method stub
            
        }
    };

}
