package com.org.dumper.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class PropertyImages extends BaseModel {

    @Id
    @GeneratedValue
    private Long id;

    private byte[] data;

    private String contentType;

    private String name;

    private Long size;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "property_id")
    private Property property;

}
