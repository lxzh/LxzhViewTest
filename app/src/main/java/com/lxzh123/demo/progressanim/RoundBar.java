package com.lxzh123.demo.progressanim;

import java.util.Timer;
import java.util.TimerTask;

import com.lxzh123.demo.testview.R;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.WindowManager;
import android.view.Window;
import android.widget.ImageView;

public class RoundBar extends Dialog {

	private ImageView image;
	private int[] ImagesId;
	private Timer timer;
	private int count;
	private int currentId = 0;
	private Handler handler;

	public RoundBar(Context context, int theme, int[] imagesId) {
		super(context, theme);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(theme);
		ImagesId = imagesId;
		count = imagesId.length;
		WindowManager.LayoutParams params = getWindow().getAttributes();
		params.height = 300;
		params.width = 300;

		params.alpha = 0.5f;

		getWindow().setAttributes(params);
		image = (ImageView) findViewById(R.id.iv_progress);
		handler = new Handler() {

			@Override
			public void handleMessage(Message msg) {
				image.setImageResource(ImagesId[msg.what]);
				super.handleMessage(msg);
			}
		};
		timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				handler.sendEmptyMessage(currentId);
				currentId++;
				if (currentId == count) {
					currentId = 0;
				}
			}
		}, 0, 200);
	}

	@Override
	public void cancel() {
		Log.d("RoundBar", "cancel");
		timer.cancel();
		currentId = 0;
		super.cancel();
	}

	protected void onStop() {
		timer.cancel();
	}
}
