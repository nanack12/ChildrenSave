package kr.co.childrensave.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {
	
	private Long Id;
	private String memberId;
	private String memberEmail;
	private String memberPhoneNo;
	private String memberPw;

	
}