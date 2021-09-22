package com.smth.dota2.galaxy.pojo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Replay {

    private int id;            
    private String randomName;   
    private String originalName;
    private String absPath;

    private Date createTime;

    private String date;

    public static DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    public  Replay(String randomName,String originalName,String absPath){

        this.randomName = randomName;
        this.originalName = originalName;
        this.absPath = absPath;

    }
    //default 
    public  Replay(){
    }



    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDate() {
        return formatter.format(createTime);
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRandomName() {
        return this.randomName;
    }

    public void setRandomName(String randomName) {
        this.randomName = randomName;
    }

    public String getOriginalName() {
        return this.originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public String getAbsPath() {
        return this.absPath;
    }

    public void setAbsPath(String absPath) {
        this.absPath = absPath;
    }

    
}
