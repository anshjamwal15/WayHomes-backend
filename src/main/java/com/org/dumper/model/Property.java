package com.org.dumper.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "property")
public class Property extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long sqFeet;

    private Long bedrooms;

    private Long bathrooms;

    private Long garages;

    private Long price;

    @Column(name = "description", length = 512)
    private String description;

    @Column(name = "address", length = 512)
    private String address;

    @OneToMany(mappedBy = "property")
    private Set<PropertyImages> propertyImages;

    @ManyToMany
    private Set<FavProperties> favProperties;
}
