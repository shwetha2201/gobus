package com.gobus.gobusservice.service;

import com.gobus.gobusservice.dto.ReviewDto;
import com.gobus.gobusservice.model.Bus;
import com.gobus.gobusservice.model.Review;
import com.gobus.gobusservice.model.User;
import com.gobus.gobusservice.repository.BusRepository;
import com.gobus.gobusservice.repository.ReviewRepository;
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
public class ReviewService {

    private final ReviewRepository reviewRepo;
    private final BusRepository busRepo;
    private final UserRepository userRepo;

    public ReviewDto postReview(ReviewDto reviewDto) {
        try {
            Optional<User> user = userRepo.findById(reviewDto.getUser().getUserId());
            user.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!"));
            Optional<Bus> bus = busRepo.findById(reviewDto.getBus().getBusId());
            bus.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Bus not found!"));
            log.info("saving review to the database...");
            Review saveReview = ReviewUtils.dtoToEntity(reviewDto);
            saveReview.setBus(bus.get());
            saveReview.setUser(user.get());
            log.info("returning review from the database to user...");
            return ReviewUtils.entityToDto(reviewRepo.save(saveReview));
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            log.error("Failed to create a review", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong!!! Please try later.");
        }
    }

    public ReviewDto get(int id) {
        try {
            Optional<Review> review = reviewRepo.findById(id);
            log.info("checking for review in database for given id...");
            if (review.isEmpty()) {
                log.error("review not found for given id...");
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Review Not Found for the given Id..");
            }
            log.info("Returning the review {} ", review.get());
            return ReviewUtils.entityToDto(review.get());
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            log.error("Failed to retrieve review by id", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong!!");
        }
    }

    public ReviewDto update(Integer id, ReviewDto reviewDto) {
        try {
            Optional<Review> review = reviewRepo.findById(id);
            if (review.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Review not found for this id");
            }
            Review dbReview = review.get();
            ReviewUtils.copyFromDto(reviewDto, dbReview);
            return ReviewUtils.entityToDto(reviewRepo.save(dbReview));
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            log.error("Failed to update the review", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong!!");
        }
    }

    public List<ReviewDto> listByBus(Integer busId) {
        try {
            Optional<Bus> bus = busRepo.findById(busId);
            if (bus.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Bus not found");
            }
            return ReviewUtils.entityToDto(reviewRepo.findAllByBus(bus.get()));
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            log.error("Failed to retrieve Review list for given Bus", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong!!");
        }
    }

    public void delete(Integer id) {
        try {
            log.info("Finding the review for this id to delete...");
            Optional<Review> review = reviewRepo.findById(id);
            if (review.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Review not found!");
            }
            log.info("Deleting Review details for given id");
            reviewRepo.delete(review.get());
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            log.error("Failed to delete review ", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong!!");
        }
    }


}
