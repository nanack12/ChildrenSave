package kr.co.childrensave.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import kr.co.childrensave.dto.MemberDTO;
import kr.co.childrensave.dto.MemberDetails;
import kr.co.childrensave.entity.UserEntity;
import kr.co.childrensave.repository.UserRepository;

@Service
public class MemberDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;

	
	@Override
	   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {   
	      UserEntity userData = userRepository.findByMemberId(username);
	      System.out.println(username);  
	      if(userData!=null) {			
	    	  System.out.println(userData.getMemberPw());
	         return new MemberDetails(userData);
	      } 
	      
	      return null;
	}

}
