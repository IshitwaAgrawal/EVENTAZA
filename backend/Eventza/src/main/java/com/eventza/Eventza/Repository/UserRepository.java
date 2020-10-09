package com.eventza.Eventza.Repository;

import com.eventza.Eventza.model.User;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

  @Query("select u from User u where u.username=?1")
  public User getUserByUsername(String username);

  @Query("select u from User u where u.verificationToken = ?1")
  public User getUserByVerificationToken(String code);

  @Query("select u from User u where u.email = ?1")
  public User getUserByEmail(String email);

  @Query("SELECT u.email from User u where u.username = ?1")
  public String getEmailByUsername(String username);

  @Transactional
  @Modifying
  @Query("UPDATE User u set u.newsletter_service = ?2 where u.id=?1")
  void updateNewsletterService(UUID id, Boolean value);


  @Transactional
  @Modifying
  @Query("UPDATE User u set u.password = ?2 where u.id=?1")
  void updatePassword(UUID id, String password);

  @Transactional
  @Modifying
  @Query("UPDATE User u set u.organizer = ?2 where u.id=?1")
  void updateOrganiser(UUID id, boolean value);

  List<User> findAll();
}
