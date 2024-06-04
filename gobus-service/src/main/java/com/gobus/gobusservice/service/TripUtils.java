package com.gobus.gobusservice.service;

import com.gobus.gobusservice.dto.ReviewDto;
import com.gobus.gobusservice.dto.TripDto;
import com.gobus.gobusservice.model.Review;
import com.gobus.gobusservice.model.Trip;

import java.util.ArrayList;
import java.util.List;

public class TripUtils {

    public static Trip dtoToEntity(TripDto tripDto) {
        Trip trip = new Trip();
        trip.setTo(tripDto.getTo());
        trip.setFrom(tripDto.getFrom());
        trip.setBoardingTime(tripDto.getBoardingTime());
        trip.setArrivalTime(tripDto.getArrivalTime());
        trip.setAvailableSeats(tripDto.getAvailableSeats());
        trip.setConfirmationStatus(tripDto.isConfirmationStatus());
        trip.setDriverName(tripDto.getDriverName());
        trip.setDriverPhoneNumber(tripDto.getDriverPhoneNumber());
        trip.setSeatPrice(tripDto.getSeatPrice());
        return trip;
    }

    public static TripDto entityToDto(Trip trip) {
        TripDto tripDto = new TripDto();
        tripDto.setTripId(trip.getTripId());
        tripDto.setTo(trip.getTo());
        tripDto.setFrom(trip.getFrom());
        tripDto.setBoardingTime(trip.getBoardingTime());
        tripDto.setArrivalTime(trip.getArrivalTime());
        tripDto.setAvailableSeats(trip.getAvailableSeats());
        tripDto.setAvailableSeats(trip.getAvailableSeats());
        tripDto.setConfirmationStatus(trip.isConfirmationStatus());
        tripDto.setDriverName(trip.getDriverName());
        tripDto.setDriverPhoneNumber(trip.getDriverPhoneNumber());
        tripDto.setSeatPrice(trip.getSeatPrice());
        return tripDto;
    }

    public static void copyFromDto(TripDto tripDto, Trip trip) {
        trip.setTo(tripDto.getTo());
        trip.setFrom(tripDto.getFrom());
        trip.setBoardingTime(tripDto.getBoardingTime());
        trip.setArrivalTime(tripDto.getArrivalTime());
        trip.setAvailableSeats(tripDto.getAvailableSeats());
        trip.setConfirmationStatus(tripDto.isConfirmationStatus());
        trip.setDriverName(tripDto.getDriverName());
        trip.setDriverPhoneNumber(tripDto.getDriverPhoneNumber());
        trip.setSeatPrice(tripDto.getSeatPrice());
    }

    public static List<TripDto> entityToDto(List<Trip> tripList){
        List<TripDto> tripDtoList = new ArrayList<>();
        for (Trip trip : tripList) {
            tripDtoList.add(TripUtils.entityToDto(trip));
        }
        return tripDtoList;
    }
}
