package com.tkgroupbd.pusti.api.controllers.mastersettings.notices;

import com.hazelcast.shaded.org.json.HTTP;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.notices.Notice;
import com.tkgroupbd.pusti.api.data.payloads.dto.mastersettings.notices.NoticesRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import com.tkgroupbd.pusti.api.services.mastersettings.notices.NoticesServices;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Tag(name = "Notice")
@RestController
@RequestMapping("/notice")
public class NoticesController {
    @Autowired
    NoticesServices noticesServices;

    // Create a new Notices
    @PostMapping(value = "/addNewNotice", consumes = "multipart/form-data")
    public ResponseEntity<MessageResponse> addNotice(@ModelAttribute NoticesRequest noticesRequest, @RequestPart MultipartFile multipartFile) throws IOException {
        MessageResponse newNotice = noticesServices.addNotice(noticesRequest, multipartFile);
        return new ResponseEntity<>(newNotice, HttpStatus.CREATED);
    }

    // Update a Notices information
    @PutMapping("/updateNotice/{id}")
    public ResponseEntity<Optional<Notice>> updateNotice(@PathVariable Integer id,
            @RequestBody NoticesRequest noticesRequest) {
        Optional<Notice> updateNotice = noticesServices.updateNotice(id, noticesRequest);
        return new ResponseEntity<Optional<Notice>>(updateNotice, HttpStatus.OK);
    }

    // Notices Status Change API
    @PutMapping("/changeStatus/{id}")
    public ResponseEntity<Optional<Notice>> changeNoticesStatus(@PathVariable Integer id,
            @RequestBody NoticesRequest noticeRequest) {
        Optional<Notice> updateNoticesStatus = noticesServices.changeStatus(id, noticeRequest);
        return new ResponseEntity<Optional<Notice>>(updateNoticesStatus, HttpStatus.OK);
    }

    // get All Notices
    @GetMapping("/getAllNotices")
    public ResponseEntity<List<Notice>> getAllNotices() {
        List<Notice> notices = noticesServices.getAllNotices();
        return new ResponseEntity<>(notices, HttpStatus.OK);
    }

    // Delete Notices by id
    @DeleteMapping("/deleteNotice/{id}")
    public ResponseEntity<?> deleteNotice(@PathVariable("id") Integer id) {
        noticesServices.deleteNotice(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Find by id Api
    @GetMapping("/getNoticeById/{id}")
    public ResponseEntity<Notice> getNoticeById(@PathVariable("id") Integer id) {
        Notice notices = noticesServices.getNoticesById(id);
        return new ResponseEntity<>(notices, HttpStatus.OK);
    }

    @GetMapping("/getNoticeByTitle")
    public ResponseEntity<List<Notice>> getNoticeByTitle(@RequestParam("noticeTitle") String noticeTitle) {
        List<Notice> notices = noticesServices.getNoticesByTitle(noticeTitle);
        return new ResponseEntity<>(notices, HttpStatus.OK);
    }

    @GetMapping("/getPaginatedNotices")
    private ResponseEntity<Page<Notice>> getPaginatedNotices(@RequestParam("offset") int offset, @RequestParam("pageSize") int pageSize) {
        Page<Notice> paginatedNotices = noticesServices.findNoticesByPagination(offset, pageSize);
        return new ResponseEntity<>(paginatedNotices,HttpStatus.OK);
    }
}
