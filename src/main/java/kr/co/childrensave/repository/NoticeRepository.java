package kr.co.childrensave.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import kr.co.childrensave.entity.NoticeEntity;

public interface NoticeRepository extends JpaRepository<NoticeEntity, Long> {
    // update board_table set board_hits=board_hits+1 where id=?
    @Modifying
    @Query(value = "update NoticeEntity c set c.boardHits=c.boardHits+1 where c.id=:id")
    void updateHits(@Param("id") Long id);
}