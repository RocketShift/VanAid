package com.example.vanaid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Login extends AppCompatActivity {
    private Button mButton;
    private Button mButton2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mButton = (Button) findViewById(R.id.login_button);
        mButton2 = (Button) findViewById(R.id.signup_button);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_activity();
            }
        });

        mButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_activity2();
            }
        });
    }
    public void open_activity(){
        Intent intent = new Intent(this, Home2.class);
        startActivity(intent);
    }
    public void open_activity2(){
        Intent intent2 = new Intent(this, Signup.class);
        startActivity(intent2);
    }
}
