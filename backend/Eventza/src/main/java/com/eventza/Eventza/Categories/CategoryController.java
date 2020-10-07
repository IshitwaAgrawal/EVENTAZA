package com.eventza.Eventza.Categories;


import java.util.List;
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

  @DeleteMapping("/categories/{categoryName}")
  public String deleteCategory(@PathVariable String categoryName) {
    categoryService.deleteCategory(categoryName);
    return categoryName + " deleted";
  }

}
