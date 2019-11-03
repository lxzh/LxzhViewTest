package com.lxzh123.demo.filltext;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.lxzh123.demo.testview.R;


public class FillTextViewActivity extends Activity {

    private FillTextView fillTextView;
    private TextView tvFilles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_fill_text_view);
        fillTextView = (FillTextView) findViewById(R.id.fillText);
        tvFilles = (TextView) findViewById(R.id.tv_fills);
    }

    public void onClick(View view) {
        String t = "";
        for (String text : fillTextView.getFillTexts()) {
            t += text;
            t += ",";
        }
        tvFilles.setText(t.subSequence(0, t.length() - 1));
    }
}
