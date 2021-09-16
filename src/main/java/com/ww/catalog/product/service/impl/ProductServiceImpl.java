package com.ww.catalog.product.service.impl;

import java.util.List;
import java.util.Optional;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ww.catalog.category.dao.CategoryRepository;
import com.ww.catalog.category.model.Category;
import com.ww.catalog.product.dao.ProductRepository;
import com.ww.catalog.product.model.Product;
import com.ww.catalog.product.service.ProductService;
import com.ww.catalog.exception.ResourceNotFoundException;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	/**
	 * @return List of all products
	 */
	@Override
	public List<Product> getListOfAllProducts() {
		List<Product> productList = productRepository.findAll();
		for (Product product : productList) {
			product.setCategory(getCategoryById(product.getCategoryId()));
		}

		return productList;
	}

	/**
	 * @param productId
	 * @return Product details
	 */
	@Override
	public Product getProductById(Long productId) throws ResourceNotFoundException {
		Optional<Product> optionalProduct = productRepository.findById(productId);
		if (optionalProduct.isPresent()) {
			Product product = optionalProduct.get();
			product.setCategory(getCategoryById(optionalProduct.get().getCategoryId()));
			return product;
		} else {
			throw new ResourceNotFoundException("No record found for the particular product id");
		}
	}

	private String getCategoryById(Long categoryId) {
		Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
		if (optionalCategory.isPresent()) {
			return optionalCategory.get().getCategoryName();
		} else {
			return null;
		}
	}

	private Long findCategoryIdForTheProvidedCategory(String categoryToFind) {
		return categoryRepository.findCategoryIdByCategory(categoryToFind);
	}


	/**
	 * Performs delete operation on products by user authentication
	 * 
	 * @return HTTP status upon result of operation
	 */
	@Override
	public Status deleteProduct(Long productId) {
		if (null != productId) {
			productRepository.deleteById(productId);
			return Response.Status.NO_CONTENT;
		} else {
			return Response.Status.BAD_REQUEST;
		}
	}

	/**
	 * Performs add operation on products by user authentication
	 * 
	 * @return HTTP status upon result of operation
	 */
	@Override
	public Status addProduct(Product product) {
		if (null != product) {
			Long categoryId = findCategoryIdForTheProvidedCategory(product.getCategory());
			product.setCategoryId(categoryId);
			productRepository.save(product);
			return Response.Status.CREATED;
		} else {
			return Response.Status.BAD_REQUEST;
		}
	}

	/**
	 * Performs update operation on products by user authentication
	 * 
	 * @return HTTP status upon result of operation
	 */
	@Override
	public Status updateProduct(Long productId, Product product) {
		if (null != product && null != productId) {
			product.setCategoryId(categoryRepository.findCategoryIdByCategory(product.getCategory()));
			productRepository.updateProduct(productId, product);
			return Response.Status.OK;
		} else {
			return Response.Status.BAD_REQUEST;
		}
	}

	@Override
	public List<Product> getProductListByCategoryId(Long categoryId) {
		List<Product> productList = productRepository.getproductByCategoryId(categoryId);
		for (Product product : productList) {
			product.setCategory(getCategoryById(product.getCategoryId()));
		}

		return productList;
	}


	public ProductRepository getProductRepository() {
		return productRepository;
	}

	public void setProductRepository(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public CategoryRepository getCategoryRepository() {
		return categoryRepository;
	}

	public void setCategoryRepository(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

}
