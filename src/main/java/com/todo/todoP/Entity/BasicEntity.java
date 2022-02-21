package com.todo.todoP.Entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@MappedSuperclass
public class BasicEntity {

    @Column(updatable = false)
    private LocalDateTime createDate;
    private LocalDateTime modifiedDate;

    @PrePersist
    public void prePersist(){
        LocalDateTime now = LocalDateTime.now();
        createDate = now;
        modifiedDate = now;
    }

    @PreUpdate
    public void preUpdate(){
        modifiedDate = LocalDateTime.now();
    }
}
