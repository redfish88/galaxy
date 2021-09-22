package com.smth.dota2.galaxy.mapper;

import java.util.HashMap;
import java.util.List;

import com.smth.dota2.galaxy.pojo.Match;
import com.smth.dota2.galaxy.pojo.MatchLogs;
import com.smth.dota2.galaxy.pojo.Hero;
import com.smth.dota2.galaxy.pojo.Player;
import com.smth.dota2.galaxy.pojo.Replay;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface MatchMapper {

    @Insert("insert into players(steam_id,nick_name) values (#{steamId},#{nickName})")
    void addPlayer(Player player);

    @Insert("update players set nick_name=#{nickName} where steam_id = #{steamId}")
    void updatePlayer(Player player);


    @Insert("delete from  players where steam_id = #{steamId}")
    void deletePlayer(String steamId);


    @Select("select steam_id,nick_name from players")
    List<Player> listPlayer();

    @Select("select steam_id,nick_name from players where steam_id = #{steamId}")
    Player getPlayerById(@Param("steamId")String steamId);

    @Select("select id,name,localized_name from heroes")
    List<Hero> listHeroes();

    @Select("select id,name,localized_name from heroes where id = #{id}")
    Hero getHeroById(@Param("id")int heroId);

    @Insert("insert into race(match_id,dt,start_time,end_time,duration,won_team_id) values "+
    "(#{matchId},#{dt},#{startTime},#{endTime},#{duration},#{wonTeamId})")
    void addMatch(Match Match);

    @Select("<script>"
    +"select dt,match_id,start_time,duration,won_team_id"
    +"from race"
    +"<where>                                                         "
    + "  <choose>                                                     "
    + "      <when test='matchId != null and id != &quot;&quot;'>     "
    + "             match_id = #{matchId}                             "
    + "      </when>                                                  "
    + "  </choose>                                                    "
    + "</where>                                                       "
    +"</script>"
    )
    List<Match> selectMatchs(Match match);

    @Select("select dt,match_id,start_time,end_time,duration,won_team_id from race where match_id = #{matchId}")
    Match getMatchByMatchId(@Param("matchId")Long matchId);


    @Insert("insert into replay_file(random_name,original_name,abs_path) values(#{randomName},#{originalName},#{absPath})")
    int uploadFile(Replay replay);

    @Select("select id,original_name,create_time from  replay_file order by id desc")
    List<Replay> selectAllReplay();

    @Select("select id,original_name,abs_path,create_time from  replay_file where id=#{id}")
    Replay selectReplayById(@Param("id")int id);

    @Insert("insert into match_logs(dt,match_id,steam_id,player_name,player_nick_name,team_id,team_solt,level,kills,deaths,assists,gold,lh,dn,hero_id,hero_name,hero_damage) "
    +       "values(#{dt},#{matchId},#{steamId},#{playerName},#{playerNickName},#{teamId},#{teamSolt},#{level},#{kills},#{deaths},#{assists},#{gold},#{lh},#{dn},#{heroId},#{heroName},#{heroDamage})")
    int addMatchLogs(MatchLogs log);

    @Insert("insert into matches(time,win,r_h_1,r_h_2,r_h_3,r_h_4,r_h_5,d_h_1,d_h_2,d_h_3,d_h_4,d_h_5,"
    +"r_p_1,r_p_2,r_p_3,r_p_4,r_p_5,d_p_1,d_p_2,d_p_3,d_p_4,d_p_5,hash) values(#{time},#{win},#{r_h_1},#{r_h_2},#{r_h_3},"
    +"#{r_h_4},#{r_h_5},#{d_h_1},#{d_h_2},#{d_h_3},#{d_h_4},#{d_h_5},#{r_p_1},#{r_p_2},#{r_p_3},#{r_p_4},#{r_p_5},"
    +"#{d_p_1},#{d_p_2},#{d_p_3},#{d_p_4},#{d_p_5},#{hash})")
    int addMatchs(HashMap<String,Object> param);


}
