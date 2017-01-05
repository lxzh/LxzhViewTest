package com.lxzh123.demo.progressanim;

import java.util.Date;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.RegionIterator;
import android.graphics.Paint.Cap;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.LinearInterpolator;

public class LoadingView extends View implements Runnable {

	/**
	 * 当前进度
	 */
	private int progress = 0;
	/**
	 * 0画圆,1抛出方块,2下落变粗,挤压圆形,3,绘制三叉，圆形恢复,4,绿色勾,5,红色感叹号出现，6,红色感叹号震动 
	 * 0:进度周期 1：抛出小球 2:下落变粗，挤压圆形 3：绘制三叉，圆形恢复/红色感叹号出现 4：绿色勾/红色感叹号震动
	 */
	private int status = -1;
	/**
	 * 是否成功
	 */
	private boolean isSuccess = true;
	private boolean isLoadingFinished=false;

	/**
     * 
     */
	private float radius = 0;
	private int interval = 1;

	/**
	 * 最大进度
	 */
	private static final int maxProgress = 100;
	private Date startTime;
	
	private int firstAngle = 0;
	private int lastAngle = 0;
	private int fast = 8;// 快速速度
	private int slow = 4; // 慢速速度
	private boolean isFirstFast = true;// 圆弧首端快

	private int mWidth, mHeight;
	private float centerX,centerY;
	private RectF mRectF = new RectF();
	/**
	 * 画笔宽度
	 */
	private int strokeWidth = 20;
	/**
	 * 起始角度
	 */
	private float startAngle =0;
	/**
	 * 结束角度
	 */
    private float endAngle = 0;
    /**
     * 震动角度
     */
    private int shockAngle = 20;
	/**
	 * 扫过角度
	 */
	private float curSweepAngle = 0;
	/**
	 * 下落百分比
	 * 
	 * @param context
	 */
	private float downPrecent = 0;
	/**
	 * 分叉百分比
	 * 
	 * @param context
	 */
	private float forkPrecent = 0;
	/**
	 * 打钩百分比
	 * 
	 * @param context
	 */
	private float tickPrecent = 0;
	/**
	 * 感叹号百分比
	 * 
	 * @param context
	 */
	private float commaPrecent = 0;
	/**
	 * 震动百分比
	 * 
	 * @param context
	 */
	private int shockPrecent = 0;
	//
	private Paint circlePaint;
	private Paint sCirclePaint1;
	private Paint sCirclePaint2;
	private Paint downRectPaint;
	private Paint commaPaint;
	private Paint tickPaint;
	
	/**
     * 测量下落路径
     */
    private PathMeasure downPathMeasure1;
    private PathMeasure downPathMeasure2;
    /**
     * 测量分叉
     */
    private PathMeasure forkPathMeasure1;
    private PathMeasure forkPathMeasure2;
    private PathMeasure forkPathMeasure3;
    /**
     * 测量打钩
     */
    private PathMeasure tickPathMeasure;
    /**
     * 感叹号
     */
    private PathMeasure commaPathMeasure1;
    private PathMeasure commaPathMeasure2;
	
	/**
	 * 小方块抛出动画
	 */
	private ValueAnimator mCastAnimation;
	/**
	 * 小方块下落
	 */
	private ValueAnimator mDownAnimation;
	/**
	 * 小方块下落
	 */
	private ValueAnimator mForkAnimation;
	/**
	 * 打钩
	 */
	private ValueAnimator mTickAnimation;
	/**
	 * 绘制感叹号
	 */
	private ValueAnimator mshockAnimation;
	/**
	 * 绘制感叹号震动
	 */
	private ValueAnimator mCommaAnimation;
	/**
	 * 定义一个渲染
	 */
	private SweepGradient sweepGradient = null;
	private int[] colors;

	private Thread thread;

	// private Paint

	public void setStart() {
		startTime = new Date();
		status = 0;
		isLoadingFinished=false;
		thread = new Thread(this);
		thread.start();
	}

	public void setStop() {
		status = -1;
	}

	public void setInterval(int n) {
		this.interval = n;
	}

