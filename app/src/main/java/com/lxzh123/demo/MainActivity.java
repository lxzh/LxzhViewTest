package com.lxzh123.demo;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

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
import com.lxzh123.demo.testview.R;
import com.lxzh123.demo.viewflipper.ViewFlipperActivity;
import com.lxzh123.demo.viewpager.ViewPagerActivity;
import com.lxzh123.demo.webview.WebviewActivity;
import com.lxzh123.demo.wechatbtn.WeChatButtonActivity;

public class MainActivity extends ListActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        String[] funcStr=new String[]{
            "ButtonFlash",
            "AlertDialog",
            "ViewFlipper",
            "PendingSkip",
            "SkipActivity",
            "AdapterView",
            "CycleWheelView",
            "ShapeView",
            "Seekbar",
            "RadioButton",
            "ViewPager",
            "ProgressAnim",
            "LinearText",
            "Rotate1",
            "Rotate2",
            "WeChatBtn",
            "SwitchView",
            "WebView"};
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, R.layout.main,funcStr);
        this.setListAdapter(adapter);
        this.getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                switch (position) {
                    case 0:
                        intent.setClass(MainActivity.this, ButtonFlashActivity.class);
                        break;
                    case 1:
                        intent.setClass(MainActivity.this, DialogActivity.class);
                        break;
                    case 2:
                        intent.setClass(MainActivity.this, ViewFlipperActivity.class);
                        break;
                    case 3:
                        intent.setClass(MainActivity.this, PendingSkipActivity1.class);
                        break;
                    case 4:
                        intent.setClass(MainActivity.this, SkipActivity1.class);
                        break;
                    case 5:
                        intent.setClass(MainActivity.this, AdapterViewActivity.class);
                        break;
                    case 6:
                        intent.setClass(MainActivity.this, CycleWheelViewActivity.class);
                        break;
                    case 7:
                        intent.setClass(MainActivity.this, ShapeViewActivity.class);
                        break;
                    case 8:
                        intent.setClass(MainActivity.this, SeekbarActivity.class);
                        break;
                    case 9:
                        intent.setClass(MainActivity.this, RadioButtonActivity.class);
                        break;
                    case 10:
                        intent.setClass(MainActivity.this, ViewPagerActivity.class);
                        break;
                    case 11:
                        intent.setClass(MainActivity.this, ProgressAnimActivity.class);
                        break;
                    case 12:
                        intent.setClass(MainActivity.this, LinearTextActivity.class);
                        break;
                    case 13:
                        intent.setClass(MainActivity.this, Rotate3D1Activity.class);
                        break;
                    case 14:
                        intent.setClass(MainActivity.this, Rotate3D2Activity.class);
                        break;
                    case 15:
                        intent.setClass(MainActivity.this, WeChatButtonActivity.class);
                        break;
                    case 16:
                        intent.setClass(MainActivity.this, SwitchViewActivity.class);
                        break;
                    case 17:
                        intent.setClass(MainActivity.this, WebviewActivity.class);
                        break;
                    default:
                        return;
                }
                startActivity(intent);
            }
        });
	}
}
