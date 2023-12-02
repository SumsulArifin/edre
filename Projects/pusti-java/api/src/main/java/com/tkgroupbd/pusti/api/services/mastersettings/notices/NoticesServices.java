package com.tkgroupbd.pusti.api.services.mastersettings.notices;

import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.notices.Notice;
import com.tkgroupbd.pusti.api.data.payloads.dto.mastersettings.notices.NoticesRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Component
public interface NoticesServices {
    MessageResponse addNotice(NoticesRequest noticesRequest, MultipartFile multipartFile) throws IOException;

    Optional<Notice> updateNotice(Integer id, NoticesRequest noticesRequest);
    Optional<Notice> changeStatus(Integer noticeId, NoticesRequest noticesRequest);
    void deleteNotice(Integer id);
    Notice getNoticesById(Integer id);
    List<Notice> getAllNotices();

    public List<Notice> getNoticesByTitle(String noticeTitle);

    public Page<Notice> findNoticesByPagination(int offset, int pageSize);
}
