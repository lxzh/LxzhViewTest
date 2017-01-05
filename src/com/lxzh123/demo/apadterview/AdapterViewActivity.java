package com.lxzh123.demo.apadterview;

import com.lxzh123.demo.testview.R;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class AdapterViewActivity extends ListActivity {
	/* 静态数组数据 */
	String[] weekStrings = new String[] { "星期日", "星期一", "星期二", "星期三", "星期四",
			"星期五", "星期六" };
	/* 数组适配器 */
	ArrayAdapter<String> adapter;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/* 构造数组适配器 */
		adapter = new ArrayAdapter<String>(this, R.layout.adapterview, weekStrings);
		/* 设置适配器 */
		this.setListAdapter(adapter);
	}
}