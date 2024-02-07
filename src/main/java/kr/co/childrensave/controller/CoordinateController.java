package kr.co.childrensave.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.annotation.Resource;
import kr.co.childrensave.dao.CoordinateDAO;
import kr.co.childrensave.dto.CoordinateDTO;
import kr.co.childrensave.service.CoordinateService;



@ControllerAdvice
@Controller
public class CoordinateController {

	@Resource
	private CoordinateDAO coordinateDAO;
	
	@Resource
	private CoordinateService coordinateService;
	
	@GetMapping("/test.do")
	public String test(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		String id = SecurityContextHolder.getContext().getAuthentication().getName();
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		Iterator<? extends GrantedAuthority> iter = authorities.iterator();
		GrantedAuthority auth = iter.next();

		String role = auth.getAuthority();

		model.addAttribute("id", id);
		model.addAttribute("role", role);
		model.addAttribute("authentication", authentication);

		
		return "test";
	}

	@ResponseBody
	@GetMapping("/testdb.do")
	public List<CoordinateDTO> selectCoordinate(){
		List<CoordinateDTO> result = new ArrayList<CoordinateDTO>();
		CoordinateDTO dto = new CoordinateDTO();
		result = coordinateDAO.selectCoordinate(dto);
		return result;
		
	}
	
	@ResponseBody
	@PostMapping("/togglelist.do")
	public List<CoordinateDTO> toggleData(CoordinateDTO dto){
		List<CoordinateDTO> result = new ArrayList<CoordinateDTO>();
		result = coordinateService.selectToggle(dto);
		
		return result;		
	}
	
	@ResponseBody
	@PostMapping("/toggleoverlay.do")
	public List<CoordinateDTO> overlayData(CoordinateDTO dto){
		List<CoordinateDTO> result1 = new ArrayList<CoordinateDTO>();
		result1 = coordinateService.selectToggleOverlay(dto);
		
		return result1;
	}
}

