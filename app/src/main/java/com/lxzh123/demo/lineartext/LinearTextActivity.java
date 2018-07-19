package com.lxzh123.demo.lineartext;

import com.lxzh123.demo.testview.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LinearTextActivity extends Activity{

	private EditText etText;
	private Button btnSet;
	private LyricTextView tvLyric;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_lineartext);
		etText=(EditText)findViewById(R.id.etText);
		btnSet=(Button)findViewById(R.id.btnSet);
		tvLyric=(LyricTextView)findViewById(R.id.lyricText);
		
		btnSet.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String text=etText.getText().toString();
				tvLyric.setText(text);
			}
		}); 
	}

	@Override
	protected void onDestroy() {
		tvLyric.stopDraw();
		super.onDestroy();
	}
}
