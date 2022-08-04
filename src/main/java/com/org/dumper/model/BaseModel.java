package com.org.dumper.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@Data
public abstract class BaseModel implements Serializable {

    @JsonIgnore
    private Date createdDate;
    @JsonIgnore
    private Date updatedDate;

    @PrePersist
    protected void onCreate() {
        createdDate = new Date();
        updatedDate = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedDate = new Date();
    }


}
