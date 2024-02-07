package kr.co.childrensave.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.childrensave.dao.CoordinateDAO;
import kr.co.childrensave.dao.HomeDAO;
import kr.co.childrensave.dto.CoordinateDTO;
import kr.co.childrensave.dto.HomeDTO;
import kr.co.childrensave.dto.MemberDTO;
import kr.co.childrensave.entity.UserEntity;
import kr.co.childrensave.repository.UserRepository;
import kr.co.childrensave.service.HomeService;
import kr.co.childrensave.service.SaveService;

@Controller
public class HomeController {
	
	@Autowired
	private SaveService saveService;
	@Resource
	private HomeDAO homedao;
	@Resource
	private HomeService homeService;
	@Resource
	private CoordinateDAO coordinateDAO;
	
	
	// 기본페이지 요청 메서드
	@GetMapping("/")
	public String intro(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();	
		String id = SecurityContextHolder.getContext().getAuthentication().getName();
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		Iterator<? extends GrantedAuthority> iter = authorities.iterator();
		GrantedAuthority auth = iter.next();
		
		String role = auth.getAuthority();
		
		model.addAttribute("id", id);
		model.addAttribute("role", role);
		model.addAttribute("authentication", authentication);
		
		return "intro";
	}
	
	
	@GetMapping("/index")
	public String index(Model model) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();	
		String id = SecurityContextHolder.getContext().getAuthentication().getName();
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		Iterator<? extends GrantedAuthority> iter = authorities.iterator();
		GrantedAuthority auth = iter.next();
		
		String role = auth.getAuthority();
		
		model.addAttribute("id", id);
		model.addAttribute("role", role);
		model.addAttribute("authentication", authentication);

		return "index"; // templates 폴더의 index.html 찾아감
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	

	

	 @PostMapping("/loginProc") 
	 public String loginProcess() { 
		 return "forward:/index"; 
	 }
	 
	 @PostMapping("/logout")
	    public String logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
	        if (authentication != null) {
	            new SecurityContextLogoutHandler().logout(request, response, authentication);
	        }
	        return "redirect:/index";  
	    }

	
	@GetMapping("/save")
	public String save() {
		return "save";
	}
	
	
	@PostMapping("/saveProc")
	public String saveProcess(MemberDTO dto) {
		
		saveService.saveProcSv(dto);
		return "redirect:/login";
	}
	
	@GetMapping("/mypage")
	public String myPage(Model model) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();	
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		Iterator<? extends GrantedAuthority> iter = authorities.iterator();
		GrantedAuthority auth = iter.next();
		
		UserEntity data = new UserEntity();
		
		String id = data.getMemberId();
		String email = data.getMemberEmail();
		String hp = data.getMemberPhoneNo();
		String pw = data.getMemberPw();
		
		
		model.addAttribute("id", id);
		model.addAttribute("email", email);
		model.addAttribute("hp", hp);
		model.addAttribute("pw", pw);
		
		return "mypage";
	}
	
	@GetMapping("/withdraw")
	public String withdraw() {
		return "withdraw";
	}
	
	@ResponseBody
	@GetMapping("/school.do")
	public List<HomeDTO> schoolData(HomeDTO dto){
		List<HomeDTO> result = new ArrayList<HomeDTO>();
		result = homedao.homeSchool(dto);
		
		return result;
		
	}

	@ResponseBody
	@GetMapping("/badack.do")
	public List<CoordinateDTO> badackData(CoordinateDTO dto){
		List<CoordinateDTO> result1 = new ArrayList<CoordinateDTO>();
		result1 = homedao.badack(dto);
		
		return result1;
	}
	
	@GetMapping("/siteinfo.do")
	public String siteinfo(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();	
		String id = SecurityContextHolder.getContext().getAuthentication().getName();
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		Iterator<? extends GrantedAuthority> iter = authorities.iterator();
		GrantedAuthority auth = iter.next();
		
		String role = auth.getAuthority();
		
		model.addAttribute("id", id);
		model.addAttribute("role", role);
		model.addAttribute("authentication", authentication);

		return "siteInfo"; // templates 폴더의 index.html 찾아감
	}
	
	@GetMapping("/teaminfo.do")
	public String teaminfo(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();	
		String id = SecurityContextHolder.getContext().getAuthentication().getName();
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		Iterator<? extends GrantedAuthority> iter = authorities.iterator();
		GrantedAuthority auth = iter.next();
		
		String role = auth.getAuthority();
		
		model.addAttribute("id", id);
		model.addAttribute("role", role);
		model.addAttribute("authentication", authentication);

		return "teamInfo"; // templates 폴더의 index.html 찾아감
	}
	
	
}
