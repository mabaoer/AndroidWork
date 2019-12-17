package com.baidu.track;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.TextView;

import com.baidu.track.activity.BaseActivity;

public class ItemActivity extends BaseActivity {

    String item;
    TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        item= getIntent().getStringExtra("title");
        title= (TextView) findViewById(R.id.title);
        title.setGravity(Gravity.CENTER);
        title.setText(item);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_item;
    }
}
