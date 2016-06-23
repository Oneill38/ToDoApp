package com.example.meganoneill.simpletodo;

import com.orm.SugarRecord;

/**
 * Created by meganoneill on 6/12/16.
 */
public class Item extends SugarRecord {
    public String name;
    public String priority;
    public String deadline;

    public Item(){
        super();
    }

    public Item(String name, String priority, String deadline){
        super();
        this.name = name;
        this.priority = priority;
        this.deadline = deadline;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getPriority(){ return priority;}

    public void setPriority(String priority) {this.priority = priority;}

    public String getDeadline() {return deadline;}

    public void setDeadline(String deadline) {this.deadline = deadline;}

    @Override
    public String toString() {
        return name;
    }

}
