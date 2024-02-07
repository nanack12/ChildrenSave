package kr.co.childrensave.controller;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

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

import kr.co.childrensave.dto.BoardDTO;
import kr.co.childrensave.dto.CommentDTO;
import kr.co.childrensave.service.BoardService;
import kr.co.childrensave.service.CommentService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")

public class BoardController {
	
	private final BoardService boardService;
	private final CommentService commentService;
	
	@GetMapping("/save")
	public String saveForm(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();	
		String id = SecurityContextHolder.getContext().getAuthentication().getName();
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		Iterator<? extends GrantedAuthority> iter = authorities.iterator();
		GrantedAuthority auth = iter.next();
		
		String role = auth.getAuthority();
		
		model.addAttribute("id", id);
		model.addAttribute("role", role);
		model.addAttribute("authentication", authentication);

		return "write";
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute BoardDTO boardDTO, Model model) {
		System.out.println("boardDTO = " + boardDTO);
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();	
		String id = SecurityContextHolder.getContext().getAuthentication().getName();
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		Iterator<? extends GrantedAuthority> iter = authorities.iterator();
		GrantedAuthority auth = iter.next();
		
		String role = auth.getAuthority();
		
		model.addAttribute("id", id);
		model.addAttribute("role", role);
		model.addAttribute("authentication", authentication);
		boardService.save(boardDTO);
		return "redirect:/board/paging";
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
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();	
		String id2 = SecurityContextHolder.getContext().getAuthentication().getName();
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		Iterator<? extends GrantedAuthority> iter = authorities.iterator();
		GrantedAuthority auth = iter.next();
		String role = auth.getAuthority();
		boardService.updateHits(id);
		BoardDTO boardDTO = boardService.findById(id);
		/* 댓글 목록 가져오기 */
		List<CommentDTO> commentDTOList = commentService.findAll(id);
		

		model.addAttribute("id", id2);
		model.addAttribute("role", role);
		model.addAttribute("authentication", authentication);
		model.addAttribute("commentList", commentDTOList);
		
		model.addAttribute("board", boardDTO);
		model.addAttribute("page", pageable.getPageNumber());
		return "view";
		
	}
	
	@GetMapping("/update/{id}")
	public String updateForm(@PathVariable Long id, Model model) {
		BoardDTO boardDTO = boardService.findById(id);
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();	
		String id2 = SecurityContextHolder.getContext().getAuthentication().getName();
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		Iterator<? extends GrantedAuthority> iter = authorities.iterator();
		GrantedAuthority auth = iter.next();
		
		String role = auth.getAuthority();
		
		model.addAttribute("id", id2);
		model.addAttribute("role", role);
		model.addAttribute("authentication", authentication);

		model.addAttribute("boardUpdate", boardDTO);
		return "edit";
	}
	
	@PostMapping("/update")
	public String update(@ModelAttribute BoardDTO boardDTO, Model model) {
		BoardDTO board = boardService.update(boardDTO);
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();	
		String id = SecurityContextHolder.getContext().getAuthentication().getName();
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		Iterator<? extends GrantedAuthority> iter = authorities.iterator();
		GrantedAuthority auth = iter.next();
		
		String role = auth.getAuthority();
		
		model.addAttribute("id", id);
		model.addAttribute("role", role);
		model.addAttribute("authentication", authentication);

		model.addAttribute("board", board);
		//return "detail";
		return "redirect:/board/" + boardDTO.getId();
	}
	
	@GetMapping("/cancel/{id}")
	public String cancelEdit(@PathVariable Long id, Model model) {
	    // 게시물 ID에 해당하는 변수가 있다고 가정하고, 'your_post_id'를 실제 게시물 ID로 바꿉니다.
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();	
		String id2 = SecurityContextHolder.getContext().getAuthentication().getName();
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		Iterator<? extends GrantedAuthority> iter = authorities.iterator();
		GrantedAuthority auth = iter.next();
		
		String role = auth.getAuthority();
		
		model.addAttribute("id", id2);
		model.addAttribute("role", role);
		model.addAttribute("authentication", authentication);
	    return "redirect:/board/{id}";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Long id, Model model) {
		boardService.delete(id);
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();	
		String id2 = SecurityContextHolder.getContext().getAuthentication().getName();
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		Iterator<? extends GrantedAuthority> iter = authorities.iterator();
		GrantedAuthority auth = iter.next();
		
		String role = auth.getAuthority();
		
		model.addAttribute("id", id2);
		model.addAttribute("role", role);
		model.addAttribute("authentication", authentication);
		return "redirect:/board/paging";
	}
	
    // /board/paging?page=1
	// /board/paging?page=1
    @GetMapping("/paging")
    public String paging(@PageableDefault(page = 1) Pageable pageable, Model model) {
//        pageable.getPageNumber();
        Page<BoardDTO> boardList = boardService.paging(pageable);
        int blockLimit = 5;
        
        int startPage, endPage;
        if (!boardList.hasContent()) { // 데이터가 없는 경우
            startPage = 1;
            endPage = 1;
        } else { // 데이터가 있는 경우
            startPage = (int) (Math.ceil(pageable.getPageNumber() / (double) blockLimit) - 1) * blockLimit + 1;
            endPage = Math.min(startPage + blockLimit - 1, boardList.getTotalPages());
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
        model.addAttribute("boardList", boardList);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
       
        return "list";  
    }
}








