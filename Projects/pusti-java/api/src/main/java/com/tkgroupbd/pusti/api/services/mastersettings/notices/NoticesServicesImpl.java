package com.tkgroupbd.pusti.api.services.mastersettings.notices;

import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.notices.Notice;
import com.tkgroupbd.pusti.api.data.payloads.dto.mastersettings.notices.NoticesRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import com.tkgroupbd.pusti.api.data.repositories.mastersettings.notices.NoticesRepository;
import com.tkgroupbd.pusti.api.exceptions.ResourceNotFoundException;
import com.tkgroupbd.pusti.api.utils.constant.Message;
import org.apache.commons.compress.utils.FileNameUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class NoticesServicesImpl implements NoticesServices {
    @Autowired
    NoticesRepository noticesRepository;

    private static final String folderPath = "C://noticeFiles";

    @Override
    public MessageResponse addNotice(NoticesRequest noticesRequest, MultipartFile multipartFile) throws IOException {

        Notice notices = new Notice();

        notices.setNoticeType(noticesRequest.getNoticeType());
        notices.setNoticeTitle(noticesRequest.getNoticeTitle());
        notices.setNoticeDetails(noticesRequest.getNoticeDetails());
        notices.setEffectiveDate(noticesRequest.getEffectiveDate());
        notices.setExpireDate(noticesRequest.getExpireDate());

//        MultipartFile noticeFile = noticesRequest.getUploadedFile();
        String currentYear = String.valueOf(LocalDate.now().getYear());
        String fileName = "notice_" + currentYear + "_1" + "." + FilenameUtils.getExtension(multipartFile.getOriginalFilename());

        Path subfolderPath = Paths.get(folderPath, currentYear);
        if (Files.notExists(subfolderPath)) {
            Files.createDirectories(subfolderPath);
        }
        Path filePath =Paths.get(String.valueOf(subfolderPath), fileName);

        int fileNumber = 1;
        while (Files.exists(filePath)) {
            String newFileName = "notice_" + LocalDate.now() + "_" + fileNumber++ + "." + FileNameUtils.getExtension(multipartFile.getOriginalFilename());
            filePath = Paths.get(String.valueOf(subfolderPath), newFileName);
        }
        Files.write(filePath, multipartFile.getBytes());

        int splitIndex = folderPath.length();
        String pathDatabase = String.valueOf(filePath).substring(splitIndex);
        notices.setUploadedFile(pathDatabase);
        noticesRepository.save(notices);

        return new MessageResponse(Message.SUCCESS_NOTICE_CREATION);
    }

    @Override
    public Optional<Notice> updateNotice(Integer notice_id, NoticesRequest noticesRequest) {

        Optional<Notice> result = noticesRepository.findById(notice_id);
        if (result.isPresent()) {
            Notice notice = result.get();

            notice.setNoticeType(noticesRequest.getNoticeType());
            notice.setNoticeTitle(noticesRequest.getNoticeTitle());
            notice.setNoticeDetails(noticesRequest.getNoticeDetails());
            notice.setEffectiveDate(noticesRequest.getEffectiveDate());
            notice.setExpireDate(noticesRequest.getExpireDate());
            noticesRepository.save(notice);

            noticesRepository.save(notice);
        } else {
            throw new ResourceNotFoundException("Notice", "notice_id", notice_id);
        }

        return result;
    }

    @Override
    public Optional<Notice> changeStatus(Integer noticeId, NoticesRequest noticeRequest) {
        Optional<Notice> notice = noticesRepository.findById(noticeId);
        if (notice.isEmpty()) {
            throw new ResourceNotFoundException("Notice", "noticeId", noticeId);
        } else
            notice.get().setStatus(noticeRequest.isStatus());
        ;
        noticesRepository.save(notice.get());
        return notice;
    }

    @Override
    public void deleteNotice(Integer id) {
        Optional<Notice> optionalNotice = noticesRepository.findById(id);

        if (optionalNotice.isPresent()) {
            Notice notice = optionalNotice.get();
            String filePath = notice.getUploadedFile();

            try {
                Path path = Paths.get(folderPath, filePath);
                Files.delete(path);
                noticesRepository.delete(notice);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Notice getNoticesById(Integer id) {
        return noticesRepository.findById(id).get();
    }

    @Override
    public List<Notice> getAllNotices() {
        return noticesRepository.findAll();
    }

    @Override
    public List<Notice> getNoticesByTitle(String noticeTitle) {
        List<Notice> notices = noticesRepository.findByNoticeTitleContaining(noticeTitle);
        Collections.sort(notices, Comparator.comparing(Notice::getNoticeTitle));
        return notices;
    }
    @Override
    public Page<Notice> findNoticesByPagination(int offset, int pageSize) {
        Page<Notice> notices = noticesRepository.findAll(PageRequest.of(offset, pageSize));
        return notices;
    }
}
