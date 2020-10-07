package com.eventza.Eventza.Categories;

import java.util.UUID;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<CategoryModel, UUID> {

  @Query("select c from CategoryModel c where c.categoryName= ?1")
  CategoryModel findByCategoryName(String categoryName);

  @Query("select c from CategoryModel c where c.id=?1")
  CategoryModel getCategoryById(UUID id);

}