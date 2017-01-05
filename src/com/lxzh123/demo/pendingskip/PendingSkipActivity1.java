package com.lxzh123.demo.pendingskip;

import com.lxzh123.demo.testview.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class PendingSkipActivity1 extends Activity {

	Button btnSkip;
	private float startX;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pendingskip1);

		btnSkip = (Button) findViewById(R.id.button1);
		btnSkip.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(PendingSkipActivity1.this, PendingSkipActivity2.class));
				finish();
			}
		});
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			finish();
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// 点击屏幕，MotionEvent.ACTION_DOWN 为手指点击屏幕事件
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			// 获取手指开始触点横坐标
			startX = event.getX();
			// 手指抬起，结束滑屏
		} else if (event.getAction() == MotionEvent.ACTION_UP) {
			// 获取手指抬起，结束点横坐标
			float endX = event.getX();
			// 结束点横坐标大于起始点横坐标，说明手指是向右滑动
			if (endX > startX) {
				Toast.makeText(this, "向右手势", Toast.LENGTH_SHORT).show();
				Toast.makeText(this, "亲~这是第一页,左边没有!", Toast.LENGTH_SHORT)
						.show();
				// finish();
				// 结束点横坐标小于起始点横坐标，说明手指是向左滑动
			} else if (endX < startX) {
				Toast.makeText(this, "向左手势", Toast.LENGTH_SHORT).show();
				Intent intent = new Intent();
				intent.putExtra("pid", 12);
				intent.setClass(PendingSkipActivity1.this, PendingSkipActivity2.class);
				startActivity(intent);

				overridePendingTransition(R.anim.push_left_in,
						R.anim.push_left_out);
				finish();
			}
			return true;
		}
		return super.onTouchEvent(event);
	}
}
