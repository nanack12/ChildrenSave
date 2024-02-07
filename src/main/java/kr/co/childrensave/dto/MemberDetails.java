package kr.co.childrensave.dto;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import kr.co.childrensave.entity.UserEntity;


public class MemberDetails implements UserDetails{
	
	public UserEntity userEntity;
	
	public MemberDetails(UserEntity userEntity) {
		this.userEntity = userEntity;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		Collection<GrantedAuthority> collection = new ArrayList<>();
		collection.add(new GrantedAuthority() {
			
			@Override
			public String getAuthority() {
				
				return userEntity.getRole();
			}
		});
		return collection;
	}

	@Override
	public String getPassword() {
		
		return userEntity.getMemberPw();
	}

	@Override
	public String getUsername() {
		
		return userEntity.getMemberId();
	}

	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}

	@Override
	public boolean isEnabled() {
		
		return true;
	}

}
