package com.lxzh123.demo.progressanim;

import com.lxzh123.demo.testview.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

public class RoundProgressBarActivity extends Activity {
    /** Called when the activity is first created. */
	
    private Button mBtnAddMain;
    private Button mBtnAddSub;
    private ImageButton mImageBtn;
    
    private Button mBtnStart;
    private Button mBtnStop;
    
    private RoundProgressBar mRoundProgressBar1;
    private RoundProgressBar mRoundProgressBar2;
    private RoundProgressBar mRoundProgressBar3;
    private RoundProgressBar mRoundProgressBar4;
    private RoundProgressBar mRoundProgressBar5;
   
    int progress = 0;
	int secondaryProgress = 0;
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_round_progressbar);
       
        init();
    }
  
    public void init()
    {
    	mRoundProgressBar1 = (RoundProgressBar) findViewById(R.id.roundBar1);
    	mRoundProgressBar2 = (RoundProgressBar) findViewById(R.id.roundBar2);
    	mRoundProgressBar3 = (RoundProgressBar) findViewById(R.id.roundBar3); 
    	mRoundProgressBar4 = (RoundProgressBar) findViewById(R.id.roundBar4);
    	mRoundProgressBar5 = (RoundProgressBar) findViewById(R.id.roundBar5);
	
    	//mRoundProgressBar1.setVisibility(View.INVISIBLE);
    	//mRoundProgressBar2.setVisibility(View.INVISIBLE);
    	//mRoundProgressBar3.setVisibility(View.INVISIBLE);
    	//mRoundProgressBar4.setVisibility(View.INVISIBLE);
    	
    	mBtnAddMain = (Button)findViewById(R.id.buttonAddMainPro);
    	mBtnAddMain.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				addMain();				
				//mRoundProgressBar3.startCartoom(10);
			}
          });
    	mBtnAddSub = (Button)findViewById(R.id.buttonAddSubPro);
    	mBtnAddSub.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				addSub();			
				//mRoundProgressBar3.stopCartoom();
			}
		});

    	mImageBtn = (ImageButton)findViewById(R.id.buttonImage);
    	mImageBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				int w = mImageBtn.getWidth();
		    	int h = mImageBtn.getHeight();
		    	
		    	Log.i("", "mImageBtn.getWidth() = " + w + 
		    			", mImageBtn.getHeight() = " + h);
	    	
		    	progress = 0;
		    	secondaryProgress = 0;
		    	
		    	mRoundProgressBar1.setProgress(0);
		    	mRoundProgressBar2.setProgress(0);
		    	mRoundProgressBar3.setProgress(0);
		    	mRoundProgressBar4.setProgress(0);
	    	
		    	mRoundProgressBar1.setSecondaryProgress(0);
		    	mRoundProgressBar2.setSecondaryProgress(0);
		    	mRoundProgressBar3.setSecondaryProgress(0);
		    	mRoundProgressBar4.setSecondaryProgress(0);
		   
			}
		});	
        mBtnStart = (Button) findViewById(R.id.buttonStart);
        mBtnStart.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mRoundProgressBar5.startCartoom(6);
			}
		});
        mBtnStop = (Button) findViewById(R.id.buttonStop);
        mBtnStop.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mRoundProgressBar5.stopCartoom();
			}
		});    	
    }
       
    public void addMain()
    {  	
    	progress += 2;
    	if (progress > 100)
    	{
    		progress = 0;
    	}   	
    	mRoundProgressBar1.setProgress(progress);
    	mRoundProgressBar2.setProgress(progress*2);
    	mRoundProgressBar3.setProgress(progress*3);
    	mRoundProgressBar4.setProgress(progress*4);

    }   
    public void addSub()
    {
    	secondaryProgress += 3;
    	if (secondaryProgress > 100)
    	{
    		secondaryProgress = 0;
    	}   	
    	mRoundProgressBar1.setSecondaryProgress(secondaryProgress);
    	mRoundProgressBar2.setSecondaryProgress(secondaryProgress);
    	mRoundProgressBar3.setSecondaryProgress(secondaryProgress);
    	mRoundProgressBar4.setSecondaryProgress(secondaryProgress);
    }   
}