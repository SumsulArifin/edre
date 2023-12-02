package com.tkgroupbd.pusti.api.data.payloads.dto.mastersettings.notices;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tkgroupbd.pusti.api.data.models.common.BaseEntity;
import com.tkgroupbd.pusti.api.data.models.enums.NoticeType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = false)
public class NoticesRequest extends BaseEntity implements Serializable {
    @Enumerated(EnumType.STRING)
    private NoticeType noticeType;

    @NotBlank(message = "cannot be blank")
    @NotNull(message = "cannot be null")
    private String noticeTitle;

    @NotBlank(message = "cannot be blank")
    @NotNull(message = "cannot be null")
    private String noticeDetails;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate effectiveDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate expireDate;
    private MultipartFile uploadedFile;
}
