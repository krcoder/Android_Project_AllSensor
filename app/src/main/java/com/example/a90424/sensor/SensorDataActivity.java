package com.example.a90424.sensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class SensorDataActivity extends AppCompatActivity implements SensorEventListener {

    public static final String SENSOR_IDX = "position";
    TextView text1,text2,text3,text4;
    SensorManager sm=null;
    List<Sensor> sensorlist = null;
    int m_currentId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_data);
        text1 = (TextView)findViewById(R.id.textView2);
        text2 = (TextView)findViewById(R.id.textView3);
        text3 = (TextView)findViewById(R.id.textView4);
        text4 = (TextView)findViewById(R.id.textView5);

        sm = (SensorManager)getSystemService(SENSOR_SERVICE);
        sm.getSensorList(Sensor.TYPE_ALL);
        sensorlist = sm.getSensorList(Sensor.TYPE_ALL);

        text1.setText(sensorlist.get(m_currentId).getName());
        text2.setText(sensorlist.get(m_currentId).getVendor());
        text3.setText("max : " + sensorlist.get(m_currentId).getMaximumRange());
        text4.setText("값이 들어갈 부분입니다");

    }

    public void onBack(View view){
        finish();
    }

    public void onStart(View view){
        //enable
        sm.registerListener(this,sensorlist.get(m_currentId),SensorManager.SENSOR_DELAY_UI);


    }

    public void onStop(View view){
        sm.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        String data = "Sensor Timestamp : " + sensorEvent.timestamp + "\n\n";
        for(int idx =0;idx<sensorEvent.values.length;idx++){
            data += ("Sensor Value #"+(idx+1)+":"+sensorEvent.values[idx]+"\n");
        }
        text4.setText(data);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
