package com.hksar.sar.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;

@Data
@MappedSuperclass
public class BaseEntity {

    @Column(name = "created_at")
    protected LocalDateTime createdAt;
    @Column(name = "updated_at")
    protected LocalDateTime updatedAt;
    @Column(name = "updated_by")
    protected String updatedBy = "";

    @PrePersist
    public void prePersist() {
        LocalDateTime localDateTime = LocalDateTime.now();
        this.createdAt = localDateTime;
        this.updatedAt = localDateTime;
        addUpdatedByUser();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
        addUpdatedByUser();
    }

    void addUpdatedByUser() {
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = (User) principal;
            this.updatedBy = user.getName() + "<" + user.getUsername() + ">";
        } catch (Exception ex) {
            this.updatedBy = "Default";
        }
    }
}
