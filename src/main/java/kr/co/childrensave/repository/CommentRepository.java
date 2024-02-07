package kr.co.childrensave.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.childrensave.entity.BoardEntity;
import kr.co.childrensave.entity.CommentEntity;

public interface CommentRepository extends JpaRepository<CommentEntity, Long>{
	//select * from comment_table where board_id=? order by id desc;
	List<CommentEntity> findAllByBoardEntityOrderByIdDesc(BoardEntity boardEntity);
}
