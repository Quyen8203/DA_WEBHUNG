package com.example.be_MrHung.repository;

import com.example.be_MrHung.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    // Tìm vé theo user_id
    @Query("SELECT t FROM Ticket t WHERE t.user_id = :userId")
    List<Ticket> findByUserId(@Param("userId") int userId);

}