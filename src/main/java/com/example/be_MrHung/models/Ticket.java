package com.example.be_MrHung.models;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int ticket_id;

    private int user_id;

    private int schedule_id;

    private int seat_id;

    @Column(columnDefinition = "DECIMAL(10,2) default 0.00")
    private BigDecimal ticket_price;

    @Column(columnDefinition = "TINYINT default 1")
    private byte ticket_status;

    private LocalDateTime booking_time;

    // Constructor mặc định
    public Ticket() {
    }

    // Constructor đầy đủ
    public Ticket(int ticketId, int userId, int scheduleId, int seatId, BigDecimal ticketPrice, byte ticketStatus, LocalDateTime bookingTime) {
        this.ticket_id = ticketId;
        this.user_id = userId;
        this.schedule_id = scheduleId;
        this.seat_id = seatId;
        this.ticket_price = ticketPrice;
        this.ticket_status = ticketStatus;
        this.booking_time = bookingTime;
    }

    public int getTicket_id() {
        return ticket_id;
    }

    public void setTicket_id(int ticket_id) {
        this.ticket_id = ticket_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getSchedule_id() {
        return schedule_id;
    }

    public void setSchedule_id(int schedule_id) {
        this.schedule_id = schedule_id;
    }

    public int getSeat_id() {
        return seat_id;
    }

    public void setSeat_id(int seat_id) {
        this.seat_id = seat_id;
    }

    public BigDecimal getTicket_price() {
        return ticket_price;
    }

    public void setTicket_price(BigDecimal ticket_price) {
        this.ticket_price = ticket_price;
    }

    public byte getTicket_status() {
        return ticket_status;
    }

    public void setTicket_status(byte ticket_status) {
        this.ticket_status = ticket_status;
    }

    public LocalDateTime getBooking_time() {
        return booking_time;
    }

    public void setBooking_time(LocalDateTime booking_time) {
        this.booking_time = booking_time;
    }
}