package com.eventza.Eventza.Repository;

import com.eventza.Eventza.model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist,UUID> {

    @Query("SELECT w from Wishlist w where w.userId=?1")
    public List<Wishlist> getWishesByUserId(UUID userId);
}