	public void setProgress(int value) {
		if (status < 1) {
			// int tmp=this.progress;
			this.progress = Math.min(maxProgress, value);
			//strokeWidth = (int) (radius * progress / 100);
			//circlePaint.setStrokeWidth(strokeWidth);
			// if(tmp!=this.progress){
			// postInvalidate();
			// if(this.progress==0){
			status = 0;
			// }else if(this.progress<maxProgress){
			// status=1;
			// }
			// }
			// if(thread!=null&thread.isAlive()){
			// thread.interrupt();
			// thread.start();
			// }
		}
	}
    /**
     * loading结束后调用
     */
	public void setResult(boolean isSuccess) {
		this.isSuccess = isSuccess;
		status = 1;
		this.progress=maxProgress;
	}
	
	public LoadingView(Context context) {
		super(context);
		init();
	}

	public LoadingView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public LoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	private void init() {
		circlePaint = new Paint();
		circlePaint.setColor(Color.argb(255, 48, 63, 159));
		circlePaint.setAntiAlias(true);
		circlePaint.setStrokeWidth(strokeWidth);
		circlePaint.setStyle(Paint.Style.STROKE);// 设置空心
		colors = new int[2];
//		circlePaint.setStrokeJoin(Paint.Join.ROUND); // 前圆角
		circlePaint.setStrokeCap(Cap.ROUND); // 后圆角

		sCirclePaint1 = new Paint();
		sCirclePaint1.setColor(Color.argb(255, 229, 57, 53));//失败 红
		sCirclePaint1.setAntiAlias(true);

		sCirclePaint2 = new Paint();
		sCirclePaint2.setColor(Color.argb(255, 0, 150, 136));//成功 绿 
		sCirclePaint2.setAntiAlias(true);

		colors[0] = sCirclePaint1.getColor();
		colors[1] = sCirclePaint2.getColor();

		downRectPaint = new Paint();	
        downRectPaint.setAntiAlias(true);
        downRectPaint.setColor(Color.argb(255, 48, 63, 159));
        downRectPaint.setStrokeWidth(strokeWidth);
        downRectPaint.setStyle(Paint.Style.FILL);
		
        commaPaint = new Paint();
        commaPaint.setAntiAlias(true);
        commaPaint.setColor(Color.argb(255, 229, 57, 53));
        commaPaint.setStrokeWidth(strokeWidth);
        commaPaint.setStyle(Paint.Style.STROKE);
		
        tickPaint = new Paint();
        tickPaint.setColor(Color.argb(255, 0, 150, 136));
        tickPaint.setAntiAlias(true);
        tickPaint.setStrokeWidth(strokeWidth);
        tickPaint.setStyle(Paint.Style.STROKE);
		
		initAnima();
	}

