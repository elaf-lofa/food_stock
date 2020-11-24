package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class NotfyFragment extends Fragment {

    private static final String HI ="http://192.168.43.242:8080/testapi.php" ;
    private List<List_Data> list_data;
    private RecyclerView rv;
    private MyAdapter adapter;
    RadioGroup radioGroup;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View  view= inflater.inflate(R.layout.notify, container, false);
        rv=view.findViewById(R.id.recyclerview);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        list_data=new ArrayList<>();
        adapter=new MyAdapter(list_data);
     //   getMovieData();
        radioGroup = view.findViewById(R.id.group);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.temp:{ String url = URLs.Temp;
                        getHum(url);}break;
                    case R.id.hum:{
                        String url = URLs.Hum;
                        getHum(url);}break;
                    case R.id.smo:{ String url = URLs.Smoke;
                        getHum(url);
                    }break;

                }
            }
        });
        return view;

    }


    public void getHum(final String url){
        JsonArrayRequest stringRequest=new JsonArrayRequest(Request.Method.GET, url,null
                ,new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                     JSONObject ob=response.getJSONObject(0);

                    int index = response.length();

                    for (int i=0; i<response.length(); i++ ){
                         ob=response.getJSONObject(i);


                    }
                    reads(ob,url,index);

                    // rv.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue requestQueue= Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

    public void reads(  JSONObject ob,String url,int i){
switch (url){
    case URLs.Temp:{
        try {
            String Temp = ob.getString("Temperature_Value").trim();
            String Time =ob.getString("Temperature_Time_Date").trim();

            int temperValue =Integer.parseInt(Temp);



            List_Data listData=new List_Data();


                listData=new List_Data(Time,"Temperature is:"+temperValue+"\u2103");


            list_data.add(listData);
            //  }
            rv.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getContext(),e+"",Toast.LENGTH_LONG).show();

        }}break;
    case URLs.Hum:{

        try {
            String Temp = ob.getString("Humidity_Value").trim();
            String Time =ob.getString("Humidity_Time_Date").trim();
            int humValue =Integer.parseInt(Temp);
            // Toast.makeText(getContext(),""+temperValue,Toast.LENGTH_LONG).show();
            List_Data listData=new List_Data();


            listData=new List_Data(Time,"Humidity is:"+humValue+"\u2103");


            list_data.add(listData);
            //  }
            rv.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getContext(),e+"",Toast.LENGTH_LONG).show();

        }}break;
    case URLs.Smoke:{
        try {
            String smock = ob.getString("Smoke_Value").trim();

           // Boolean b = Boolean.parseBoolean(smock);
            int value = Integer.parseInt(smock);
            String Time =ob.getString("Smoke_Date_Time").trim();
            List_Data listData=new List_Data("","");
if(value==0){            listData=new List_Data(Time,"Smoke is: not detect");
}
if(value==1) {
    listData = new List_Data(Time, "Smoke is: detect");
}


            list_data.add(listData);
            //  }
            rv.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getContext(),e+"",Toast.LENGTH_LONG).show();

        }}break;

}}
//********************************************************************







    public void sms(JSONObject ob){
        try{
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage("0905182758",null,ob.getString("name"),null,null);
        }catch (Exception e){
            Toast.makeText(getContext(),"",Toast.LENGTH_LONG).show();
        }
    }
}