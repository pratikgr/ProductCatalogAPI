package com.ww.catalog.category.dao;

import com.ww.catalog.category.model.Category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface CategoryRepository extends JpaRepository<Category, Long> {

  @Query("select category.id from Category category where category.categoryName = :categoryToFind")
  public Long findCategoryIdByCategory(@Param("categoryToFind") String categoryToFind);

  @Query("UPDATE Category c SET c.categoryName = :#{#category.categoryName} WHERE c.id = :categoryId")
  @Modifying(clearAutomatically = true)
  public void updateCategoryById(@Param("categoryId") Long categoryId, @Param("category") Category category);

}
