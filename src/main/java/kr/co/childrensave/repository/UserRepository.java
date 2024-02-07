package kr.co.childrensave.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.childrensave.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
	
	boolean existsByMemberId(String memberId);
	
	UserEntity findByMemberId(String memberId);
}
