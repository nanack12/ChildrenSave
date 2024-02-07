package kr.co.childrensave.controller;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.childrensave.dto.NoticeDTO;
import kr.co.childrensave.service.NoticeService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/notice")

public class NoticeController {
	
	private final NoticeService noticeService;
	
	@GetMapping("/write")
	public String saveForm() {
		return "noticeWrite";
	}
	
	@PostMapping("/write")
	public String save(@ModelAttribute NoticeDTO noticeDTO) {
	    System.out.println("noticeDTO = " + noticeDTO);
	    noticeService.save(noticeDTO);
	    return "redirect:/notice/list";  // 수정된 부분
	}
	
	/*
	 * @GetMapping("/") public String findAll(Model model) { //DB에서 전체 게시글 데이터를 가져와서
	 * list.html에 보여준다. List<BoardDTO> boardDTOList = boardService.findAll();
	 * model.addAttribute("boardList", boardDTOList); return "list"; }
	 */
	
	@GetMapping("/{id}")
	public String findById(@PathVariable Long id, Model model,
						   @PageableDefault(page=1) Pageable pageable) {
		/*
		 	해당 게시글의 조회수를 하나 올리고
		 	게시글 데이터를 가져와서 detail, html에 출력
		 	
		 */
		noticeService.updateHits(id);
		NoticeDTO noticeDTO = noticeService.findById(id);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();	
		String id2 = SecurityContextHolder.getContext().getAuthentication().getName();
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		Iterator<? extends GrantedAuthority> iter = authorities.iterator();
		GrantedAuthority auth = iter.next();
		
		String role = auth.getAuthority();
		
		model.addAttribute("id", id2);
		model.addAttribute("role", role);
		model.addAttribute("authentication", authentication);
				
		model.addAttribute("board", noticeDTO);
		model.addAttribute("page", pageable.getPageNumber());
		return "noticeView";
		
	}
	
	@GetMapping("/update/{id}")
	public String updateForm(@PathVariable Long id, Model model) {
		NoticeDTO noticeDTO = noticeService.findById(id);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();	
		String id2 = SecurityContextHolder.getContext().getAuthentication().getName();
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		Iterator<? extends GrantedAuthority> iter = authorities.iterator();
		GrantedAuthority auth = iter.next();
		
		String role = auth.getAuthority();
		
		model.addAttribute("id", id2);
		model.addAttribute("role", role);
		model.addAttribute("authentication", authentication);
		model.addAttribute("boardUpdate", noticeDTO);
		return "noticeEdit";
	}
	
	@PostMapping("/update")
	public String update(@ModelAttribute NoticeDTO noticeDTO, Model model) {
		NoticeDTO notice= noticeService.update(noticeDTO);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();	
		String id2 = SecurityContextHolder.getContext().getAuthentication().getName();
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		Iterator<? extends GrantedAuthority> iter = authorities.iterator();
		GrantedAuthority auth = iter.next();
		
		String role = auth.getAuthority();
		
		model.addAttribute("id", id2);
		model.addAttribute("role", role);
		model.addAttribute("authentication", authentication);
		model.addAttribute("notice", notice);
		//return "detail";
		return "redirect:/notice/" + noticeDTO.getId();
	}
	
	@GetMapping("/cancel/{id}")
	public String cancelEdit(@PathVariable Long id) {
	    // 게시물 ID에 해당하는 변수가 있다고 가정하고, 'your_post_id'를 실제 게시물 ID로 바꿉니다.
	    return "redirect:/notice/{id}";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Long id) {
		noticeService.delete(id);
		return "redirect:/notice/list";
	}
	
    @GetMapping("/list")
    public String paging(@PageableDefault(page = 1) Pageable pageable, Model model) {
//        pageable.getPageNumber();
        Page<NoticeDTO> noticeList = noticeService.paging(pageable);
        int blockLimit = 5;
        
        int startPage, endPage;
        if (!noticeList.hasContent()) { // 데이터가 없는 경우
            startPage = 1;
            endPage = 1;
        } else { // 데이터가 있는 경우
            startPage = (int) (Math.ceil(pageable.getPageNumber() / (double) blockLimit) - 1) * blockLimit + 1;
            endPage = Math.min(startPage + blockLimit - 1, noticeList.getTotalPages());
        }

        // page 갯수 20개
        // 현재 사용자가 3페이지
        // 1 2 3
        // 현재 사용자가 7페이지
        // 7 8 9
        // 보여지는 페이지 갯수 3개
        // 총 페이지 갯수 8개
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();    
        String id = SecurityContextHolder.getContext().getAuthentication().getName();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iter = authorities.iterator();
        GrantedAuthority auth = iter.next();
        
        String role = auth.getAuthority();
        
        model.addAttribute("id", id);
        model.addAttribute("role", role);
        model.addAttribute("authentication", authentication);
        model.addAttribute("boardList", noticeList);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "noticeList";

    }
}








