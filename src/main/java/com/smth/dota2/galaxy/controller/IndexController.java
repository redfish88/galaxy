package com.smth.dota2.galaxy.controller;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.io.File;



import com.smth.dota2.galaxy.analyzer.Info;
import com.smth.dota2.galaxy.analyzer.Matchend;
import com.smth.dota2.galaxy.mapper.MatchMapper;
import com.smth.dota2.galaxy.pojo.Replay;
import com.smth.dota2.galaxy.pojo.Hero;
import com.smth.dota2.galaxy.pojo.Match;
import com.smth.dota2.galaxy.pojo.MatchLogs;
import com.smth.dota2.galaxy.pojo.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.util.DigestUtils;
import org.springframework.util.concurrent.SuccessCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
public class IndexController {

	@Autowired
    public Environment env;

	@Autowired
	public MatchMapper mapper;


	// public String upload_folder = "D://programming//replay//";

	//public String upload_folder = env.getProperty("app.upload.folder");

	@GetMapping("/")
	public String index() throws Exception{
        Info info = new Info();
		return info.resolve();
	}


	@PostMapping("/admin/uploadRec")
	public String uploadFile(@RequestParam("file") MultipartFile file,
	RedirectAttributes redirectAttributes) throws Exception{


		if(file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "请选择一个上传文件");
        }
		String upload_folder = env.getProperty("app.upload.folder");
        try {
            byte[] bytes = file.getBytes();
			String randomName = DigestUtils.md5DigestAsHex( file.getOriginalFilename().getBytes());
            Path path = Paths.get(upload_folder + file.getOriginalFilename());
            Files.write(path, bytes);
           // redirectAttributes.addFlashAttribute("message", "已经将 '" + file.getOriginalFilename() + "' 的文件上传成功");
			Match match = new Matchend(upload_folder + file.getOriginalFilename()).showScoreboard();

			Match check = mapper.getMatchByMatchId(match.getMatchId());
			if(null != check){
			 	File upFile = new File(upload_folder + file.getOriginalFilename());
				 upFile.delete();
				return  result(false,"文件已存在！");
			}else{
				mapper.uploadFile(new Replay(randomName,file.getOriginalFilename(),upload_folder + file.getOriginalFilename()));
				mapper.addMatch(match);
				HashMap<String,Object> param = new HashMap<String,Object>();
				param.put("hash", match.getMatchId().toString());
				param.put("win", match.getWonTeamName());
				param.put("time", new Date(match.getStartTime()*1000));
				for(int index=0 ; index<match.getPlayerInfo().size(); index ++){

					MatchLogs log= match.getPlayerInfo().get(index);
					
					log.setMatchId(match.getMatchId());
					Player player = mapper.getPlayerById(log.getSteamId());
					Hero hero = mapper.getHeroById(log.getHeroId());
					log.setDt(new Date(match.getStartTime()*1000));
					log.setHeroName(hero.getLocalizedName());
					if(log.getTeamId() == 2){
						
						param.put("r_h_"+(log.getTeamSolt()+1), log.getHeroName());
						param.put("r_p_"+(log.getTeamSolt()+1), log.getSteamId());
						
					}else{
						
						param.put("d_h_"+(log.getTeamSolt()+1), log.getHeroName());
						param.put("d_p_"+(log.getTeamSolt()+1), log.getSteamId());
					}

					if(null != player){
						log.setPlayerNickName(player.getNickName());
						
					}else{
					
						mapper.addPlayer(new Player(log.getSteamId(),log.getPlayerName()));
					}
					mapper.addMatchLogs(log);
				}
				mapper.addMatchs(param);

			}

			
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return result(true, "录像上传成功!");
    }


	@GetMapping("/admin/list_replay")
	public List<Replay> showAllReplay(){

		List<Replay> list = mapper.selectAllReplay();
		return list;

	}

	@GetMapping("/players")
	public List<Player> showAllPlayer(){

		List<Player> list = mapper.listPlayer();
		return list;

	}
	@GetMapping("/admin/addPlayer")
	public String addPlayer(@RequestParam("steamId")String steamId,@RequestParam("nickName")String nickName){

		Player player = mapper.getPlayerById(steamId);
		if(null != player){

			return result(false, "成员已存在");
		}else {
			mapper.addPlayer(new Player(steamId,nickName));
		}
		return result(true, "新增成员成功");

	}
	@GetMapping("/admin/updatePlayer")
	public String modifyPlayer(@RequestParam("steamId")String steamId,@RequestParam("nickName")String nickName){

		mapper.updatePlayer(new Player(steamId,nickName));
		
		return result(true,"修改成功");

	}
	@GetMapping("/admin/deletePlayer")
	public String deletePlayer(String steamId){

		mapper.deletePlayer(steamId);
		return result(true,"删除成功");

	}

	@GetMapping("/heroes")
	public List<Hero> showAllHero(){
		List<Hero> list = mapper.listHeroes();
		return list;

	}

	@PostMapping("/admin/uploadJar")
	public String uploadJar(@RequestParam("jar") MultipartFile jar,
	RedirectAttributes redirectAttributes) throws Exception{
		byte[] bytes = jar.getBytes();

        Path path = Paths.get("/jar/" + jar.getOriginalFilename());
        Files.write(path, bytes);
        redirectAttributes.addFlashAttribute("message", "已经将 '" + jar.getOriginalFilename() + "' 的文件上传成功");
		return "上传成功";

	}




	@GetMapping("/admin/handle_replay")
	public Match handleReplay(@RequestParam("replay_id")int replayId) throws IOException, InterruptedException{
		Replay replay = mapper.selectReplayById(replayId);
		
		String replayPath = replay.getAbsPath();

		Match result = new Matchend(replayPath).showScoreboard();

		return result;

	}

	private String result(Boolean status,String msg){

		HashMap<String,Object> result = new HashMap<String,Object>();
		result.put("status", status);
		result.put("msg", msg);
		return result.toString();
	}
	



}
