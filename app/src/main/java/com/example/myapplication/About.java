package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class About extends AppCompatActivity {
TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        textView= findViewById(R.id.about);
        textView.setText("On monitor \nversion 1.0.0 \nif you have any questions, or suggestions, do not hesitate to contact us" +
                "\nOnmonitor@gmail.com");
    }
}
