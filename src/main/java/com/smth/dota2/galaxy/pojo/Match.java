package com.smth.dota2.galaxy.pojo;

import java.util.List;
import java.util.Date;

public class Match {

    private Long matchId       ;
    private Date dt             ;
    private Long startTime     ;
    private Long endTime       ;
    private Long duration       ;
    private Integer gameMode      ;
    private Integer radiantTeamId;
    private Integer direTeamId   ;
    private Integer wonTeamId    ; 
    private String  wonTeamName  ;

    private List<MatchLogs> playerInfo;

    public Match(){

    }
    public Match(Long matchId,Long startTime,Long endTime,Long duration,int gameMode,int wonTeamId){
        this.matchId = matchId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = duration;
        this.gameMode = gameMode;
        this.wonTeamId = wonTeamId;
    }
    

    public Long getMatchId() {
        return this.matchId;
    }

    public void setMatchId(Long matchId) {
        this.matchId = matchId;
    }

    public Date getDt() {
        return this.dt;
    }

    public void setDt(Date dt) {
        this.dt = dt;
    }

 
    public Long getStartTime() {
        return this.startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return this.endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Long getDuration() {
        return this.duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public Integer getGameMode() {
        return this.gameMode;
    }

    public void setGameMode(Integer gameMode) {
        this.gameMode = gameMode;
    }

    public Integer getRadiantTeamId() {
        return this.radiantTeamId;
    }

    public void setRadiantTeamId(Integer radiantTeamId) {
        this.radiantTeamId = radiantTeamId;
    }

    public Integer getDireTeamId() {
        return this.direTeamId;
    }

    public void setDireTeamId(Integer direTeamId) {
        this.direTeamId = direTeamId;
    }

    public Integer getWonTeamId() {
        return this.wonTeamId;
    }

    public void setWonTeamId(Integer wonTeamId) {
        this.wonTeamId = wonTeamId;
    }

    public String getWonTeamName() {
  
        switch(wonTeamId) {
            case 2:
                return "天辉";
            case 3:
                return "夜魇";
            default:
                return "";
        }
    }

    public void setWonTeamName(String wonTeamName) {
        this.wonTeamName = wonTeamName;
    }

    public List<MatchLogs> getPlayerInfo() {
        return this.playerInfo;
    }

    public void setPlayerInfo(List<MatchLogs> playerInfo) {
        this.playerInfo = playerInfo;
    }


}
