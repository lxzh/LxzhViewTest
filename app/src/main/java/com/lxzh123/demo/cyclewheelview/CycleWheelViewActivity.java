package com.lxzh123.demo.cyclewheelview;

import java.util.ArrayList;
import java.util.List;

import com.lxzh123.demo.cyclewheelview.CycleWheelView.CycleWheelViewException;
import com.lxzh123.demo.cyclewheelview.CycleWheelView.WheelItemSelectedListener;
import com.lxzh123.demo.testview.R;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

public class CycleWheelViewActivity extends Activity{
	private CycleWheelView cycleWheelView0,cycleWheelView1, cycleWheelView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cyclewheel);
        cycleWheelView0 = (CycleWheelView) findViewById(R.id.cycleWheelView);
        List<String> labels = new ArrayList<String>();
        for (int i = 0; i < 12; i++) {
            labels.add("" + i);
        }
        cycleWheelView0.setLabels(labels);
        cycleWheelView0.setAlphaGradual(0.5f);
        cycleWheelView0.setOnWheelItemSelectedListener(new WheelItemSelectedListener() {
            @Override
            public void onItemSelected(int position, String label) {
                Log.d("test", label);
            }
        });
        
        cycleWheelView1 = (CycleWheelView) findViewById(R.id.cycleWheelView1);
        List<String> labels1 = new ArrayList<String>();
        for (int i = 0; i < 24; i++) {
            labels1.add("" + i);
        }
        cycleWheelView1.setLabels(labels1);
        try {
            cycleWheelView1.setWheelSize(5);
        } catch (CycleWheelViewException e) {
            e.printStackTrace();
        }
        cycleWheelView1.setSelection(2);
        cycleWheelView1.setWheelItemLayout(R.layout.item_cyclewheel_custom, R.id.tv_label_item_wheel_custom);
        cycleWheelView1.setOnWheelItemSelectedListener(new WheelItemSelectedListener() {
            @Override
            public void onItemSelected(int position, String label) {
                Log.d("test", label);
            }
        });

        cycleWheelView2 = (CycleWheelView) findViewById(R.id.cycleWheelView2);
        List<String> labels2 = new ArrayList<String>();
        for (int i = 0; i < 60; i++) {
            labels2.add("" + i);
        }
        cycleWheelView2.setLabels(labels2);
        try {
            cycleWheelView2.setWheelSize(7);
        } catch (CycleWheelViewException e) {
            e.printStackTrace();
        }
        cycleWheelView2.setCycleEnable(true);
        cycleWheelView2.setSelection(30);
        cycleWheelView2.setAlphaGradual(0.6f);
        cycleWheelView2.setDivider(Color.parseColor("#abcdef"), 2);
        cycleWheelView2.setSolid(Color.WHITE,Color.WHITE);
        cycleWheelView2.setLabelColor(Color.BLUE);
        cycleWheelView2.setLabelSelectColor(Color.RED);
        cycleWheelView2.setOnWheelItemSelectedListener(new WheelItemSelectedListener() {
            @Override
            public void onItemSelected(int position, String label) {
                Log.d("test", label);
            }
        });

    }
}
