package com.ww.catalog.product.dao;

import java.util.List;

import com.ww.catalog.product.model.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ProductRepository extends JpaRepository<Product, Long> {

    
  @Query("SELECT p from Product p WHERE p.categoryId = :categoryId")
  public List<Product> getproductByCategoryId(@Param("categoryId") Long categoryId);

  @Query("UPDATE Product p SET p.productName = :#{#product.productName}  where p.id = :productId")
  @Modifying(clearAutomatically = true)
  public void updateProduct(@Param("productId") Long productId, @Param("product") Product product);


}
