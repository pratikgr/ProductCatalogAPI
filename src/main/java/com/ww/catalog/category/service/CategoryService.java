package com.ww.catalog.category.service;

import java.util.List;

import javax.ws.rs.core.Response.Status;

import org.springframework.stereotype.Service;

import com.ww.catalog.category.model.Category;
import com.ww.catalog.exception.CustomException;

@Service
public interface CategoryService {

	List<Category> getAllCategories();

	Status addCategory(Category category);

	Status deleteCategory(Long categoryId);

	Category getCategoryById(Long categoryId) throws CustomException;

	Status updateCategory(Long categoryId, Category category);
}
