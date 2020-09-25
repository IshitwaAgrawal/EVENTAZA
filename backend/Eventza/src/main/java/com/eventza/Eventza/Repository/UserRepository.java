package com.eventza.Eventza.Repository;

import com.eventza.Eventza.DTO.UserDTO;
import com.eventza.Eventza.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    public User getUserByUsername(String username);

    @Query("select u from User u where u.verificationToken = ?1")
    public User getUserByVerificationToken(String code);

    @Query("select u from User u where u.email = ?1")
    public User getUserByEmail(String email);

    @Query("SELECT u.email from User u where u.username = ?1")
    public String getEmailByUsername(String username);
}
