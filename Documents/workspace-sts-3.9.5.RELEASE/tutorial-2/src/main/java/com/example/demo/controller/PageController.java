package com.example.demo.controller;


import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class PageController {
	
	@RequestMapping("/viral")
	public String index() {
		return "viral";
	}

//	@RequestMapping("/challenge")
//	public String challenge(@RequestParam(value = "name") String name, Model model) {
//		model.addAttribute("name", name);
//		return "challenge";
//	}
	
	@RequestMapping(value = {"/challange","/challenge/{name}"})
	public String challengePath(@PathVariable Optional<String> name, Model model) {
		if (name.isPresent()) {
			model.addAttribute ("name", name.get());
		}
		else {
			model.addAttribute("name", "KB");
		}
		return "challenge";
	}
	
	@RequestMapping("/generator")
	public String viralGenerator(
			@RequestParam(value="a", required=false, defaultValue = "0") String tempA,
			@RequestParam(value="b", required = false, defaultValue = "0") String tempB,
			Model model) {
		
		String tempStr = "h";
		
		int x = Integer.parseInt(tempA);
		int y = Integer.parseInt(tempB);
		
		if(x==0) tempStr += "m";
		else {
			for (int i=0; i < x; i++) {
				tempStr+= "m";
			}
		}
		
		String res = tempStr;
		if (y>1) {
			for (int j=0; j < y-1; j++) {
				res += " " + tempStr;
			}
		}
		
		model.addAttribute("a", x);
		model.addAttribute("b", y);
		model.addAttribute("h", res);
		return "generator";
	}
}

