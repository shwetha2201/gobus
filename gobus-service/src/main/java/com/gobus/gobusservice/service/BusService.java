package com.gobus.gobusservice.service;

import com.gobus.gobusservice.dto.BusDto;
import com.gobus.gobusservice.model.Bus;
import com.gobus.gobusservice.model.User;
import com.gobus.gobusservice.repository.BusRepository;
import com.gobus.gobusservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class BusService {

    private final BusRepository busRepo;
    private final UserRepository userRepo;

    public BusDto add(BusDto busDto) {
        try {
            Optional<User> user = userRepo.findById(busDto.getBusOwner().getUserId());
            if (user.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found for the specified bus owner id");
            }
            log.info("Verifying whether user is BusOwner or not");
            if (user.get().getUserType() != User.UserType.BUS_OWNER) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User must be a bus owner!");
            }
            Bus bus = BusUtils.dtoToEntity(busDto);
            bus.setBusOwner(user.get());
            Bus savedBus = busRepo.save(bus);
            log.info("Successfully saved the Bus Details to the db {}", savedBus);
            return BusUtils.entityToDto(savedBus);
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            log.error("Failed to Add Bus Details", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong!!! Please try later.");
        }
    }

    public BusDto update(Integer id, BusDto busDto) {
        try {
            Optional<Bus> bus = busRepo.findById(id);
            if (bus.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Bus not found!");
            }
            log.info("Verifying whether user is Busowner or not to update bus details");
            Bus dbBus = bus.get();
            BusUtils.copyFromDto(busDto, dbBus);
            return BusUtils.entityToDto(busRepo.save(dbBus));
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            log.error("Failed to update Bus details", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong!!");
        }
    }

    public BusDto get(int busId) {
        try {
            Optional<Bus> bus = busRepo.findById(busId);
            log.info("checking for bus in database for given id...");
            if (bus.isEmpty()) {
                log.error("bus not found for given id...");
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Bus not found for given Id...!");
            }
            log.info("Returning the Bus {} ", bus.get());
            return BusUtils.entityToDto(bus.get());
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            log.error("Failed to retrieve user by id", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong!!");
        }
    }

    public void delete(Integer id) {
        try {
            log.info("Verifying whether user is Bus Owner or not to delete bus details");
            Optional<Bus> bus = busRepo.findById(id);
            if (bus.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Bus not found!");
            }
            log.info("Deleting Bus details for given id");
            busRepo.delete(bus.get());
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            log.error("Failed to delete Bus details", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong!!");
        }
    }

    public List<BusDto> listByOwner(Integer ownerId) {
        try {
            Optional<User> user = userRepo.findById(ownerId);
            if (user.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
            }
            return BusUtils.entityToDto(busRepo.findAllByBusOwner(user.get()));
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            log.error("Failed to retrieve Bus list", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong!!");
        }
    }

}
