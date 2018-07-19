package com.lxzh123.demo.seekbar;

import com.lxzh123.demo.testview.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

public class SeekbarActivity extends Activity implements
		SeekBar.OnSeekBarChangeListener {

	SeekBar mSeekBar;
	TextView mProgressText;
	TextView mTrackingText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.seekbar);
		setTitle("SeekBar");

		mSeekBar = (SeekBar) findViewById(R.id.seekBar);
		// setOnSeekBarChangeListener() - 响应拖动进度条事件
		mSeekBar.setOnSeekBarChangeListener(this);

		mProgressText = (TextView) findViewById(R.id.progress);
		mTrackingText = (TextView) findViewById(R.id.tracking);
	}

	// 拖动进度条后，进度发生改变时的回调事件
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromTouch) {
		mProgressText.setText(progress + "%");
	}

	// 拖动进度条前开始跟踪触摸
	public void onStartTrackingTouch(SeekBar seekBar) {
		mTrackingText.setText("开始跟踪触摸");
	}

	// 拖动进度条后停止跟踪触摸
	public void onStopTrackingTouch(SeekBar seekBar) {
		mTrackingText.setText("停止跟踪触摸");
	}
}
