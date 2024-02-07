package kr.co.childrensave.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import kr.co.childrensave.dto.BoardDTO;
import lombok.Getter;
import lombok.Setter;

//DB의 테이블 역할을 하는 클래스
@Getter
@Setter
@Table(name = "board_table")
@Entity
public class BoardEntity extends BaseEntity{
	@Id // pk 컬럼 지정. 필수
	@GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
	private Long id;
	
	@Column(length = 20, nullable = false) //크기는 20, not null을 지정하는 옵션
	private String boardWriter;
	
	@Column//크기 255, null 가능
	private String boardPass;
	
	@Column
	private String boardTitle;
	
	@Column(length = 500)
	private String boardContents;
	
	@Column
	private int boardHits;
	
	@OneToMany(mappedBy = "boardEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<CommentEntity> commentEntityList = new ArrayList<>();
	
	public static BoardEntity toSaveEntity(BoardDTO boardDTO) {
		
		BoardEntity boardEntity = new BoardEntity();
		boardEntity.setBoardWriter(boardDTO.getBoardWriter());
		boardEntity.setBoardPass(boardDTO.getBoardPass());
		boardEntity.setBoardTitle(boardDTO.getBoardTitle());
		boardEntity.setBoardContents(boardDTO.getBoardContents());
		boardEntity.setBoardHits(0);
		
		return boardEntity;
	}

	public static BoardEntity toUpdateEntity(BoardDTO boardDTO) {
		BoardEntity boardEntity = new BoardEntity();
		boardEntity.setId(boardDTO.getId());
		boardEntity.setBoardWriter(boardDTO.getBoardWriter());
		boardEntity.setBoardPass(boardDTO.getBoardPass());
		boardEntity.setBoardTitle(boardDTO.getBoardTitle());
		boardEntity.setBoardContents(boardDTO.getBoardContents());
		boardEntity.setBoardHits(boardDTO.getBoardHits());
		
		return boardEntity;
	}
		
}
