package kr.co.childrensave.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import kr.co.childrensave.dto.NoticeDTO;
import lombok.Getter;
import lombok.Setter;

//DB의 테이블 역할을 하는 클래스
@Getter
@Setter
@Table(name = "notice_table")
@Entity
public class NoticeEntity extends BaseEntity{
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
	
	@OneToMany(mappedBy = "noticeEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    	
	public static NoticeEntity toSaveEntity(NoticeDTO noticeDTO) {
		
		NoticeEntity noticeEntity = new NoticeEntity();
		noticeEntity.setBoardWriter(noticeDTO.getBoardWriter());
		noticeEntity.setBoardPass(noticeDTO.getBoardPass());
		noticeEntity.setBoardTitle(noticeDTO.getBoardTitle());
		noticeEntity.setBoardContents(noticeDTO.getBoardContents());
		noticeEntity.setBoardHits(0);
		
		return noticeEntity;
	}

	public static NoticeEntity toUpdateEntity(NoticeDTO noticeDTO) {
		NoticeEntity noticeEntity = new NoticeEntity();
		noticeEntity.setId(noticeDTO.getId());
		noticeEntity.setBoardWriter(noticeDTO.getBoardWriter());
		noticeEntity.setBoardPass(noticeDTO.getBoardPass());
		noticeEntity.setBoardTitle(noticeDTO.getBoardTitle());
		noticeEntity.setBoardContents(noticeDTO.getBoardContents());
		noticeEntity.setBoardHits(noticeDTO.getBoardHits());
		
		return noticeEntity;
	}
		
}
