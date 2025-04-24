package com.example.be_MrHung.models;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ticketId;

    private int userId;

    private int scheduleId;

    private int seatId;

    @Column(columnDefinition = "DECIMAL(10,2) default 0.00")
    private BigDecimal ticketPrice;

    @Column(columnDefinition = "TINYINT default 1")
    private byte ticketStatus;

    private LocalDateTime bookingTime;

    // Constructor mặc định
    public Ticket() {
    }

    // Constructor đầy đủ
    public Ticket(int ticketId, int userId, int scheduleId, int seatId, BigDecimal ticketPrice, byte ticketStatus, LocalDateTime bookingTime) {
        this.ticketId = ticketId;
        this.userId = userId;
        this.scheduleId = scheduleId;
        this.seatId = seatId;
        this.ticketPrice = ticketPrice;
        this.ticketStatus = ticketStatus;
        this.bookingTime = bookingTime;
    }

    // Getters và Setters
    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(BigDecimal ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public byte getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(byte ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    public LocalDateTime getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(LocalDateTime bookingTime) {
        this.bookingTime = bookingTime;
    }
}