package com.ww.catalog.category.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "SubCategory")
public class SubCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(title = "The database generated Category ID")
    @JsonIgnore
    private long id;

    @Schema(title = "SubCategory Name")
    @Column(name = "SUBCATEGORY_NAME", unique = true)
    private String subCategoryName;

    @Schema(title = "Product Id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

}
