package com.example.vanaid;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class Home7  extends AppCompatActivity {

    private ImageView mImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home7);

        mImageView = (ImageView) findViewById(R.id.imgbutton6);

        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openactivity();

            }
        });
    }
    public void openactivity(){
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }
}
