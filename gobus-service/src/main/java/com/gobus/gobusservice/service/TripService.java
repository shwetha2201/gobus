package com.gobus.gobusservice.service;

import com.gobus.gobusservice.dto.TripDto;
import com.gobus.gobusservice.model.Bus;
import com.gobus.gobusservice.model.Trip;
import com.gobus.gobusservice.model.User;
import com.gobus.gobusservice.repository.BusRepository;
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
public class TripService {

    private final BusRepository busRepo;
    private final TripRepository tripRepo;
    private final UserRepository userRepo;

    public TripDto add(TripDto tripdto) {
        try {
            Optional<Bus> bus = busRepo.findById(tripdto.getBus().getBusId());
            if (bus.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Bus not found..");
            }
            if (tripdto.getFrom().equalsIgnoreCase(tripdto.getTo())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "From and To cannot be same");
            }
            if (tripdto.getArrivalTime().isBefore(tripdto.getBoardingTime())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Arrival time must be greater than boarding time!");
            }
            Trip trip = TripUtils.dtoToEntity(tripdto);
            trip.setBus(bus.get());
            trip.setAvailableSeats(bus.get().getNumberOfSeats());
            Trip savedTrip = tripRepo.save(trip);
            log.info("Successfully saved the Trip Details to the db {}", savedTrip);
            return TripUtils.entityToDto(savedTrip);
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            log.error("Failed to Add Trip Details", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong!!! Please try later.");
        }
    }

    public TripDto get(Integer tripId) {
        try {
            Optional<Trip> trip = tripRepo.findById(tripId);
            if (trip.isEmpty()) {
                log.error("bus not found for given id...");
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Bus not found for given Id...!");
            }
            log.info("Returning the Bus {} ", trip.get());
            return TripUtils.entityToDto(trip.get());
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            log.error("Failed to retrieve user by id", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong!!");
        }
    }

    public void delete(Integer id) {
        try {
            log.info("Verifying whether user is Bus Owner or not to delete Trip details");
            Optional<Trip> trip = tripRepo.findById(id);
            if (trip.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Trip not found!");
            }
            log.info("Deleting Trip details for given id");
            tripRepo.delete(trip.get());
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            log.error("Failed to delete Trip details", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong!!");
        }
    }

    public TripDto update(Integer id, TripDto tripDto) {
        try {
            Optional<Trip> trip = tripRepo.findById(id);
            if (trip.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Trip not found for this id");
            }
            Trip dbTrip = trip.get();
            TripUtils.copyFromDto(tripDto, dbTrip);
            return TripUtils.entityToDto(tripRepo.save(dbTrip));
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            log.error("Failed to update trip details", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong!!");
        }
    }

    public List<TripDto> listByBus(Integer busId) {
        try {
            Optional<Bus> bus = busRepo.findById(busId);
            if (bus.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no bus for this busId..");
            }
            return TripUtils.entityToDto(tripRepo.findByBus(bus.get()));
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            log.error("Failed to retrieve trip details for this bus...");
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong!!");
        }
    }

    public List<TripDto> listByRoute(String from, String to, LocalDate date) {
        try {
            List<Trip> trips = tripRepo.findAllByFromAndTo(from, to);
            List<Trip> filtered = trips.stream().filter(t -> t.getBoardingTime().toLocalDate().isEqual(date)).toList();
            return TripUtils.entityToDto(filtered);
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            log.error("Failed to retrieve route details for this date and destination...");
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong!!");
        }
    }

    public List<TripDto> listByOwnerId(int ownerId) {
        try {
            Optional<User> owner = userRepo.findById(ownerId);
            owner.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Owner not found"));
            List<Bus> buses = busRepo.findAllByBusOwner(owner.get());
            return TripUtils.entityToDto(tripRepo.findAllByBusIn(buses));
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            log.error("Failed to retrieve route details for this date and destination...");
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong!!");
        }
    }
}