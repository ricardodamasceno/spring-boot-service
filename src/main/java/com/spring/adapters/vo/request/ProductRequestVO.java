package com.spring.adapters.vo.request;

import com.spring.domain.enums.ProductCategory;
import lombok.Data;
import lombok.NonNull;

import java.math.BigDecimal;

@Data
public class ProductRequestVO {

    @NonNull
    private String name;

    @NonNull
    private ProductCategory productCategory;

    @NonNull
    private BigDecimal value;

}
