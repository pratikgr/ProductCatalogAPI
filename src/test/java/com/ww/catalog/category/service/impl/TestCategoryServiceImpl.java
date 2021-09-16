package com.ww.catalog.category.service.impl;

// import static org.junit.Assert.assertEquals;
// import static org.junit.Assert.assertNotNull;
// import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.ws.rs.core.Response;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.test.context.junit.jupiter.SpringExtension;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.ww.catalog.category.dao.CategoryRepository;
import com.ww.catalog.category.model.Category;
import com.ww.catalog.exception.CustomException;
import com.ww.catalog.exception.ResourceNotFoundException;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestCategoryServiceImpl {

	private CategoryServiceImpl categoryServiceImpl = new CategoryServiceImpl();

	@Mock
	private CategoryRepository categoryRepository;

	private Category category = new Category();
	// private Optional<Category> optionalCategory = Optional.of(category);
	private List<Category> categoryList = new ArrayList<>();

	@BeforeAll
	public void setup() {
		categoryServiceImpl.setCategoryRepository(categoryRepository);
	}

	private void setEntities() {
		category.setId(1L);
		category.setCategoryName("categoryName");
		categoryList.add(category);
	}


	/**
	 * Add category
	 */
	@Test
	public void testAddCategory() {
		setEntities();
		assertEquals(Response.Status.CREATED, categoryServiceImpl.addCategory(category));
	}

	/**
	 * Add category when category is null
	 */
	@Test
	public void testAddCategoryWhenCategoryIsNull() {
		setEntities();
		assertEquals(Response.Status.BAD_REQUEST, categoryServiceImpl.addCategory(null));
	}

	/**
	 * Delete category when category id is null
	 */
	@Test
	public void testDeleteCategoryWhenCategoryIdIsNull() {
		setEntities();
		assertEquals(Response.Status.BAD_REQUEST, categoryServiceImpl.deleteCategory(null));
	}


	/**
	 * Delete category when category id is valid
	 */
	@Test
	public void testDeleteCategoryWhenCategoryIdIsNotValid() {
		setEntities();
		Mockito.doThrow(IllegalArgumentException.class).when(categoryRepository).deleteById(1L);
		assertEquals(Response.Status.NOT_FOUND, categoryServiceImpl.deleteCategory(1L));
	}

	/**
	 * Get category by id when categoryId is null
	 */
	@Test
	public void testGetCategoryByIdWhenIdIsNull() {
		setEntities();
		try {
			categoryServiceImpl.getCategoryById(null);
		} catch (CustomException e) {
			assertEquals(ResourceNotFoundException.class, e.getClass());
		}
	}


	/**
	 * Get category by id when categoryId
	 */
	@Test
	public void testGetCategoryByIdWhenOptionalCategoryIsBlank() {
		setEntities();
		Mockito.when(categoryRepository.findById(1L)).thenReturn(Optional.empty());
		try {
			assertNull(categoryServiceImpl.getCategoryById(1L));
		} catch (CustomException e) {
			assertEquals(ResourceNotFoundException.class, e.getClass());
		}
	}

	/**
	 * update category when category id is null
	 */
	@Test
	public void testUpdateCategoryWhenCategoryIdIsNull() {
		assertEquals(Response.Status.BAD_REQUEST, categoryServiceImpl.updateCategory(null, category));
	}

	/**
	 * update category when category is null
	 */
	@Test
	public void testUpdateCategoryWhenCategoryIsNull() {
		assertEquals(Response.Status.BAD_REQUEST, categoryServiceImpl.updateCategory(1L, null));
	}

	/**
	 * update category when category and category id are null
	 */
	@Test
	public void testUpdateCategoryWhenCategoryAndCategoryIdAreNull() {
		assertEquals(Response.Status.BAD_REQUEST, categoryServiceImpl.updateCategory(null, null));
	}
}
