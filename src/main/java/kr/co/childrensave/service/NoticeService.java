package kr.co.childrensave.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import kr.co.childrensave.dto.NoticeDTO;
import kr.co.childrensave.entity.NoticeEntity;
import kr.co.childrensave.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;

// 서비스 클래스: 비즈니스 로직을 담당하는 클래스
@Service
@RequiredArgsConstructor
public class NoticeService {
	
    // 필수적으로 주입되어야 하는 의존성을 final로 선언하고 생성자를 통해 주입
	private final NoticeRepository noticeRepository;

	// 새로운 게시물 저장
	public void save(NoticeDTO noticeDTO) {
		NoticeEntity noticeEntity = NoticeEntity.toSaveEntity(noticeDTO);
		// 저장
		noticeRepository.save(noticeEntity);
	}
		
	// 모든 게시물 조회
	public List<NoticeDTO> findAll(){
		// 모든 게시물을 가져옴
		List<NoticeEntity> noticeEntityList = noticeRepository.findAll();
		List<NoticeDTO> noticeDTOList = new ArrayList<>();
		// 각 게시물을 DTO로 변환하여 리스트에 추가
		for (NoticeEntity noticeEntity: noticeEntityList) {
			noticeDTOList.add(NoticeDTO.toNoticeDTO(noticeEntity));
		}
		return noticeDTOList;
	}
	
	// 조회수 증가
	@Transactional
	public void updateHits(Long id) {
		// 트랜잭션 내에서 조회수 증가 로직 수행
		noticeRepository.updateHits(id);
	}

	// 게시물 상세 조회
	public NoticeDTO findById(Long id) {
		// ID로 게시물을 찾음
		Optional<NoticeEntity> optionalNoticeEntity = noticeRepository.findById(id);
		if (optionalNoticeEntity.isPresent()) {
			// 게시물이 존재하면 DTO로 변환하여 반환
			NoticeEntity noticeEntity= optionalNoticeEntity.get();
			NoticeDTO noticeDTO = NoticeDTO.toNoticeDTO(noticeEntity);
			return noticeDTO;
		} else {
			// 게시물이 존재하지 않으면 null 반환
			return null;
		}
	}

	// 게시물 업데이트
	public NoticeDTO update(NoticeDTO noticeDTO) {
		// DTO를 Entity로 변환
		NoticeEntity noticeEntity = NoticeEntity.toUpdateEntity(noticeDTO);
		// 저장
		noticeRepository.save(noticeEntity);
		// 업데이트된 게시물의 상세 정보를 조회하여 반환
		return findById(noticeDTO.getId());
	}

	public void delete(Long id) {
		noticeRepository.deleteById(id);
	}

	public Page<NoticeDTO> paging(Pageable pageable) {
		int page = pageable.getPageNumber() -1;
		int pageLimit = 3; // 한 페이지에 보여줄 글 갯수
		//한 페이지당 3개씩 글을 보여주고 정렬 기준은 id 기준으로 내림차순 정렬
		//page 위치에 있는 값은 0부터 시작
		Page<NoticeEntity> noticeEntities = 
				noticeRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "id")));
		
		System.out.println("noticeEntities.getContent() = " + noticeEntities.getContent()); // 요청 페이지에 해당하는 글
        System.out.println("noticeEntities.getTotalElements() = " + noticeEntities.getTotalElements()); // 전체 글갯수
        System.out.println("noticeEntities.getNumber() = " + noticeEntities.getNumber()); // DB로 요청한 페이지 번호
        System.out.println("noticeEntities.getTotalPages() = " + noticeEntities.getTotalPages()); // 전체 페이지 갯수
        System.out.println("noticeEntities.getSize() = " + noticeEntities.getSize()); // 한 페이지에 보여지는 글 갯수
        System.out.println("noticeEntities.hasPrevious() = " + noticeEntities.hasPrevious()); // 이전 페이지 존재 여부
        System.out.println("noticeEntities.isFirst() = " + noticeEntities.isFirst()); // 첫 페이지 여부
        System.out.println("noticeEntities.isLast() = " + noticeEntities.isLast()); // 마지막 페이지 여부
		
        //목록: id, writer, title, hits, createdTime , entity를 bardDTO객체로 변환
        Page<NoticeDTO> noticeDTOS = noticeEntities.map(notice -> new NoticeDTO(notice.getId(), notice.getBoardWriter(), notice.getBoardTitle(), notice.getBoardHits(), notice.getCreatedTime()));
        return noticeDTOS;
	} 
}









