package com.lxzh123.demo.radiobutton;

import com.lxzh123.demo.testview.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class RadioButtonActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.radiobutton);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
