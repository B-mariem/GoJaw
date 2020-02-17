package com.mariem.gojaw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Intent intent = getIntent();
        String user_name = intent.getStringExtra(Constant.ARG_NAME);
        String user_id = intent.getStringExtra(Constant.ARG_ID);
        textView=findViewById(R.id.text);
        textView.setText(user_id+user_name);
    }

}
