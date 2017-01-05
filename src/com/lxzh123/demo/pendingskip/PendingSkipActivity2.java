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

public class PendingSkipActivity2 extends Activity{

	Button btnSkip;
	private float startX;
	int pid;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pendingskip2);
		Intent intent=getIntent();
		pid=intent.getIntExtra("pid", 0);
		
		btnSkip = (Button) findViewById(R.id.button2);
		btnSkip.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(PendingSkipActivity2.this, PendingSkipActivity3.class));
				finish();
			}
		});
	}

	@Override
    public boolean onTouchEvent(MotionEvent event) {
        //点击屏幕，MotionEvent.ACTION_DOWN 为手指点击屏幕事件
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            //获取手指开始触点横坐标
            startX = event.getX();
            //手指抬起，结束滑屏
        } else if(event.getAction() == MotionEvent.ACTION_UP) {
            //获取手指抬起，结束点横坐标
            float endX = event.getX();
            Intent intent=new Intent();
            //结束点横坐标大于起始点横坐标，说明手指是向右滑动
            if(endX > startX) {
    			Toast.makeText(this, "向右手势", Toast.LENGTH_SHORT).show();
    			intent.putExtra("pid", 21);
    			intent.setClass(PendingSkipActivity2.this, PendingSkipActivity1.class);
    			startActivity(intent);
    			overridePendingTransition(R.anim.push_right_in,
    					R.anim.push_right_out);
    			finish();
            //结束点横坐标小于起始点横坐标，说明手指是向左滑动
            } else if (endX < startX) {
    			Toast.makeText(this, "向左手势", Toast.LENGTH_SHORT).show();
    			intent.putExtra("pid", 23);
    			intent.setClass(PendingSkipActivity2.this, PendingSkipActivity3.class);
    			startActivity(intent);
    			overridePendingTransition(R.anim.push_left_in,
    					R.anim.push_left_out);
    			finish();
            }
            return true;
        }
        return super.onTouchEvent(event);
    }

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if(pid==12)
			{
				startActivity(new Intent(PendingSkipActivity2.this, PendingSkipActivity1.class));
				overridePendingTransition(R.anim.push_right_in,
    					R.anim.push_right_out);
			}else if(pid==32)
			{
				startActivity(new Intent(PendingSkipActivity2.this, PendingSkipActivity3.class));
				overridePendingTransition(R.anim.push_left_in,
    					R.anim.push_left_out);
			}
			finish();
		}
		return super.onKeyDown(keyCode, event);
	}
}
