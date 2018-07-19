package com.lxzh123.demo.activityskip;

import com.lxzh123.demo.testview.R;
import com.lxzh123.demo.util.DswLog;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class SkipActivity1 extends Activity {
	private Button btnSkip;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activityskip1);
		DswLog.d("MainActivity", "onCreate");
		btnSkip=(Button)findViewById(R.id.skip);	
		btnSkip.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(getBaseContext(), SkipActivity2.class);
				startActivityForResult(intent, 0);
			}
		});
	}

	@Override
	public void startActivityForResult(Intent intent, int requestCode) {
		DswLog.d("MainActivity", "startActivityForResult");
		super.startActivityForResult(intent, requestCode);
	}

	@Override
	public void finish() {
		DswLog.d("MainActivity", "finish");
		super.finish();
	}

	@Override
	public void finishActivity(int requestCode) {
		DswLog.d("MainActivity", "finishActivity");
		super.finishActivity(requestCode);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		DswLog.d("MainActivity", "onActivityResult");
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	@Override
	public void onBackPressed() {
		DswLog.d("MainActivity", "onBackPressed");
		super.onBackPressed();
	}

	@Override
	protected void onDestroy() {
		DswLog.d("MainActivity", "onDestroy");
		super.onDestroy();
	}

	@Override
	public void onDetachedFromWindow() {
		DswLog.d("MainActivity", "onDetachedFromWindow");
		super.onDetachedFromWindow();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		DswLog.d("MainActivity", "onKeyDown");
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void onLowMemory() {
		DswLog.d("MainActivity", "onLowMemory");
		super.onLowMemory();
	}

	@Override
	protected void onPause() {
		DswLog.d("MainActivity", "onPause");
		super.onPause();
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		DswLog.d("MainActivity", "onPostCreate");
		super.onPostCreate(savedInstanceState);
	}

	@Override
	protected void onPostResume() {
		DswLog.d("MainActivity", "onPostResume");
		super.onPostResume();
	}

	@Override
	protected void onRestart() {
		DswLog.d("MainActivity", "onRestart");
		super.onRestart();
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		DswLog.d("MainActivity", "onRestoreInstanceState");
		super.onRestoreInstanceState(savedInstanceState);
	}

	@Override
	protected void onResume() {
		DswLog.d("MainActivity", "onResume");
		super.onResume();
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		DswLog.d("MainActivity", "onSaveInstanceState");
		super.onSaveInstanceState(outState);
	}

	@Override
	protected void onStart() {
		DswLog.d("MainActivity", "onStart");
		super.onStart();
	}

	@Override
	protected void onStop() {
		DswLog.d("MainActivity", "onStop");
		super.onStop();
	}

}
