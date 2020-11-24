package com.example.myapplication;


public class List_Data {
    private String Time;
    private String Value;
    private String read;

    public List_Data(String Value, String Time) {
        this.Time = Value;
        this.Value = Time;
    //    this.image_url=image_url;
    }
public List_Data(){}
    public String getValue() {
        return Value;
    }

    public String getTime() {
        return Time; }



}