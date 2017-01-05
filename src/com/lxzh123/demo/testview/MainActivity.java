package com.lxzh123.demo.testview;

import com.lxzh123.demo.activityskip.SkipActivity1;
import com.lxzh123.demo.apadterview.AdapterViewActivity;
import com.lxzh123.demo.buttonflash.ButtonFlashActivity;
import com.lxzh123.demo.cyclewheelview.CycleWheelViewActivity;
import com.lxzh123.demo.dialog.DialogActivity;
import com.lxzh123.demo.lineartext.LinearTextActivity;
import com.lxzh123.demo.pendingskip.PendingSkipActivity1;
import com.lxzh123.demo.progressanim.ProgressAnimActivity;
import com.lxzh123.demo.radiobutton.RadioButtonActivity;
import com.lxzh123.demo.rotate1.Rotate3D1Activity;
import com.lxzh123.demo.rotate2.Rotate3D2Activity;
import com.lxzh123.demo.seekbar.SeekbarActivity;
import com.lxzh123.demo.shape.ShapeViewActivity;
import com.lxzh123.demo.switchview.SwitchViewActivity;
import com.lxzh123.demo.viewflipper.ViewFlipperActivity;
import com.lxzh123.demo.viewpager.ViewPagerActivity;
import com.lxzh123.demo.wechatbtn.WeChatButtonActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}
	//方法修饰符必须为public，private和protected都不行
	public void OnButtonClick(View v) {
		Intent intent = new Intent();
		switch (v.getId()) {
		case R.id.btnButtonFlash:
			intent.setClass(MainActivity.this, ButtonFlashActivity.class);
			break;
		case R.id.btnDialog:
			intent.setClass(MainActivity.this, DialogActivity.class);
			break;
		case R.id.btnViewFlipper:
			intent.setClass(MainActivity.this, ViewFlipperActivity.class);
			break;
		case R.id.btnPendingSkip:
			intent.setClass(MainActivity.this, PendingSkipActivity1.class);
			break;
		case R.id.btnSkipActivity:
			intent.setClass(MainActivity.this, SkipActivity1.class);
			break;
		case R.id.btnAdapterView:
			intent.setClass(MainActivity.this, AdapterViewActivity.class);
			break;
		case R.id.btnCycleWheelView:
			intent.setClass(MainActivity.this, CycleWheelViewActivity.class);
			break;
		case R.id.btnShapeView:
			intent.setClass(MainActivity.this, ShapeViewActivity.class);
			break;
		case R.id.btnSeekbar:
			intent.setClass(MainActivity.this, SeekbarActivity.class);
			break;
		case R.id.btnRadioButton:
			intent.setClass(MainActivity.this, RadioButtonActivity.class);
			break;
		case R.id.btnViewPager:
			intent.setClass(MainActivity.this, ViewPagerActivity.class);
			break;
		case R.id.btnProgressAnim:
			intent.setClass(MainActivity.this, ProgressAnimActivity.class);
			break;
		case R.id.btnLinearText:
			intent.setClass(MainActivity.this, LinearTextActivity.class);
			break;
		case R.id.btnRotate1:
			intent.setClass(MainActivity.this, Rotate3D1Activity.class);
			break;
		case R.id.btnRotate2:
			intent.setClass(MainActivity.this, Rotate3D2Activity.class);
			break;
		case R.id.btnWeChatBtn:
			intent.setClass(MainActivity.this, WeChatButtonActivity.class);
			break;
		case R.id.btnSwitchView:
			intent.setClass(MainActivity.this, SwitchViewActivity.class);
			break;
//		case R.id.btnRotate1:
//			intent.setClass(MainActivity.this, Rotate3D1Activity.class);
//			break;
//		case R.id.btnRotate2:
//			intent.setClass(MainActivity.this, Rotate3D2Activity.class);
//			break;
//		case R.id.btnWeChatBtn:
//			intent.setClass(MainActivity.this, WeChatButtonActivity.class);
//			break;
		default:
			return;
		}
		startActivity(intent);
	}
	
}
