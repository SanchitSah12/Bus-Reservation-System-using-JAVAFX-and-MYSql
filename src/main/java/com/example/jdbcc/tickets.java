package com.example.jdbcc;



public class tickets {
    private String id;
    private String username;
    private String busName;
    private int no_of_tickets;


    tickets(String id, String username, String busName, int no_of_tickets) {
        this.id = id;
        this.username = username;
        this.busName = busName;
        this.no_of_tickets = no_of_tickets;
    }

    public String getId(){
        return id;
    }
    public String getBusName(){
        return busName;
    }
    public String getUsername(){
        return username;
    }
    public int getNo_of_tickets(){
        return no_of_tickets;
    }
    public void setId(String id){
        this.id = id;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public void setBusName(String busName){
        this.busName = busName;
    }
    public void setNo_of_tickets(int no_of_tickets){
        this.no_of_tickets = no_of_tickets;
    }

}