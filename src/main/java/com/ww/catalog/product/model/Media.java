package com.ww.catalog.product.model;

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
public class Media {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(title = "The database generated sku ID")
    @JsonIgnore
    private long id;

    @Schema(title = "Media URL")
    private String Url;

    @Schema(title = "Media URL alt Text")
    private String altText;

    @Schema(title = "Product Id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;
}
