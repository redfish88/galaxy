package com.smth.dota2.galaxy.pojo;

public class Player {

    private String steamId;

    private String nickName;

    private String avatar;

    public Player(String steamId,String nickName){
        this.steamId = steamId;
        this.nickName = nickName;
    }

    public String getNickName(){
        return nickName;
    }
    public void setNickName(String nickName){
        this.nickName = nickName;
    }


    public String getAvatar() {
        return this.avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    
    public String getSteamId() {
        return this.steamId;
    }

    public void setSteamId(String steamId) {
        this.steamId = steamId;
    }
}
