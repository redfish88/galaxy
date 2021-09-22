package com.smth.dota2.galaxy.analyzer;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import skadistats.clarity.Clarity;
import skadistats.clarity.io.Util;
import skadistats.clarity.model.Entity;
import skadistats.clarity.model.FieldPath;
import skadistats.clarity.processor.entities.Entities;
import skadistats.clarity.processor.entities.UsesEntities;
import skadistats.clarity.processor.runner.ControllableRunner;
import skadistats.clarity.source.MappedFileSource;
import skadistats.clarity.util.TextTable;
import skadistats.clarity.wire.common.proto.Demo.CDemoFileInfo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.smth.dota2.galaxy.pojo.Match;
import com.smth.dota2.galaxy.pojo.MatchLogs;
import java.util.Date;

@UsesEntities
public class Matchend {

    private static final Logger log = LoggerFactory.getLogger(Matchend.class.getPackage().getClass());

    public static void main(String[] args) throws Exception {
        long tStart = System.currentTimeMillis();
        new Matchend("D://programming//java//galaxy//replays//6148787615.dem").showScoreboard();
        long tMatch = System.currentTimeMillis() - tStart;
        log.info("total time taken: {}s", (tMatch) / 1000.0);
    }

    private String fileName;


    private final ControllableRunner runner;

    public Matchend(String fileName) throws IOException, InterruptedException {
        this.fileName = fileName;
        runner = new ControllableRunner(new MappedFileSource(fileName)).runWith(this);
        runner.seek(runner.getLastTick());
        runner.halt();
    }

