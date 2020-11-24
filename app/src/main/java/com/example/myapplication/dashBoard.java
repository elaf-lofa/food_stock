package com.example.myapplication;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.charts.LineChart;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class dashBoard extends Fragment {
    LineChart lineChart,lineChart2;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters


    public dashBoard() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment dashBoard.
     */
    // TODO: Rename and change types and number of parameters
    public static dashBoard newInstance(String param1, String param2) {
        dashBoard fragment = new dashBoard();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dash_board, container, false);

lineChart = view.findViewById(R.id.line);
lineChart2 = view.findViewById(R.id.line2);

draw();
draw2();
        return view;


    }

    public void draw(){


        JsonArrayRequest stringRequest=new JsonArrayRequest(Request.Method.GET, URLs.Tchart,null
                ,new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                      // Toast.makeText(getActivity(), ""+response.length(), Toast.LENGTH_LONG).show();
                    ArrayList<Entry> entries1= new ArrayList<>();

                    for (int i=0; i<10; i++ ){
                        JSONObject ob=response.getJSONObject(i);

                      //  Toast.makeText(getActivity(), "" + ob.getString("Humidity_Value"), Toast.LENGTH_LONG).show();
                        String Temp = ob.getString("Temperature_Value").trim();
                        String Time = ob.getString("Temperature_Time_Date").trim();


                            entries1.add(new Entry( i,(Integer.parseInt(Temp))));

                    }
                    LineDataSet dataSet1 = new LineDataSet (entries1,"Temperature reads");
                    LineData data1 = new LineData(dataSet1);
                    lineChart.setData(data1);
                    lineChart.animateY(5000);
                    lineChart.animateX(3000);
                    dataSet1.setDrawFilled(true);
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
//*****************************************************8
public void draw2(){


    JsonArrayRequest stringRequest=new JsonArrayRequest(Request.Method.GET, URLs.Hchart,null
            ,new Response.Listener<JSONArray>() {
        @Override
        public void onResponse(JSONArray response) {
            try {
               // Toast.makeText(getActivity(), ""+response.length(), Toast.LENGTH_LONG).show();
                ArrayList<Entry> entries= new ArrayList<>();

                for (int i=0; i<10; i++ ){
                    JSONObject ob=response.getJSONObject(i);

                    //  Toast.makeText(getActivity(), "" + ob.getString("Humidity_Value"), Toast.LENGTH_LONG).show();
                    String Hum = ob.getString("Humidity_Value").trim();


                        entries.add(new Entry(i,Integer.parseInt(Hum)));

                }
                LineDataSet dataSet1 = new LineDataSet (entries,"Humidity reads");
                ArrayList<String> label= new ArrayList<String>();
                label.add("12-23");
                label.add("19-23");
                label.add("12-20");
                label.add("12-25");
                LineData data1 = new LineData(dataSet1);
                lineChart2.setData(data1);
                lineChart2.animateY(5000);
                lineChart2.animateX(3000);
                dataSet1.setDrawFilled(true);
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




/*public void chart1(View view){

    x = new ArrayList<Entry>();
    y = new ArrayList<String>();
    mChart = view.findViewById(R.id.line);
    mChart.setDrawGridBackground(false);
    mChart.setDescription(new Description());

    mChart.setScaleEnabled(true);
    mChart.getXAxis().setTextSize(12f);
    mChart.getAxisLeft().setTextSize(12f);
    XAxis xl = mChart.getXAxis();
    YAxis leftAxis = mChart.getAxisLeft();
    leftAxis.setInverted(true);
    YAxis rightAxis = mChart.getAxisRight();
    rightAxis.setEnabled(false);

}

    public void chart2(View view){

        x = new ArrayList<Entry>();
        y = new ArrayList<String>();
        mChart = view.findViewById(R.id.line2);
        mChart.setDrawGridBackground(false);
        mChart.setDescription(new Description());

        mChart.setScaleEnabled(true);
        mChart.getXAxis().setTextSize(12f);
        mChart.getAxisLeft().setTextSize(12f);
        XAxis xl = mChart.getXAxis();
        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setInverted(true);
        YAxis rightAxis = mChart.getAxisRight();
        rightAxis.setEnabled(false);

    }


    private void Graph_List() {
        final ProgressDialog loading = new ProgressDialog(getContext());
        loading.setMessage("Please Wait...");
        loading.setCancelable(false);
        // loading.show();
            // json response code
        JsonArrayRequest stringRequest=new JsonArrayRequest(Request.Method.GET, URLs.CHART_URL,null
                ,new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    Toast.makeText(getContext(), ""+response.length(), Toast.LENGTH_SHORT).show();

                    for (int i=0; i<response.length(); i++ ){
                        JSONObject ob=response.getJSONObject(i);
                        String Temp_value = ob.getString("Temperature_Value").trim();
                        String date = ob.getString("Temperature_Time_Date").trim();
                        Float Fval = Float.valueOf(date).floatValue();
                        Toast.makeText(getContext(), ""+date+"/"+Temp_value, Toast.LENGTH_SHORT).show();

                        x.add(new Entry(Integer.parseInt(Temp_value),Fval));

                        y.add(date);

                    }
                    LineDataSet set1 = new LineDataSet(x, Token);
                    set1.setColors(ColorTemplate.COLORFUL_COLORS);
                    set1.setLineWidth(1.5f);
                    set1.setCircleRadius(4f);

                    LineData data = new LineData(set1);
                    mChart.setData(data);
                    mChart.invalidate();


                } catch (Exception e) {
                    loading.dismiss();
                    Log.d("Tag", e.getMessage());

                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.dismiss();
                        Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();


                    }
                }) {

        };
        VolleySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }



    private void Graph_List2() {
        String Graph_URL="http://192.168.43.242:8080/project/testapi.php";
        final ProgressDialog loading = new ProgressDialog(getContext());
        loading.setMessage("Please Wait...");
        loading.setCancelable(false);
        // loading.show();
        // json response code
        JsonArrayRequest stringRequest=new JsonArrayRequest(Request.Method.GET, Graph_URL,null
                ,new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    Toast.makeText(getContext(), ""+response.length(), Toast.LENGTH_SHORT).show();

                    for (int i=0; i<response.length(); i++ ){
                        JSONObject ob=response.getJSONObject(i);
                        String score = ob.getString("Temperature_Value").trim();
                        String date = ob.getString("Temperature_Time_Date").trim();
                        Toast.makeText(getContext(), ""+date+"/"+score, Toast.LENGTH_SHORT).show();

                        x.add(new Entry(Integer.parseInt(score), i));

                        y.add(date);

                    }
                    LineDataSet set1 = new LineDataSet(x, Token);
                    set1.setColors(ColorTemplate.COLORFUL_COLORS);
                    set1.setLineWidth(1.5f);
                    set1.setCircleRadius(4f);

                    LineData data = new LineData(set1);
                    mChart.setData(data);
                    mChart.invalidate();


                } catch (Exception e) {
                    loading.dismiss();
                    Log.d("Tag", e.getMessage());

                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.dismiss();
                        Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();


                    }
                }) {

        };
        VolleySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }*/
}

