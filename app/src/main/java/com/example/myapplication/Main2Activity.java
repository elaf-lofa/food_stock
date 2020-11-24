package com.example.myapplication;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import org.json.JSONObject;

public class Main2Activity extends AppCompatActivity {
    private TextView mTextMessage;
    Fragment selectedFragment1 = new dashBoard();
    Fragment selectedFragment2 = new NotfyFragment();
    Fragment selectedFragment3 = new Real_timeFragment();
    Fragment fragment;
Toolbar toolbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


            setContentView(R.layout.activity_main2);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
         if(savedInstanceState == null){
             fragmentManager.beginTransaction().replace(R.id.fragment_container, selectedFragment3 ).commit(); }
toolbar=findViewById(R.id.toolbar);
setSupportActionBar(toolbar);
 }
    final FragmentManager fragmentManager = getSupportFragmentManager();
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction=fragmentManager.beginTransaction();
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    transaction.replace(R.id.fragment_container, selectedFragment3).commit();
                    return true;
                    //fragment = selectedFragment3;
                case R.id.navigation_dashboard:
                    transaction.replace(R.id.fragment_container, selectedFragment1).commit();
                    return true;
                case R.id.navigation_notifications:
                    transaction.replace(R.id.fragment_container, selectedFragment2).commit();
                    return true;

            }

            return true;
        }
    };

    @Override
public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.nav_header,menu);
        return true;
    }
    @Override
public boolean onOptionsItemSelected(MenuItem menuItem){
        switch (menuItem.getItemId()){
            case  R.id.item1:{
                SharedPrefManager.getInstance(getApplicationContext()).logout();
            }break;

            case  R.id.item2:{
                startActivity(new Intent(this, About.class));

            }break;
        }
        return  super.onOptionsItemSelected(menuItem);
    }

    public void sms(){
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage("+249905182758", null, "welcome", null, null);
            Toast.makeText(getApplicationContext(), "sms send", Toast.LENGTH_LONG).show();
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),""+e,Toast.LENGTH_LONG).show();
        }  }



    }

