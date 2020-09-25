package com.eventza.Eventza.Service;

import com.eventza.Eventza.Repository.WishlistRepository;
import com.eventza.Eventza.model.Wishlist;
import com.eventza.Eventza.model.WishlistResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class WishlistService {
    @Autowired
    WishlistRepository repo;

    public Wishlist addNew(Wishlist w){
        return repo.save(w);
    }

    public List<Wishlist> getAll(){
        return repo.findAll();
    }

    public List<Wishlist> getWishesByUsername(UUID id){
        return repo.getWishesByUserId(id);
    }
}
