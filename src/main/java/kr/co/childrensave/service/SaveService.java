package kr.co.childrensave.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import kr.co.childrensave.dto.MemberDTO;
import kr.co.childrensave.entity.UserEntity;
import kr.co.childrensave.repository.UserRepository;

@Service
public class SaveService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public void saveProcSv(MemberDTO dto) {
		
		//db에 동일한 아이디를 가진 회원 체크 
		boolean isUser = userRepository.existsByMemberId(dto.getMemberId());
		if(isUser) {
			return;
		}
		
		UserEntity data = new UserEntity();
		
		data.setMemberId(dto.getMemberId());
		data.setMemberEmail(dto.getMemberEmail());
		data.setMemberPhoneNo(dto.getMemberPhoneNo());
		data.setMemberPw(bCryptPasswordEncoder.encode(dto.getMemberPw()));
		data.setRole("ROLE_USER");
		
		userRepository.save(data);
	}
}
