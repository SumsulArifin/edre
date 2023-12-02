package com.tkgroupbd.pusti.api.data.models.common;


import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tkgroupbd.pusti.api.configs.ipandbrowserconfig.RequestInfoContextHolder;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;


@MappedSuperclass
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BaseEntity {
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(insertable = true, updatable = false)
    private LocalDate createdAt;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(insertable = false, updatable = true)
    private LocalDate updatedAt;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(insertable = false, updatable = true)
    private LocalDate deletedAt;

    private String browser = RequestInfoContextHolder.getBrowser();
    ;
    private String createdBy;
    private String updatedBy;
    private String deletedBy;
    private String ip = RequestInfoContextHolder.getIp();
    ;
    private boolean status = true;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDate.now();
        setCreatedBy();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDate.now();
        setUpdatedBy();
    }

    @PreRemove
    protected void onDelete() {
        deletedAt = LocalDate.now();
        setDeletedBy();
    }

    private void setCreatedBy() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            createdBy = ((UserDetails) authentication.getPrincipal()).getUsername();
        }
    }

    private void setUpdatedBy() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            updatedBy = ((UserDetails) authentication.getPrincipal()).getUsername();
        }
    }

    private void setDeletedBy() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            deletedBy = ((UserDetails) authentication.getPrincipal()).getUsername();
        }
    }
}
