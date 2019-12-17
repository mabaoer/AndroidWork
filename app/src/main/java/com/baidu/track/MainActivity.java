package com.baidu.track;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.baidu.track.activity.BaseActivity;
import com.baidu.track.utils.DBUtils;

import java.util.Map;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    Button login, register;
    EditText user, pwd;
    RadioButton man, woman;
    String username, password, shenfen;
    User u;
    private MyBroadRec r;
    public static final String PREFERENCE_NAME = "SaveSetting";
    public static int MODE = Context.MODE_PRIVATE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        login = (Button) findViewById(R.id.btn_login);
        register = (Button) findViewById(R.id.btn_register);
        user = (EditText) findViewById(R.id.et_username);
        pwd = (EditText) findViewById(R.id.et_password);
        man = (RadioButton) findViewById(R.id.rb_man);
        woman = (RadioButton) findViewById(R.id.rb_woman);
        login.setOnClickListener(this);
        register.setOnClickListener(this);

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.cup.edu.cn");
        r=new MyBroadRec();
        registerReceiver(r,intentFilter);
    }
    @Override
    public void onStart() {
        super.onStart();
        loadSharedPreferences();
    }

    @Override
    public void onStop() {
        super.onStop();
        saveSharedPreferences();
    }

    private void loadSharedPreferences() {
        SharedPreferences sharedPreferences =
                getSharedPreferences(PREFERENCE_NAME, MODE);
        String user1 = sharedPreferences.getString("user", "1");
        String pwd1 = sharedPreferences.getString("pwd", "2");
        user.setText(user1);
        pwd.setText(pwd1);
    }

    private void saveSharedPreferences() {
        SharedPreferences sharedPreferences =
                getSharedPreferences(PREFERENCE_NAME, MODE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("user", user.getText().toString());
        editor.putString("pwd", pwd.getText().toString());
        editor.commit();
    }

    @Override
    protected int getContentViewId() {
        return  R.layout.activity_main1;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                username = user.getText().toString().trim();
                password = pwd.getText().toString().trim();
                if (man.isClickable()) {
                    shenfen = "子女";
                } else if (woman.isClickable()) {
                    shenfen = "社区";
                    u = new User(username, password, shenfen);
                }
                u = new User(username, password, shenfen);
                checkLogin(u);
                break;
            case R.id.btn_register:
                Intent intent = new Intent(this, registerActivity.class);
                startActivity(intent);
                break;

        }
    }

    class DBThread implements Runnable {
        private User user;
        private Context context;

        public void setUser(User user) {
            this.user = user;
        }

        public void setContext(Context context) {
            this.context = context;
        }

        @Override
        public void run() {
            Map<String, String> result = DBUtils.login(user);
            if (result != null && result.size() > 0) {
                Intent intent = new Intent(MainActivity.this, indexActivity.class);
                intent.putExtra("user", u);
                context.startActivity(intent);
                //Intent intent = new Intent("com.cup.edu.cn");
               //intent.putExtra("username", username);
                //sendBroadcast(intent);
            }
        }
    }

    private void checkLogin(User u) {
        DBThread dt = new DBThread();
        dt.setUser(u);
        dt.setContext(this);
        Thread thread = new Thread(dt);
        thread.start();
    }
}
