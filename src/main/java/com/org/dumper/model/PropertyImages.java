package com.org.dumper.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PropertyImages extends BaseModel {

    @Id
    @GeneratedValue
    private Long id;

    private String path;

    private String contentType;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "property_id")
    private Property property;

}
