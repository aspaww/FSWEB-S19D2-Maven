// src/main/java/com/workintech/s18d4/entity/Account.java
package com.workintech.s18d4.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "accounts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String accountName;
    private Double moneyAmount;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
