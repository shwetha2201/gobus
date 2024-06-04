package com.gobus.gobusservice.controller;

import com.gobus.gobusservice.dto.TicketDto;
import com.gobus.gobusservice.service.TicketService;
import jakarta.annotation.security.PermitAll;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/tickets")
public class TicketController {
    private final TicketService ticketService;

    @PostMapping
    @Secured("ROLE_CUSTOMER")
    public ResponseEntity<TicketDto> book(@RequestBody TicketDto ticketDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ticketService.book(ticketDto));
    }

    @PostMapping("/{ticketId}/paymentId/{paymentId}")
    @Secured("ROLE_CUSTOMER")
    public ResponseEntity<TicketDto> confirm(Integer ticketId, Integer paymentId) {
        return ResponseEntity.ok(ticketService.confirmTicket(ticketId,paymentId));
    }

    @PatchMapping("/{id}/action/cancel")
    @Secured("ROLE_CUSTOMER")
    public ResponseEntity<Void> cancel(@PathVariable int id) {
        ticketService.cancel(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<TicketDto> get(@PathVariable Integer id) {
        return ResponseEntity.ok(ticketService.get(id));
    }

    @GetMapping("/listByTrip")
    @Secured({"ROLE_BUS_OWNER","ROLE_ADMIN"})
    public ResponseEntity<List<TicketDto>> listByTrip(@RequestParam int tripId) {
        return ResponseEntity.ok(ticketService.listByTrip(tripId));
    }

    @GetMapping("/listByUser")
    @Secured("ROLE_CUSTOMER")
    public ResponseEntity<List<TicketDto>> listByUser(@RequestParam int userId, @RequestParam LocalDate date) {
        return ResponseEntity.ok(ticketService.listByUser(userId, date));
    }
}
