package com.example.a90424.sensor;

import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    SensorManager sm;
    List<Sensor> sensorlist;

    TextView text;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorlist = sm.getSensorList(Sensor.TYPE_ALL);

        text = (TextView)findViewById(R.id.textView);
        list = (ListView)findViewById(R.id.listView);

        MyAdapter adapter = new MyAdapter();
        list.setAdapter(adapter);

        list.setOnItemClickListener(
                new AdapterView.OnItemClickListener()
                {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Sensor s = sensorlist.get(i);
                        text.setText(""+s.getName()+"selected!");

                        Intent intent = new Intent(getApplicationContext(),SensorDataActivity.class);

                        intent.putExtra("Position",i);
                        startActivity(intent);
            }
        });


    }




    class MyAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return sensorlist.size();
        }

        @Override
        public Object getItem(int i) {
            return sensorlist.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            Sensor s = sensorlist.get(i);

            LinearLayout layout = new LinearLayout(getApplicationContext());
            layout.setOrientation(LinearLayout.VERTICAL);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );


            TextView item = new TextView(getApplicationContext());
            item.setText(s.getName());
            item.setTextColor(Color.BLACK);
            item.setTextSize(20.0f);
            layout.addView(item,params);

            TextView subitem = new TextView((getApplicationContext()));
            subitem.setText(s.getVendor());
            subitem.setTextColor(Color.BLUE);
            subitem.setTextSize(12.0f);
            layout.addView(subitem,params);


            return layout;
        }
    }
}


















