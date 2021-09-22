package com.smth.dota2.galaxy.controller;




import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class RouteController {


    @GetMapping("/upload")
	public String upload() throws Exception{
		return "upload";
	}
}
