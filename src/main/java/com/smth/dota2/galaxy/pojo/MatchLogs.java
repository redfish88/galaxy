package com.smth.dota2.galaxy.pojo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MatchLogs {

    private Date     dt;
    private String  dtStr;
    private Long matchId    ;
    private String  steamId     ;
    private String  playerName ;
    private String  playerNickName;
    private Integer level       ;
    private Integer kills       ;
    private Integer deaths      ;
    private Integer assists     ;
    private Integer gold        ;
    private Integer lh          ;
    private Integer dn          ;
    private Integer heroId     ;
    private String  heroName   ;
    private Integer heroDamage ;
    private Integer towerDamage;
    private String  killedBy   ;
    private String  multiKills ; 
    private int     teamId;
    private String  teamName;
    private int     teamSolt;



    public Date getDt() {
        return this.dt;
    }

    public void setDt(Date dt) {
        this.dt = dt;
    }

    public String getDtStr() {
        String result1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(getDt());
        return result1;
    }

    public void setDtStr(String dtStr) {
        this.dtStr = dtStr;
    }


    public Long getMatchId() {
        return this.matchId;
    }

    public void setMatchId(Long matchId) {
        this.matchId = matchId;
    }

    public String getSteamId() {
        return this.steamId;
    }

    public void setSteamId(String steamId) {
        this.steamId = steamId;
    }

    public String getPlayerName() {
        return this.playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Integer getLevel() {
        return this.level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getKills() {
        return this.kills;
    }

    public void setKills(Integer kills) {
        this.kills = kills;
    }

    public Integer getDeaths() {
        return this.deaths;
    }

    public void setDeaths(Integer deaths) {
        this.deaths = deaths;
    }

    public Integer getAssists() {
        return this.assists;
    }

    public void setAssists(Integer assists) {
        this.assists = assists;
    }

    public Integer getGold() {
        return this.gold;
    }

    public void setGold(Integer gold) {
        this.gold = gold;
    }

    public Integer getLh() {
        return this.lh;
    }

    public void setLh(Integer lh) {
        this.lh = lh;
    }

    public Integer getDn() {
        return this.dn;
    }

    public void setDn(Integer dn) {
        this.dn = dn;
    }

    public Integer getHeroId() {
        return this.heroId;
    }

    public void setHeroId(Integer heroId) {
        this.heroId = heroId;
    }


    public String getPlayerNickName() {
        return this.playerNickName;
    }

    public void setPlayerNickName(String playerNickName) {
        this.playerNickName = playerNickName;
    }

    public String getHeroName() {
        return this.heroName;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }


    public Integer getHeroDamage() {
        return this.heroDamage;
    }

    public void setHeroDamage(Integer heroDamage) {
        this.heroDamage = heroDamage;
    }

    public Integer getTowerDamage() {
        return this.towerDamage;
    }

    public void setTowerDamage(Integer towerDamage) {
        this.towerDamage = towerDamage;
    }

    public String getKilledBy() {
        return this.killedBy;
    }

    public void setKilledBy(String killedBy) {
        this.killedBy = killedBy;
    }

    public String getMultiKills() {
        return this.multiKills;
    }

    public void setMultiKills(String multiKills) {
        this.multiKills = multiKills;
    }
 

    public int getTeamId() {
        return this.teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }



    public String getTeamName() {
        switch(teamId) {
            case 2:
                return "天辉";
            case 3:
                return "夜魇";
            default:
                return "";
        }
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }


    public int getTeamSolt() {
        return this.teamSolt;
    }

    public void setTeamSolt(int teamSolt) {
        this.teamSolt = teamSolt;
    }


    
}
