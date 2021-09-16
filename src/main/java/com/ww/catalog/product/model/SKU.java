package com.ww.catalog.product.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SKU {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(title = "The database generated sku ID")
    @JsonIgnore
    private long id;

    @Schema(title = "SKU Name", required = true)
    @Column(unique = true)
    private String name;

    @Schema(title = "SKU Description")
    private String description;

    @Schema(title = "SKU Retail Price")
    private BigDecimal retailPrice;

    @Schema(title = "SKU Sale Price")
    private BigDecimal SalePrice;

    @Schema(title = "SKU Inventory Type")
    private String inventoryType;

    @Schema(title = "SKU Quantity")
    private long quantity;

    @Schema(title = "Product Id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;
}
