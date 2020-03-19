package com.lxzh123.demo.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.lxzh123.demo.testview.R;

public class DialogTestActivity extends Activity {
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_test);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_left:
                showCustomDialog(R.style.DialogLeft, Gravity.LEFT);
                break;
            case R.id.btn_top:
                showCustomDialog(R.style.DialogTop, Gravity.TOP);
                break;
            case R.id.btn_right:
                showCustomDialog(R.style.DialogRight, Gravity.RIGHT);
                break;
            case R.id.btn_bottom:
                showCustomDialog(R.style.DialogBottom, Gravity.BOTTOM);
                break;
            case R.id.btn_center:
                showCustomDialog(R.style.DialogCenter, Gravity.CENTER);
                break;
        }
    }

    private void showCustomDialog(int style, int gravity) {
        View view = LayoutInflater.from(this).inflate(R.layout.layout_custom_dialog, null);
        dialog = new Dialog(this,style);
        dialog.setContentView(view);
//        AlertDialog.Builder builder = new AlertDialog.Builder(this, style);
//        builder.setTitle("对话框")
//                .setMessage("测试底部弹出式对话框")
//                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//            }
//        });
//        dialog = builder.create();

        //获取当前Activity所在的窗体
        Window dialogWindow = dialog.getWindow();
        //设置Dialog从窗体底部弹出
        dialogWindow.setGravity(gravity);
        //获得窗体的属性
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
//        lp.y = 20;//设置Dialog距离底部的距离
        lp.width = 800;
        lp.height = 1000;
////       将属性设置给窗体
        dialogWindow.setAttributes(lp);
        dialog.show();//显示对话框
    }
}