    public Match showScoreboard() {
 
        Match result;
        try {
            CDemoFileInfo info = Clarity.infoForFile(this.fileName);
            int winner = info.getGameInfo().getDota().getGameWinner();
            long matchId = info.getGameInfo().getDota().getMatchId();
            Long duraTime = (long)info.getPlaybackTime();
            Long endTime = (long)info.getGameInfo().getDota().getEndTime();
            Long startTime =(long) endTime -duraTime;
            int gameMode = info.getGameInfo().getDota().getGameMode();
            result = new Match(matchId, startTime, endTime, duraTime, gameMode, winner);
            result.setDt(new Date(startTime*1000));

            List<MatchLogs> list =  showTableWithColumns(
                new DefaultResolver<Integer>("PlayerResource", "m_vecPlayerData.%i.m_iPlayerTeam"),
                new ColumnDef("Name", new DefaultResolver<String>("PlayerResource", "m_vecPlayerData.%i.m_iszPlayerName")),
                new ColumnDef("Level", new DefaultResolver<Integer>("PlayerResource", "m_vecPlayerTeamData.%i.m_iLevel")),
                new ColumnDef("K", new DefaultResolver<Integer>("PlayerResource", "m_vecPlayerTeamData.%i.m_iKills")),
                new ColumnDef("D", new DefaultResolver<Integer>("PlayerResource", "m_vecPlayerTeamData.%i.m_iDeaths")),
                new ColumnDef("A", new DefaultResolver<Integer>("PlayerResource", "m_vecPlayerTeamData.%i.m_iAssists")),
                new ColumnDef("Gold", new DefaultResolver<Integer>("Data%n", "m_vecDataTeam.%p.m_iTotalEarnedGold")),
                new ColumnDef("LH", new DefaultResolver<Integer>("Data%n", "m_vecDataTeam.%p.m_iLastHitCount")),
                new ColumnDef("DN", new DefaultResolver<Integer>("Data%n", "m_vecDataTeam.%p.m_iDenyCount")),
                new ColumnDef("HeroID", new DefaultResolver<Integer>("PlayerResource", "m_vecPlayerTeamData.%i.m_nSelectedHeroID")),
                new ColumnDef("Team", new DefaultResolver<Integer>("PlayerResource", "m_vecPlayerData.%i.m_iPlayerTeam")),
                new ColumnDef("TeamSlot", new DefaultResolver<Integer>("PlayerResource", "m_vecPlayerTeamData.%i.m_iTeamSlot")),
                new ColumnDef("SteamID", new DefaultResolver<String>("PlayerResource", "m_vecPlayerData.%i.m_iPlayerSteamID")),
                new ColumnDef("HeroDamage", new DefaultResolver<String>("Data%n", "m_vecDataTeam.%p.m_iHeroDamage"))
        );
        result.setPlayerInfo(list);
        return result;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    public List<MatchLogs> showTableWithColumns(ValueResolver<Integer> teamResolver, ColumnDef... columnDefs) {
        List<MatchLogs> list = new ArrayList<MatchLogs>();
        TextTable.Builder b = new TextTable.Builder();
        for (int c = 0; c < columnDefs.length; c++) {
            b.addColumn(columnDefs[c].name, c == 0 ? TextTable.Alignment.LEFT : TextTable.Alignment.RIGHT);
        }
        TextTable table = b.build();

        int team = 0;
        int pos = 0;
        int r = 0;
        MatchLogs log;
        for(int i=0;i<10;i++){
            log = new MatchLogs();
            list.add(log);
        }
        for (int idx = 0; idx < 256; idx++) {
            try {
                int newTeam = teamResolver.resolveValue(idx, team, pos);
                if (newTeam != team) {
                    team = newTeam;
                    pos = 0;
                } else {
                    pos++;
                }
            } catch (Exception e) {
                // when the team resolver throws an exception, this was the last index there was
                break;
            }
            if (team != 2 && team != 3) {
                continue;
            }
            for (int c = 0; c < columnDefs.length; c++) {
                table.setData(r, c, columnDefs[c].resolver.resolveValue(idx, team, pos));
                MatchLogs obj = list.get(r);
                switch(c) {
                    case 0:
                    obj.setPlayerName(columnDefs[c].resolver.resolveValue(idx, team, pos).toString());
                    break;
                    case 1:
                    obj.setLevel(Integer.parseInt(columnDefs[c].resolver.resolveValue(idx, team, pos).toString()));
                    break;
                    case 2:
                    obj.setKills(Integer.parseInt(columnDefs[c].resolver.resolveValue(idx, team, pos).toString()));
                    break;
                    case 3:
                    obj.setDeaths(Integer.parseInt(columnDefs[c].resolver.resolveValue(idx, team, pos).toString()));
                    break;
                    case 4:
                    obj.setAssists(Integer.parseInt(columnDefs[c].resolver.resolveValue(idx, team, pos).toString()));
                    break;
                    case 5:
                    obj.setGold(Integer.parseInt(columnDefs[c].resolver.resolveValue(idx, team, pos).toString()));
                    break;
                    case 6:
                    obj.setLh(Integer.parseInt(columnDefs[c].resolver.resolveValue(idx, team, pos).toString()));
                    break;
                    case 7:
                    obj.setDn(Integer.parseInt(columnDefs[c].resolver.resolveValue(idx, team, pos).toString()));
                    break;
                    case 8:
                    obj.setHeroId(Integer.parseInt(columnDefs[c].resolver.resolveValue(idx, team, pos).toString()));
                    break;
                    case 9:
                    obj.setTeamId(Integer.parseInt(columnDefs[c].resolver.resolveValue(idx, team, pos).toString()));
                    break;
                    case 10:
                    obj.setTeamSolt(Integer.parseInt(columnDefs[c].resolver.resolveValue(idx, team, pos).toString()));
                    break;
                    case 11:
                    obj.setSteamId(columnDefs[c].resolver.resolveValue(idx, team, pos).toString());
                    break;
                    case 12:
                    obj.setHeroDamage(Integer.parseInt(columnDefs[c].resolver.resolveValue(idx, team, pos).toString()));
                    break;
                }
            }
            r++;
        }
        return list;
    }

    private String getEngineDependentEntityName(String entityName) {
        switch (runner.getEngineType().getId()) {
            case SOURCE1:
                return "DT_DOTA_" + entityName;
            case SOURCE2:
                return "CDOTA_" + entityName;
            default:
                throw new RuntimeException("invalid engine type");
        }
    }

    private String getTeamName(int team) {
        switch(team) {
            case 2:
                return "Radiant";
            case 3:
                return "Dire";
            default:
                return "";
        }
    }

    private Entity getEntity(String entityName) {
        return runner.getContext().getProcessor(Entities.class).getByDtName(getEngineDependentEntityName(entityName));
    }

    private class ColumnDef {
        private final String name;
        private final ValueResolver<?> resolver;

        public ColumnDef(String name, ValueResolver<?> resolver) {
            this.name = name;
            this.resolver = resolver;
        }
    }

    private interface ValueResolver<V> {
        V resolveValue(int index, int team, int pos);
    }

    private class DefaultResolver<V> implements ValueResolver<V> {
        private final String entityName;
        private final String pattern;

        public DefaultResolver(String entityName, String pattern) {
            this.entityName = entityName;
            this.pattern = pattern;
        }

        @Override
        public V resolveValue(int index, int team, int pos) {
            String fieldPathString = pattern
                    .replaceAll("%i", Util.arrayIdxToString(index))
                    .replaceAll("%t", Util.arrayIdxToString(team))
                    .replaceAll("%p", Util.arrayIdxToString(pos));
            String compiledName = entityName.replaceAll("%n", getTeamName(team));
            Entity entity = getEntity(compiledName);
            FieldPath fieldPath = entity.getDtClass().getFieldPathForName(fieldPathString);
            return entity.getPropertyForFieldPath(fieldPath);
        }
    }

}
