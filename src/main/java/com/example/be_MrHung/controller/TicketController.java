package com.example.be_MrHung.controller;

import com.example.be_MrHung.eNum.ResponseData;
import com.example.be_MrHung.models.Ticket;
import com.example.be_MrHung.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*",methods = {})
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    // Lấy danh sách tất cả vé
    @GetMapping("/list")
    public ResponseEntity<?> getAllTickets() {
        return ResponseEntity.ok(ticketService.getAllTickets());
    }

    // Lấy vé theo ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getTicketById(@PathVariable int id) {
        ResponseData<Ticket> response = ticketService.getTicketById(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    // Lấy danh sách vé theo user_id
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getTicketsByUserId(@PathVariable int userId) {
        ResponseData<List<Ticket>> response = ticketService.getTicketsByUserId(userId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    // Tạo vé (một hoặc nhiều vé cùng lúc)
    @PostMapping("/create")
    public ResponseEntity<?> createTickets(@RequestBody List<Ticket> tickets) {
        ResponseData<List<Ticket>> response = ticketService.createTickets(tickets);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    // Cập nhật vé
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateTicket(@PathVariable int id, @RequestBody Ticket ticket) {
        ResponseData<Ticket> response = ticketService.updateTicket(id, ticket);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    // Xóa vé
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTicket(@PathVariable int id) {
        ResponseData<Ticket> response = ticketService.deleteTicket(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}