package com.lxzh123.demo.lineartext;

import java.util.Date;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.Shader.TileMode;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

public class LyricTextView extends TextView implements Runnable{
	private String text;
	private Date startTime;
	private int totalSecond=10;
	private float textWidth;
	private float textHeight;
	private float finishWidth;
	private Paint paint;
	private boolean isStart=false;
	private int width;
	private int height;
	
	public LyricTextView(Context context) {
		super(context);
	}

//    @SuppressLint("NewApi")
//	public LyricTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//		super(context, attrs, defStyleAttr, defStyleRes);
//	}

	public LyricTextView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public LyricTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		textHeight = getTextSize();
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		this.width=getMeasuredWidth();
		this.height=getMeasuredHeight();
		Log.d("onMeasure", "width="+width+",height="+height);
	}

	public int getTotalSecond() {
		return totalSecond;
	}

	public void setTotalSecond(int totalSecond) {
		this.totalSecond = totalSecond;
	}

	// 设置走马灯的文字内容
    public void setText(String content) {
        this.text=content;
        if(content.equals("")){
        	isStart=false;
        }else{
			paint = new Paint();
//			textHeight = getTextSize();
			if (textHeight > height) {
				textHeight = height;
			}
			paint.setTextSize(getTextSize());
			paint.setColor(Color.BLUE);
			paint.setFlags(Paint.ANTI_ALIAS_FLAG);
			textWidth = paint.measureText(text);
			if (textWidth > width) {
				textWidth = width;
			}
			Log.d("setText", "textWidth="+textWidth);
			Log.d("setText", "textHeight="+textHeight);
			isStart = true;
			startDraw();
			Log.d("setText", "startDraw");
		}
    }
    public void startDraw() {
        Thread thread = new Thread(this);
        thread.start();
        startTime=new Date();
    }

    public void stopDraw(){
    	isStart=false;
    }
    
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if(isStart){
			Shader lyricShader=getPaintShader(finishWidth);
			paint.setShader(lyricShader);
			paint.measureText(text);
			canvas.drawText(text, (width-textWidth)/2, (height-textHeight)/2, paint);
			Log.d("onDraw", "finishWidth="+finishWidth);
		}
	}

	@Override
	public void run() {
		try{
			while(true){
				Thread.sleep(100);
				long time=(new Date().getTime()-startTime.getTime());
				if(time>totalSecond*1000){
					time%=(totalSecond*1000);
				}
				finishWidth=textWidth*time/(totalSecond*1000F);
				postInvalidate();
			}
		}catch(Exception ex){
			
		}
	}
	
	public Shader getPaintShader(float finishWidth) {
		Shader shader = new LinearGradient(0, 0, (width-textWidth)/2+finishWidth, 0,
				new int[] { Color.YELLOW, Color.BLUE }, new float[] {0.99f, 1 }, TileMode.CLAMP);
		return shader;
	}
}
