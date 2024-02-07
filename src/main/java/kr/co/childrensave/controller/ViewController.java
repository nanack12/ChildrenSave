package kr.co.childrensave.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {
	
	@GetMapping("/fragments/header")
	public String headerPage(@AuthenticationPrincipal User user , Model model) {
		model.addAttribute("id", user.getUsername());
		model.addAttribute("role", user.getAuthorities());
		return "/fragments/header";
	}
}
