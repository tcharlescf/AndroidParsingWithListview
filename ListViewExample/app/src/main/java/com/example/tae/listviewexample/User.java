package com.example.tae.listviewexample;

/**
 * Created by Tae on 11/14/15.
 */
public class User {

    public String address;
    public String ceoName;
    public String resName;

    public void address(String address){
        this.address = address;
    }
    public void ceoName(String ceoName){
        this.ceoName = ceoName;
    }
    public void resName(String resName){
        this.resName = resName;
    }

    public String toString(){
        return "Address : " + address + "\n" +
                "Ceo Name : " + ceoName + "\n" +
                "Restaurant Name : " + resName;
    }
}
