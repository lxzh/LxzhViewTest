package com.lxzh123.demo.viewflipper;
 
 import com.lxzh123.demo.testview.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;
 
 public class ViewFlipperActivity extends Activity implements OnGestureListener{
     // ViewFlipper实例
	 ViewFlipper flipper;
     // 定义手势检测器实例
     GestureDetector detector;
     //定义一个动画数组，用于为ViewFlipper指定切换动画效果
     Animation[] animations = new Animation[4];
     //定义手势动作两点之间的最小距离
     final int FLIP_DISTANCE = 50;
     @Override
     public void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.viewflipper);
         View view1=View.inflate(this, R.layout.viewflipper_01, null);
         View view2=View.inflate(this, R.layout.viewflipper_02, null);
         //创建手势检测器
         detector = new GestureDetector(this);
      // 获得ViewFlipper实例
         flipper = (ViewFlipper)findViewById(R.id.flipper);
      // 为ViewFlipper添加5个ImageView组件
         flipper.addView(view1);
         flipper.addView(view2);
//         flipper.addView(addImageView(R.drawable.guide01));
//         flipper.addView(addImageView(R.drawable.guide02));
//         flipper.addView(addImageView(R.drawable.guide03));
//         flipper.addView(addImageView(R.drawable.guide04));
//         flipper.addView(addImageView(R.drawable.guide05));
         //初始化Animation数组
         animations[0] = AnimationUtils.loadAnimation(this,R.anim.left_in);
         animations[1] = AnimationUtils.loadAnimation(this,R.anim.left_out);
         animations[2] = AnimationUtils.loadAnimation(this,R.anim.right_in);
         animations[3] = AnimationUtils.loadAnimation(this,R.anim.right_out);
 
     }
     // 定义添加ImageView的工具方法
     private View addImageView(int resId){
         ImageView imageView = new ImageView(this);
         imageView.setImageResource(resId);
         imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
         return imageView;
     }
     
     @Override
     public boolean onDown(MotionEvent arg0) {
         // TODO Auto-generated method stub
         return false;
     }
     @Override
     public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX,
             float velocityY) {
         /*
          * 如果第一个触点事件的X座标大于第二个触点事件的X座标超过FLIP_DISTANCE
          * 也就是手势从右向左滑。
 */
         if (event1.getX() - event2.getX() > FLIP_DISTANCE)
         {
             // 为flipper设置切换的的动画效果
             flipper.setInAnimation(animations[0]);
             flipper.setOutAnimation(animations[1]);
             flipper.showPrevious();
             return true;
         }
         /*
          * 如果第二个触点事件的X座标大于第一个触点事件的X座标超过FLIP_DISTANCE 
          * 也就是手势从右向左滑。
 */
         else if (event2.getX() - event1.getX() > FLIP_DISTANCE)
         {
             // 为flipper设置切换的的动画效果
             flipper.setInAnimation(animations[2]);
             flipper.setOutAnimation(animations[3]);
             flipper.showNext();
             return true;
         }
         return false;
     }
     @Override
     public void onLongPress(MotionEvent event) {
 
     }
     @Override
     public boolean onScroll(MotionEvent arg0, MotionEvent arg1, float arg2,
             float arg3) {
         return false;
     }
     @Override
     public void onShowPress(MotionEvent arg0) {
         
     }
     @Override
     public boolean onSingleTapUp(MotionEvent event) {
         return false;
     }
 
     @Override
     public boolean onTouchEvent(MotionEvent event) {
         //将该Activity上的触碰事件交给GestureDetector处理
         return detector.onTouchEvent(event);
     }
 }