	private void initAnima() {
		// 抛出动画
		endAngle = (float) Math.atan(4f / 3);
		mCastAnimation = ValueAnimator.ofFloat(0f, endAngle * 0.9f);
		mCastAnimation.setDuration(500);
		mCastAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
		mCastAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				curSweepAngle = Float.parseFloat(animation.getAnimatedValue()+"");
				invalidate();
			}
		});
		mCastAnimation.addListener(new AnimatorListenerAdapter() {
			@Override
			public void onAnimationEnd(Animator animation) {
				super.onAnimationEnd(animation);
				curSweepAngle = 0;
				if (isSuccess) {
					status = 2;
					Log.d("2", "mDownAnimation.start()");
					mDownAnimation.start();
				} else {
					status = 5;
					Log.d("5", "mCommaAnimation.start()");
					mCommaAnimation.start();
				}
			}
		});

		// 下落动画
		mDownAnimation = ValueAnimator.ofFloat(0f, 1f);
		mDownAnimation.setDuration(500);
		mDownAnimation.setInterpolator(new AccelerateInterpolator());
		mDownAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				downPrecent = Float.parseFloat(animation.getAnimatedValue()+"");
				invalidate();
			}
		});

		mDownAnimation.addListener(new AnimatorListenerAdapter() {
			@Override
			public void onAnimationEnd(Animator animation) {
				super.onAnimationEnd(animation);
				status = 3;
				Log.d("3", "mForkAnimation.start()");
				mForkAnimation.start();
			}
		});

		// 分叉动画
		mForkAnimation = ValueAnimator.ofFloat(0f, 1f);
		mForkAnimation.setDuration(100);
		mForkAnimation.setInterpolator(new LinearInterpolator());
		mForkAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				forkPrecent = Float.parseFloat(animation.getAnimatedValue()+"");
				invalidate();
			}
		});

		mForkAnimation.addListener(new AnimatorListenerAdapter() {
			@Override
			public void onAnimationEnd(Animator animation) {
				super.onAnimationEnd(animation);
				mTickAnimation.start();
			}
		});

		// 打钩动画
		mTickAnimation = ValueAnimator.ofFloat(0f, 1f);
		mTickAnimation.setStartDelay(1000);
		mTickAnimation.setDuration(500);
		mTickAnimation.setInterpolator(new AccelerateInterpolator());
		mTickAnimation.addListener(new AnimatorListenerAdapter() {
			@Override
			public void onAnimationStart(Animator animation) {
				super.onAnimationStart(animation);
				status = 4;
				Log.d("4", "mTickAnimation.start()");
			}
		});
		mTickAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				tickPrecent = Float.parseFloat(animation.getAnimatedValue()+"");
				invalidate();
			}
		});

		
		// 感叹号动画
		mCommaAnimation = ValueAnimator.ofFloat(0f, 1f);
		mCommaAnimation.setDuration(300);
		mCommaAnimation.setInterpolator(new AccelerateInterpolator());
		mCommaAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				commaPrecent = Float.parseFloat(animation.getAnimatedValue()+"");
				invalidate();
			}
		});

		mCommaAnimation.addListener(new AnimatorListenerAdapter() {
			@Override
			public void onAnimationEnd(Animator animation) {
				super.onAnimationEnd(animation);
				status = 6;
				Log.d("6", "mshockAnimation.start()");
				mshockAnimation.start();
			}
		});

		// 震动动画
		mshockAnimation = ValueAnimator.ofInt(-1, 0, 1, 0, -1, 0, 1, 0, -1, 0, 1, 0);
		mshockAnimation.setDuration(500);
		mshockAnimation.setInterpolator(new LinearInterpolator());
		mshockAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				shockPrecent = Integer.parseInt(animation.getAnimatedValue()+"");
				invalidate();
			}
		});
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		mWidth = w;
		mHeight = h;
		radius = Math.min(getMeasuredWidth(), getMeasuredHeight()) / 4 - strokeWidth;
		mRectF.set(new RectF(radius + strokeWidth, radius * 1.5F + strokeWidth, 3 * radius + strokeWidth, 3.5F * radius
				+ strokeWidth));
		centerX=mRectF.left+mRectF.width()/2;
		centerY=mRectF.top+mRectF.height()/2;
		//初始化下落路径
        Path downPath1 = new Path();
        downPath1.moveTo(centerX, centerY-2*radius);
        downPath1.lineTo(centerX, centerY-radius);
        Path downPath2 = new Path();
        downPath2.moveTo(centerX, centerY-2*radius);
        downPath2.lineTo(centerX, centerY);
        downPathMeasure1 = new PathMeasure(downPath1,false);
        downPathMeasure2 = new PathMeasure(downPath2,false);
		// 初始化分叉路径
		Path forkpath1 = new Path();
		forkpath1.moveTo(centerX, centerY);
		forkpath1.lineTo(centerX, centerY+radius);
		float sin60 = (float) Math.sin(Math.PI / 3);
		float cos60 = (float) Math.cos(Math.PI / 3);
		Path forkpath2 = new Path();
		forkpath2.moveTo(centerX, centerY);
		forkpath2.lineTo(centerX - radius * sin60, centerY + radius * cos60);
		Path forkpath3 = new Path();
		forkpath3.moveTo(centerX, centerY);
		forkpath3.lineTo(centerX + radius * sin60, centerY + radius * cos60);
		forkPathMeasure1 = new PathMeasure(forkpath1,false);
        forkPathMeasure2 = new PathMeasure(forkpath2,false);
        forkPathMeasure3 = new PathMeasure(forkpath3,false);
        //初始化打钩路径
        Path tickPath = new Path();
        tickPath.moveTo(centerX-radius/2, centerY);
        tickPath.lineTo(centerX-radius/2 + 0.3f * radius, centerY + 0.3f * radius);
        tickPath.lineTo(centerX+0.5f * radius,centerY-0.3f * radius);
        tickPathMeasure = new PathMeasure(tickPath,false);
        //感叹号路径
        Path commaPath1 = new Path();
        Path commaPath2 = new Path();
        commaPath1.moveTo(centerX, centerY-0.75f * radius);
        commaPath1.lineTo(centerX, centerY+0.25f * radius);
        commaPath2.moveTo(centerX, centerY+0.75f * radius);
        commaPath2.lineTo(centerX, centerY+0.50f * radius);
        commaPathMeasure1 = new PathMeasure(commaPath1,false);
        commaPathMeasure2 = new PathMeasure(commaPath2,false);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
		int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
		int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
		int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
		setMeasuredDimension(widthSpecSize + 10 * strokeWidth, heightSpecSize + 10 * strokeWidth);
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
		super.onLayout(changed, left, top, right, bottom);
	}

	@Override
	public void run() {
		try {
			while (true) {
				postInvalidate(); // refresh
				Thread.sleep(interval);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void onDraw(Canvas canvas) {
		if(status>=0){
			if(!isLoadingFinished){
				// drawTwoBoll(canvas);
				drawProgress(canvas);
			}else{
				switch (status) {
				case 1:
					drawSmallRectFly(canvas);
					break;
				case 2:
					drawRectDown(canvas);
					break;
				case 3:
					drawFork(canvas);
					break;
				case 4:
					drawTick(canvas);
					break;
				case 5:
					drawComma(canvas);
					break;
				case 6:
					drawShockComma(canvas);
					break;
				default:

					break;
				}
			}
		}
		super.onDraw(canvas);
	}

	private void drawTwoBoll(Canvas canvas) {
		float w = mRectF.width();
		float h = mRectF.height();
		float R = radius;
		float r = R / 2;
		float xL = mRectF.left + R - r;
		float xR = mRectF.left + R + r;
		float y = mRectF.top + R;

		Date now = new Date();
		long delta = (now.getTime() - startTime.getTime()) % 1000;
		float angle;

		float x1, x2, y1, y2;
		float deltaX, deltaY;
		angle = (delta % 250) * (float) (Math.PI / 25 / 10);
		deltaX = (float) Math.sqrt(400 / (16 + 25 * Math.pow(Math.tan(angle), 2))) * r / 5;
		deltaY = (float) Math.abs(Math.tan(angle)) * deltaX;
		if (delta < 250) {
			x1 = xR + deltaX * (delta < 125 ? 1 : -1);
			y1 = y + deltaY;
		} else if (delta < 500) {
			x1 = xL + deltaX * (delta < 375 ? 1 : -1);
			y1 = y - deltaY;
		} else if (delta < 750) {
			x1 = xL + deltaX * (delta < 625 ? -1 : 1);
			y1 = y + deltaY;
		} else {
			x1 = xR + deltaX * (delta < 875 ? -1 : 1);
			y1 = y - deltaY;
		}
		x2 = xL + xR - x1;
		y2 = y1;

		canvas.drawCircle(x1, y1, 20, sCirclePaint1);
		canvas.drawCircle(x2, y2, 20, sCirclePaint2);
	}

	// 画圆
	private void drawProgress(Canvas canvas) {
		/*
		 * //startAngle=-90+3.6F*progress; // if(isFirstFast){ //
		 * firstAngle-=fast; // lastAngle-=slow; // }else{ // firstAngle-=slow;
		 * // lastAngle-=fast; // } // if(firstAngle<lastAngle){ //
		 * canvas.drawArc(mRectF, lastAngle, firstAngle, false, circlePaint); //
		 * }else{ // canvas.drawArc(mRectF, lastAngle, firstAngle, false,
		 * circlePaint); // } // if(Math.abs(firstAngle-lastAngle)%360<2){ //
		 * firstAngle=lastAngle=firstAngle%360; // isFirstFast=!isFirstFast; //
		 * }
		 */
		
		if (isFirstFast) {
			firstAngle -= fast;
			lastAngle -= slow;
		} else {
			firstAngle -= slow;
			lastAngle -= fast;
		}
		float angle1 = (float) (firstAngle * Math.PI / 180);
		float angle2 = (float) (lastAngle * Math.PI / 180);
		float x1 = (float) (centerX + radius * Math.cos(angle1));
		float y1 = (float) (centerY + radius * Math.sin(angle1));
		float x2 = (float) (centerX + radius * Math.cos(angle2));
		float y2 = (float) (centerY + radius * Math.sin(angle2));
		LinearGradient shader;
		if (Math.abs(firstAngle - lastAngle) != 360) {
			shader = new LinearGradient(x1, y1, x2, y2, colors, null, TileMode.CLAMP);
		}else{
			shader = new LinearGradient(x1, y1-5, x2, y2+5, colors, null, TileMode.CLAMP);
		}
		circlePaint.setShader(shader);
		
		if (firstAngle < lastAngle) {
			canvas.drawArc(mRectF, firstAngle, lastAngle - firstAngle, false, circlePaint);
		} else {
			canvas.drawArc(mRectF, lastAngle, firstAngle - lastAngle, false, circlePaint);
		}

		canvas.drawCircle(x1, y1, strokeWidth / 2, sCirclePaint1);
		canvas.drawCircle(x2, y2, strokeWidth / 2, sCirclePaint2);

		if (Math.abs(firstAngle - lastAngle) == 360) {
			firstAngle = lastAngle = 0;
			isFirstFast = !isFirstFast;
			
			if(status>0){//状态不为0，说明加载结束，为了使加载动画完成最后一个周期
				if((isSuccess!=isFirstFast)){
					
					circlePaint.setColor(isSuccess?sCirclePaint2.getColor():sCirclePaint1.getColor());
//					canvas.drawArc(mRectF, -590, 150, false, circlePaint);
					isLoadingFinished=true;
					Log.d("isSuccess!=isFirstFast", "firstAngle="+firstAngle+",lastAngle="+lastAngle);
					post(new Runnable() {
			            @Override
			            public void run() {
			            	mCastAnimation.start();
			            	Log.d("1", "mCastAnimation.start()");
			            }
			        });
				}
			}
			
		}
	}

	/**
     * 抛出小球
     * @param canvas
     */
	private void drawSmallRectFly(Canvas canvas) {
		canvas.save();
        canvas.translate(centerX-1.5F*radius, centerY);//将坐标移动到大圆圆心
        float bigRadius = 5*radius/2;//大圆半径
        float x1 = (float) (bigRadius*Math.cos(curSweepAngle));
        float y1 = -(float) (bigRadius*Math.sin(curSweepAngle));
        float x2 = (float) (bigRadius*Math.cos(curSweepAngle+0.05*endAngle+0.1*endAngle*(1-curSweepAngle/0.9*endAngle)));//
        float y2 = -(float) (bigRadius*Math.sin(curSweepAngle+0.05*endAngle+0.1*endAngle*(1-curSweepAngle/0.9*endAngle)));

        canvas.drawCircle(x1, y1, 10, isSuccess?sCirclePaint2:sCirclePaint1);
        canvas.restore();
        //circlePaint.setColor(Color.argb(255, 48, 63, 159));
        canvas.drawArc(mRectF, 0, 360, false, circlePaint);
	}
	/**
     * 绘制下落变粗,挤压圆形过程
     * @param canvas
     */
	private void drawRectDown(Canvas canvas) {
		//下落方块的起始端坐标
        float pos1[] = new float[2];
        float tan1[] = new float[2];
        downPathMeasure1.getPosTan(downPrecent * downPathMeasure1.getLength(), pos1, tan1);
        //下落方块的末端坐标
        float pos2[] = new float[2];
        float tan2[] = new float[2];
        downPathMeasure2.getPosTan(downPrecent * downPathMeasure2.getLength(), pos2, tan2);
        //椭圆形区域
        Rect mRect = new Rect(Math.round(mRectF.left),Math.round(mRectF.top+mRectF.height()*0.1f*downPrecent),
                Math.round(mRectF.right),Math.round(mRectF.bottom-mRectF.height()*0.1f*downPrecent));
        
        downRectPaint.setColor(isSuccess?sCirclePaint2.getColor():sCirclePaint1.getColor());
        //非交集
        Region region1 = new Region(Math.round(pos1[0])-strokeWidth/4,Math.round(pos1[1]),Math.round(pos2[0]+strokeWidth/4),Math.round(pos2[1]));
        region1.op(mRect, Region.Op.DIFFERENCE);
        drawRegion(canvas, region1, downRectPaint);
        
        //交集
        Region region2 = new Region(Math.round(pos1[0])-strokeWidth/2,Math.round(pos1[1]),Math.round(pos2[0]+strokeWidth/2),Math.round(pos2[1]));
        boolean isINTERSECT = region2.op(mRect, Region.Op.INTERSECT);
        drawRegion(canvas, region2, downRectPaint);

        //椭圆形区域
        if(isINTERSECT) {//如果有交集
        	circlePaint.setShader(null);
            float extrusionPrecent = (pos2[1]-radius)/radius;
            RectF rectF = new RectF(mRectF.left, mRectF.top + mRectF.height() * 0.1f * extrusionPrecent, mRectF.right, mRectF.bottom - mRectF.height() * 0.1f * extrusionPrecent);
            canvas.drawArc(rectF, 0, 360, false, circlePaint);
        }else{
            canvas.drawArc(mRectF, 0, 360, false, circlePaint);
        }
	}
	/**
     * 绘制三叉，圆形恢复
     * @param canvas
     */
	private void drawFork(Canvas canvas) {
		float pos1[] = new float[2];
        float tan1[] = new float[2];
        forkPathMeasure1.getPosTan(forkPrecent * forkPathMeasure1.getLength(), pos1, tan1);
        float pos2[] = new float[2];
        float tan2[] = new float[2];
        forkPathMeasure2.getPosTan(forkPrecent * forkPathMeasure2.getLength(), pos2, tan2);
        float pos3[] = new float[2];
        float tan3[] = new float[2];
        forkPathMeasure3.getPosTan(forkPrecent * forkPathMeasure3.getLength(), pos3, tan3);
        
        canvas.drawLine(centerX, centerY-radius, centerX, centerY, downRectPaint);
        canvas.drawLine(centerX, centerY, pos1[0], pos1[1], downRectPaint);
        canvas.drawLine(centerX, centerY, pos2[0], pos2[1], downRectPaint);
        canvas.drawLine(centerX, centerY, pos3[0], pos3[1], downRectPaint);
        //椭圆形区域
        RectF rectF = new RectF(mRectF.left, mRectF.top + mRectF.height() * 0.1f * (1-forkPrecent), 
                mRectF.right, mRectF.bottom - mRectF.height() * 0.1f * (1-forkPrecent));
        canvas.drawArc(rectF, 0, 360, false, circlePaint);
	}

	/**
     * 绘制绿色钩
     * @param canvas
     */
	private void drawTick(Canvas canvas) {
		Path path = new Path();
        /*
         * On KITKAT and earlier releases, the resulting path may not display on a hardware-accelerated Canvas. 
         * A simple workaround is to add a single operation to this path, such as dst.rLineTo(0, 0).
         */
        tickPathMeasure.getSegment(0, tickPrecent * tickPathMeasure.getLength(), path, true);
        path.rLineTo(0, 0);
        canvas.drawPath(path, tickPaint);
        canvas.drawArc(mRectF, 0, 360, false, tickPaint);
	}

	 /**
     * 绘制感叹号
     * @param canvas
     */
	private void drawComma(Canvas canvas) {
		Path path1 = new Path();
        commaPathMeasure1.getSegment(0, commaPrecent * commaPathMeasure1.getLength(), path1, true);
        path1.rLineTo(0, 0);
        Path path2 = new Path();
        commaPathMeasure2.getSegment(0, commaPrecent * commaPathMeasure2.getLength(), path2, true);
        path2.rLineTo(0, 0);
        canvas.drawPath(path1, commaPaint);
        canvas.drawPath(path2, commaPaint);
        canvas.drawArc(mRectF, 0, 360, false, commaPaint);
	}

	/**
     * 绘制红色感叹号震动效果
     * @param canvas
     */
	private void drawShockComma(Canvas canvas) {
		Path path1 = new Path();
        commaPathMeasure1.getSegment(0, commaPathMeasure1.getLength(), path1, true);
        path1.rLineTo(0, 0);
        Path path2 = new Path();
        commaPathMeasure2.getSegment(0, commaPathMeasure2.getLength(), path2, true);
        path2.rLineTo(0, 0);
        
        if (shockPrecent!=0){
            canvas.save();
            if (shockPrecent==1)
                canvas.rotate(shockAngle, centerX-strokeWidth, centerY-strokeWidth);
            else if(shockPrecent==-1)
                canvas.rotate(-shockAngle, centerX-strokeWidth, centerY-strokeWidth);
        }
        canvas.drawPath(path1, commaPaint);
        canvas.drawPath(path2, commaPaint);
        canvas.drawArc(mRectF, 0, 360, false, commaPaint);
        if (shockPrecent!=0) {
            canvas.restore();
        }
	}
	
	/**
     * 绘制区域
     * @param canvas
     * @param rgn
     * @param paint
     */
    private void drawRegion(Canvas canvas,Region rgn,Paint paint) {
        RegionIterator iter = new RegionIterator(rgn);
        Rect r = new Rect();
        while (iter.next(r)) {
            canvas.drawRect(r, paint);
        }
    }
}
