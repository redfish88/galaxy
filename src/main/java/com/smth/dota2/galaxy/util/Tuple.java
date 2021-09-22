package com.smth.dota2.galaxy.util;

public class Tuple<T,V> {

    public final T first;
    public final V second;

    public Tuple(T a, V b){
        this.first  = a;
        this.second = b;
    }
    public String toString(){
        return "(" + first +"," + second +")";
    }

    
}
