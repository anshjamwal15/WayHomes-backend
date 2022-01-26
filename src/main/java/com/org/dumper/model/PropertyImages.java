package com.org.dumper.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class PropertyImages extends BaseModel {

    @Id
    @GeneratedValue
    private Long id;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] data;

    private String path;

    private String contentType;

    private String name;

    private Long size;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "property_id")
    private Property property;

}
