package com.lxzh123.demo.buttonflash;

import com.lxzh123.demo.testview.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class ButtonFlashActivity extends Activity {
	private Animation showImage;   
	private Animation hideImage;   
	Button button;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buttonflash);
        showImage=AnimationUtils.loadAnimation(this, R.anim.showimage);
    	hideImage=AnimationUtils.loadAnimation(this, R.anim.hideimage);
    	showImage.setDuration(500);
    	hideImage.setDuration(500);
    	showImage.setFillAfter(true);
    	//hideImage.setFillAfter(true);
    	button=(Button)findViewById(R.id.button);
    	button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//button.startAnimation(hideImage);
				button.startAnimation(showImage);
			}
		});
    	
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
