package com.example.meganoneill.simpletodo;

import com.orm.SugarRecord;

/**
 * Created by meganoneill on 6/12/16.
 */
public class Item extends SugarRecord {
    public String name;
    public String priority;

    public Item(){
        super();
    }

    public Item(String name, String priority){
        super();
        this.name = name;
        this.priority = priority;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

}
