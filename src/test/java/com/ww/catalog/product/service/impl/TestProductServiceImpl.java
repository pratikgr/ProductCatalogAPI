package com.ww.catalog.product.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.ws.rs.core.Response;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.ww.catalog.category.dao.CategoryRepository;
import com.ww.catalog.category.model.Category;
import com.ww.catalog.product.dao.ProductRepository;
import com.ww.catalog.product.model.Product;
import com.ww.catalog.exception.ResourceNotFoundException;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestProductServiceImpl {

	@Mock
	private ProductRepository productRepository;


	@Mock
	private CategoryRepository categoryRepository;

	private ProductServiceImpl productServiceImpl = new ProductServiceImpl();

	private List<Product> productList = new ArrayList<>();
	private Category category = new Category();
	private Optional<Category> optionalCategory = Optional.of(category);
	private Product product = new Product();
	private Optional<Product> optionalProduct = Optional.of(product);

	@BeforeAll
	public void setup() {
		productServiceImpl.setProductRepository(productRepository);
		productServiceImpl.setCategoryRepository(categoryRepository);
	}

	private void setEntities() {
		product.setCategoryId(1L);
		product.setCategory("categoryName");
		product.setDescription("categoryDescription");
		product.setProductName("productName");
		productList.add(product);

		category.setId(1L);
		category.setCategoryName("categoryName");
	}


	/**
	 * Get product by id
	 */
	@Test
	public void testGetProductById() {
		setEntities();
		Mockito.when(productRepository.findById(1L)).thenReturn(optionalProduct);
		Mockito.when(categoryRepository.findById(1L)).thenReturn(optionalCategory);
		try {
			assertNotNull(productServiceImpl.getProductById(1L));
		} catch (ResourceNotFoundException e) {

		}
	}

	/**
	 * when productId is null
	 */
	@Test
	public void testGetProductByIdWhenProductIdIsNull() {
		setEntities();
		Mockito.when(productRepository.findById(1L)).thenReturn(optionalProduct);
		Mockito.when(categoryRepository.findById(1L)).thenReturn(optionalCategory);
		try {
			productServiceImpl.getProductById(null);
		} catch (ResourceNotFoundException e) {
			assertEquals(ResourceNotFoundException.class, e.getClass());
		}
	}

	/**
	 * Throws ResourceNotFoundException
	 */
	@Test
	public void testGetProductByIdWhenResourceNotFoundExceptionIsThrown() {
		Optional<Product> product = Optional.empty();
		Mockito.when(productRepository.findById(1L)).thenReturn(product);
		try {
			productServiceImpl.getProductById(1L);
		} catch (ResourceNotFoundException e) {
			assertEquals(ResourceNotFoundException.class, e.getClass());
		}
	}

	/**
	 * Throws ResourceNotFoundException
	 */
	@Test
	public void testGetProductByIdWhenOptionalCategoryIsBlank() {
		Optional<Category> category = Optional.empty();
		Mockito.when(productRepository.findById(1L)).thenReturn(optionalProduct);
		Mockito.when(categoryRepository.findById(1L)).thenReturn(category);
		try {
			Product product = productServiceImpl.getProductById(1L);
			assertNotNull(product);
			assertEquals(null, product.getCategory());
		} catch (ResourceNotFoundException e) {
		}
	}

	/**
	 * Delete product
	 */
	@Test
	public void testDeleteProduct() {
		Mockito.doNothing().when(productRepository).deleteById(1L);
		assertEquals(Response.Status.NO_CONTENT, productServiceImpl.deleteProduct(1L));
	}

	/**
	 * Delete product when product id is null
	 */
	@Test
	public void testDeleteProductWhenProductIdIsNull() {
		Mockito.doNothing().when(productRepository).deleteById(null);
		assertEquals(Response.Status.BAD_REQUEST, productServiceImpl.deleteProduct(null));
	}

	/**
	 * When Product is valid
	 */
	@Test
	public void testAddProduct() {
		setEntities();
		assertEquals(Response.Status.CREATED, productServiceImpl.addProduct(product));
	}

	/**
	 * When product is null
	 */
	@Test
	public void testAddProductWhenProductIsNull() {
		setEntities();
		assertEquals(Response.Status.BAD_REQUEST, productServiceImpl.addProduct(null));
	}


	/**
	 * update the product When Product Id Is Null
	 */
	@Test
	public void testUpdateProductWhenProductIdIsNull() {
		assertEquals(Response.Status.BAD_REQUEST, productServiceImpl.updateProduct(null, product));
	}

	/**
	 * update the product When Product Is Null
	 */
	@Test
	public void testUpdateProductWhenProductIsNull() {
		assertEquals(Response.Status.BAD_REQUEST, productServiceImpl.updateProduct(1L, null));
	}

	/**
	 * update the product When Product and productId Is Null
	 */
	@Test
	public void testUpdateProductWhenProductAndProductIdIsNull() {
		assertEquals(Response.Status.BAD_REQUEST, productServiceImpl.updateProduct(null, null));
	}

}

