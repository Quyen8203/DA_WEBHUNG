package com.example.be_MrHung.repository;

import com.example.be_MrHung.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT u FROM User u WHERE u.user_email = :email")
    Optional<User> findByUserEmail(@Param("email") String email);
}
