package com.example.testing_1;

public class RoomData {
    private String city;
    private String area;
    private String priority;
    private String contact;
    private String imageid;
    private String state;
    private String houseno;
    private String Key;

    public RoomData(){

    }

    public RoomData(String City, String Area, String Priority, String Contact, String Imageid, String State, String Houseno) {
        city = City;
        area = Area;
        priority = Priority;
        contact = Contact;
        imageid = Imageid;
        state = State;
        houseno=Houseno;
    }

    public String getCity() {
        return city;
    }

    public String getArea() {
        return area;
    }

    public String getPriority() {
        return priority;
    }

    public String getContact() {
        return contact;
    }

    public String getImageid() {
        return imageid;
    }

    public String getState() {
        return state;
    }


    public String getHouseno(){ return houseno;}


    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }
}

