package com.org.dumper.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
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
