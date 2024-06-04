package com.gobus.gobusservice.controller;

import com.gobus.gobusservice.dto.TripDto;
import com.gobus.gobusservice.service.TripService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/trips")
@AllArgsConstructor
@Validated
public class TripController {
    private final TripService tripService;

    @PostMapping
    @Secured("ROLE_BUS_OWNER")
    public ResponseEntity<TripDto> add(@RequestBody TripDto tripDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tripService.add(tripDto));
    }

    @PutMapping("/{id}")
    @Secured("ROLE_BUS_OWNER")
    public ResponseEntity<TripDto> update(@PathVariable int id, TripDto tripDto) {
        return ResponseEntity.ok(tripService.update(id,tripDto));
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<TripDto> get(@PathVariable int id) {
        return ResponseEntity.ok(tripService.get(id));
    }

    @DeleteMapping("/{id}")
    @Secured("ROLE_BUS_OWNER")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        tripService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/listByBus")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<TripDto>> listByBus(@RequestParam int busId) {
        return ResponseEntity.ok(tripService.listByBus(busId));
    }

    @GetMapping("/listByOwner")
    @Secured("ROLE_BUS_OWNER")
    public ResponseEntity<List<TripDto>> listByOwner(@RequestParam int ownerId) {
        return ResponseEntity.ok(tripService.listByOwnerId(ownerId));
    }

    @GetMapping("/listByRoute")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<TripDto>> listByRoute(@RequestParam String from, @RequestParam String to, @RequestParam LocalDate date) {
        return ResponseEntity.ok(tripService.listByRoute(from, to, date));
    }


}
