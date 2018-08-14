package com.lxzh123.demo.webview;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.lxzh123.demo.testview.R;

public class WebviewActivity extends Activity {

    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_webview);
        webView=(WebView)findViewById(R.id.webView);

        /**
         * 加载asset中的html文件
         * baseUrl:file:///android_asset/
         * filePath: xxx.html
         */
        webView.loadUrl("file:///android_asset/html/page.html");

        WebSettings webSettings=webView.getSettings();
        webSettings.setJavaScriptEnabled(true);//支持js
        webSettings.setDefaultTextEncodingName("UTF-8");//设置字符编码

        webView.setScrollBarStyle(0);//设置滚动条风格，0:指滚动条不占用空间，直接覆盖在网页上


    }
}
