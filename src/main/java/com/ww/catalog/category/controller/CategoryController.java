package com.ww.catalog.category.controller;

import java.util.List;

import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import com.ww.catalog.category.model.Category;
import com.ww.catalog.category.service.CategoryService;
import com.ww.catalog.exception.CustomException;

@RestController
@RequestMapping(path = "v1/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	/**
	 * @return List of all categories
	 */
	@Operation(summary = "Get List of all Categories", responses = {
		@ApiResponse(description = "Successful Operation", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
		@ApiResponse(responseCode = "404", description = "Not found", content = @Content),
		@ApiResponse(responseCode = "401", description = "Authentication Failure", content = @Content(schema = @Schema(hidden = true))) })
	@GetMapping(produces = "application/json")
	public List<Category> getListOfAllCategories() {
		return categoryService.getAllCategories();
	}

	@Operation(summary = "Get Category by Id", responses = {
		@ApiResponse(description = "Successful Operation", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
		@ApiResponse(responseCode = "404", description = "Not found", content = @Content),
		@ApiResponse(responseCode = "401", description = "Authentication Failure", content = @Content(schema = @Schema(hidden = true))) })
	@GetMapping(path = "/{categoryId}", produces = "application/json")
	public Category getCategoryById(@PathVariable Long categoryId) {
		try {
			return categoryService.getCategoryById(categoryId);
		} catch (CustomException e) {
			return null;
		}
	}

	/**
	 * @param category
	 * @return On success, Status = CREATED
	 * @return On user requesting operation not having sufficient role, Status =
	 *         UNAUTHORIZED
	 * @return On providing bad request object, Status = BAD_REQUEST
	 */
	@Operation(summary = "Add new Category", responses = {
		@ApiResponse(description = "Successful Operation", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
		@ApiResponse(responseCode = "404", description = "Not found", content = @Content),
		@ApiResponse(responseCode = "401", description = "Authentication Failure", content = @Content(schema = @Schema(hidden = true))) })
	@SecurityRequirement(name = "productcatalogapi")
	@PostMapping(consumes = "application/json", produces = "application/json")
	public Status addCategories(@RequestBody Category category) {
		return categoryService.addCategory(category);
	}

	/**
	 * @param categoryId
	 * @return On success, Status = NO_CONTENT
	 * @return On id not found, Status = NOT_FOUND
	 * @return On user requesting operation not having sufficient role, Status =
	 *         UNAUTHORIZED
	 * @return On providing bad request object, Status = BAD_REQUEST
	 */
	@Operation(summary = "Delete Category by Id", responses = {
		@ApiResponse(description = "Successful Operation", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
		@ApiResponse(responseCode = "404", description = "Not found", content = @Content),
		@ApiResponse(responseCode = "401", description = "Authentication Failure", content = @Content(schema = @Schema(hidden = true))) })
	@SecurityRequirement(name = "productcatalogapi")
	@DeleteMapping(path = "/{categoryId}", produces = "application/json")
	public Status deleteCategory(@PathVariable Long categoryId) {
		return categoryService.deleteCategory(categoryId);
	}

	/**
	 * @param categoryId,
	 *            category
	 * @return On success, Status = NO_CONTENT
	 * @return On id not found, Status = NOT_FOUND
	 * @return On user requesting operation not having sufficient role, Status =
	 *         UNAUTHORIZED
	 * @return On providing bad request object, Status = BAD_REQUEST
	 */
	@Operation(summary = "Update Category by Id", responses = {
		@ApiResponse(description = "Successful Operation", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
		@ApiResponse(responseCode = "404", description = "Not found", content = @Content),
		@ApiResponse(responseCode = "401", description = "Authentication Failure", content = @Content(schema = @Schema(hidden = true))) })
	@SecurityRequirement(name = "productcatalogapi")
	@PutMapping(path = "/{categoryId}", produces = "application/json")
	public Status updateCategory(@PathVariable Long categoryId, @RequestBody Category category) {
		return categoryService.updateCategory(categoryId, category);
	}

	// getter setter
	public CategoryService getCategoryService() {
		return categoryService;
	}

	public void setProductService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
}
