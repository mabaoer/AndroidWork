package com.baidu.track;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TabHost;
import android.widget.TextView;


import com.baidu.track.activity.BaseActivity;
import com.baidu.track.activity.MainActivity;
import com.baidu.track.activity.TrackQueryActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class indexActivity extends BaseActivity {
    TextView textView2;
    LinearLayout linearLayout;
    String string[] = {"心率", "血压", "行程", "活动强度", "睡眠","路径查询"};
    List<Map<String, Object>> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TabHost tabHost = (TabHost) findViewById(R.id.tabhost);
        tabHost.setup();
        LayoutInflater.from(this).inflate(R.layout.tabhost_index,
                tabHost.getTabContentView(), true);
        LayoutInflater.from(this).inflate(R.layout.tabhost_liulan,
                tabHost.getTabContentView(), true);
        LayoutInflater.from(this).inflate(R.layout.tabhost_my,
                tabHost.getTabContentView(), true);
        tabHost.addTab(tabHost.newTabSpec("TAB1")
                .setIndicator("首页").setContent(R.id.tabhost_index));
        tabHost.addTab(tabHost.newTabSpec("TAB2")
                .setIndicator("浏览").setContent(R.id.tabhost_liulan));
        tabHost.addTab(tabHost.newTabSpec("TAB2")
                .setIndicator("我的").setContent(R.id.tabhost_my));
        Intent intent = getIntent();
        User user = (User) intent.getSerializableExtra("user");
        textView2 = (TextView) findViewById(R.id.username);
        textView2.setText(user.getUsername());
        list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map;
        for (int i = 0; i < string.length; i++) {
            map = new HashMap<String, Object>();
            map.put("name", string[i]);
            list.add(map);
        }
        SimpleAdapter adapter = new SimpleAdapter(indexActivity.this, list, R.layout.lisyview,
                new String[]{"name"},
                new int[]{R.id.name});
        ListView listView = (ListView) findViewById(R.id.ListView01);
        listView.setAdapter(adapter);
        AdapterView.OnItemClickListener listViewListener = new
                AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                        //String msg = "父View：" + arg0.toString() + "\n" + "子View： " + arg1.toString() + "\n" + "位置：" + String.valueOf(arg2) + "，ID：" + String.valueOf(arg3);
                        if(arg2==2){
                            Intent intent = new Intent(indexActivity.this, MainActivity.class);
                            intent.putExtra("title", string[arg2]);
                            startActivity(intent);
                        }else if(arg2==5){
                            //Intent intent = new Intent(indexActivity.this, ItemActivity.class);
                            Intent intent = new Intent(indexActivity.this, TrackQueryActivity.class);
                            intent.putExtra("title", string[arg2]);
                            startActivity(intent);
                        }
                        else {
                            Intent intent = new Intent(indexActivity.this, LineChartActivity.class);
                            intent.putExtra("title", string[arg2]);
                            startActivity(intent);
                        }
                    }
                };
        listView.setOnItemClickListener(listViewListener);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_index;
    }
}
