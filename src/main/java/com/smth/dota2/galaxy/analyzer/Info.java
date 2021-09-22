package com.smth.dota2.galaxy.analyzer;

import skadistats.clarity.Clarity;
import skadistats.clarity.wire.common.proto.Demo.CDemoFileInfo;

public class Info {
    
    public static void main(String[] args) throws Exception {

        CDemoFileInfo info = Clarity.infoForFile("D://programming//java//galaxy//replays//6148787615.dem");
        info.getGameInfo();
        System.out.println(info);
        
    }
    public Info() throws Exception{
        CDemoFileInfo info = Clarity.infoForFile("D://programming//java//galaxy//replays//6148787615.dem");
        info.getAllFields();
        System.out.println(info);
    }

    public String resolve() throws Exception{
        CDemoFileInfo info = Clarity.infoForFile("D://programming//java//galaxy//replays//6148787615.dem");
        return info.toString();
    }

}
