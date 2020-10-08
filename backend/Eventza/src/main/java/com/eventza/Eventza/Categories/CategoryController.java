package com.eventza.Eventza.Categories;


import java.math.BigInteger;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CategoryController {

  @Autowired
  private CategoryService categoryService;

  @GetMapping("/categories/{categoryName}")
  public CategoryModel getRequestCategory(@PathVariable String categoryName) {
    return categoryService.getRequestedCategory(categoryName);
  }

  @GetMapping("/categories")
  public List<CategoryModel> getAllCategories() {
    return categoryService.getAllCategories();
  }

  @PostMapping("/categories")
  public String addNewCategory(@RequestBody CategoryModel category) {
    categoryService.addNewCategory(category);
    return "Added new category";
  }

  @PutMapping("/categories/{categoryName}")
  public String updateExistingCategory(@PathVariable String categoryName,
      @RequestBody CategoryModel category) {
    categoryService.updateExistingCategory(category);
    return categoryName + " updated";
  }

  @DeleteMapping("/categories/{categoryId}")
  public String deleteCategory(@PathVariable String categoryId) {
    UUID id;
    try {
      id = UUID.fromString(categoryId);
    } catch (Exception e) {
      id = new UUID(
          new BigInteger(categoryId.substring(0, 16), 16).longValue(),
          new BigInteger(categoryId.substring(16), 16).longValue());
    }
    categoryService.deleteCategory(id);
    return "category deleted";
  }

}
