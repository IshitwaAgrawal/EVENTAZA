package com.eventza.Eventza.Categories;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CategoryModel {


  @Id
  @Column(nullable = false)
  private UUID id;
  private String categoryName;

  public CategoryModel() { }

  public CategoryModel(String categoryName){
    this.id = UUID.randomUUID();
    this.categoryName = categoryName;
  }

  public UUID getId() {
    return id;
  }

  public String getCategoryName() {
    return categoryName;
  }

  public void setCategoryName(String categoryName) {
    this.categoryName = categoryName;
  }
}
