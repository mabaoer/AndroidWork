package com.baidu.track;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyBroadRec extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "6666666666", Toast.LENGTH_SHORT).show();
        Intent intent1 = new Intent("com.cup.edu",null);
        intent1.putExtra("username",intent.getStringExtra("username"));
        context.startActivity(intent1);
    }
}
