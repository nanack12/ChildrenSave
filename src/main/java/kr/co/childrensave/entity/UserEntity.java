package kr.co.childrensave.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import kr.co.childrensave.dto.MemberDTO;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class UserEntity {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(unique = true)
	private String memberId;
	
	private String memberEmail;
	private String memberPhoneNo;
	private String memberPw;
	private String role;
	
	
}
