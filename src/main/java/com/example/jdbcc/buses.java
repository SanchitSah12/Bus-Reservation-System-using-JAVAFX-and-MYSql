package com.example.jdbcc;

public class buses {
    private String id;
    private String busName;
    private String busType;
    private String from;
    private String to;
    private String date;
    private String time;
    private int seats;
    private int price;

    buses(String id,String busName,String busType,String from,String to,String date,String time,int seats,int price){
        this.id = id;
        this.busName = busName;
        this.busType = busType;
        this.from = from;
        this.to = to;
        this.date = date;
        this.time = time;
        this.seats = seats;
        this.price = price;


    }

    public String getId(){
        return id;
    }
    public String getBusName(){
        return busName;
    }
    public String getBusType(){
        return busType;
    }
    public String getFrom(){
        return from;
    }
    public String getTo(){
        return to;
    }
    public String getDate(){
        return date;
    }
    public String getTime(){
        return time;
    }
    public int getSeats(){
        return seats;
    }
    public int getPrice(){
        return price;
    }
    public void setId(String id){
        this.id = id;
    }


    public void setBusName(String busName){
        this.busName = busName;
    }
    public void setBusType(String busType){
        this.busType = busType;
    }
    public void setFrom(String from){
        this.from = from;
    }
    public void setTo(String to){
        this.to = to;
    }
    public void setDate(String date){
        this.date = date;
    }
    public void setTime(String time){
        this.time = time;
    }
    public void setSeats(int seats){
        this.seats = seats;
    }
    public void setPrice(int price){
        this.price = price;
    }

}