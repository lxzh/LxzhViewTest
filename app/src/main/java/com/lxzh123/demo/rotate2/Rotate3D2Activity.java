package com.lxzh123.demo.rotate2;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;

public class Rotate3D2Activity extends Activity {
	CubeView cv;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		cv = new CubeView(this);
		// cv.rotate(30, 30);
		setContentView(cv);
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_DPAD_LEFT: {
			cv.rotate(60, 0);
		}
			break;
		case KeyEvent.KEYCODE_DPAD_RIGHT: {
			cv.rotate(-60, 0);
		}
			break;
		case KeyEvent.KEYCODE_BACK:
			this.finish();
			break;
		}
		return true;
	}
}