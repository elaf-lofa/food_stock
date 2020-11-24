package com.example.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import androidx.fragment.app.Fragment;


public class Real_timeFragment extends Fragment {
    View view;
    TextView textView,textView1,textView2;
    ViewFlipper viewFlipper;
    Button GetT,GetH,GetS,change;
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        getHumidity();
        getTemper();
        getSmoke();
    view=inflater.inflate(R.layout.fragment_real, container, false);

     textView = view.findViewById(R.id.ttext);
     textView1 = view.findViewById(R.id.htext);

        viewFlipper = view.findViewById(R.id.view);
        int images[] ={R.drawable.slid1,R.drawable.slide2,R.drawable.slide3};
        for(int image:images){
            flipp(image);   }

    return view;
    }
    public void flipp(int image){
        ImageView imageView = new ImageView(view.getContext());
        imageView.setBackgroundResource(image);
        viewFlipper.addView(imageView);
        viewFlipper.setFlipInterval(4000);
        viewFlipper.setAutoStart(true);
        viewFlipper.setInAnimation(view.getContext(),android.R.anim.slide_in_left);
        viewFlipper.setOutAnimation(view.getContext(),android.R.anim.slide_out_right);

    }

    private void getTemper() {

        JsonArrayRequest stringRequest=new JsonArrayRequest(Request.Method.GET, URLs.Temp,null
                ,new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                  //  Toast.makeText(getContext(), ""+response.length(), Toast.LENGTH_SHORT).show();
                    for (int i=0; i<response.length(); i++ ){
                        JSONObject ob=response.getJSONObject(i);
                       // Toast.makeText(getContext(), ob.getString("message"), Toast.LENGTH_SHORT).show();

                        readT(ob);

                    }
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

        //********************************************************************


    }
    public void readT( final JSONObject ob){
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        GetT =  view.findViewById(R.id.getT);
        GetT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    Toast.makeText(getActivity(),""+ob.getString("Temperature_Value"),Toast.LENGTH_LONG).show();
                    String Temp = ob.getString("Temperature_Value").trim();
                    String T = Temp+"\u2103";
                    textView.setText(T);
                    if((Integer.parseInt(Temp)>=35&&Integer.parseInt(Temp)<=45)){
                        builder.setMessage("be careful Temperature value is:"+Temp+"\u2103")
                                .setTitle("ALERT!")
                                .setCancelable(false)
                                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                }).show();
                    }
                    else if(Integer.parseInt(Temp)>45){
                        builder.setMessage("Temperature value is: "+Temp+"\nDANGEROUS!")
                                .setTitle("ALERT!")
                                .setCancelable(false)
                                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                }).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }}
        });

}

    private void getHumidity() {

        JsonArrayRequest stringRequest=new JsonArrayRequest(Request.Method.GET, URLs.Hum,null
                ,new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {

                    for (int i=0; i<response.length(); i++ ){
                        JSONObject ob=response.getJSONObject(i);


                        readH(ob);
                    }
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





    public void readH( final JSONObject ob) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        GetH = view.findViewById(R.id.getH);
        GetH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                   // Toast.makeText(getActivity(), "" + ob.getString("Humidity_Value"), Toast.LENGTH_LONG).show();
                    String Hum = ob.getString("Humidity_Value").trim();
                    String H =Hum+"%";
                    textView1.setText(H);


                    if((Integer.parseInt(Hum)>=30&&Integer.parseInt(Hum)<=70)){
                        builder.setMessage("be careful Humidity value is:"+Hum+"%")
                                .setTitle("ALERT!")
                                .setCancelable(false)
                                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                }).show();}
                    else if(Integer.parseInt(Hum)>70){
                        builder.setMessage("Humidity value is: "+Hum+"%\nDANGEROUS!")
                                .setTitle("ALERT!")
                                .setCancelable(false)
                                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                }).show();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });


    }

    private void getSmoke() {

        JsonArrayRequest stringRequest=new JsonArrayRequest(Request.Method.GET, URLs.Smoke,null
                ,new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {

                    for (int i=0; i<response.length(); i++ ){
                        JSONObject ob=response.getJSONObject(i);


                        readS(ob);
                    }
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





    public void readS( final JSONObject ob) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        GetS = view.findViewById(R.id.smoke);
        change = view.findViewById(R.id.action);

        GetS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                  //  change.setBackgroundColor(R.drawable.saafe);
                    String smock = ob.getString("Smoke_Value");

                    if(smock.equals("0")){
                    change.setBackgroundResource(R.drawable.saafe);
                    change.setText("Safe");
                    }
                    else if(smock.equals("1")){
                        change.setBackgroundResource(R.drawable.warn1);
                        change.setText("Warning");
                        builder.setMessage("be careful Smoke Detected!")
                                .setTitle("ALERT!")
                                .setCancelable(false)
                                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                }).show();

                    }



                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });


    }








}