package com.example.be_MrHung.services;

import com.example.be_MrHung.eNum.ResponseData;
import com.example.be_MrHung.models.Ticket;
import com.example.be_MrHung.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    // Lấy danh sách tất cả vé
    public ResponseData<List<Ticket>> getAllTickets() {
        return new ResponseData<>(HttpStatus.OK, "Thành công", ticketRepository.findAll());
    }

    // Lấy vé theo ID
    public ResponseData<Ticket> getTicketById(int id) {
        Optional<Ticket> optionalTicket = ticketRepository.findById(id);
        if (optionalTicket.isPresent()) {
            return new ResponseData<>(HttpStatus.OK, "Thành công", optionalTicket.get());
        }
        return new ResponseData<>(HttpStatus.NOT_FOUND, "Không tìm thấy vé với ID: " + id, null);
    }

    // Lấy danh sách vé theo user_id
    public ResponseData<List<Ticket>> getTicketsByUserId(int userId) {
        List<Ticket> tickets = ticketRepository.findByUserId(userId);
        if (tickets.isEmpty()) {
            return new ResponseData<>(HttpStatus.NOT_FOUND, "Không tìm thấy vé cho user_id: " + userId, null);
        }
        return new ResponseData<>(HttpStatus.OK, "Thành công", tickets);
    }

    // Tạo nhiều vé cùng lúc (bao gồm trường hợp chỉ có một vé)
    public ResponseData<List<Ticket>> createTickets(List<Ticket> tickets) {
        try {
            if (tickets == null || tickets.isEmpty()) {
                return new ResponseData<>(HttpStatus.BAD_REQUEST, "Danh sách vé không được rỗng", null);
            }

            List<Ticket> savedTickets = new ArrayList<>();

            for (Ticket ticket : tickets) {
                // Kiểm tra các trường bắt buộc
                if (ticket.getUserId() <= 0) {
                    return new ResponseData<>(HttpStatus.BAD_REQUEST, "user_id không hợp lệ", null);
                }
                if (ticket.getScheduleId() <= 0) {
                    return new ResponseData<>(HttpStatus.BAD_REQUEST, "schedule_id không hợp lệ", null);
                }
                if (ticket.getSeatId() <= 0) {
                    return new ResponseData<>(HttpStatus.BAD_REQUEST, "seat_id không hợp lệ", null);
                }

                // Đặt thời gian đặt vé
                ticket.setBookingTime(LocalDateTime.now());

                // Lưu vé
                savedTickets.add(ticketRepository.save(ticket));
            }

            return new ResponseData<>(HttpStatus.CREATED, "Tạo vé thành công", savedTickets);
        } catch (Exception e) {
            return new ResponseData<>(HttpStatus.INTERNAL_SERVER_ERROR, "Lỗi khi tạo vé: " + e.getMessage(), null);
        }
    }

    // Cập nhật vé
    public ResponseData<Ticket> updateTicket(int id, Ticket ticket) {
        try {
            Ticket existingTicket = ticketRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy vé với ID: " + id));

            if (ticket.getTicketId() != id) {
                throw new IllegalArgumentException("ID trong đường dẫn không khớp với ID trong body");
            }

            // Cập nhật các trường
            existingTicket.setUserId(ticket.getUserId());
            existingTicket.setScheduleId(ticket.getScheduleId());
            existingTicket.setSeatId(ticket.getSeatId());


            Ticket updatedTicket = ticketRepository.save(existingTicket);
            return new ResponseData<>(HttpStatus.OK, "Cập nhật vé thành công", updatedTicket);
        } catch (IllegalArgumentException e) {
            return new ResponseData<>(HttpStatus.NOT_FOUND, e.getMessage(), null);
        } catch (Exception e) {
            return new ResponseData<>(HttpStatus.INTERNAL_SERVER_ERROR, "Lỗi khi cập nhật vé: " + e.getMessage(), null);
        }
    }

    // Xóa vé
    public ResponseData<Ticket> deleteTicket(int id) {
        try {
            Ticket ticket = ticketRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy vé với ID: " + id));
            ticketRepository.deleteById(id);
            return new ResponseData<>(HttpStatus.OK, "Xóa vé thành công", ticket);
        } catch (IllegalArgumentException e) {
            return new ResponseData<>(HttpStatus.NOT_FOUND, e.getMessage(), null);
        } catch (Exception e) {
            return new ResponseData<>(HttpStatus.INTERNAL_SERVER_ERROR, "Lỗi khi xóa vé: " + e.getMessage(), null);
        }
    }
}