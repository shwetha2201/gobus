package com.gobus.gobusservice.controller;

import com.gobus.gobusservice.dto.BusDto;
import com.gobus.gobusservice.service.BusService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/buses")
@AllArgsConstructor
@Validated
public class BusController {

    private final BusService busService;

    @PostMapping
    @Secured("ROLE_BUS_OWNER")
    public ResponseEntity<BusDto> add(@RequestBody @Valid BusDto busDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(busService.add(busDto));
    }

    @PutMapping("/{id}")
    @Secured("ROLE_BUS_OWNER")
    public ResponseEntity<BusDto> update(@PathVariable Integer id, @RequestBody @Valid BusDto busdto) {
        return ResponseEntity.ok(busService.update(id, busdto));
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<BusDto> get(@PathVariable Integer id) {
        return ResponseEntity.ok(busService.get(id));
    }

    @DeleteMapping("/{id}")
    @Secured("ROLE_BUS_OWNER")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        busService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping
    @Secured({"ROLE_ADMIN", "ROLE_BUS_OWNER"})
    public ResponseEntity<List<BusDto>> listByOwner(@RequestParam Integer ownerId) {
        return ResponseEntity.ok(busService.listByOwner(ownerId));
    }

}
