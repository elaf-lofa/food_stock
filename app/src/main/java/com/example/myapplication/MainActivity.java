package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    Animation fadeine;
ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, Main2Activity.class));
        }//shared
     /*   imageView = findViewById(R.id.logo);
        fadeine = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fadein);

        imageView.setVisibility(View.VISIBLE);
        imageView.startAnimation(fadeine);

        Thread thread = new Thread() {
            public void run(){
                try {
                    sleep(8000);
                    startActivity((new Intent(MainActivity.this,Login.class)));
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };
        thread.start();*/
    }
    public void welcome(View view){
        startActivity(new Intent(MainActivity.this,Login.class));

    }
}
