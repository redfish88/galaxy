package com.smth.dota2.galaxy.pojo;



public class Hero {

    private int id;

    private String name;

    private String localizedName;

    private String avatar;


    public Hero(){

    }
    public Hero(int id,String name,String localizedName)
    {
        this.id = id;
        this.name = name;
        this.localizedName = localizedName;
    }


    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocalizedName() {
        return this.localizedName;
    }

    public void setLocalizedName(String localizedName) {
        this.localizedName = localizedName;
    }


    public String getAvatar() {
        return this.avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    
}
