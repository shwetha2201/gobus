package com.gobus.gobusservice.service;

import com.gobus.gobusservice.dto.TicketDto;
import com.gobus.gobusservice.model.Payment;
import com.gobus.gobusservice.model.Ticket;
import com.gobus.gobusservice.model.Trip;
import com.gobus.gobusservice.model.User;
import com.gobus.gobusservice.repository.PaymentRepository;
import com.gobus.gobusservice.repository.TicketRepository;
import com.gobus.gobusservice.repository.TripRepository;
import com.gobus.gobusservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class TicketService {

    private final TicketRepository ticketRepo;

    private final TripRepository tripRepo;

    private final UserRepository userRepo;

    private final PaymentRepository paymentRepo;

    public TicketDto book(TicketDto ticketDto) {
        Optional<Trip> trip = tripRepo.findById(ticketDto.getTrip().getTripId());
        if (trip.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Trip not found for specified Id");
        }
        Optional<User> user = userRepo.findById(ticketDto.getUser().getUserId());
        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found for specified Id");
        }
        if (trip.get().getAvailableSeats() < ticketDto.getNumberOfSeatsBooked()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Requested number of seats not available!");
        }
        Ticket ticket = new Ticket();
        ticket.setNumberOfSeatsBooked(ticketDto.getNumberOfSeatsBooked());
        ticket.setPassengerName(ticket.getPassengerName());
        ticket.setPassengerPhoneNumber(ticket.getPassengerPhoneNumber());
        ticket.setTicketStatus(Ticket.TicketStatus.PENDING);
        ticket.setTotalPrice(ticketDto.getNumberOfSeatsBooked() * trip.get().getSeatPrice());
        log.info("Booking the ticket....");
        return TicketUtils.entityToDto(ticket);
    }

    public TicketDto get(Integer ticketId) {
        try {
            Optional<Ticket> ticket = ticketRepo.findById(ticketId);
            log.info("checking for ticket in database for given id...");
            if (ticket.isEmpty()) {
                log.error("ticket not found for given id...");
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ticket not found for given Id...!");
            }
            log.info("Returning the Ticket {} ", ticket.get());
            return TicketUtils.entityToDto(ticket.get());
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            log.error("Failed to retrieve ticket details by id", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong!!");
        }
    }


    public List<TicketDto> listByTrip(Integer tripId) {
        try {
            Optional<Trip> trip = tripRepo.findById(tripId);
            if (trip.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Trip not found");
            }
            return TicketUtils.entityToDto(ticketRepo.findByTrip(trip.get()));
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            log.error("Failed to retrieve Ticket list", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong!!");
        }
    }

    public List<TicketDto> listByUser(int userId, LocalDate date) {
        try {
            Optional<User> user = userRepo.findById(userId);
            if (user.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found for this ID..");
            }
            return null;
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            log.error("Failed to retrieve Ticket list", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong!!");
        }
    }

    public TicketDto confirmTicket(int ticketId, int paymentId) {
        Optional<Ticket> ticket = ticketRepo.findById(ticketId);
        if (ticket.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ticket not found for this id");
        }
        Optional<Payment> payment = paymentRepo.findById(paymentId);
        if (payment.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Payment not found for this id");
        }
        if (payment.get().getPaymentStatus() != Payment.PaymentStatus.SUCCESS) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Payment wasn't successful");
        }
        Ticket t = ticket.get();
        t.setTicketStatus(Ticket.TicketStatus.CONFIRMED);
        ticketRepo.save(t);
        return TicketUtils.entityToDto(t);
    }

    public void cancel(int id) {
        Optional<Ticket> ticket = ticketRepo.findById(id);
        if (ticket.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ticket not found for this id");
        }
        Ticket t = ticket.get();
        if (t.getTicketStatus() == Ticket.TicketStatus.CANCELLED) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"This ticket has been cancelled already");
        }
        t.setTicketStatus((Ticket.TicketStatus.CANCELLED));
        ticketRepo.save(t);
    }

}
