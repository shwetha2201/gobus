package com.gobus.gobusservice.controller;

import com.gobus.gobusservice.dto.ReviewDto;
import com.gobus.gobusservice.service.ReviewService;
import jakarta.annotation.security.PermitAll;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    @Secured("ROLE_CUSTOMER")
    public ResponseEntity<ReviewDto> postReview(@RequestBody ReviewDto reviewDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reviewService.postReview(reviewDto));
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ReviewDto> get(@PathVariable int id) {
        return ResponseEntity.ok(reviewService.get(id));
    }

    @PutMapping("/{id}")
    @Secured("ROLE_CUSTOMER")
    public ResponseEntity<ReviewDto> update(@PathVariable int id, ReviewDto reviewDto) {
        return ResponseEntity.ok(reviewService.update(id, reviewDto));
    }

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<ReviewDto>> listByBus(@RequestParam int busId) {
        return ResponseEntity.ok(reviewService.listByBus(busId));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        reviewService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
