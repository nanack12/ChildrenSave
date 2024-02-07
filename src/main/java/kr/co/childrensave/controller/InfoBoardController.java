package kr.co.childrensave.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller

public class InfoBoardController {
	
	@GetMapping("/info")
    public String board() {
        
        return "infoBoard";
    }
}