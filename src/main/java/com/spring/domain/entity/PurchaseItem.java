package com.spring.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
public class PurchaseItem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "purchase_id")
    private Purchase purchase;

    @ManyToOne
    private Product product;

    private BigDecimal itemValue;

}
