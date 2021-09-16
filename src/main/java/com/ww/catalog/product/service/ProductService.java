package com.ww.catalog.product.service;

import java.util.List;

import javax.ws.rs.core.Response.Status;

import org.springframework.stereotype.Service;

import com.ww.catalog.exception.ResourceNotFoundException;
import com.ww.catalog.product.model.Product;

@Service
public interface ProductService {

	List<Product> getListOfAllProducts();

	Product getProductById(Long productId) throws ResourceNotFoundException;

	Status deleteProduct(Long productId);

	Status addProduct(Product product);

	Status updateProduct(Long productId, Product product);

	List<Product> getProductListByCategoryId(Long categoryId);
}
