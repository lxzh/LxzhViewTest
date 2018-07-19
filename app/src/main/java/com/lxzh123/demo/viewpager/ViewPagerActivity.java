package com.lxzh123.demo.viewpager;

import java.util.ArrayList;
import java.util.List;

import com.lxzh123.demo.testview.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class ViewPagerActivity extends Activity {

	private View view1, view2, view3;// 需要滑动的页卡
	private ViewPager viewPager;// viewpager
	private PagerTitleStrip pagerTitleStrip;// viewpager的标题
	private PagerTabStrip pagerTabStrip;// 一个viewpager的指示器，效果就是一个横的粗的下划线
	private List<View> viewList;// 把需要滑动的页卡添加到这个list中
	private List<String> titleList;// viewpager的标题
	private Button weibo_button;// button对象，一会用来进入第二个Viewpager的示例
	private Intent intent;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewpage);
		initView();
	}

	/*
	 * 在这里需要说明一下，在上面的图片中我们看到了，PagerTabStrip，PagerTitleStrip，他们其实是viewpager的一个指示器，
	 * 前者效果就是一个横的粗的下划线
	 * ，后者用来显示各个页卡的标题，当然而这也可以共存。在使用他们的时候需要注意，看下面的布局文件，要在android.support
	 * .v4.view.ViewPager里面添加
	 * android.support.v4.view.PagerTabStrip以及android.support
	 * .v4.view.PagerTitleStrip。
	 */
	private void initView() {
		viewPager = (ViewPager) findViewById(R.id.viewpager);
		// pagerTitleStrip = (PagerTitleStrip) findViewById(R.id.pagertitle);
		pagerTabStrip = (PagerTabStrip) findViewById(R.id.pagertab);
		pagerTabStrip.setTabIndicatorColor(getResources().getColor(
				android.R.color.primary_text_dark));
		pagerTabStrip.setDrawFullUnderline(false);
		pagerTabStrip.setBackgroundColor(getResources().getColor(
				android.R.color.white));
		pagerTabStrip.setTextSpacing(50);
		/*
		 * weibo_button=(Button) findViewById(R.id.button1);
		 * weibo_button.setOnClickListener(new OnClickListener() {
		 * 
		 * public void onClick(View v) { intent=new
		 * Intent(ViewPagerDemo.this,WeiBoActivity.class);
		 * startActivity(intent); } });
		 */

		view1 = findViewById(R.layout.viewpageitem1);
		view2 = findViewById(R.layout.viewpageitem2);
		view3 = findViewById(R.layout.viewpageitem3);

		LayoutInflater lf = getLayoutInflater().from(this);
		view1 = lf.inflate(R.layout.viewpageitem1, null);
		view2 = lf.inflate(R.layout.viewpageitem2, null);
		view3 = lf.inflate(R.layout.viewpageitem3, null);

		viewList = new ArrayList<View>();// 将要分页显示的View装入数组中
		viewList.add(view1);
		viewList.add(view2);
		viewList.add(view3);

		titleList = new ArrayList<String>();// 每个页面的Title数据
		titleList.add("wp");
		titleList.add("jy");
		titleList.add("jh");

		PagerAdapter pagerAdapter = new PagerAdapter() {
			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				return arg0 == arg1;
			}

			@Override
			public int getCount() {
				return viewList.size();
			}

			@Override
			public void destroyItem(ViewGroup container, int position,Object object) {
				container.removeView(viewList.get(position));
			}

			@Override
			public int getItemPosition(Object object) {
				return super.getItemPosition(object);
			}

			@Override
			public CharSequence getPageTitle(int position) {
				return titleList.get(position);// 直接用适配器来完成标题的显示，所以从上面可以看到，我们没有使用PagerTitleStrip。当然你可以使用。
			}

			@Override
			public Object instantiateItem(ViewGroup container, int position) {
				container.addView(viewList.get(position));
				if(position==1){
					 weibo_button=(Button)findViewById(R.id.button1);//这个需要注意，我们是在重写adapter里面实例化button组件的，如果你在onCreate()方法里这样做会报错的。
					 weibo_button.setOnClickListener(new OnClickListener() {
					
						 public void onClick(View v) {
							 intent=new Intent(ViewPagerActivity.this,ViewPagerActivity1.class);
							 startActivity(intent);
						 }
					 });
				}
				return viewList.get(position);
			}

		};
		viewPager.setAdapter(pagerAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}