package kr.co.childrensave.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.childrensave.dto.CommentDTO;
import kr.co.childrensave.service.CommentService;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/save")
    public ResponseEntity save(@ModelAttribute CommentDTO commentDTO, Model model) {
        System.out.println("commentDTO = " + commentDTO);
        
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();	
		String id = SecurityContextHolder.getContext().getAuthentication().getName();
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		Iterator<? extends GrantedAuthority> iter = authorities.iterator();
		GrantedAuthority auth = iter.next();
		
		String role = auth.getAuthority();
		
		model.addAttribute("id", id);
		model.addAttribute("role", role);
		model.addAttribute("authentication", authentication);
        Long saveResult = commentService.save(commentDTO);
        if (saveResult != null) {
            // 작성 성공하면 댓글 목록을 가져와서 리턴
            // 댓글목록: 해당 게시글의 댓글전체
            List<CommentDTO> commentDTOList = commentService.findAll(commentDTO.getBoardId());
            return new ResponseEntity<>(commentDTOList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("해당 게시글이 존재하지 않습니다.", HttpStatus.NOT_FOUND);
        }
    }
}