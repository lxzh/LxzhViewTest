package com.lxzh123.demo.wechatbtn;

import com.lxzh123.demo.testview.R;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.Toast;

public class WeChatButtonActivity extends Activity {
	private PopupWindow window = null;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_activity_wechatbtn_main);
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_settings:
			showOutMenu();
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	private void showOutMenu() {
		LayoutInflater layoutIn = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
		View view = layoutIn.inflate(R.layout.layout_popmenu_wechatbtn, null);
		Button btn1 = (Button) view.findViewById(R.id.btn1);
		Button btn2 = (Button) view.findViewById(R.id.btn2);
		btn1.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				window.dismiss();
				Toast.makeText(WeChatButtonActivity.this, "用户信息已清除", 3).show();
			}
		});
		btn2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});
		if (window == null) {
			window = new PopupWindow(view, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT,
					true);
			window.setAnimationStyle(R.style.popuStyle);
			window.setBackgroundDrawable(new BitmapDrawable());
		}
		window.showAtLocation(findViewById(R.id.main_layout), Gravity.BOTTOM, 0, 0);

	}
}
