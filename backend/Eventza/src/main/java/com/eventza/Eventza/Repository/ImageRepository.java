package com.eventza.Eventza.Repository;

import com.eventza.Eventza.model.ImageModel;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

public interface ImageRepository extends CrudRepository<ImageModel, String> {
  Optional<ImageModel> findById(UUID id);
}
