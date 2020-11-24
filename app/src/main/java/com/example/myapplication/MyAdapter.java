package com.example.myapplication;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
    private List<List_Data>list_data;

    public MyAdapter(List<List_Data> list_data) {
        this.list_data = list_data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        List_Data listData=list_data.get(position);
       // if(listData.getValue()==null){}
           // else{

        holder.value.setText(listData.getValue());
        holder.date.setText(listData.getTime());//}
    }

    @Override
    public int getItemCount() {
        return list_data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView value,date;
        public ViewHolder(View itemView) {
            super(itemView);
            value=itemView.findViewById(R.id.txt_value);
            date=itemView.findViewById(R.id.txt_time);
        }
    }
}
