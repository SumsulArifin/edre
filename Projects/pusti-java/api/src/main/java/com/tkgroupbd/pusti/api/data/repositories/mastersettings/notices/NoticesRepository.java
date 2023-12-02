package com.tkgroupbd.pusti.api.data.repositories.mastersettings.notices;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.notices.Notice;

import java.util.List;

@Repository
public interface NoticesRepository extends JpaRepository<Notice, Integer> {
    @Query("SELECT n FROM Notice n WHERE n.noticeTitle LIKE %:noticeTitle% ESCAPE '!' ORDER BY n.noticeTitle ASC")
    List<Notice> findByNoticeTitleContaining(String noticeTitle);
}
