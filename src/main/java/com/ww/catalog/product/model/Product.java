package com.ww.catalog.product.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(title = "The database generated product ID")
    private long id;

    @Schema(title = "The Product Name", required = true)
    @Column(unique = true)
    private String productName;

    @Schema(title = "The product description")
    private String description;
    @Schema(title = "The URL of the product")
    private String Url;
    @Schema(title = "Currency")
    private String currency;

    @Schema(title = "Media List")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn( name = "media_fk", referencedColumnName = "id")
    private Set<Media> medias = new HashSet<>();

    @Schema(title = "SKU List")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn( name = "sku_fk", referencedColumnName = "id")
    private Set<SKU> skus = new HashSet<>();

    @Column(name = "CATEGORY_ID", nullable = false)
	private Long categoryId;

    @Transient
	private String category;

    @Schema(title = "Sub Category")
    private String subCategory;
}