package com.gobus.gobusservice.repository;

import com.gobus.gobusservice.model.Bus;
import com.gobus.gobusservice.model.Review;
import com.gobus.gobusservice.model.Trip;
import com.gobus.gobusservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TripRepository extends JpaRepository<Trip, Integer> {

    List<Trip> findByBus(Bus bus);
    List<Trip> findAllByBusIn(List<Bus> buses);
    List<Trip> findAllByFromAndTo(String from, String to);
}
