package com.example.n0582158.signlanguageapp;

/**
 * Created by n0582158 on 01/02/2018.
 */

public class StateModel {

    String name;
    int image;

    public StateModel(String name, int image){

        this.name = name;
        this.image = image;
    }

    public String getName(){
        return name;
    }

    public int getImage(){
        return image;
    }
}
