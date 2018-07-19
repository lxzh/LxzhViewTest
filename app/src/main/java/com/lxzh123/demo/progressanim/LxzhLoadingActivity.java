package com.lxzh123.demo.progressanim;


import com.lxzh123.demo.testview.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LxzhLoadingActivity extends Activity {
	private SuperLoadingProgress mSuperLoadingProgress;
	private LoadingView mLoadingView;
	private EditText txtInterval;
	private boolean isStart=false;
	Thread thread;
	private int interval=10;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_activity_loading);
		mSuperLoadingProgress=(SuperLoadingProgress) findViewById(R.id.superLoadingProgress);
		mLoadingView=(LoadingView) findViewById(R.id.loadingView);
		txtInterval=(EditText) findViewById(R.id.etInterval);
	}
	
	public void onButtonClicked(View view){
		switch(view.getId()){
		case R.id.btnSucceed:
			new Thread(){
                @Override
                public void run() {
                    try {
                        mSuperLoadingProgress.setProgress(0);
                        while(mSuperLoadingProgress.getProgress()<100) {
                            Thread.sleep(interval);
                            mSuperLoadingProgress.setProgress(mSuperLoadingProgress.getProgress() + 1);
                        }
                        mSuperLoadingProgress.finishSuccess();
                        
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
            if(thread!=null&&thread.isAlive()){
            	thread.interrupt();
            }
            thread=new Thread(){
                @Override
                public void run() {
                    try {
                    	mLoadingView.setStop();
                    	mLoadingView.setStart();

                    	for(int i=1;i<=100;i++){
                    		Thread.sleep(20);
                    		mLoadingView.setProgress(i);
                    	}
                    	mLoadingView.setResult(true);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            thread.start();
			break;
		case R.id.btnFailed:
			new Thread(){
                @Override
                public void run() {
                    try {
                        mSuperLoadingProgress.setProgress(0);
                        while(mSuperLoadingProgress.getProgress()<100) {
                            Thread.sleep(10);
                            mSuperLoadingProgress.setProgress(mSuperLoadingProgress.getProgress() + 1);
                        }
                        mSuperLoadingProgress.finishFail();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
            new Thread(){
                @Override
                public void run() {
                    try {
                    	mLoadingView.setStop();
                    	mLoadingView.setStart();

                    	for(int i=1;i<=100;i++){
                    		Thread.sleep(20);
                    		mLoadingView.setProgress(i);
                    	}
                    	mLoadingView.setResult(false);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
			break;
		case R.id.btnSet:
			String txt=txtInterval.getText().toString();
			interval=Integer.parseInt(txt);
			mLoadingView.setInterval(interval);
			break;
		}
	}

}
