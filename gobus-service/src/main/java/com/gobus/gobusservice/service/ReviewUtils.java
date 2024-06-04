package com.gobus.gobusservice.service;

import com.gobus.gobusservice.dto.ReviewDto;
import com.gobus.gobusservice.model.Review;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class ReviewUtils {

    public static Review dtoToEntity(ReviewDto reviewDto) {
        Review review = new Review();
        review.setComment(reviewDto.getComment());
        review.setNumberOfStars(reviewDto.getNumberOfStars());
        return review;
    }

    public static ReviewDto entityToDto(Review review) {
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setReviewId(review.getReviewId());
        reviewDto.setBus(BusUtils.entityToDto(review.getBus()));
        reviewDto.setUser(UserUtils.entityToDto(review.getUser()));
        reviewDto.setComment(review.getComment());
        reviewDto.setNumberOfStars(review.getNumberOfStars());
        return reviewDto;
    }

    public static void copyFromDto(ReviewDto dto, Review review) {
        review.setComment(dto.getComment());
        review.setNumberOfStars(dto.getNumberOfStars());
    }

    public static List<ReviewDto> entityToDto(List<Review> reviewList) {
        List<ReviewDto> reviewDtoList = new ArrayList<>();
        for (Review review : reviewList) {
            reviewDtoList.add(ReviewUtils.entityToDto(review));
        }
        return reviewDtoList;
    }
}
