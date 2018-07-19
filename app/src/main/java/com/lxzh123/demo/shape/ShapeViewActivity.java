package com.lxzh123.demo.shape;

import com.lxzh123.demo.testview.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class ShapeViewActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shapetest);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
