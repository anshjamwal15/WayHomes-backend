package com.org.dumper.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.io.Serializable;
import java.time.Instant;

@MappedSuperclass
@Data
public abstract class BaseModel implements Serializable {

    @JsonIgnore
    private Instant createdDate;
    @JsonIgnore
    private Instant updatedDate;

    @PrePersist
    protected void onCreate() {
        createdDate = Instant.now();
        updatedDate = Instant.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedDate = Instant.now();
    }


}
