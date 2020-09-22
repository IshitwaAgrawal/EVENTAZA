package com.eventza.Eventza.Categories;

import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<CategoryModel, UUID> {

  CategoryModel findByCategoryName(String categoryName);
}
