package com.baidu.track;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.baidu.track.activity.BaseActivity;
import com.baidu.track.utils.DBUtils;

public class registerActivity extends BaseActivity implements View.OnClickListener {
    Button queding;
    EditText user, pwd;
    RadioButton man, woman;
    String username, password, shenfen;
    User u;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        queding = (Button) findViewById(R.id.btn_queding);
        user = (EditText) findViewById(R.id.et_username);
        pwd = (EditText) findViewById(R.id.et_password);
        man = (RadioButton) findViewById(R.id.rb_man);
        woman = (RadioButton) findViewById(R.id.rb_woman);
        queding.setOnClickListener(this);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_register;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_queding:
                username = user.getText().toString().trim();
                password = pwd.getText().toString().trim();
                if (man.isClickable()) {
                    shenfen = "子女";
                } else if (woman.isClickable()) {
                    shenfen = "社区";
                    u = new User(username, password, shenfen);
                }
                u = new User(username, password, shenfen);
                register(u);
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
            DBUtils.register(user);
            Intent intent = new Intent(registerActivity.this, MainActivity.class);
            intent.putExtra("user", u);
            context.startActivity(intent);
        }
    }

    private void register(User u) {
        DBThread dt = new DBThread();
        dt.setUser(u);
        dt.setContext(this);
        Thread thread = new Thread(dt);
        thread.start();
    }
}
