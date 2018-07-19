package com.lxzh123.demo.progressanim;

import java.util.Timer;
import java.util.TimerTask;

import com.lxzh123.demo.testview.R;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class ProgressAnimActivity extends Activity {
	private ImageView image1, image2, image3, image4, image5;
	private RoundBar roundBar;
	private Animation rAnimation2,rAnimation5;
	private int[] ImagesId;
	private int[] ImagesId1;
	private int[] ImagesId3;
	private int[] ImagesId4;
	private boolean idShow1 = false;
	private boolean idShow2 = false;
	private boolean idShow3 = false;
	private boolean idShow4 = false;
	private boolean idShow5 = false;
	private int count1 = 0, count3 = 0, count4 = 0;
	private Handler handler;
	private Timer timer1, timer3, timer4;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_activity_progress_main);
		ImagesId = new int[] { R.drawable.r1, R.drawable.r2, R.drawable.r3,
				R.drawable.r4, R.drawable.r5, R.drawable.r6, R.drawable.r7,
				R.drawable.r8 };
		ImagesId1 = new int[] { R.drawable.load1, R.drawable.load2,
				R.drawable.load3, R.drawable.load4, R.drawable.load5,
				R.drawable.load6 };
		ImagesId3 = new int[] { R.drawable.calculating_1, R.drawable.calculating_2,
				R.drawable.calculating_3, R.drawable.calculating_4,
				R.drawable.calculating_4, R.drawable.calculating_6,
				R.drawable.calculating_5, R.drawable.calculating_8 };
		ImagesId4 = new int[] { R.drawable.pageloading_01,
				R.drawable.pageloading_02, R.drawable.pageloading_03,
				R.drawable.pageloading_04, R.drawable.pageloading_05,
				R.drawable.pageloading_06 };

		image1 = (ImageView) findViewById(R.id.progress1);
		image2 = (ImageView) findViewById(R.id.progress2);
		image3 = (ImageView) findViewById(R.id.progress3);
		image4 = (ImageView) findViewById(R.id.progress4);
		image5 = (ImageView) findViewById(R.id.progress5);

		roundBar = new RoundBar(ProgressAnimActivity.this,
				R.layout.dialog_progresslay, ImagesId);
		rAnimation2 = AnimationUtils.loadAnimation(this, R.anim.laoding2);
		rAnimation5 = AnimationUtils.loadAnimation(this, R.anim.laoding5);
		handler = new Handler() {

			@Override
			public void handleMessage(Message msg) {

				if (msg.arg1 == 1) {
					image1.setBackgroundResource(ImagesId1[msg.what]);
				} else if (msg.arg1 == 2) {

				} else if (msg.arg1 == 3) {
					image3.setBackgroundResource(ImagesId3[msg.what]);
				} else if (msg.arg1 == 4) {
					image4.setBackgroundResource(ImagesId4[msg.what]);
				} else if (msg.arg1 == 5) {

				}
				super.handleMessage(msg);
			}
		};
	}
	public void OnButtonClick(View v) {
		switch (v.getId()) {
		case R.id.showbar1:
			if (!idShow1) {
				image1.setVisibility(View.VISIBLE);
				startAnim1();
			} else {
				timer1.cancel();
				image1.setVisibility(View.INVISIBLE);
			}
			idShow1 = !idShow1;
			break;
		case R.id.showbar2:
			if (!idShow2) {
				image2.setVisibility(View.VISIBLE);
				startAnim2();
			} else {
				image2.clearAnimation();
				image2.setVisibility(View.INVISIBLE);
			}
			idShow2 = !idShow2;
			break;
		case R.id.showbar3:
			if (!idShow3) {
				image3.setVisibility(View.VISIBLE);
				startAnim3();
			} else {
				timer3.cancel();
				image3.setVisibility(View.INVISIBLE);
			}
			idShow3 = !idShow3;
			break;
		case R.id.showbar4:
			if (!idShow4) {
				image4.setVisibility(View.VISIBLE);
				startAnim4();
			} else {
				timer4.cancel();
				image4.setVisibility(View.INVISIBLE);
			}
			idShow4 = !idShow4;
			break;
		case R.id.showbar5:
			if (!idShow5) {
				image5.setVisibility(View.VISIBLE);
				startAnim5();
			} else {
				image5.clearAnimation();
				image5.setVisibility(View.INVISIBLE);
			}
			idShow5 = !idShow5;
			break;
		case R.id.showbar6:
			startActivity(new Intent(ProgressAnimActivity.this,RoundProgressBarActivity.class));
			break;
		case R.id.btnColorRoundProgress:
			startActivity(new Intent(ProgressAnimActivity.this,ColorRoundProgressActivity.class));
			break;
		case R.id.btnLxzhLoading:
			startActivity(new Intent(ProgressAnimActivity.this,LxzhLoadingActivity.class));
			break;

		}
	}
	
	public void startAnim1() {
		timer1 = new Timer();
		timer1.schedule(new TimerTask() {

			@Override
			public void run() {
				Message message = new Message();
				message.arg1 = 1;
				message.what = count1;
				handler.sendMessage(message);
				count1++;
				if (count1 == 6) {
					count1 = 0;
				}
			}
		}, 0, 200);
	}

	public void startAnim2() {
		image2.startAnimation(rAnimation2);
	}

	public void startAnim3() {
		timer3 = new Timer();
		timer3.schedule(new TimerTask() {

			@Override
			public void run() {
				Message message = new Message();
				message.arg1 = 3;
				message.what = count3;
				handler.sendMessage(message);
				count3++;
				if (count3 == 8) {
					count3 = 0;
				}
			}
		}, 0, 300);
	}

	public void startAnim4() {
		timer4 = new Timer();
		timer4.schedule(new TimerTask() {

			@Override
			public void run() {
				Message message = new Message();
				message.arg1 = 4;
				message.what = count4;
				handler.sendMessage(message);
				count4++;
				if (count4 == 6) {
					count4 = 0;
				}
			}
		}, 0, 300);
	}

	public void startAnim5() {
		//尽量不要共用rotate动画，否则除第一个以外其余的旋转中心会变
		image5.startAnimation(rAnimation5);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, 1, 0, "对话框");
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		roundBar.show();
		return super.onOptionsItemSelected(item);
	}
}
