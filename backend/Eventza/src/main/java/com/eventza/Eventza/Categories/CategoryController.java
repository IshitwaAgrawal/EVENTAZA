package com.eventza.Eventza.Categories;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {

  @Autowired
  private CategoryService categoryService;

  @RequestMapping(method = RequestMethod.GET, path = "/categories/{categoryName}")
  public CategoryModel getRequestCategory(@PathVariable String categoryName){
    return categoryService.getRequestedCategory(categoryName);
  }

  @RequestMapping(method = RequestMethod.GET, path = "/categories")
  public List<CategoryModel> getAllCategories(){
    return categoryService.getAllCategories();
  }

  @RequestMapping(method = RequestMethod.POST, path = "/categories")
  public String addNewCategory(@RequestBody CategoryModel category){
    categoryService.addNewCategory(category);
    return "Added new category";
  }

  @RequestMapping(method = RequestMethod.PUT, path = "/categories/{categoryName}")
  public String updateExistingCategory(@PathVariable String categoryName, @RequestBody CategoryModel category){
    categoryService.updateExistingCategory(category);
    return categoryName + " updated";
  }

  @RequestMapping(method = RequestMethod.DELETE, path = "/categories/{categoryName}")
  public String deleteCategory(@PathVariable String categoryName){
    categoryService.deleteCategory(categoryName);
    return categoryName + " deleted";
  }

}
