package com.ww.catalog.product.controller;

import java.util.List;

import javax.ws.rs.core.Response.Status;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import com.ww.catalog.product.model.Product;
import com.ww.catalog.product.service.ProductService;
import com.ww.catalog.exception.ResourceNotFoundException;

/**
 * Product controller
 */
@RestController
@RequestMapping(path = "v1/products")
public class ProductController {

	private ProductService productService;

	public ProductController(ProductService productService){
		this.productService = productService;
	}

	/**
	 * @return list of all products
	 */
	@Operation(summary = "Get List of all Products", responses = {
		@ApiResponse(description = "Successful Operation", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
		@ApiResponse(responseCode = "404", description = "Not found", content = @Content),
		@ApiResponse(responseCode = "401", description = "Authentication Failure", content = @Content(schema = @Schema(hidden = true))) })
	@GetMapping(produces = "application/json")
	public List<Product> getListOfAllProducts() {
		return productService.getListOfAllProducts();
	}

	/**
	 * @param productId
	 * @return returns product that belong to the provided productId
	 */
	@Operation(summary = "Get Product details by Product Id", responses = {
		@ApiResponse(description = "Successful Operation", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
		@ApiResponse(responseCode = "404", description = "Not found", content = @Content),
		@ApiResponse(responseCode = "401", description = "Authentication Failure", content = @Content(schema = @Schema(hidden = true))) })
	@GetMapping(path = "/{productId}", produces = "application/json")
	public Product getProductById(@PathVariable Long productId) {
		try {
			return productService.getProductById(productId);
		} catch (ResourceNotFoundException e) {
			return null;
		}
	}

	/**
	 * @param productId
	 * @return On success, Status = NO_CONTENT
	 * @return On user requesting operation not having sufficient role, Status =
	 *         UNAUTHORIZED
	 * @return On resource not found, Status = NOT_FOUND
	 * @return On providing bad request object, Status = BAD_REQUEST
	 */
	@Operation(summary = "Delete Product by Product Id", responses = {
		@ApiResponse(description = "Successful Operation", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
		@ApiResponse(responseCode = "404", description = "Not found", content = @Content),
		@ApiResponse(responseCode = "401", description = "Authentication Failure", content = @Content(schema = @Schema(hidden = true))) })
	@SecurityRequirement(name = "productcatalogapi")
	@DeleteMapping(path = "/{productId}", produces = "application/json")
	public Status deleteProducts(@PathVariable Long productId) {
		return productService.deleteProduct(productId);
	}

	/**
	 * @param product
	 * @return On success, Status = CREATED
	 * @return On user requesting operation not having sufficient role, Status =
	 *         UNAUTHORIZED
	 * @return On resource not found, Status = NOT_FOUND
	 * @return On providing bad request object, Status = BAD_REQUEST
	 */
	@Operation(summary = "Add new Product", responses = {
		@ApiResponse(description = "Successful Operation", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
		@ApiResponse(responseCode = "404", description = "Not found", content = @Content),
		@ApiResponse(responseCode = "401", description = "Authentication Failure", content = @Content(schema = @Schema(hidden = true))) })
	@SecurityRequirement(name = "productcatalogapi")
	@PostMapping(consumes = "application/json", produces = "application/json")
	public Status addProducts(@RequestBody Product product) {
		return productService.addProduct(product);
	}

	/**
	 * @param productId
	 * @return On success, Status = OK
	 * @return On user requesting operation not having sufficient role, Status =
	 *         UNAUTHORIZED
	 * @return On providing bad request object, Status = BAD_REQUEST
	 */
	@Operation(summary = "Update Product by Product Id", responses = {
		@ApiResponse(description = "Successful Operation", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
		@ApiResponse(responseCode = "404", description = "Not found", content = @Content),
		@ApiResponse(responseCode = "401", description = "Authentication Failure", content = @Content(schema = @Schema(hidden = true))) })
	@SecurityRequirement(name = "productcatalogapi")
	@PutMapping(path = "/{productId}", consumes = "application/json", produces = "application/json")
	public Status updateProducts(@PathVariable Long productId, @RequestBody Product product) {
		return productService.updateProduct(productId, product);
	}

	/**
	 * @param categoryId
	 * @return List of Products
	 */
	@Operation(summary = "Get List of Products by Category Id", responses = {
		@ApiResponse(description = "Successful Operation", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
		@ApiResponse(responseCode = "404", description = "Not found", content = @Content),
		@ApiResponse(responseCode = "401", description = "Authentication Failure", content = @Content(schema = @Schema(hidden = true))) })
	@GetMapping(path = "/categories/{categoryId}", produces = "application/json")
	public List<Product> getProductsByCategoryId(@PathVariable Long categoryId) {
		return productService.getProductListByCategoryId(categoryId);
	}

	// getter setter
	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
}
