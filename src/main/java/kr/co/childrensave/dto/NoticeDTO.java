package kr.co.childrensave.dto;

import java.time.LocalDateTime;

import kr.co.childrensave.entity.NoticeEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// DTO(Data Transfer Object), VO, Bean // Entity(목적이 다름)
@Getter
@Setter
@ToString
@NoArgsConstructor //기본생성자
@AllArgsConstructor //모든 필드를 매개변수로 하는 생성자
public class NoticeDTO {
	private Long id;
	private String boardWriter;
	private String boardPass;
	private String boardTitle;
	private String boardContents;
	private int boardHits;
	private LocalDateTime boardCreatedTime;
	private LocalDateTime boardUpdatedTime;
	
	
	public NoticeDTO(Long id, String boardWriter, String boardTitle, int boardHits, LocalDateTime boardCreatedTime) {
		this.id = id;
		this.boardWriter = boardWriter;
		this.boardTitle = boardTitle;
		this.boardHits = boardHits;
		this.boardCreatedTime = boardCreatedTime;
	}
	
	public static NoticeDTO toNoticeDTO(NoticeEntity noticeEntity) {
		NoticeDTO noticeDTO = new NoticeDTO();
		noticeDTO.setId(noticeEntity.getId());
		noticeDTO.setBoardWriter(noticeEntity.getBoardWriter());
		noticeDTO.setBoardPass(noticeEntity.getBoardPass());
		noticeDTO.setBoardTitle(noticeEntity.getBoardTitle());
		noticeDTO.setBoardContents(noticeEntity.getBoardContents());
		noticeDTO.setBoardHits(noticeEntity.getBoardHits());
		noticeDTO.setBoardCreatedTime(noticeEntity.getCreatedTime());
		noticeDTO.setBoardUpdatedTime(noticeEntity.getUpdateTime());
		return noticeDTO;
	}



}


