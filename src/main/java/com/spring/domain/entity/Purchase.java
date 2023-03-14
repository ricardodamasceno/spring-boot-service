package com.spring.domain.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.spring.domain.enums.PurchaseStatus;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@Data
@Entity
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    private User buyer;

    private LocalDateTime purchaseDate;

    @JsonManagedReference
    @OneToMany(mappedBy = "purchase", fetch = FetchType.EAGER)
    private List<PurchaseItem> items;

    private BigDecimal purchaseValue;

    @Enumerated(EnumType.STRING)
    private PurchaseStatus status;

}
