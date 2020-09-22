package com.eventza.Eventza.Categories;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

  @Autowired
  private CategoryRepository categoryRepository;

  //method to fetch a category by its name from the database
  public CategoryModel getRequestedCategory(String categoryName){
  return categoryRepository.findByCategoryName(categoryName);
  }

  //method to fetch all available categories from the database
  public List<CategoryModel> getAllCategories(){
    List<CategoryModel> categoryList = new ArrayList<>();
    categoryRepository.findAll().forEach(category -> categoryList.add(category));
    return categoryList;
  }

  //method to add a new category in the database
  public void addNewCategory(CategoryModel category){
  categoryRepository.save(category);
  }

  //method to update an existing category
  public void updateExistingCategory(CategoryModel category){
    categoryRepository.save(category);
  }

  //method to delete an existing category from the database
  public void deleteCategory(String categoryName){
    categoryRepository.deleteById(getRequestedCategory(categoryName).getId());
  }


}
