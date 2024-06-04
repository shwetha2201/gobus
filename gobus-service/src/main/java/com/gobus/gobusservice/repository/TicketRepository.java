package com.gobus.gobusservice.repository;

import com.gobus.gobusservice.model.Ticket;
import com.gobus.gobusservice.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    List<Ticket> findByTrip(Trip trip);
}
