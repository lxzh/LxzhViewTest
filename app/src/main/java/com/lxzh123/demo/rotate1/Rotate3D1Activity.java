package com.lxzh123.demo.rotate1;

import com.lxzh123.demo.testview.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public class Rotate3D1Activity extends Activity {
	private ViewGroup layoutmain;
	private ViewGroup layoutnext;

	private Button btn_MainLast;
	private Button btn_MainNext;
	private Button btn_NextLast;
	private Button btn_NextNext;

	private int position = 0;
	private Rotate3D1 lQuest1Animation;
	private Rotate3D1 lQuest2Animation;
	private Rotate3D1 rQuest1Animation;
	private Rotate3D1 rQuest2Animation;
	private int mCenterX = 160;
	private int mCenterY = 240;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		initAnimation();
		initMain();
	}

	private void initMain() {
		setContentView(R.layout.layout_rotate_main);

		layoutmain = (LinearLayout) findViewById(R.id.layout_main);
		btn_MainLast = (Button) findViewById(R.id.main_last);
		btn_MainNext = (Button) findViewById(R.id.main_next);

		btn_MainLast.setOnClickListener(listener);
		btn_MainNext.setOnClickListener(listener);
	}

	private void initNext() {
		setContentView(R.layout.layout_rotate_next);

		layoutnext = (LinearLayout) findViewById(R.id.layout_next);
		btn_NextLast = (Button) findViewById(R.id.next_last);
		btn_NextNext = (Button) findViewById(R.id.next_next);

		btn_NextLast.setOnClickListener(listener);
		btn_NextNext.setOnClickListener(listener);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		int mposition;
		if (keyCode == KeyEvent.KEYCODE_0) {
			position++;
			mposition = position % 4;
			if (mposition == 0) {
				layoutmain.startAnimation(lQuest1Animation);
				initNext();
				layoutnext.startAnimation(lQuest2Animation);
			} else if (mposition == 1) {
				layoutmain.startAnimation(lQuest1Animation);
				initNext();
				layoutnext.startAnimation(lQuest2Animation);
			} else if (mposition == 2) {
				layoutmain.startAnimation(lQuest1Animation);
				initNext();
				layoutnext.startAnimation(lQuest2Animation);
			} else if (mposition == 3) {
				layoutmain.startAnimation(lQuest1Animation);
				initNext();
				layoutnext.startAnimation(lQuest2Animation);
			}
		}
		if (keyCode == KeyEvent.KEYCODE_1) {
			position--;
			mposition = position % 4;
			if (mposition == 0) {
				layoutmain.startAnimation(lQuest1Animation);
				initMain();
				layoutnext.startAnimation(lQuest2Animation);
			} else if (mposition == 1) {
				layoutnext.startAnimation(lQuest1Animation);
				initMain();
				layoutmain.startAnimation(lQuest2Animation);
			} else if (mposition == 2) {
				layoutnext.startAnimation(lQuest1Animation);
				initMain();
				layoutmain.startAnimation(lQuest2Animation);
			} else if (mposition == 3) {
				layoutnext.startAnimation(lQuest1Animation);
				initMain();
				layoutmain.startAnimation(lQuest2Animation);
			}
		}
		return super.onKeyDown(keyCode, event);
	}

	private View.OnClickListener listener = new View.OnClickListener() {
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.main_last:
				layoutmain.startAnimation(lQuest1Animation);
				initNext();
				layoutnext.startAnimation(lQuest2Animation);
				break;
			case R.id.main_next:
				layoutmain.startAnimation(rQuest1Animation);
				initNext();
				layoutnext.startAnimation(rQuest2Animation);
				break;
			case R.id.next_last:
				layoutnext.startAnimation(lQuest1Animation);
				initMain();
				layoutmain.startAnimation(lQuest2Animation);
				break;
			case R.id.next_next:
				layoutnext.startAnimation(rQuest1Animation);
				initMain();
				layoutmain.startAnimation(rQuest2Animation);
				break;
			}
		}
	};

	public void initAnimation() {

		DisplayMetrics dm = new DisplayMetrics();
		dm = getResources().getDisplayMetrics();
		mCenterX = dm.widthPixels / 2;
		mCenterY = dm.heightPixels / 2;

		int duration = 800;
		lQuest1Animation = new Rotate3D1(0, -90, mCenterX, 150);
		lQuest1Animation.setFillAfter(true);
		lQuest1Animation.setDuration(duration);

		lQuest2Animation = new Rotate3D1(90, 0, mCenterX, 150);
		lQuest2Animation.setFillAfter(true);
		lQuest2Animation.setDuration(duration);

		rQuest1Animation = new Rotate3D1(0, 90, mCenterX, 150);
		rQuest1Animation.setFillAfter(true);
		rQuest1Animation.setDuration(duration);

		rQuest2Animation = new Rotate3D1(-90, 0, mCenterX, 150);
		rQuest2Animation.setFillAfter(true);
		rQuest2Animation.setDuration(duration);
	}

}
