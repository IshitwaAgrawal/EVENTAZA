package com.eventza.Eventza.model;

import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ImageModel {

  private UUID id;
  @Id
  private String imageName;
  private byte[] imageByte;

  public ImageModel() {
  }

  public ImageModel(UUID id, String imageName, byte[] imageByte) {
    this.id = id;
    this.imageName = imageName;
    this.imageByte = imageByte;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getImageName() {
    return imageName;
  }

  public void setImageName(String imageName) {
    this.imageName = imageName;
  }

  public byte[] getImageByte() {
    return imageByte;
  }

  public void setImageByte(byte[] imageByte) {
    this.imageByte = imageByte;
  }
}
