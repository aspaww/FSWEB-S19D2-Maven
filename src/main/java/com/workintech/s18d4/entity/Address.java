// src/main/java/com/workintech/s18d4/entity/Address.java
package com.workintech.s18d4.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "addresses")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String street;
    private Integer no;
    private String city;
    private String country;
    private String description;

    @OneToOne(mappedBy = "address", cascade = CascadeType.ALL)
    private Customer customer;
}
